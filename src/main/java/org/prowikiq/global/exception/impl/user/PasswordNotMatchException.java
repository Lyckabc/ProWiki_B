package org.prowikiq.global.exception.impl.user;

import org.prowikiq.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * Class: PasswordNotMatchException Project: prowikiQ Package:
 * org.prowikiq.global.exception.impl.user
 * <p>
 * Description: PasswordNotMatchException
 *
 * @author dong-hoshin
 * @date 4/27/24 22:45 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public class PasswordNotMatchException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "비밀번호가 일치하지 않습니다";
    }
}
