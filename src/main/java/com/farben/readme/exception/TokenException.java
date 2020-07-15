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
 *
 * @ClassName: SecurityException
 * @Description: TODO
 * @author: 43994701
 * @date May 21, 2020
 *
 */
public class TokenException extends Exception {

    private static final long serialVersionUID = 8570780381261239675L;

    public TokenException() {
        super();
    }

    public TokenException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TokenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TokenException(final String message) {
        super(message);
    }

    public TokenException(final Throwable cause) {
        super(cause);
    }
}
