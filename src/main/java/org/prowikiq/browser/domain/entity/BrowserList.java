package org.prowikiq.browser.domain.entity;

import java.sql.Timestamp;
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
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "filePathId", referencedColumnName = "filePathId")
<<<<<<< HEAD
    private FilePath filePathId;

    @Column(name = "filePath", columnDefinition = "TEXT")
    private String filePath;
=======
    private FilePath filePath;
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72

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


<<<<<<< HEAD
=======
    // Getters and Setters

    public Long getBrowserListId() { return browserListId; }

    public void setbrowserListId(Long pageId) {
        this.browserListId = browserListId;
    }

    public FilePath getFilePath() {
        return filePath;
    }

    public void setFilePath(FilePath filePath) {
        this.filePath = filePath;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageCategory() {
        return pageCategory;
    }

    public void setPageCategory(String pageCategory) {
        this.pageCategory = pageCategory;
    }

    public LocalDateTime getTargetDay() {
        return targetDay;
    }

    public void setTargetDay(LocalDateTime targetDay) {
        this.targetDay = targetDay;
    }

    public LocalDateTime getFinishedDay() {
        return finishedDay;
    }

    public void setFinishedDay(LocalDateTime finishedDay) {
        this.finishedDay = finishedDay;
    }

    public Boolean getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72
}
