package org.prowikiq.wiki.domain.dto;

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

/**
 * Class: WikiPageCreateDto Project: prowikiQ Package: org.prowikiq.wiki.domain.dto
 * <p>
 * Description: WikiPageCreateDto
 *
 * @author dong-hoshin
 * @date 5/7/24 19:45 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WikiPageCreateDto {
    private String pageTitle;
    private String pageContent;
    private String pageCategory;
    private FilePath pagePathId;
    private String pagePath;

    // WikiCommonEntity
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime latestedAt;

    //Object
    private StorageObject storageObjectId;
    private String objectTitle;
    private Boolean isFolder;

    //User
    private User userId;
    private String createdAtUser;
    private String modifiedAtUser;
    private String requestUser;
    private String solvedUser;

    //To Do
    private ToDo toDoId;
    private LocalDateTime targetDay;
    private LocalDateTime finishedDay;

}
