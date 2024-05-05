package org.prowikiq.wiki.entity;

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
@Builder
@Entity
@Table(name = "\"wikipage\"")
public class WikiPage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pageId", nullable = false)
    private Long pageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "filePathId", referencedColumnName = "filePathId")
    private FilePath filePath;

    @Column(name = "pageTitle", length = 255)
    private String pageTitle;

    @Column(name = "pageContent", columnDefinition = "TEXT")
    private String pageContent;

    @Column(name = "pageCategory", length = 255)
    private String pageCategory;

    @Column(name = "pageFilePath", columnDefinition = "TEXT")
    private String pageFilePath;

    // Getters and Setters
}
