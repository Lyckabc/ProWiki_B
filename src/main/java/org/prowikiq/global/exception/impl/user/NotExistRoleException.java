package org.prowikiq.global.exception.impl.user;

import org.prowikiq.global.exception.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * Class: NotExistRoleException Project: prowikiQ Package: org.prowikiq.global.exception.impl.user
 * <p>
 * Description: NotExistRoleException
 *
 * @author dong-hoshin
 * @date 5/18/24 09:15 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public class NotExistRoleException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "존재하지 않는 역할입니다.";
    }
}
