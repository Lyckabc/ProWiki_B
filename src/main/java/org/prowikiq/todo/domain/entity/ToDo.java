package org.prowikiq.todo.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.user.domain.entity.User;

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

    @Column(name = "request_title")
    private String requestTitle;

    @Column(name = "request_content")
    private String requestContent;

    @Column(name = "request_answer_value")
    private String requestAnswerValue;

    @Column(name = "target_day")
    private LocalDateTime targetDay;

    @Column(name = "finished_day")
    private LocalDateTime finishedDay;

    // User associated with this ToDo
    @ManyToOne(fetch = FetchType.LAZY)  // Optimize performance by loading on-demand
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "request_user")
    private String requestUser;

    @Column(name = "solved_user")
    private String solvedUser;

    // Page associated with this ToDo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "page_id", referencedColumnName = "file_path_id")
    private FilePath pageId;

    @Column(name = "page_title")
    private String pageTitle;
}
