package org.prowikiq.todo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.prowikiq.todo.domain.ToDoStatus;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.todo.service.ToDoService;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class: ToDoController Project: prowikiQ Package: org.prowikiq.todo.controller
 * <p>
 * Description: ToDoController
 *
 * @author dong-hoshin
 * @date 5/16/24 13:38 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Api(tags = "ToDo Controller")
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;
    private final UserRepository userRepository;
    @ApiOperation(value = "create todo with optional pageId", notes = "pageId isn't essential but for detail")
    @PostMapping("/")
    public ResponseEntity<Void> createToDo(@RequestParam(required = false) Long pageId,
                                        @RequestBody ToDoDto.RequestWrite requestWrite) {
        User user = getCurrentUser();
        toDoService.createToDo(pageId, requestWrite, user);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update ToDo", notes = "Update an existing ToDo by its ID")
    @PutMapping("/{id}")
    public ToDoDto updateToDo(@PathVariable(name = "id") Long toDoId,
        @RequestParam(required = false) Long pageId,
        @RequestBody ToDoDto.AdminUpdate requestUpdate,
        @RequestParam("status") ToDoStatus status
        ) {
        User user = getCurrentUser();
        boolean isAdmin = checkIfAdmin(user);

        return toDoService.modifyToDo(toDoId, pageId, requestUpdate, status, isAdmin);
    }

    private User getCurrentUser() {
        //todo 임시 User get 코드 차후에 HttpServletRequest안에 token값으로 가져올 예정
        User user = userRepository.findByUserId(Long.valueOf(1)).orElseThrow();
        return user;
    }
    private boolean checkIfAdmin(User user) {
        //todo User, Role을 계층식 관계데이터로 만들어서 관리할 예정.
//        user.getRoles().contains(Role.ADMIN);
        return true;
    }

    @ApiOperation(value = "status ", notes = "decide to complete ToDo ot Not by ToDoId")
    @PutMapping("/{id}/check")
    public ResponseEntity<ToDoDto> completeChk(@PathVariable(name = "id") Long toDoId, @RequestParam("completeChk") Boolean completeChk) {
        ToDoDto toDoDto = toDoService.completeChkToDo(toDoId, completeChk);
        return ResponseEntity.ok(toDoDto);
    }

}
