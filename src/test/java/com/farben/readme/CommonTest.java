package com.farben.readme;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public class CommonTest {

    public static void main(String[] args) {
        try {
//            System.out.println(ConfigTools.encrypt("Wechat_20191008"));
//            System.out.println(ConfigTools.decrypt("EmWWw1ixHBr49I8u3psnz/Df+MEPFsLUoj6BPyPd1ZixXf/auKWz6acvIPBzi+3T2KyQZf343pWP6mdczd/QMQ=="));
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
            config.setAlgorithm("PBEWithMD5AndDES");
            config.setPassword("2GAVyGDjfA2WjE0kLhNNPSxwS9vV2pKZ");
            encryptor.setConfig(config);
            String decryptStr = encryptor.decrypt("ueYTIgtA9ewK2i+ounqDLHVS4tvg20v2");
            System.out.println(decryptStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
