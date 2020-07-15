/*
 *
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.util.aes;

import com.farben.readme.exception.CoreCryptographySystemException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Helper to encrypt and decrypt information
 */
public class CoreCryptographyHelper {

    /**
     * The class name
     */
    private static final String CNAME = CoreCryptographyHelper.class.getName();

    /**
     * The log for debug mode
     */
    private static final Log DEBUGGER = LogFactory.getLog("DEBUGGER." + CoreCryptographyHelper.CNAME);

    /**
     * Encoding error message
     */
    private static final String ERROR_MESSAGE_UNSUPPORTED_ENCODING = "Specified encoding is not supported, encoding = ";

    /**
     * Encoding for convertion of String and bytes
     */
    private static final String ENCODING = "UTF-8";

    /**
     * Map storing instances of Random
     */
    private static Map<String, Random> randomMap = new HashMap<String, Random>();

    /**
     *
     * Generate and return a secure random String
     *
     * @param randomNumberAlgorithm
     * @param secretLength
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public static String generateSharedSecret(final String randomNumberAlgorithm, final int secretLength)
        throws NoSuchAlgorithmException {
        final String METHOD = "generateSharedSecret(String, int)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        String sharedSecret = null;
        byte[] randomBytes = generateSecureRandomBytes(randomNumberAlgorithm, secretLength);
        try {
            sharedSecret = new String(Base64.encodeBase64(randomBytes), CoreCryptographyHelper.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new CoreCryptographySystemException(
                CoreCryptographyHelper.ERROR_MESSAGE_UNSUPPORTED_ENCODING + CoreCryptographyHelper.ENCODING, e);
        }


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return sharedSecret;
    }

    /**
     *
     * Retrieve Secure Random Generator
     *
     * @param secureRandomAlgorithm
     *            the algorithm used for generating strongly random number
     * @return Random
     * @throws NoSuchAlgorithmException
     */
    private static Random getSecureRandomGenerator(final String secureRandomAlgorithm) throws NoSuchAlgorithmException {
        final String METHOD = "getSecureRandomGenerator(String)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        Random random = null;
        synchronized (CoreCryptographyHelper.randomMap) {
            // Retrieve SecureRandom instances from Map
            random = CoreCryptographyHelper.randomMap.get(secureRandomAlgorithm);
            if (random == null) {
                // If an instance is not in the Map, get and set it.
                random = SecureRandom.getInstance(secureRandomAlgorithm);
                CoreCryptographyHelper.randomMap.put(secureRandomAlgorithm, random);
            }
        }


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return random;
    }

    /**
     *
     * Generate Secure Random Bytes
     *
     * @param secureRandomAlgorithm
     *            the algorithm used for generating strongly random number
     * @param ivLength
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    private static byte[] generateSecureRandomBytes(final String secureRandomAlgorithm, final int ivLength)
        throws NoSuchAlgorithmException {
        final String METHOD = "generateSecureRandomBytes(String, int)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        // Generate random bytes based on specified random algorithm and length
        byte[] randomBytes = new byte[ivLength];
        Random random = getSecureRandomGenerator(secureRandomAlgorithm);
        random.nextBytes(randomBytes);


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return randomBytes;
    }

    /**
     *
     * Generate symmetric key with specified algorithm
     *
     * @param digestAlgorithm
     *            e.g AES/CBC/PKCS5Padding
     * @param keyAlgorithm
     * @Param sharedSecret It is generated by generateSharedSecret() of the
     *        class
     * @Param keyLength
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Key generateSymmetricKey(final String digestAlgorithm, final String keyAlgorithm, final String sharedSecret,
        final int keyLength) throws NoSuchAlgorithmException {
        final String METHOD = "generateSymmetricKey(String, String, String, int)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        Key key = null;
        MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
        byte[] keyBaseBytes = null;
        try {
            keyBaseBytes = sharedSecret.getBytes(CoreCryptographyHelper.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new CoreCryptographySystemException(
                CoreCryptographyHelper.ERROR_MESSAGE_UNSUPPORTED_ENCODING + CoreCryptographyHelper.ENCODING, e);
        }
        byte[] keyBaseHash = md.digest(keyBaseBytes);
        StringBuffer symmetricKeyBuffer = new StringBuffer();

        for (int i = 0; i < keyBaseHash.length; i++) {
            // Generate and cast value to hex digits
            symmetricKeyBuffer.append(Integer.toString((keyBaseHash[i] & 0xff) + 0x100, 16).substring(1));
        }

        // Extract the first 16 characters as 128-bit symmetric key
        int keyCharsNumber = keyLength / 8;
        String symmetricKeyStr = symmetricKeyBuffer.toString().substring(0, keyCharsNumber);
        byte[] keyBytes = null;
        try {
            keyBytes = symmetricKeyStr.getBytes(CoreCryptographyHelper.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new CoreCryptographySystemException(
                CoreCryptographyHelper.ERROR_MESSAGE_UNSUPPORTED_ENCODING + CoreCryptographyHelper.ENCODING, e);
        }
        key = new SecretKeySpec(keyBytes, keyAlgorithm);


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return key;
    }

    /**
     *
     * Encrypt data with Cipher Block Chaining but without Cipher Provider
     *
     * @param cipherAlgorithm
     * @param secureRandomAlgorithm
     *            the algorithm used for generating strongly random number
     * @param ivLength
     *            the length of initialisationVector that is for the Cipher
     *            initialization. The initialisationVector / IV is generated
     *            inside the method and plugged with the rawData encrypted as a
     *            full encrypted value returned to caller
     * @param rawData
     * @param key
     * @return String
     * @throws GeneralSecurityException
     */
    public static String encryptCBCWithoutProvider(final String cipherAlgorithm, final String secureRandomAlgorithm,
        final int ivLength, final String rawData, final Key key) throws GeneralSecurityException {
        final String METHOD = "encryptCBCWithoutProvider(String, String, int, String, Key)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        String encryptedValue = CoreCryptographyHelper.encryptCBC(cipher, ivLength, secureRandomAlgorithm, rawData, key);


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return encryptedValue;
    }

