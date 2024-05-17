package org.prowikiq.todo.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prowikiq.todo.ToDoStatus;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;

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
    private Long toDoId;
    private String toDoTitle;
    private String toDoContent;
    private String requestAnswerValue;
    private LocalDateTime targetDay;
    private LocalDateTime finishedDay;
    private UserDto userId;
    private Long requestUserId;
    private Long solvedUserId;
    private String answerValue;
    private Boolean requestAnswerCheck;
    private Boolean completeCheck;
    private ToDoStatus toDoStatus;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RequestWrite {
        private String toDoTitle;
        private String toDoContent;
        private String requestAnswerValue;
        private LocalDateTime targetDay;
        private LocalDateTime finishedDay;
        private ToDoStatus toDoStatus;

        public ToDo toEntity(User user) {
            return ToDo.builder()
                .toDoTitle(toDoTitle)
                .toDoContent(toDoContent)
                .requestAnswerValue(requestAnswerValue)
                .targetDay(targetDay)
                .finishedDay(finishedDay)
                .toDoStatus(toDoStatus)
                .userId(user)
                .requestUserId(user.getUserId())
                .build();
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AdminUpdate {
        private String toDoTitle;
        private String toDoContent;
        private String requestAnswerValue;
        private LocalDateTime targetDay;
        private LocalDateTime finishedDay;
        private Long requestUserId;
        private Long solvedUserId;
        private String answerValue;
        private Boolean requestAnswerCheck;
        private Boolean completeCheck;
        private ToDoStatus toDoStatus;
    }
}
