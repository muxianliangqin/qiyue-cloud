package com.qiyue.crawler.service.impl;

import com.alibaba.fastjson.util.IOUtils;
import com.qiyue.base.constant.Constant;
import com.qiyue.base.enums.ErrorEnum;
import com.qiyue.base.exceptions.DatabaseException;
import com.qiyue.crawler.dao.entity.FileDao;
import com.qiyue.crawler.entity.FileEntity;
import com.qiyue.crawler.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FileImpl implements FileService {

    @Value(value = "${file.attachment.crawler.dir}")
    private String attachmentDir;

    @Autowired
    private FileDao fileDao;

    @Override
    public void download(Long fileId, HttpServletResponse response) {
        FileEntity fileEntity = fileDao.findByFileId(fileId).orElseThrow(() ->
                new DatabaseException(ErrorEnum.RECORD_NOT_FOUND, "fileId", "文件ID")
        );
        String filePath = fileEntity.getPath();
        filePath = attachmentDir + File.separator + filePath;
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(filePath);
            os = response.getOutputStream();
            byte[] bytes = new byte[Constant.IO_BYTES_BUFFER_SIZE];
            int index = -1;
            while ((index = fis.read(bytes)) != -1) {
                os.write(bytes, 0, index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fis);
            IOUtils.close(os);
        }
    }
}