    /**
     *
     * Encrypt data with Cipher Block Chaining and a specified Cipher Provider
     * such as an HSM
     *
     * @param cipherAlgorithm
     *            e.g AES/CBC/PKCS5Padding
     * @param cipherCryptoProvider
     *            e.g nCipherKM
     * @param secureRandomAlgorithm
     *            the algorithm used for generating strongly random number
     * @param ivLength
     *            the length of initialisationVector that is for the Cipher
     *            initialization. The initialisationVector / IV is generated
     *            inside the method and plugged with the rawData encrypted as a
     *            full encrypted value returned to caller
     * @param rawData
     * @param key
     * @return String
     * @throws GeneralSecurityException
     */
    public static String encryptCBCWithProvider(final String cipherAlgorithm, final String cipherCryptoProvider,
        final String secureRandomAlgorithm, final int ivLength, final String rawData, final Key key)
            throws GeneralSecurityException {
        final String METHOD = "encryptCBCWithProvider(String, String, String, int, String, Key)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        Cipher cipher = Cipher.getInstance(cipherAlgorithm, cipherCryptoProvider);
        String encryptedValue = CoreCryptographyHelper.encryptCBC(cipher, ivLength, secureRandomAlgorithm, rawData, key);


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return encryptedValue;
    }

    /**
     *
     * Encrypt with CBC mode
     *
     * @param cipher
     * @param ivLength
     *            the length of initialisationVector that is for the Cipher
     *            initialization. The initialisationVector / IV is generated
     *            inside the method and plugged with the rawData encrypted as a
     *            full encrypted value returned to caller
     * @param secureRandomAlgorithm
     *            the algorithm used for generating strongly random number
     * @param rawData
     * @param key
     * @return
     * @throws GeneralSecurityException
     */
    private static String encryptCBC(final Cipher cipher, final int ivLength, final String secureRandomAlgorithm,
        final String rawData, final Key key) throws GeneralSecurityException {
        final String METHOD = "encryptCBC(Cipher, int, String, String, Key)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        String encryptedValue = null;
        // Generate IV for cipher initialization
        byte[] initialisationVector = generateSecureRandomBytes(secureRandomAlgorithm, ivLength);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialisationVector);

