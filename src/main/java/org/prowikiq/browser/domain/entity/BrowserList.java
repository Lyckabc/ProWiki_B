package org.prowikiq.browser.domain.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prowikiq.global.BaseEntity;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.wiki.entity.WikiPage;


/**
 * Class: BrowserList Project: prowikiQ Package: org.prowikiq.browser.domain.entity
 * <p>
 * Description: BrowserList Entity
 *
 * @author dong-hoshin
 * @date 4/29/24 15:23 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"browserlist\"")
public class BrowserList extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "browserListId", nullable = false)
    private Long browserListId;

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "filePathId", referencedColumnName = "filePathId")
    private FilePath filePathId;*/

    @Column(name = "filePath", columnDefinition = "TEXT")
    private String filePath;

    @Column(name = "pageTitle", columnDefinition = "TEXT")
    private String pageTitle;

    @Column(name = "pageCategory", columnDefinition = "TEXT")
    private String pageCategory;

    @Column(name = "targetDay")
    private LocalDateTime targetDay;

    @Column(name = "finishedDay")
    private LocalDateTime finishedDay;

    @Column(name = "isFolder")
    private Boolean isFolder;

}
