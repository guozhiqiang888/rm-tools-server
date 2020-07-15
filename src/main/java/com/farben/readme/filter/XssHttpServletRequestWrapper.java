package com.farben.readme.filter;

import com.alibaba.fastjson.JSON;
import com.farben.readme.util.XssCleanUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;
    private boolean isIncludeRichText = false;

    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText) {
        super(request);
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    @Override
    public String getParameter(String name) {
//        if (("content".equals(name) || name.endsWith("WithHtml")) && !isIncludeRichText) {
//            return super.getParameter(name);
//        }
        name = XssCleanUtil.cleanXss(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotEmpty(value)) {
            value = XssCleanUtil.cleanXss(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (StringUtils.isNotEmpty(arr[i])) {
                    arr[i] = XssCleanUtil.cleanXss(arr[i]);
                }
            }
        }
        return arr;
    }

    @Override
    public Map getParameterMap() {
        HashMap paramMap = (HashMap) super.getParameterMap();
        paramMap = (HashMap) paramMap.clone();
        for (Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String[] values = (String[]) entry.getValue();
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof String && StringUtils.isNotEmpty(values[i])) {
                    values[i] = XssCleanUtil.cleanXss(values[i]);
                }
            }
            entry.setValue(values);
        }
        return paramMap;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        String body = getRequestBody(super.getInputStream());
        Map<String, Object> map = JSON.parseObject(body, Map.class);
        Map<String, Object> resultMap = new HashMap<>(map.size());
        for (String key : map.keySet()) {
            Object val = map.get(key);
            if (val instanceof String) {
                resultMap.put(key, XssCleanUtil.cleanXss(val.toString()));
            } else {
                resultMap.put(key, val);
            }
        }
        body = JSON.toJSONString(resultMap);
        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };

    }

    @Override
    public String getHeader(String name) {
        name = XssCleanUtil.cleanXss(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotEmpty(value)) {
            value = XssCleanUtil.cleanXss(value);
        }
        return value;
    }

    /**
     * get original request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * get original request (static method)
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }

    private String getRequestBody(InputStream stream) {
        String line;
        StringBuilder body = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        try {
            while ((line = br.readLine()) != null) {
                body.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

}
