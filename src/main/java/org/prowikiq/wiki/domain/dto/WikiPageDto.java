package org.prowikiq.wiki.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // WikiCommonEntity
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime latestedAt;

    //Object
    private Long storageObjectId;

    //User
//    private Long userId;
    private Long createdAtUserId;
    private Long modifiedAtUserId;


    //To Do
    private Long toDoId;

    /*public static WikiPageCreateDto fromEntity(WikiPage wikiPage) {
        return WikiPageCreateDto.builder()
            .pageId(wikiPage.getPageId())
            .pageTitle(wikiPage.getPageTitle())
            .pageContent(wikiPage.getPageContent())
            .pageCategory(wikiPage.getPageCategory())
            .pagePathId(wikiPage.getPagePathId() != null ? wikiPage.getPagePathId().getFilePathId() : null)
            .createdAt(wikiPage.getCreatedAt())
            .modifiedAt(wikiPage.getModifiedAt())
            .latestedAt(wikiPage.getLatestedAt())
            .storageObjectId(wikiPage.getStorageObjectId() != null ? wikiPage.getStorageObjectId().getObjectId() : null)
            .userId(wikiPage.getUserId() != null ? wikiPage.getUserId().getUserId() : null)
            .createdAtUserId(wikiPage.getCreatedAtUserId())
            .modifiedAtUserId(wikiPage.getModifiedAtUserId())
            .requestUserId(wikiPage.getRequestUserId())
            .solvedUserId(wikiPage.getSolvedUserId())
            .toDoId(wikiPage.getToDoId() != null ? wikiPage.getToDoId().getToDoId() : null)
            .build();
    }*/

}
