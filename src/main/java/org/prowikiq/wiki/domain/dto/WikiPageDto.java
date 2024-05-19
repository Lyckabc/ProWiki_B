package org.prowikiq.wiki.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.wiki.domain.entity.WikiPage;

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
public class WikiPageDto {

    private Long pageId;
    private String pageTitle;
    private String pageContent;
    private String pageCategory;
    private String pagePath;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime latestedAt;
    private StorageObjectDto storageObjectId;
    private UserDto userId;
    private Long createdAtUserId;
    private Long modifiedAtUserId;
    private ToDoDto toDoId;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String pageTitle;
        private String pageContent;
        private String pageCategory;
        private String pagePath;

        public WikiPage toEntity() {
            return WikiPage.builder()
                .pageTitle(pageTitle)
                .pageContent(pageContent)
                .pageCategory(pageCategory)
                .pagePath(pagePath)
                .build();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long pageId;
        private String pageTitle;
        private String pageContent;
        private String pageCategory;
        private String pagePath;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private LocalDateTime latestedAt;
        private StorageObjectDto storageObjectId;
        private UserDto userId;
        private Long createdAtUserId;
        private Long modifiedAtUserId;
        private ToDoDto toDoId;
    }


}
