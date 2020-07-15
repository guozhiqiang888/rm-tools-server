package com.farben.readme.util;

import java.util.HashMap;
import java.util.Map;

public class DataSourcePasswordEncodeUtil {

    private static final String SALT = "dfsad@#%$@TDGDF%$#%@#%WFRGFDHJKcvxznmfdsgdfgs2432534fgdf46t";

    private static final int[] KEY = {
      23, 22, 24, 4, 51, 26, 37, 27, 24, 6, 26, 38, 29, 35, 18, 21, 14, 3, 12, 4, 41, 39, 18, 44, 54, 21, 33, 35, 31, 22, 34, 53, 51, 44, 8, 12, 3, 0, 28, 1, 48, 9, 51, 57, 20, 44, 27, 3, 16, 48
    };

    private static final Map MMP = new HashMap(12);

    static{
        short i = 10;
        for(; i< 16; i++){
            MMP.put(i, (char)('A' + i -10));
            MMP.put((char)('A' + i - 10), i);
        }
    }

    public static byte[] encrypt(byte[] original){
        byte[] keyByte = new byte[KEY.length];

        for(int i = 0; i < KEY.length; i++){
            keyByte[i] = (byte) SALT.charAt(KEY[i]);
        }

        int k = 0;
        byte[] encryptByte = new byte[original.length];
        for(int i = 0; i < original.length; i++){
            encryptByte[i] = (byte) (original[i] ^ keyByte[k++ % keyByte.length]);
        }

        return encryptByte;
    }

    public static byte[] decrypt(byte[] original){
        return encrypt(original);
    }

    public static String encryptToHex(String original){
        byte[] originalBytes = original.getBytes();
        byte[] bytes = encrypt(originalBytes);

        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < bytes.length; i ++){
            short a = (short)(bytes[i] & 15);
            short b = (short)((bytes[i] & (15 << 4)) >>> 4);
            if(b < 10){
                stringBuffer.append(b);
            }else{
                stringBuffer.append(MMP.get(b));
            }

            if(a < 10){
                stringBuffer.append(a);
            }else{
                stringBuffer.append(MMP.get(a));
            }
        }
        return stringBuffer.toString();
    }

    public static String decryptFromHex(String original){
        byte[] bytes = new byte[original.length() / 2];
        int len = 0;
        for(int i = 0; i< original.length(); i += 2){
            short a, b;
            if(original.charAt(i) >= '0' && original.charAt(i) <= '9'){
                a = (short) (original.charAt(i) - '0');
                a <<= 4;
            }else{
                a = (short) MMP.get(original.charAt(i));
                a <<= 4;
            }

            if(original.charAt(i + 1) >= '0' && original.charAt(i+1) <= '9'){
                b = (short)(original.charAt(i+1) - '0');
            }else{
                b = (short) MMP.get(original.charAt(i+1));
            }
            bytes[len ++] = (byte) (a + b);
        }
        return new String(decrypt(bytes));
    }

    public static void main(String[] args) {
        String str = null;
        System.out.println("dfsad@#$%@ewqewq21321321321dsewqe*&^$dwqe123KIYN%$".length());
        System.out.println(KEY.length);
        System.out.println(str = encryptToHex("Wechat_20191008"));
        System.out.println(decryptFromHex(str));
    }
}
