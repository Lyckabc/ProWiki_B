package org.prowikiq.browser.domain.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.dto.WikiPageDto;

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
public class BrowserListDto {
    private Long browserListId;
    //page
    private WikiPageDto pageId;

    //object
    private StorageObjectDto storageObjectId;

    //User
    private UserDto userId;

    // Todo
    private ToDoDto toDoId;

    //
    //private String status;

    //BaseEntity
    private LocalDateTime createdAt; // Include createdAt
    private LocalDateTime modifiedAt; // Include modifiedAt
    private LocalDateTime latestedAt;

}
