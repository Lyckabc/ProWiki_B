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
import org.prowikiq.global.WikiCommonEntity;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.user.domain.entity.User;
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
public class BrowserList extends WikiCommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "browserListId", nullable = false)
    private Long browserListId;

    //Page

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pageId", referencedColumnName = "pageId")
    private FilePath pageId;

    @Column(name = "pageTitle")
    private String pageTitle;

    @Column(name = "pageCategory")
    private String pageCategory;

}
