package com.farben.readme.util.aes;


import com.farben.readme.constant.Constant;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class EncAndDecRSAExample {
    public static void main(final String[] args) throws IOException {

        String tokenParam = "WeConnect!@#$369" + "-" + System.currentTimeMillis();
        String logon = CoreSymmetricCryptographyUtil.encryptData(Constant.AES_128_KEY, tokenParam);
        String target = "f974da3be6b20e41e27a2563fbdeafdc";
        AES128Test(target);
        System.out.println("---------------------"+ logon);


        // target = "A.AMIR@MAPEI.AE";
        // AES128Test(target, cycs);
        // System.out.println("---------------------");
        //
        // String test =
        // "$5sZcagVEMVMjlSBrTf44svel0Lx71CU+kYovL2YYiVeWHiHWGuRuJeCNvlM9uLDe";
        // System.out.println(URLEncoder.encode(test));
        // System.out.println("decrypt: " + test);
        // System.out.println(cycs.decryptData(CommonConstants.AES_128_KEY,
        // test));

        // rsaTest(target,cycs);
    }

    public static void rsaTest(final String target, final CoreSymmetricCryptographyUtil cycs) {
        System.out.println("------------enc by private key and dec by public key -----------");
        /*
         * for(int i=0;i<10;i++){ String encPubData =
         * cycs.rsaEncryptByPublicKey(target); System.out.println("encPbData: "
         * +encPubData); }
         */
        String encPriData = cycs.rsaEncryptByPrivateKey(target);
        System.out.println("encPriData: " + encPriData);
        Map headerMap = new HashMap<String, String>();
        headerMap.put(Constant.CLIENT_ID_KEY, encPriData);

        String test = headerMap.get(Constant.CLIENT_ID_KEY).toString();
        System.out.println("test      =" + test);
        String decPubData = cycs.rsaDecryptByPublicKey(test);
        System.out.println("decPbData: " + decPubData);
        //
        // System.out.println("------------enc by public key and dec by private
        // key -----------");
        // String encPubData = cycs.rsaEncryptByPublicKey(target);
        // System.out.println("encPbData: " +encPubData);
        // String decPriData = cycs.rsaDecryptByPrivateKey(encPubData);
        // System.out.println("decPriData: " +decPriData);
    }

    public static void AES128Test(final String pwd) {
        String encData = CoreSymmetricCryptographyUtil.encryptData(Constant.AES_128_KEY, pwd);
        String dData = CoreSymmetricCryptographyUtil.decryptData(Constant.AES_128_KEY, encData);
        System.out.println("encrypt value:" + pwd);
        System.out.println(encData);
        System.out.printf("Enc:%s, Dnc:%s%n", encData, dData);
        System.out.println(URLEncoder.encode(encData));

        System.out.println(CoreSymmetricCryptographyUtil.decryptData(Constant.AES_128_KEY, "$N4cpyB7EnlaxzWofL2It/nEGaJC4ZxNB+xZsJ4Q0a++1UBGU7AFlmIXCS8Xc8wgWnFVPr4tjE1hXfuaVcKKlbw=="));
    }

}
