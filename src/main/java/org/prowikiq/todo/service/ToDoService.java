package org.prowikiq.todo.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.todo.domain.repository.ToDoRepository;
import org.prowikiq.user.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * Class: ToDoService Project: prowikiQ Package: org.prowikiq.todo.service
 * <p>
 * Description: ToDoService
 *
 * @author dong-hoshin
 * @date 5/12/24 18:26 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Transactional
    public ToDo getToDoFromId(Long toDoId) {
        if (toDoId == null) {
            return null;
        }
        Optional<ToDo> toDo = toDoRepository.findByToDoId(toDoId);

        return toDo.orElseThrow(() -> new NoSuchElementException("해당 ToDoId 대한 저장 객체가 없습니다."));
    }

}