        // Call Cipher to generate cipher text
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] encVal = null;
        try {
            encVal = cipher.doFinal(rawData.getBytes(CoreCryptographyHelper.ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new CoreCryptographySystemException(
                CoreCryptographyHelper.ERROR_MESSAGE_UNSUPPORTED_ENCODING + CoreCryptographyHelper.ENCODING, e);
        }

        // Combine the IV with cipher text as final value
        byte[] combination = new byte[initialisationVector.length + encVal.length];
        System.arraycopy(initialisationVector, 0, combination, 0, initialisationVector.length);
        System.arraycopy(encVal, 0, combination, initialisationVector.length, encVal.length);

        try {
            // Encode the combined value with BASE64Encoder to produce the
            // final encrypted value
            encryptedValue = new String(Base64.encodeBase64(combination), CoreCryptographyHelper.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new CoreCryptographySystemException(
                CoreCryptographyHelper.ERROR_MESSAGE_UNSUPPORTED_ENCODING + CoreCryptographyHelper.ENCODING, e);
        }


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return encryptedValue;
    }

    /**
     *
     * Decrypt with CBC mode but without Cipher Provider
     *
     * @param cipherAlgorithm
     * @param ivLength
     *            the length of initialisationVector that is for the Cipher
     *            initialization.When decryption method is called, the
     *            encrypted value passed in is separated to two parts before
     *            Cipher takes action, one is the IV and the other is the real
     *            encrypted data generated from the raw data passed by caller.
     * @param encryptedData
     * @param key
     * @return String
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String decryptCBCWithoutProvider(final String cipherAlgorithm, final int ivLength, final String encryptedData,
        final Key key) throws GeneralSecurityException, IOException {
        final String METHOD = "decryptCBCWithoutProvider(String, String, int, String, Key)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        String decryptedValue = decryptCBC(cipher, ivLength, encryptedData, key);


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return decryptedValue;
    }

    /**
     *
     * Decrypt with both CBC mode and Cipher Provider
     *
     * @param cipherAlgorithm
     * @param cipherCryptoProvider
     * @param ivLength
     *            the length of initialisationVector that is for the Cipher
     *            initialization.When decryption method is called, the
     *            encrypted value passed in is separated to two parts before
     *            Cipher takes action, one is the IV and the other is the real
     *            encrypted data generated from the raw data passed by caller.
     * @param encryptedData
     * @param key
     * @return String
     * @throws GeneralSecurityException
     */
    public static String decryptCBCWithProvider(final String cipherAlgorithm, final String cipherCryptoProvider, final int ivLength,
        final String encryptedData, final Key key) throws GeneralSecurityException {
        final String METHOD = "decryptCBCWithProvider(String, String, String, int, String, Key)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        Cipher cipher = Cipher.getInstance(cipherAlgorithm, cipherCryptoProvider);
        String decryptedValue = decryptCBC(cipher, ivLength, encryptedData, key);


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return decryptedValue;
    }

    /**
     *
     * Decrypt with CBC mode
     *
     * @param cipher
     * @param ivLength
     *            the length of initialisationVector that is for the Cipher
     *            initialization.When decryption method is called, the
     *            encrypted value passed in is separated to two parts before
     *            Cipher takes action, one is the IV and the other is the real
     *            encrypted data generated from the raw data passed by caller.
     * @param encryptedData
     * @param key
     * @return
     * @throws GeneralSecurityException
     */
    private static String decryptCBC(final Cipher cipher, final int ivLength, final String encryptedData, final Key key)
        throws GeneralSecurityException {
        final String METHOD = "decryptCBC(Cipher, int, String, Key)";
        final boolean DEBUG = CoreCryptographyHelper.DEBUGGER.isDebugEnabled();
        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": ENTRY");
        }


        String decryptedValue = null;
        byte[] decodedBytes = null;
        try {
            // Decode encryptedData with BASE64Decoder
            decodedBytes = Base64.decodeBase64(encryptedData.getBytes(CoreCryptographyHelper.ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new CoreCryptographySystemException(
                CoreCryptographyHelper.ERROR_MESSAGE_UNSUPPORTED_ENCODING + CoreCryptographyHelper.ENCODING, e);
        }

        // Separate IV and cipher text from decodedBytes
        byte[] initialisationVector = new byte[ivLength];
        byte[] decodedCipherText = new byte[decodedBytes.length - ivLength];
        System.arraycopy(decodedBytes, 0, initialisationVector, 0, initialisationVector.length);
        System.arraycopy(decodedBytes, initialisationVector.length, decodedCipherText, 0, decodedCipherText.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialisationVector);

        // Call cipher to decrypt decodedCipherText
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
        try {
            decryptedValue = new String(decryptedBytes, CoreCryptographyHelper.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new CoreCryptographySystemException(
                CoreCryptographyHelper.ERROR_MESSAGE_UNSUPPORTED_ENCODING + CoreCryptographyHelper.ENCODING, e);
        }


        if (DEBUG) {
            CoreCryptographyHelper.DEBUGGER.debug(CoreCryptographyHelper.CNAME + "#" + METHOD + ": EXIT");
        }

        return decryptedValue;
    }

}
