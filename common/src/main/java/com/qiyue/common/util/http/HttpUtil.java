package com.qiyue.common.util.http;

import com.qiyue.common.constant.Constant;
import com.qiyue.common.util.BaseUtil;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
public class HttpUtil {

    private static boolean proxySet = false;
    private static String proxyHost = "127.0.0.1";
    private static int proxyPort = 8080;


    //璁剧疆璇锋眰澶村睘鎬�??
    public static Map<String, String> setProperty() {
        HashMap<String, String> pMap = new HashMap<String, String>();
        pMap.put("connection", "Keep-Alive");
        pMap.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        pMap.put("Content-Type", "application/x-www-form-urlencoded");
        return pMap;
    }

    /**
     * GET方式
     *
     * @param url
     * @param params
     * @return
     */
    public static String sendGet(String url, Map<String, String> params, String encode) {
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        try {
            url = url + "?" + paramsHandle(params);
            HttpURLConnection conn = getConnection(url);
            conn.setRequestMethod("GET");
            //联�?�url
            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), encode));
            }
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.error("连接异常的url:{}", url);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * GET方式
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        return sendGet(url, null, Constant.ENCODE_UTF8);
    }

    /**
     * GET方式
     *
     * @param url
     * @return
     */
    public static String sendGet(String url, String encode) {
        return sendGet(url, null, encode);
    }

    /**
     * POST 方式
     *
     * @param url
     * @param params
     * @return
     */
    public static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            HttpURLConnection conn = getConnection(url);
            conn.connect();
            //POST方式提交
            conn.setRequestMethod("POST");
            // URLConnection编码
            out = new OutputStreamWriter(conn.getOutputStream(), Constant.ENCODE_UTF8);
            // 写入参数
            out.write(paramsHandle(params));
            out.flush();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constant.ENCODE_UTF8));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Constant.ENCODE_UTF8));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static HttpURLConnection getConnection(String urlStr) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            if (urlStr.startsWith("https")) {
                //创建SSLContext
                SSLContext sslContext;
                try {
                    sslContext = SSLContext.getInstance("SSL");
                    TrustManager[] tm = {new X509TrustManagerUtil()};
                    //初始化
                    sslContext.init(null, tm, new java.security.SecureRandom());
                    //获取SSLSocketFactory对象
                    SSLSocketFactory ssf = sslContext.getSocketFactory();
                    conn = (HttpsURLConnection) url.openConnection();
                    //设置当前实例使用的SSLSoctetFactory
                    ((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }

            // 发�?�POST请求必须设置如下两行�?
            conn.setDoOutput(true);//向连接中写入数据
            conn.setDoInput(true);//从连接中读取数据
            conn.setUseCaches(false);//禁止缓存
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Charset", Constant.ENCODE_UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static HttpURLConnection getConnectionProxy(String url) {
        HttpURLConnection conn = null;
        try {
            URL realUrl = new URL(url);
            Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            conn = (HttpURLConnection) realUrl.openConnection(proxy);
            // 发�?�POST请求必须设置如下两行�?
            conn.setDoOutput(true);//向连接中写入数据
            conn.setDoInput(true);//从连接中读取数据
            conn.setUseCaches(false);//禁止缓存
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Charset", Constant.ENCODE_UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static Map<String, String> sendForm(String url, Map<String, String> paramsMap, Map<String, String> filesMap) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String result = "";
        int responseCode = 0;
        HttpURLConnection conn = getConnection(url);
        String boundary = "";
        try {
            conn.setRequestMethod("POST");
            boundary = "----" + BaseUtil.getRandomString(10, Constant.TYPE_MIX) + "----";
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            OutputStream os = new DataOutputStream(conn.getOutputStream()); // 获取输出�?
            formParamFormat(paramsMap, os, boundary);
            formFileFormat(filesMap, os, boundary);
            os.flush();
            os.close();
            BufferedReader in = null;
            responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK
                    || responseCode == HttpURLConnection.HTTP_CREATED
                    || responseCode == HttpURLConnection.HTTP_ACCEPTED) {
//                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constant.ENCODE_UTF8));
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constant.ENCODE_GBK));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), Constant.ENCODE_UTF8));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw e;
        }
        map.put("result", result);
        map.put("responseCode", String.valueOf(responseCode));
        return map;
    }

    /**
     * java form参数上传
     *
     * @param paramsMap
     * @param os
     * @param boundary
     * @throws IOException
     */
    /*模拟form提交数据，参数示例
	-------------------12345654321-----------
	Content-Disposition: form-data; name="fieldName"

	fieldValue
	-------------------12345654321-----------
	Content-Disposition: form-data; name="idcard_number"

	56635863236655
	-------------------12345654321-----------
     */
    public static void formParamFormat(Map<String, String> paramsMap, OutputStream os, String boundary) throws IOException {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> iterator = paramsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            sb.append("\r\n");
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"" + entry.getKey() + "\";");
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append(entry.getValue());
        }
        os.write(sb.toString().getBytes(Constant.ENCODE_UTF8));
    }

    /**
     * java form文件上传
     *
     * @param os
     * @param boundary
     * @throws IOException
     */
    public static void formFileFormat(Map<String, String> filesMap, OutputStream os, String boundary) throws IOException {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> iterator = filesMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            sb.append("\r\n");
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            File file = new File(entry.getValue());
            sb.append("Content-Disposition: form-data;name=\"" + entry.getKey() + "\";filename=\"" + file.getName() + "\"");
            sb.append("\r\n");
            sb.append("Content-Type:application/octet-stream");
            sb.append("\r\n");
            sb.append("\r\n");
            os.write(sb.toString().getBytes(Constant.ENCODE_UTF8));
            //文件写入
            FileInputStream fis = new FileInputStream(file);
            byte[] fileByte = new byte[10 * 1024];
            int len = 0;
            while ((len = fis.read(fileByte)) != -1) {
                os.write(fileByte, 0, len);
            }
            fis.close();
        }
        StringBuilder sb1 = new StringBuilder();
        sb1.append("\r\n");
        sb1.append("--");
        sb1.append(boundary);
        sb1.append("--");
        sb1.append("\r\n");
        os.write(sb1.toString().getBytes(Constant.ENCODE_UTF8));
    }

    public static String paramsHandle(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        if (params == null) {
            sb.append("");
        } else {
            Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                if (iterator.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }

    public static String urlEncode(String source, String encode) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }

}

