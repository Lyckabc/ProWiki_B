package org.prowikiq.global.exception.impl.user;

import org.prowikiq.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * Class: AlreadyExistUserException Project: prowikiQ Package:
 * org.prowikiq.global.exception.impl.user
 * <p>
 * Description: AlreadyExistUserException
 *
 * @author dong-hoshin
 * @date 4/27/24 17:37 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public class AlreadyExistUserException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "이미 존재하는 사용자 휴대폰 번호입니다.";
    }
}
