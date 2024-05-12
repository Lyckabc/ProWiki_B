package org.prowikiq.todo.domain.repository;

import java.util.Optional;
import org.prowikiq.todo.domain.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class: ToDoRepository Project: prowikiQ Package: org.prowikiq.todo.domain.repository
 * <p>
 * Description: ToDoRepository
 *
 * @author dong-hoshin
 * @date 5/8/24 15:31 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    Optional<ToDo> findByToDoId(Long toDoId);

}
