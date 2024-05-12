package org.prowikiq.wiki.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.object.service.StorageObjectService;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.dto.WikiPageCreateDto;
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
    public WikiPageCreateDto getWikiPageDto(Long pageId) {
        WikiPage wikiPage = wikiPageRepository.findById(pageId)
            .orElseThrow(() -> new NoSuchElementException("WikiPage not found"));
        return convertToDto(wikiPage);
    }
    private WikiPageCreateDto convertToDto(WikiPage wikiPage) {
        return WikiPageCreateDto.builder()
            .pageId(wikiPage.getPageId())
            .pageTitle(wikiPage.getPageTitle())
            .pageContent(wikiPage.getPageContent())
            .pageCategory(wikiPage.getPageCategory())
            .pagePath(wikiPage.getPagePath())
            .createdAt(wikiPage.getCreatedAt())
            .modifiedAt(wikiPage.getModifiedAt())
            .storageObjectId(wikiPage.getStorageObjectId())
            .userId(Optional.ofNullable(wikiPage.getUserId()).map(User::getUserId).orElse(null))
            .createdAtUserId(wikiPage.getCreatedAtUserId())
            .modifiedAtUserId(wikiPage.getModifiedAtUserId())
            .requestUserId(wikiPage.getRequestUserId())
            .solvedUserId(wikiPage.getSolvedUserId())
            .toDoId(Optional.ofNullable(wikiPage.getToDoId()).map(ToDo::getToDoId).orElse(null))
            .build();
    }

    @Transactional
    public WikiPage createPage (String dtoPageTitle,String dtoPageCategory, User user,
        StorageObject object, ToDo toDo) {
        //time
        LocalDateTime now = LocalDateTime.now();

        WikiPage page = WikiPage.builder()
            .pageTitle(dtoPageTitle)
            .pageCategory(dtoPageCategory)
            .pageContent()
//            .pagePath()
            .createdAt(now)
            .modifiedAt(now)
            .latestedAt(now)
            .storageObjectId(object)
            .userId(user)
            .createdAtUserId(user.getUserId())
            .modifiedAtUserId(user.getUserId())
            .toDoId(toDo)
            .build();
        wikiPageRepository.save(page);
        return page;
    }
}
