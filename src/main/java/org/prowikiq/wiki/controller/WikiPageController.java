package org.prowikiq.wiki.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.dto.UserSignDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.prowikiq.wiki.service.WikiPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class: WikiPageController Project: prowikiQ Package: org.prowikiq.wiki.controller
 * <p>
 * Description: WikiPageController
 *
 * @author dong-hoshin
 * @date 5/13/24 03:15 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Api(tags = "wiki Controller")
@RestController
@RequestMapping("/wikiPages")
@RequiredArgsConstructor
public class WikiPageController {
    private final WikiPageService wikiPageService;


    @GetMapping("/{id}")
    public ResponseEntity<WikiPage> getWikiPage(@PathVariable Long id) {
        WikiPage wikiPage = wikiPageService.getWikiPagefromId(id);
        return ResponseEntity.ok(wikiPage);
    }

    @ApiOperation(value = "Page write", notes = "WikiPage 작성")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody WikiPageDto wDto,
        StorageObject object, User user, ToDo toDo) {
        wikiPageService.createPage(wDto, object, user, toDo);
        return ResponseEntity.ok("WikiPage create successfully");
    }

}
