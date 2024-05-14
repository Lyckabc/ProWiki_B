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
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.prowikiq.global.BaseEntity;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.dto.WikiPageDto;

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
@Setter
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

    //object
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id", referencedColumnName = "object_id")
    private StorageObject storageObjectId;


    //User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "created_at_user_id")
    private Long createdAtUserId;

    @Column(name = "modified_at_user_id")
    private Long modifiedAtUserId;

    // Todo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_do_id", referencedColumnName = "to_do_id")
    private ToDo toDoId;

    public WikiPage update(WikiPageDto.Request request) {
        this.pageTitle = request.getPageTitle();
        this.pageContent = request.getPageContent();
        this.pageCategory = request.getPageCategory();
        this.pagePath = request.getPagePath();
        this.setModifiedAt(LocalDateTime.now());
        this.setLatestedAt(LocalDateTime.now());
        return this;
    }

    public WikiPageDto.Response toDto() {
        return WikiPageDto.Response.builder()
            .pageId(this.pageId)
            .pageTitle(this.pageTitle)
            .pageContent(this.pageContent)
            .pageCategory(this.pageCategory)
            .pagePath(this.pagePath)
            .createdAt(this.getCreatedAt())
            .modifiedAt(this.getModifiedAt())
            .latestedAt(this.getLatestedAt())
            .storageObjectId(this.storageObjectId != null ? this.storageObjectId.toDto() : null)
            .userId(this.userId != null ? this.userId.toDto() : null)
            .createdAtUserId(this.createdAtUserId)
            .modifiedAtUserId(this.modifiedAtUserId)
            .toDoId(this.toDoId != null ? this.toDoId.toDto() : null)
            .build();
    }

}
