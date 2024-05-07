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
import lombok.Setter;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.Object;
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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class WikiCommonEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false,  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = true)
    private LocalDateTime modifiedAt;

    //filePath

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "filePathId", referencedColumnName = "filePathId")
    private FilePath filePathId;

    @Column(name = "filePath")
    private String filePath;

    /*
    //Ancestor
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ancestorId", referencedColumnName = "ancestorId")
    private Object ancestorId;

    @Column(name = "ancestor")
    private String ancestor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parentFolderId", referencedColumnName = "objectId")
    private Object parentFolderId;

    @Column(name = "parentFolder")
    private String parentFolder;
    */

    //object

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "objectId", referencedColumnName = "objectId")
    private FilePath objectId;

    @Column(name = "isFolder")
    private Boolean isFolder;

    //User
    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩을 사용하여 성능 최적화
    @JoinColumn(name = "userId", referencedColumnName = "userId")  // 외래 키 매핑
    private User userId;  // User 엔티티와의 연결을 정의

    @Column(name = "createdAtUser",nullable = false, updatable = false)
    private String createdAtUser;

    @Column(name = "modifiedAtUser")
    private String modifiedAtUser;

    //User's data but for Todo
    @Column(name = "requestUser")
    private String requestUser;

    @Column(name = "solvedUser")
    private String solvedUser;


    // Todo

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "toDoId", referencedColumnName = "toDoId")
    private FilePath toDoId;

    @Column(name = "targetDay")
    private LocalDateTime targetDay;

    @Column(name = "finishedDay")
    private LocalDateTime finishedDay;

}
