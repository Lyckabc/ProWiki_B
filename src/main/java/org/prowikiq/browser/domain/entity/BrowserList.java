package org.prowikiq.browser.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.prowikiq.global.BaseEntity;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Class: BrowserList
 * Project: prowikiQ
 * Package: org.prowikiq.browser.domain.entity
 *
 * Description: Entity representing a BrowserList, part of the prowikiQ project.
 *
 * @author dong-hoshin
 * @date 4/29/24 15:23 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "\"browserList\"")
@EntityListeners(AuditingEntityListener.class)
public class BrowserList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "browser_list_id", nullable = false)
    private Long browserListId;

    // Related page details
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "page_id", referencedColumnName = "page_id")
    private WikiPage pageId;

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name = "page_category")
    private String pageCategory;

    @Column(name = "page_path")
    private String pagePath;

    //object
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id", referencedColumnName = "object_id")
    private StorageObject storageObjectId;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "is_folder")
    private Boolean isFolder;

    @Column(name = "object_path")
    private String objectPath;

    //User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "user_phone_num")
    private String userPhoneNum;

    @Column(name = "created_at_user_id", nullable = false, updatable = false)
    private Long createdAtUserId;

    @Column(name = "modified_at_user_id")
    private Long modifiedAtUserId;

    // Todo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_do_id", referencedColumnName = "to_do_id")
    private ToDo toDoId;

    @Column(name = "to_do_title")
    private String toDoTitle;

    @Column(name = "target_day")
    private LocalDateTime targetDay;

    @Column(name = "finished_day")
    private LocalDateTime finishedDay;

    @Column(name = "request_user_id")
    private Long requestUserId;

    @Column(name = "solved_user_id")
    private Long solvedUserId;

}
