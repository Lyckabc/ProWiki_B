package org.prowikiq.global.exception.impl.user;

import org.prowikiq.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * Class: NotExistUserException Project: prowikiQ Package: org.prowikiq.global.exception.impl.user
 * <p>
 * Description: NotExistUserException
 *
 * @author dong-hoshin
 * @date 4/27/24 22:44 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public class NotExistUserException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "존재하지 않는 사용자입니다.";
    }
}
