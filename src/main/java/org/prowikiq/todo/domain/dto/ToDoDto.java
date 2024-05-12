package org.prowikiq.todo.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.wiki.domain.dto.WikiPageDto;

/**
 * Class: ToDoCreateDto Project: prowikiQ Package: org.prowikiq.todo.domain.dto
 * <p>
 * Description: ToDoCreateDto
 *
 * @author dong-hoshin
 * @date 5/7/24 19:44 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDto {
    private String toDoTitle;
    private String todoContent;
    private String requestAnswerValue;
    private LocalDateTime targetDay;
    private LocalDateTime finishedDay;
    private UserDto userId;
    private Long requestUserId;
    private Long solvedUserId;
    private WikiPageDto pageId;
}
