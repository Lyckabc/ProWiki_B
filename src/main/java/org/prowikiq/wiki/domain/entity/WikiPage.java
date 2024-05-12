package org.prowikiq.wiki.domain.entity;

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
import lombok.experimental.SuperBuilder;
import org.prowikiq.global.BaseEntity;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;

/**
 * Class: WikPage Project: prowikiQ Package: org.prowikiq.wiki.entity
 * <p>
 * Description: WikPage Entity
 *
 * @author dong-hoshin
 * @date 4/29/24 15:25 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "\"wikipage\"")
public class WikiPage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id", nullable = false)
    private Long pageId;

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name = "page_content")
    private String pageContent;

    @Column(name = "page_category")
    private String pageCategory;

    @Column(name = "page_path",columnDefinition = "Page content Link as like word, description about cause, comment")
    private String pagePath;

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


    //User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "created_at_user_id", nullable = false, updatable = false)
    private Long createdAtUserId;

    @Column(name = "modified_at_user_id")
    private Long modifiedAtUserId;



    // Todo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_do_id", referencedColumnName = "to_do_id")
    private ToDo toDoId;

}
