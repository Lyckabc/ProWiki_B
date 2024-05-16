package org.prowikiq.todo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.todo.service.ToDoService;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
                                        @RequestBody ToDoDto.Request todoREQ) {
        // 임시 User get 코드 차후에 HttpServletRequest안에 token값으로 가져올 예정
        User user = userRepository.findByUserId(Long.valueOf(1)).orElseThrow();

        toDoService.createToDo(pageId, todoREQ, user);
        return ResponseEntity.ok().build();
    }

}
