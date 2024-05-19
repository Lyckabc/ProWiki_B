package org.prowikiq.todo.domain;

/**
 * Class: ToDoStatus Project: prowikiQ Package: org.prowikiq.todo
 * <p>
 * Description: ToDoStatus
 *
 * @author dong-hoshin
 * @date 5/16/24 16:04 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public enum ToDoStatus {
    DONE,
    IN_PROGRESS,
    NOT_STARTED;

    public static final ToDoStatus DEFAULT = NOT_STARTED;
}
