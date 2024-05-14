package org.prowikiq.todo.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.todo.domain.repository.ToDoRepository;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.service.UserService;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
import org.prowikiq.wiki.service.WikiPageService;
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
    private final UserService userService;

    @Transactional
    public ToDo getToDoFromId(Long toDoId) {
        if (toDoId == null) {
            return null;
        }
        Optional<ToDo> toDo = toDoRepository.findByToDoId(toDoId);

        return toDo.orElseThrow(() -> new NoSuchElementException("해당 toDoId 대한 저장 객체가 없습니다."));
    }
    @Transactional
    public ToDo getToDoFromTitle(String toDoTitle) {
        if (toDoTitle == null) {
            return null;
        }
        Optional<ToDo> toDo = toDoRepository.findByToDoTitle(toDoTitle);

        return toDo.orElseThrow(() -> new NoSuchElementException("해당 toDoTitle 대한 저장 객체가 없습니다."));
    }

    @Transactional
    public ToDoDto handleToDo(Long toDoId, String toDoTitle) {
        ToDo toDo = null;

        if (toDoId != null) {
            toDo = getToDoFromId(toDoId);
        }

        if (toDo == null && toDoTitle != null) {
            toDo = getToDoFromTitle(toDoTitle);
        }

        ToDoDto dtoToDo = toDoConvertToDto(toDo);
        return dtoToDo;
    }

    @Transactional
    public ToDoDto toDoConvertToDto(ToDo toDo) {
        UserDto userDto = userService.userConvertToDto(toDo.getUserId());


        ToDoDto dto =  ToDoDto.builder()
            .toDoTitle(toDo.getToDoTitle())
            .toDoContent(toDo.getToDoContent())
            .requestAnswerValue(toDo.getRequestAnswerValue())
            .targetDay(toDo.getTargetDay())
            .finishedDay(toDo.getFinishedDay())
            .userId(userDto)
            .requestUserId(toDo.getRequestUserId())
            .solvedUserId(toDo.getSolvedUserId())
            .build();

        return dto;
    }

}
