package com.cjl.web.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

public class CommonHttpClient {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommonHttpClient.class.getName());

    public String doGet(String uri, List<NameValuePair> parameters) {
        HttpGet httpGet = new HttpGet(uri);
        String param = null;
        try {
            param = EntityUtils.toString(new UrlEncodedFormEntity(parameters));
            // build get uri with params
            httpGet.setURI(new URIBuilder(httpGet.getURI().toString() + "?" + param).build());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("创建连接异常", e);
        }
        return sendHttpGet(httpGet);
    }

    private String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000)
                .setSocketTimeout(30000).build();
        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        httpClient = HttpClients.createDefault();
        httpGet.setConfig(config);
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.error("数据响应异常", e);
        }
        entity = response.getEntity();
        try {
            responseContent = EntityUtils.toString(entity, "UTF-8");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.error("Get 数据解析错误!data=" + responseContent, e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.error("Get 数据解析错误!data=" + responseContent, e);
        }
        return responseContent;
    }

    public String doPost(String url, List<NameValuePair> parameters) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                .setSocketTimeout(10000).build();
        CloseableHttpResponse response = null;
        String content = "";
        try {
            post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
            post.setConfig(config);
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
            LOGGER.info("Post响应数据:" + content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Post异常! data=" + content, e);
        } finally {
            try {
                response.close();
                LOGGER.info("Post请求连接关闭");
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("Post连接关闭异常", e);
            }

        }
        return null;
    }

    public static String doPostJsonString(String url, String jsonStr, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000)
                .setSocketTimeout(30000).build();
        CloseableHttpResponse response = null;
        String content = "";
        try {
            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    String name = key;
                    String value = headerMap.get(name);
                    post.addHeader(name, value);
                }
            }
            post.setHeader(new BasicHeader("Content-type", "application/json;charset=utf-8"));
            StringEntity se = new StringEntity(jsonStr, "UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            post.setConfig(config);

            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
            LOGGER.info("Post响应数据:" + content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Post异常! data=" + content, e);
        } finally {
            try {
                response.close();
                LOGGER.info("Post请求连接关闭");
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("Post连接关闭异常", e);
            }

        }
        return null;
    }

    /**
     * 绕过证书验证
     *
     * @param url
     * @param parameMap
     * @return
     * @throws ParseException
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public String doPost(String url, Map<String, String> parameMap) throws ParseException, UnsupportedEncodingException,
            IOException, KeyManagementException, NoSuchAlgorithmException {


        // 采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext)).build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000)
                .setSocketTimeout(30000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        String content = "";
        try {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> it = parameMap.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, String> entry = it.next();
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            post.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            post.setConfig(config);
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
            System.out.println("content:" + content);
            EntityUtils.consume(entity);
            return content;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            LOGGER.error("Post 连接协议出错!data=" + content, e);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Post 读取异常!data=" + content, e);
        } finally {
            try {
                response.close();
                LOGGER.info("Post请求连接关闭");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static String doPost(String url, String data, Map<String, String> headMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "text/plain");
        httpPost.setEntity(new StringEntity(data));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseContent);
        response.close();
        httpClient.close();
        return responseContent;
    }

    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

}
