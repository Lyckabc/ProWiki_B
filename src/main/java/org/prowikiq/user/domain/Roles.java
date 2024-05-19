package org.prowikiq.user.domain;

/**
 * Class: Roles Project: prowikiQ Package: org.prowikiq.user.domain
 * <p>
 * Description: Roles
 *
 * @author dong-hoshin
 * @date 5/18/24 09:54 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public enum Roles {
    ROOT,
    PROJECT_MANAGER,
    TEAM_MANAGER,
    MEMBER;
    public static final Roles DEFAULT = MEMBER;
}
