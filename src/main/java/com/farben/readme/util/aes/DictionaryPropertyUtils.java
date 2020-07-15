package com.farben.readme.util.aes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DictionaryPropertyUtils {
    private final static Logger logger = LoggerFactory.getLogger(DictionaryPropertyUtils.class);

    /**
     * get AES128 key from encAndDec_key.properties
     *
     * @return
     */
    public static File loadRSAPublicKey() {
        String publicKeyPath = loadStrFromPro("rsa_public_path");
        File file = new File(publicKeyPath);
        return file;
    }

    public static File loadRSAPrivateKey() {
        String privateKeyPath = loadStrFromPro("rsa_private_path");
        File file = new File(privateKeyPath);
        return file;
    }

    public static String loadStrFromPro(final String proName) {
        Properties pro = new Properties();
        InputStream in = DictionaryPropertyUtils.class.getResourceAsStream("/encAndDec_key.properties");
        String configName = "";
        try {
            pro.load(in);
            configName = pro.getProperty(proName);
        } catch (IOException e) {
            DictionaryPropertyUtils.logger.error("[loadStrFromPro] IOException:", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                DictionaryPropertyUtils.logger.error("[loadStrFromPro] IOException:", e);
            }
        }
        return configName;
    }

    public static String loadAES128Key() {
        Properties pro = new Properties();
        InputStream in = DictionaryPropertyUtils.class.getResourceAsStream("/encAndDec_key.properties");
        String aes128Key = "";
        try {
            pro.load(in);
            aes128Key = pro.getProperty("aes_128_key");
        } catch (IOException e) {
            DictionaryPropertyUtils.logger.error("[loadAES128Key] IOException:", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                DictionaryPropertyUtils.logger.error("[loadAES128Key] IOException:", e);
            }
        }
        return aes128Key;
    }

}
