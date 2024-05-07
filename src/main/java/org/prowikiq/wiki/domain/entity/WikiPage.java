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
@Builder
@Entity
@Table(name = "\"wikipage\"")
public class WikiPage extends WikiCommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pageId", nullable = false)
    private Long pageId;

    @Column(name = "pageContent")
    private String pageContent;

    /*@Column(name = "pagePath")
    private String pagePath;*/

    @Column(name = "pageTitle")
    private String pageTitle;

    @Column(name = "pageCategory")
    private String pageCategory;

}
