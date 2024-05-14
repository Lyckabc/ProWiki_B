package org.prowikiq.wiki.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.browser.domain.dto.BrowserListDto;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.object.service.StorageObjectService;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.todo.service.ToDoService;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.service.UserService;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.prowikiq.wiki.domain.repository.WikiPageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class: WikiPageService Project: prowikiQ Package: org.prowikiq.wiki.service
 * <p>
 * Description: WikiPageService
 *
 * @author dong-hoshin
 * @date 5/12/24 11:55 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
@RequiredArgsConstructor
public class WikiPageService {
    private final WikiPageRepository wikiPageRepository;
    private final StorageObjectService storageObjectService;
    private final UserService userService;
    private final ToDoService toDoService;

    Logger logger = LoggerFactory.getLogger(getClass());


    @Transactional
    public WikiPage getWikiPagefromId(Long wikiPageId) {
        Optional<WikiPage> wikiPage = wikiPageRepository.findByPageId(wikiPageId);

        return wikiPage.orElseThrow(() -> new NoSuchElementException("해당 pageId에 대한 저장 객체가 없습니다."));
    }

    @Transactional
    public WikiPage getWikiPagefromTitle(String wikiPageTitle) {
        Optional<WikiPage> wikiPage = wikiPageRepository.findByPageTitle(wikiPageTitle);

        return wikiPage.orElseThrow(() -> new NoSuchElementException("해당 pageId에 대한 저장 객체가 없습니다."));
    }

    @Transactional
    public WikiPageDto wikiConvertToDto(WikiPage wikiPage) {
        ToDoDto toDoDto;
        if (wikiPage.getToDoId() != null) {
            toDoDto = toDoService.toDoConvertToDto(wikiPage.getToDoId());
        } else {
            toDoDto = null;
        }
        StorageObjectDto storageObjectDto = storageObjectService.objectConvertToDto(wikiPage.getStorageObjectId());
        UserDto userDto = userService.userConvertToDto(wikiPage.getUserId());

        WikiPageDto dto =  WikiPageDto.builder()
            .pageId(wikiPage.getPageId())
            .pageTitle(wikiPage.getPageTitle())
            .pageContent(wikiPage.getPageContent())
            .pageCategory(wikiPage.getPageCategory())
            .pagePath(wikiPage.getPagePath())
            .createdAt(wikiPage.getCreatedAt())
            .modifiedAt(wikiPage.getModifiedAt())
            .storageObjectId(storageObjectDto != null ? storageObjectDto : null)
            .userId(userDto != null ? userDto : null)
            .createdAtUserId(wikiPage.getCreatedAtUserId())
            .modifiedAtUserId(wikiPage.getModifiedAtUserId())
            .toDoId(toDoDto)
            .build();

        return dto;
    }

    @Transactional
    public WikiPage createPage (WikiPageDto wDto,StorageObject object, User user,ToDo toDo) {
        //time
        LocalDateTime now = LocalDateTime.now();

        WikiPage page = WikiPage.builder()
            .pageTitle(wDto.getPageTitle())
            .pageCategory(wDto.getPageCategory())
            .pageContent(wDto.getPageContent())
            .pagePath(wDto.getPagePath())
            .createdAt(now)
            .modifiedAt(now)
            .latestedAt(now)
            .storageObjectId(object)
            .createdAtUserId(user.getUserId())
            .modifiedAtUserId(user.getUserId())
            .toDoId(toDo)
            .build();

        wikiPageRepository.save(page);
        return page;
    }

    @Transactional
    public WikiPageDto handleWikiPage
        (Long pageId, String pageTitle, String pageCategory, String pagePath,
            StorageObject object, User user,ToDo toDo) {
        WikiPage wikiPage;
        String objectPath = object.getObjectPath();
        if (pageTitle == null) {
            pageTitle = objectPath.substring
                (objectPath.lastIndexOf('/') + 1).trim();
            if (pageTitle.contains(".")) {
                pageTitle = pageTitle.substring(0, pageTitle.lastIndexOf('.'));
            }
        }
        if (pageId != null) {
            wikiPage = getWikiPagefromId(pageId);
            if (pageTitle != null)  {
                wikiPage.setPageTitle(pageTitle);
            } else if (pageTitle == null && objectPath != null) {
                wikiPage.setPageTitle(pageTitle);
            }
            if (pageCategory != null) wikiPage.setPageCategory(pageCategory);
            if (pagePath != null) wikiPage.setPagePath(pagePath);
        } else {
            wikiPage = createWikiPageFromImport
                (pageTitle, pageCategory, pagePath, object, user, toDo);
        }

        return wikiConvertToDto(wikiPage);
    }

    private WikiPage createWikiPageFromImport
        (String pageTitle, String pageCategory, String pagePath,
            StorageObject object, User user,ToDo toDo) {
        if (user == null) {
            logger.error("User information is required but not provided");
            return null; // Or throw an exception depending on your error handling strategy
        }
        LocalDateTime now = LocalDateTime.now();

        Long userId = user.getUserId();

        WikiPage newWikiPage = WikiPage.builder()
            .pageTitle(pageTitle)
            .pageCategory("")
            .pageContent("")
            .pagePath("")
            .createdAt(now)
            .modifiedAt(now)
            .latestedAt(now)
            .storageObjectId(object)
            .userId(user)
            .createdAtUserId(userId)
            .modifiedAtUserId(userId)
            .toDoId(toDo)
            .build();

        return wikiPageRepository.save(newWikiPage);
    }
}
