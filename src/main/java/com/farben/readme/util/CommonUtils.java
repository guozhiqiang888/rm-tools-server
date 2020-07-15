/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2017. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public final class CommonUtils {

    private CommonUtils(){
    }

    /**
     * Generate active code
     *
     * @return int
     */
    public static int generateActiveCode() {
        int max = 99999999;
        int min = 10000000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * Get next minutes after current time
     *
     * @param i next minutes
     * @return Date
     */
    public static Date getNextMinute(int i) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, i);
        date = calendar.getTime();
        return date;
    }

    /**
     * convert string to hash
     *
     * @param key
     * @return String
     */
    public static String toHash(String key) {
        int arraySize = 1000003; //array's size uses prime
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) { //from left side of the string
            int letterValue = key.charAt(i) - 96; //convert the obtained string to a number, such as 'a' is 97,then 97-96=1 represents the value of a
            hashCode = ((hashCode << 7) + letterValue) % arraySize; //prevent code overflow,modulo operation for each result
        }
        hashCode = Math.abs(hashCode);
        return String.format("%06d", hashCode);
    }


    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        System.out.println("l============>" + l);
//        String str = toHash("43994701" + l);
//        System.out.println("str============>" + str);
//        int i1 = generateActiveCode();
//        System.out.println("i1============>" + i1);
        System.out.println(uuid());
        System.out.println(uuid().length());
    }


}
