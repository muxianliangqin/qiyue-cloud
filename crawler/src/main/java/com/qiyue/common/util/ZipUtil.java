package com.qiyue.common.util;

import com.qiyue.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class ZipUtil {

	public static String gzip(String str) throws Exception {
		String zip = "";
		if (BaseUtil.isEmpty(str)) {
			log.debug(str);
			return zip;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzos = null;
		try {
			gzos = new GZIPOutputStream(baos);
			gzos.write(str.getBytes(Constant.ENCODE_UTF8));
			gzos.finish();
			zip = new String(new Base64().encode(baos.toByteArray()),Constant.ENCODE_UTF8);
		} catch (IOException e) {
			throw new Exception("压缩失败", e);
		} finally {
			if (gzos != null) {
				try {
					gzos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return zip;
	}

	/**
	 * Description:使用gzip进行解压缩
	 *
	 * @param gzipStr
	 * @return
	 * @throws Exception
	 */
	public static String unGzip(String gzipStr) throws Exception {
		String unGzip = "";
		if (BaseUtil.isEmpty(gzipStr)) {
			throw new Exception("str不能为空");
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayInputStream bais = null;
		GZIPInputStream gzis = null;
		try {
			byte[] zipArr = new Base64().decode(gzipStr.getBytes(Constant.ENCODE_UTF8));
			bais = new ByteArrayInputStream(zipArr);
			gzis = new GZIPInputStream(bais);

			byte[] buffer = new byte[10 * 1024];
			int offset = -1;
			while ((offset = gzis.read(buffer)) != -1) {
				baos.write(buffer, 0, offset);
			}
			unGzip = baos.toString(Constant.ENCODE_UTF8);
		} catch (IOException e) {
			throw new Exception("压缩失败", e);
		} finally {
			if (gzis != null) {
				try {
					gzis.close();
				} catch (IOException e) {
				}
			}
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
				}
			}
		}

		return unGzip;
	}

}
