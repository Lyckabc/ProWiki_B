package org.prowikiq.wiki.entity;

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
import org.prowikiq.global.WikiCommonEntity;
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
@SuperBuilder
@Entity
@Table(name = "\"wikipage\"")
public class WikiPage extends WikiCommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id", nullable = false)
    private Long pageId;

    @Column(name = "page_content")
    private String pageContent;

    /*@Column(name = "page_path")
    private String pagePath;*/

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name = "page_category")
    private String pageCategory;

}
