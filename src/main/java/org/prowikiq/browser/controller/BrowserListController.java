package org.prowikiq.browser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.service.BrowserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class: BrowserListController Project: prowikiQ Package: org.prowikiq.browser.controller
 * <p>
 * Description: BrowserListController
 *
 * @author dong-hoshin
 * @date 4/29/24 21:01 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Api(tags = "Browser List Controller")
@RestController
@RequestMapping("/api/browser-lists")
public class BrowserListController {

    private final BrowserListService browserListService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public BrowserListController(BrowserListService browserListService, ResourceLoader resourceLoader) {
        this.browserListService = browserListService;
        this.resourceLoader = resourceLoader;
    }

    @PostMapping
    public ResponseEntity<BrowserList> createBrowserList(@RequestBody BrowserList browserList) {
        BrowserList created = browserListService.createBrowserList(browserList);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

  /*  @GetMapping("/{id}")
    public ResponseEntity<BrowserList> getBrowserList(@PathVariable Long id) {
        BrowserList browserList = browserListService.getBrowserList(id);
        return ResponseEntity.ok(browserList);
    }*/

    @GetMapping
    public ResponseEntity<List<BrowserList>> getAllBrowserLists() {
        List<BrowserList> lists = browserListService.getAllBrowserLists();
        return ResponseEntity.ok(lists);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<BrowserList> updateBrowserList(@PathVariable Long id, @RequestBody BrowserList browserList) {
        browserList.setPageId(id); // Ensure the ID is set correctly
        BrowserList updated = browserListService.updateBrowserList(browserList);
        return ResponseEntity.ok(updated);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrowserList(@PathVariable Long id) {
        browserListService.deleteBrowserList(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "가져오기", notes = "find ./Project_2023 -type d > ./directory_list.txt :::: Import directory list from a file.")
    @PostMapping("/import")
    public ResponseEntity<List<BrowserList>> importBrowserLists() {

            String resourcePath = "classpath:directory_list.csv";
            List<BrowserList> importedDtos = browserListService.importBrowserLists(resourcePath);
            return ResponseEntity.ok(importedDtos);

    }


}
