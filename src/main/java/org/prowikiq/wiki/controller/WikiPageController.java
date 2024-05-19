package org.prowikiq.wiki.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.prowikiq.wiki.service.WikiPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/wiki-pages")
@RequiredArgsConstructor
public class WikiPageController {
    private final WikiPageService wikiPageService;

    @ApiOperation(value = "Get Wiki Page by ID", notes = "Retrieve a specific WikiPage by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<WikiPage> getWikiPage(@PathVariable Long id) {
        WikiPage wikiPage = wikiPageService.getWikiPagefromId(id);
        return ResponseEntity.ok(wikiPage);
    }

    @ApiOperation(value = "Page write", notes = "WikiPage 작성")
    @PostMapping("/")
    public ResponseEntity<String> createWikiPage(@RequestBody WikiPageDto.Request wDto,
                                            @RequestParam(required = false) StorageObject object,
                                            @RequestParam(required = false) ToDo toDo,
                                            HttpServletRequest request) {
        wikiPageService.createPage(wDto, object, toDo, request);
        return ResponseEntity.ok("WikiPage create successfully");
    }

    @ApiOperation(value = "Update Wiki Page", notes = "Update an existing WikiPage by its ID")
    @PutMapping("/{id}")
    public WikiPageDto.Response updateWikiPage(@PathVariable(name = "id") Long pageId,
                                        @RequestBody WikiPageDto.Request request) {
        return wikiPageService.modifyPage(pageId, request);
    }

}
