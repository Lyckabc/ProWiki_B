package org.prowikiq.browser.domain.dto;


import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.prowikiq.browser.domain.entity.BrowserList;

import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.Object;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.entity.WikiPage;

/**
 * Class: BrowserListCreateDto Project: prowikiQ Package: org.prowikiq.browser.domain.dto
 * <p>
 * Description: BrowserListCreateDto
 *
 * @author dong-hoshin
 * @date 4/29/24 21:16 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrowserListCreateDto {

    //page
    private WikiPage pageId;
    private String pageTitle;
    private String pageCategory;


    //BaseEntity
    private LocalDateTime createdAt; // Include createdAt
    private LocalDateTime modifiedAt; // Include modifiedAt


    //filePath
    private FilePath filePathId;
    private String filePath;

    //object
    private Object objectId;
    private Boolean isFolder;

    //User
    private User userId;
    private String createdAtUser;
    private String modifiedAtUser;

    // Additional user-related fields for todo functionality
    private String requestUser;
    private String solvedUser;

    // Todo
    private ToDo toDoId;
    private LocalDateTime targetDay;
    private LocalDateTime finishedDay;




 /*   public BrowserList toBrowserList() {
        BrowserList browserList = new BrowserList();
        browserList.setFilePath(this.filePath);
        browserList.setPageTitle(this.pageTitle);
        browserList.setPageCategory(this.pageCategory);
//        browserList.setTargetDay(this.targetDay);
//        browserList.setFinishedDay(this.finishedDay);
        browserList.setIsFolder(this.isFolder);
//        browserList.setCreatedAt(this.createdAt);
//        browserList.setModifiedAt(this.modifiedAt);
        return browserList;
    }*/

}
