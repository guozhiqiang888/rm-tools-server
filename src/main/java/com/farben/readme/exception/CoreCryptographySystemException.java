/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.exception;

/**
 * Dedicated exception for Core Cryptography Services
 *
 */
public class CoreCryptographySystemException extends RuntimeException {

    /**
     * Class serial number
     */
    private static final long serialVersionUID = -7734161890040352361L;

    public CoreCryptographySystemException(final String errorMsg, final Throwable t) {
        super(errorMsg, t);
    }

}
