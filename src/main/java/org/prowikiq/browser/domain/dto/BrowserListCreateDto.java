package org.prowikiq.browser.domain.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.entity.WikiPage;

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
//    private String pageTitle;
//    private String pageCategory;
    private FilePath pagePathId;
//    private String pagePath;

    //BaseEntity
    private LocalDateTime createdAt; // Include createdAt
    private LocalDateTime modifiedAt; // Include modifiedAt
    private LocalDateTime latestedAt;


    //object
    private StorageObject storageObjectId;
//    private String objectName;
//    private Boolean isFolder;
    private FilePath objectPathId;
//    private String objectPath;


    //User
    private User userId;
    private Long createdAtUserId;
    private Long modifiedAtUserId;

    // Additional user-related fields for todo functionality
    private Long requestUserId;
    private Long solvedUserId;

    // Todo
    private ToDo toDoId;
//    private LocalDateTime targetDay;
//    private LocalDateTime finishedDay;


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
