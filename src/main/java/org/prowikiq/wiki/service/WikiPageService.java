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
    public WikiPage createPageFromImport(BrowserListDto bDto) {
        WikiPageDto wikiPageDto = bDto.getPageId();
        UserDto user = bDto.getUserId();
        StorageObjectDto storageObject = bDto.getStorageObjectId();
        ToDoDto toDo = bDto.getToDoId();

        // Creating page based on provided DTO
        WikiPage page = WikiPage.builder()
            .pageTitle(wikiPageDto.getPageTitle())
            .pageCategory(wikiPageDto.getPageCategory())
            .pageContent("")
            .pagePath(bDto.getPagePath())
            .userId(user)
            .storageObjectId(storageObject)
            .createdAt(bDto.getCreatedAt())
            .modifiedAt(bDto.getModifiedAt())
            .toDoId(toDo)
            .build();

        return wikiPageRepository.save(page);
    }
    @Transactional
    public WikiPageDto wikiConvertToDto(WikiPage wikiPage) {
        StorageObjectDto storageObjectDto = storageObjectService.objectConvertToDto(wikiPage.getStorageObjectId());
        UserDto userDto = userService.userConvertToDto(wikiPage.getUserId());
        ToDoDto toDoDto = toDoService.toDoConvertToDto(wikiPage.getToDoId());

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
            .toDoId(toDoDto != null ? toDoDto : null)
            .build();

        return dto;
    }

    @Transactional
    public WikiPage createPage (WikiPageDto wDto, User user) {
        //time
        LocalDateTime now = LocalDateTime.now();
        String pageContent = "";
        String pagePath = "";

        WikiPageDto pageDto = WikiPageDto.builder()
            .pageTitle(wDto.getPageTitle())
            .pageCategory(wDto.getPageCategory())
            .pageContent(wDto.getPageContent())
            .pagePath(wDto.getPagePath())
            .createdAt(now)
            .modifiedAt(now)
            .latestedAt(now)
            .storageObjectId(wDto.getStorageObjectId() != null ? wDto.getStorageObjectId() : null)
            .createdAtUserId(user.getUserId())
            .modifiedAtUserId(user != null ? user.getUserId() : null)
            .toDoId(wDto.getToDoId() != null ? wDto.getToDoId() : null)
            .build();

        WikiPage page = createPageFromImport(pageDto);
        wikiPageRepository.save(page);
        return page;
    }
}
