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
import org.prowikiq.global.WikiCommonEntity;
import org.prowikiq.object.domain.entity.FilePath;
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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "\"browserList\"")
@EntityListeners(AuditingEntityListener.class)
public class BrowserList extends WikiCommonEntity {

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

    //PagePath for Object ex; in Wiki's path of Linked word.
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "page_path_id", referencedColumnName = "file_path_id")
    private FilePath pagePathId;

    @Column(name = "page_path")
    private String pagePath;

    //ObjectPath for Object ex; in Server's path of file.
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "object_path_id", referencedColumnName = "file_path_id")
    private FilePath objectPathId;

    @Column(name = "object_path")
    private String objectPath;
}
