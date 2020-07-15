package com.farben.readme.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public final class JasyptUtil {

    private static final String ALGORITHM = "PBEWithMD5AndDES";

    private static final String ENCODE_KEY = "2GAVyGDjfA2WjE0kLhNNPSxwS9vV2pKZ";

    private static final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    private static final EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();

    static {
        config.setAlgorithm(ALGORITHM);
        config.setPassword(ENCODE_KEY);
        encryptor.setConfig(config);
    }

    public static String decrypt(final String content) {
        return encryptor.decrypt(content);
    }

    public static String encrypt(final String content) {
        return encryptor.encrypt(content);
    }

    public static void main(String[] args) {
        String hbcncmb2019 = JasyptUtil.encrypt("hbcncmb2019");
        System.out.println(hbcncmb2019);
        String str = JasyptUtil.encrypt("Ali12345");
        System.out.println(str);
    }
}
