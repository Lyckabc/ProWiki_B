package org.prowikiq.todo.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.todo.domain.ToDoStatus;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.todo.domain.dto.ToDoDto.RequestWrite;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.todo.domain.repository.ToDoRepository;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.service.UserService;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.prowikiq.wiki.domain.repository.WikiPageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final WikiPageRepository wikiPageRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

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

    @Transactional
    public void createToDo(Long pageId, ToDoDto.RequestWrite request, User user) {
        WikiPage page = null;


        if (pageId != null) {
            page = wikiPageRepository.findByPageId(pageId)
                .orElseThrow(() -> new RuntimeException("해당 Page 없음"));
        }

        ToDo todo = request.toEntity(user);
        toDoRepository.save(todo);
        if (page != null) {
            page.setToDoId(todo);
        }
//        logger.info(String.valueOf(todo));
//        logger.info(String.valueOf(page.orElse(null)));
    }

    @Transactional
    public ToDoDto modifyToDo(Long toDoId, Long pageId, ToDoDto.AdminUpdate requestUpdate, ToDoStatus status,Boolean isAdmin) {
        ToDo todo = toDoRepository.findByToDoId(toDoId)
            .orElseThrow(() -> new RuntimeException("해당 ToDo없음"));

        if (pageId != null) {
            WikiPage page = wikiPageRepository.findByPageId(pageId)
                .orElseThrow(() -> new RuntimeException("해당 Page 없음"));
            page.setToDoId(todo);
        }

        if (isAdmin) {
            requestUpdate.setToDoStatus(status);
            todo.adminUpdate(requestUpdate);
        } else {
            ToDoDto.RequestWrite requestWrite = RequestWrite.builder()
                .toDoTitle(requestUpdate.getToDoTitle())
                .toDoContent(requestUpdate.getToDoContent())
                .requestAnswerValue(requestUpdate.getRequestAnswerValue())
                .targetDay(requestUpdate.getTargetDay())
                .finishedDay(requestUpdate.getFinishedDay())
                .toDoStatus(requestUpdate.getToDoStatus())
                .build();

            todo.update(requestWrite);
        }

        return todo.toDto();
    }

    @Transactional
    public ToDoDto completeChkToDo(Long toDoId,Boolean completeChk) {
        ToDo toDo = toDoRepository.findByToDoId(toDoId)
                    .orElseThrow(() -> new RuntimeException("There is no toDoId"));

        toDo.setCompleteCheck(completeChk);
        toDoRepository.save(toDo);

        return toDo.toDto();
    }
}
