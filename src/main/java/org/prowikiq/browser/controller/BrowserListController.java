package org.prowikiq.browser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.service.BrowserListService;
import org.prowikiq.object.domain.entity.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<BrowserList> getBrowserList(@PathVariable Long id) {
        BrowserList browserList = browserListService.getBrowserList(id);
        return ResponseEntity.ok(browserList);
    }

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

    @ApiOperation(value = "가져오기", notes = "find ./Project_2023 -type d > ./directory_list.txt")
    @PostMapping("/import")
    public ResponseEntity<List<BrowserList>> importBrowserLists(@RequestParam("file") MultipartFile file) throws IOException {
        List<BrowserList> importedLists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 9) {
                    BrowserList browserList = new BrowserList();
                    FilePath filePath = new FilePath();
                    String pathOfFile = data[1].trim();
                    String[] pathParts = pathOfFile.split("/");
                    filePath.setPath(pathOfFile);
                    // Assuming the first column is pageId, second is filePath and so on
                    browserList.setPageId(Long.parseLong(data[0].trim()));
                    // Here you would set other properties like filePath, pageTitle, etc.
                    browserList.setFilePath(filePath);
                    //browserList.setPageTitle(data[2].trim());
                    String pageTitle = data[2].trim();
                    if (pageTitle.isEmpty()) {
                        // Extract the last part of the file path as the page title
                        pageTitle = pathParts[pathParts.length - 1];  // Last part of the path
                    }
                    browserList.setPageTitle(pageTitle);
                    browserList.setPageCategory(data[3].trim());
                    // Handle dates and booleans appropriately
                    // browserList.setTargetDay(LocalDateTime.parse(data[4].trim()));
                    // browserList.setFinishedDay(LocalDateTime.parse(data[5].trim()));
                    // browserList.setIsFolder(Boolean.parseBoolean(data[6].trim()));
                    // Determine if the path represents a folder or a file based on the presence of a period
                    String lastSegment = pathParts[pathParts.length - 1];
                    browserList.setIsFolder(!lastSegment.contains("."));  // True if no period (no file extension)
                    browserList.setCreatedAt(LocalDateTime.now());
                    browserList.setModifiedAt(LocalDateTime.now());

                    importedLists.add(browserListService.createBrowserList(browserList));
                }
            }
        }
        return ResponseEntity.ok(importedLists);
    }


}
