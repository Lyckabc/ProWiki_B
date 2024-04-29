package org.prowikiq.browser.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.service.BrowserListService;
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

    @PostMapping("/import")
    public ResponseEntity<List<BrowserList>> importBrowserLists(@RequestParam("file") MultipartFile file) throws IOException {
        List<BrowserList> importedLists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                BrowserList browserList = parseBrowserList(line);
                if (browserList != null) {
                    importedLists.add(browserListService.createBrowserList(browserList));
                }
            }
        }
        return ResponseEntity.ok(importedLists);
    }

    private BrowserList parseBrowserList(String line) {
        // Example: Assuming CSV format: getFilePath,pageTitle,pageCategory,targetDay,finishedDay,isFolder
        String[] data = line.split(",");
        if (data.length < 5) {
            return null; // Not enough data to form a BrowserList
        }
        BrowserList browserList = new BrowserList();
        browserList.setFilePath(data[0].trim());
        browserList.setPageTitle(data[1].trim());
        browserList.setPageCategory(data[2].trim());
        browserList.setTargetDay(Timestamp.valueOf(data[3].trim()));
        browserList.setFinishedDay(Timestamp.valueOf(data[4].trim()));
        browserList.setIsFolder(Boolean.parseBoolean(data[5].trim()));
        return browserList;
    }

}
