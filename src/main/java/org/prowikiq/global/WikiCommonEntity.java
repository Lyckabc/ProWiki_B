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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.Object;
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

    //filePath
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_path_id", referencedColumnName = "file_path_id")
    private FilePath filePathId;

    @Column(name = "file_path")
    private String filePath;

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
    private Object objectId;

    @Column(name = "is_folder")
    private Boolean isFolder;

    //User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId; // Reference to the User entity

    @Column(name = "created_at_user", nullable = false, updatable = false)
    private String createdAtUser; // User who created this entity

    @Column(name = "modified_at_user")
    private String modifiedAtUser;

    //User's data but for Todo
    @Column(name = "request_user")
    private String requestUser; // User who requested some action related to this entity

    @Column(name = "solved_user")
    private String solvedUser;


    // Todo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_do_id", referencedColumnName = "to_do_id")
    private ToDo toDoId;

    @Column(name = "target_day")
    private LocalDateTime targetDay; 

    @Column(name = "finished_day")
    private LocalDateTime finishedDay;

}
