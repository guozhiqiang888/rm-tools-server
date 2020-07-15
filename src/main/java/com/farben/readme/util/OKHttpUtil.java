package com.farben.readme.util;

import com.farben.readme.exception.CallApiException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OKHttpUtil {

    private static final Logger log = LoggerFactory.getLogger(OKHttpUtil.class);

    private final static String SUCCESS_CODE = "200";

    /**
     * read timeout
     **/
    public final static int READ_TIMEOUT = 100;

    /**
     * connect timeout
     **/
    public final static int CONNECT_TIMEOUT = 60;

    /**
     * write timeout
     **/
    public final static int WRITE_TIMEOUT = 60;

    private final static MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    private final static byte[] LOCKER = new byte[0];

    private static OKHttpUtil mInstance;

    private OkHttpClient okHttpClient;


    private OKHttpUtil() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts());
        clientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        okHttpClient = clientBuilder.build();
    }

    public static OKHttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                mInstance = new OKHttpUtil();
            }
        }

        return mInstance;
    }


    public Response get(String uri) {
        return get(uri, null);
    }

    public Response get(String uri, Map<String, String> headMap) {
        log.debug("----Weconnect Service call HTTP server, URL is {} START", uri);
        uri = filterSpecialChar(uri);
        Request.Builder builder = new Request.Builder().get().url(uri);
        if (headMap != null) {
            for (String headKey : headMap.keySet()) {
                builder = builder.addHeader(headKey, headMap.get(headKey));
            }
        }
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            log.debug("----Weconnect Service call HTTP server, response is {} END", response.toString());
            return response;
        } catch (IOException e) {
            throw new CallApiException();
        }
    }

    public Response post(String uri, String requestBodyJson, Map<String, String> headMap) {
        log.info("----Weconnect Service call HTTP POST server, URL is {} and requestBodyJson is {} START", uri, requestBodyJson);
        Request.Builder builder = new Request.Builder();
        if (headMap != null) {
            for (String headKey : headMap.keySet()) {
                builder = builder.addHeader(headKey, headMap.get(headKey));
            }
        }
        RequestBody requestBody = RequestBody.create(JSON, requestBodyJson);
        Request request = builder.post(requestBody).url(uri).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            log.info("----Weconnect Service call HTTP POST server, response is {} END", response.toString());
            return response;
        } catch (IOException e) {
            throw new CallApiException();
        }

    }

    public Response post(String uri, String requestBodyJson) {
        return this.post(uri, requestBodyJson, null);
    }

    public Response patch(String uri, String requestBodyJson) {
        return patch(uri, requestBodyJson);
    }

    public Response patch(String uri, String requestBodyJson, Map<String, String> headMap) {
        log.debug("----Weconnect Service call HTTP PATCH server, URL is {} and requestBodyJson is {} START", uri, requestBodyJson);
        Request.Builder builder = new Request.Builder();
        if (headMap != null) {
            for (String headKey : headMap.keySet()) {
                builder = builder.addHeader(headKey, headMap.get(headKey));
            }
        }
        RequestBody requestBody = RequestBody.create(JSON, requestBodyJson);
        Request request = builder.patch(requestBody).url(uri).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            log.debug("----Weconnect Service call HTTP PATCH server, response is {} END", response.toString());
            return response;
        } catch (IOException e) {
            throw new CallApiException();
        }

    }

    public Response put(String uri, String requestBodyJson) {
        return put(uri, requestBodyJson);
    }

    public Response put(String uri, String requestBodyJson, Map<String, String> headMap) {
        log.debug("----Weconnect Service call HTTP PUT server, URL is {} and requestBodyJson is {} START", uri, requestBodyJson);
        Request.Builder builder = new Request.Builder();
        if (headMap != null) {
            for (String headKey : headMap.keySet()) {
                builder = builder.addHeader(headKey, headMap.get(headKey));
            }
        }
        RequestBody requestBody = RequestBody.create(JSON, requestBodyJson);
        Request request = builder.put(requestBody).url(uri).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            log.debug("----Weconnect Service call HTTP PUT server, response is {} END", response.toString());
            return response;
        } catch (IOException e) {
            throw new CallApiException();
        }

    }


    /**
     * skip certification for HTTPS request
     *
     * @return
     */
    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

    /**
     * trust all certification
     */
    class TrustAllCerts implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * filter special characters
     * @param value
     *          < > " ' &
     *          % ^ ? !
     * @return
     */
    public static String filterSpecialChar(String value) {
        /*
            < > " ' &
            &lt; &gt; &quot; &apos; &amp;
         */
//        value = StringEscapeUtils.escapeXml(value);
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("'", "&apos;");
//        value = value.replaceAll("&", "&amp;");
        /*
            % ^ ? !
         */
//        value = value.replaceAll("%", "&#37;");
//        value = value.replaceAll("\\^", "&#94;");
//        value = value.replaceAll("\\?", "&#63;");
//        value = value.replaceAll("!", "&#33;");
        return value;
    }

}
