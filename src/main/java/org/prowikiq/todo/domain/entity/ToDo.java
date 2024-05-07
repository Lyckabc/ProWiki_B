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
 * Class: ToDo Project: prowikiQ Package: org.prowikiq.todo.domain.entity
 * <p>
 * Description:
 * ToDo
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
    @Column(name = "toDoId")
    private Long toDoId;

    @Column(name = "requestTitle")
    private String requestTitle;

    @Column(name = "requestContent")
    private String requestContent;

    @Column(name = "requestAnswerValue") //특정 정답 값을 기록해두고 작업을 할 User는 해당 값을 맞춰야함
    private String requestAnswerValue;

    @Column(name = "targetDay")
    private LocalDateTime targetDay;

    @Column(name = "finishedDay")
    private LocalDateTime finishedDay;

    //user
    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩을 사용하여 성능 최적화
    @JoinColumn(name = "userId", referencedColumnName = "userId")  // 외래 키 매핑
    private User userId;  // User 엔티티와의 연결을 정의

    @Column(name = "requestUser")
    private String requestUser;

    @Column(name = "solvedUser")
    private String solvedUser;

    // page
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pageId", referencedColumnName = "pageId")
    private FilePath pageId;

    @Column(name = "pageTitle")
    private String pageTitle;



}
