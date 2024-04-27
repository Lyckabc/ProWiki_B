package org.prowikiq.global.exception;

/**
 * Class: AbstractException Project: prowikiQ Package: org.prowikiq.global.exception.impl
 * <p>
 * Description: AbstractException
 *
 * @author dong-hoshin
 * @date 4/27/24 17:36 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public abstract class AbstractException extends RuntimeException{
    abstract public int getStatusCode();
    abstract public String getMessage();
}