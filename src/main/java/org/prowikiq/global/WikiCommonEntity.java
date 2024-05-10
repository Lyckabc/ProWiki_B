package org.prowikiq.global;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Class: WikiCommonEntity Project: prowikiQ Package: org.prowikiq.global
 * <p>
 * Description: WikiCommonEntity
 *
 * @author dong-hoshin
 * @date 5/7/24 17:25 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
public abstract class WikiCommonEntity {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false,  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;

    @LastModifiedDate
    @Column(name = "latested_at", nullable = true)
    private LocalDateTime latestedAt;


    /*
    //Ancestor
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ancestor_id", referencedColumnName = "ancestorId")
    private Ancestor ancestorId;

    @Column(name = "ancestor")
    private String ancestor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parentFolder_id", referencedColumnName = "objectId")
    private Object parentFolderId;

    @Column(name = "parentFolder")
    private String parentFolder;
    */

    //object
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id", referencedColumnName = "object_id")
    private StorageObject storageObjectId;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "is_folder")
    private Boolean isFolder;

    //User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "created_at_user_id", nullable = false, updatable = false)
    private Long createdAtUserId;

    @Column(name = "modified_at_user_id")
    private Long modifiedAtUserId;

    //User's data but for Todo
    @Column(name = "request_user_id")
    private Long requestUserId;

    @Column(name = "solved_user_id")
    private Long solvedUserId;


    // Todo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_do_id", referencedColumnName = "to_do_id")
    private ToDo toDoId;

    @Column(name = "target_day")
    private LocalDateTime targetDay; 

    @Column(name = "finished_day")
    private LocalDateTime finishedDay;

}
