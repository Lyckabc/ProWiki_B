package org.prowikiq.browser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.service.BrowserListService;
import org.prowikiq.object.domain.entity.FilePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/api/browserlists")
public class BrowserListController {

    private final BrowserListService browserListService;

    @Autowired
    public BrowserListController(BrowserListService browserListService) {
        this.browserListService = browserListService;
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
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "DB/directory_list - Sheet1.csv");
        Stream<String> resourcePath = Files.lines(file.toPath());
        List<BrowserList> importedLists = browserListService.importBrowserLists(resourcePath);
        return ResponseEntity.ok(importedLists);
    }


}
