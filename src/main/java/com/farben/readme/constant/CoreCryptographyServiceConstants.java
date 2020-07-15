/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.constant;

/**
 * Class defines constants dedicated for cryptography services
 */
public class CoreCryptographyServiceConstants {

    /**
     * Encryption error message
     */
    public static final String ERROR_MESSAGE_ENCRYPTION = "Error occurred while encrypting credential.";

    /**
     * Decryption error message
     */
    public static final String ERROR_MESSAGE_DECRYPTION = "Error occurred while decrypting credential.";

    /**
     * Error message of generating shared secret
     */
    public static final String ERROR_MESSAGE_SHAREDSECRET = "Error occurred while generating shared secret.";

    /**
     * Error message of loading key store
     */
    public static final String ERROR_MESSAGE_LOAD_KEYSTORE = "Error occurred while loading key store.";

    /**
     * Encryption Identifier that is plugged to head of encrypted data for
     * encryption
     */
    public static final String ENCRYPTION_IDENTIFIER = "$";
    /**
     * Error message of getting private key
     */
    public static final String ERROR_GET_PRIVATE_KEY = "Error occurred while getting private key.";
    /**
     * Error message of getting public key
     */
    public static final String ERROR_GET_PUBLIC_KEY = "Error occurred while getting public key.";
    /**
     * Error message of paring byte array to string
     */
    public static final String ERROR_PARSE_BYTE_ARRAY_TO_STRING = "Error occurred while parseing byte array to String.";
    
    
    /**
     * SecureRandom Algorithm set by Spring
     */
     public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

    /**
     * Cipher Algorithm set by Spring
     */
     public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * DigestAlgorithm set by Spring
     */
     public static final String DIGEST_ALGORITHM = "SHA-256";

    /**
     * Key Algorithm set by Spring
     */
     public static final String AES_ALGORITHM = "AES";

    /**
     * Key Length, set as 128 bits by default
     */
     public static final int AES_KEY_LENGTH = 128;

    /**
     * Initialisation Vector Length for generating sharedSecret and Cipher
     * actions, default 16 bytes
     */
    public static final int INITIALISATIONVECTORLENGTH = 16;

    /**
     * Length of Shared Secret
     */
    public static final int AES_SECRET_LENGTH = 16;

    /**
     * RSA ALGORITHM
     */
     public static final String RSA_ALGORITHM = "RSA";
    /**
     * public key
     */
     public static final String RSA_PUBLIC_KEY = "RSAPublicKey";
    /**
     * private key
     */
     public static final String RSA_PRIVATE_KEY = "RSAPrivateKey";
    /**
     * the length of RSA key
     */
     public static final int RSA_KEY_SIZE = 512;

}
