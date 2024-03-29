package com.farben.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;

public class JsonResourceUtil {

    private static Logger logger = Logger.getLogger(JsonResourceUtil.class);

    private JsonResourceUtil() {

    }

    /**
     * 从json资源文件获取JSONObject
     * @param filename filename 为文件名字 如 “/json/app_version_info.json”
     * @return JSONObject
     */
    public static JSONObject getJsonObjFromResource(String filename) {
        JSONObject json = null;
        if (!filename.contains(".json")) {
            filename += ".json";
        }
        try {
            URL url = JsonResourceUtil.class.getResource(filename);
            String path = url.getPath();
            File file = new File(path);
            if (file.exists()) {
                String content = FileUtils.readFileToString(file, "UTF-8");
                json = JSON.parseObject(content);
            } else {
                logger.info("file not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("readFileToString" + e.getMessage());
        }
        return json;
    }
}
