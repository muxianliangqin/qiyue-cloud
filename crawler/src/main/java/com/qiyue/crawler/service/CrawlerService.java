package com.qiyue.crawler.service;

import com.qiyue.crawler.dao.entity.CategoryEntity;
import com.qiyue.crawler.dao.entity.WebEntity;
import com.qiyue.service.response.Response;
import com.qiyue.base.request.CrawlerResult;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CrawlerService {

    Response findWebs(int userId, Pageable pageable);

    Response findByUserIdInAndState(List<Integer> userIds, Pageable pageable);

    Response findByCategoryIdAndState(int categoryId, Pageable pageable);

    Response deleteWeb(int webId);

    Response addWeb(String title, String url, int userId);

    Response modifyWeb(WebEntity webEntity);

    Response deleteCategory(int categoryId);

    Response addCategory(String title, String url, String xpathTitle, String xpathText, String charset, int webId);

    Response modifyCategory(CategoryEntity categoryEntity);

    Response pluginResultSave(CrawlerResult crawlerResult);

    Response categoryHasRead(int categoryId);

    Response newsHasRead(int newsId);
    // 附件下载
    void download(HttpServletResponse response, int id);
}
