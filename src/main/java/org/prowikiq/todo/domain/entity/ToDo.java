package org.prowikiq.todo.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prowikiq.global.BaseEntity;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.todo.ToDoStatus;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Class: ToDo
 * Project: prowikiQ
 * Package: org.prowikiq.todo.domain.entity
 *
 * Description: Represents a ToDo item in the system.
 *
 * @author dong-hoshin
 * @date 5/7/24 17:04 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"todo\"")
public class ToDo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "to_do_id")
    private Long toDoId;

    @Column(name = "to_do_title")
    private String toDoTitle;

    @Column(name = "to_do_content")
    private String toDoContent;

    @Column(name = "request_answer_value")
    private String requestAnswerValue;

    @Column(name = "answer_value")
    private String answerValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_status")
    private ToDoStatus toDoStatus;

    @Column(name = "complete_check")
    private Boolean completeCheck;

    @Column(name = "request_answer_check")
    private Boolean requestAnswerCheck;

    @Column(name = "target_day")
    private LocalDateTime targetDay;

    @Column(name = "finished_day")
    private LocalDateTime finishedDay;

    // User associated with this ToDo
    @ManyToOne(fetch = FetchType.LAZY)  // Optimize performance by loading on-demand
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "request_user_id")
    private Long requestUserId;

    @Column(name = "solved_user_id")
    private Long solvedUserId;
    public void setCompleteCheck(Boolean completeCheck) {
        this.completeCheck = completeCheck;
    }


    public ToDoDto toDto() {
        return ToDoDto.builder()
            .toDoId(this.toDoId)
            .toDoTitle(this.toDoTitle)
            .toDoContent(this.toDoContent)
            .requestAnswerValue(this.requestAnswerValue)
            .targetDay(this.targetDay)
            .finishedDay(this.finishedDay)
            .userId(this.userId != null ? this.userId.toDto() : null)
            .requestUserId(this.requestUserId)
            .solvedUserId(this.solvedUserId)
            .answerValue(this.answerValue)
            .requestAnswerCheck(this.requestAnswerCheck)
            .completeCheck(this.completeCheck)
            .toDoStatus(this.toDoStatus)
            .build();
    }

    public ToDo update(ToDoDto.RequestWrite requestWrite) {
        this.toDoTitle = requestWrite.getToDoTitle();
        this.toDoContent = requestWrite.getToDoContent();
        this.requestAnswerValue = requestWrite.getRequestAnswerValue();
        this.targetDay = requestWrite.getTargetDay();
        this.finishedDay = requestWrite.getFinishedDay();
        this.toDoStatus = requestWrite.getToDoStatus();
        return this;
    }

    public ToDo adminUpdate(ToDoDto.AdminUpdate requestUpdate) {
        this.toDoTitle = requestUpdate.getToDoTitle();
        this.toDoContent = requestUpdate.getToDoContent();
        this.requestAnswerValue = requestUpdate.getRequestAnswerValue();
        this.targetDay = requestUpdate.getTargetDay();
        this.finishedDay = requestUpdate.getFinishedDay();
        this.requestUserId = requestUpdate.getRequestUserId();
        this.solvedUserId = requestUpdate.getSolvedUserId();
        this.answerValue = requestUpdate.getAnswerValue();
        this.requestAnswerCheck = requestUpdate.getRequestAnswerCheck();
        this.completeCheck = requestUpdate.getCompleteCheck();
        this.toDoStatus = requestUpdate.getToDoStatus();
        return this;
    }

}
