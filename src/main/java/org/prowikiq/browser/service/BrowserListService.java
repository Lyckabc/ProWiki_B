package org.prowikiq.browser.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;
import org.prowikiq.object.domain.entity.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Class: BrowserListService Project: prowikiQ Package: org.prowikiq.browser.service
 * <p>
 * Description: BrowserListService
 *
 * @author dong-hoshin
 * @date 4/29/24 20:49 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
public class BrowserListService {
    private final BrowserListRepository browserListRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public BrowserListService(BrowserListRepository browserListRepository, ResourceLoader resourceLoader) {
        this.browserListRepository = browserListRepository;
        this.resourceLoader = resourceLoader;
    }

    @Transactional
    public BrowserList createBrowserList(BrowserList browserList) {
        return browserListRepository.save(browserList);
    }

    /*@Transactional
    public BrowserList importBrowserLists(BrowserList browserList)*/

    @Transactional(readOnly = true)
    public BrowserList getBrowserList(Long pageId) {
        return browserListRepository.findById(pageId).orElseThrow(() -> new RuntimeException("BrowserList not found"));
    }

    @Transactional(readOnly = true)
    public List<BrowserList> getAllBrowserLists() {
        return browserListRepository.findAll();
    }

    @Transactional
    public BrowserList updateBrowserList(BrowserList browserList) {
        return browserListRepository.save(browserList);
    }

    @Transactional
    public void deleteBrowserList(Long pageId) {
        browserListRepository.deleteById(pageId);
    }

    @Transactional
    public BrowserList createAndReturnPageId(BrowserList browserList) {
        // First save to generate the pageId
        browserList = browserListRepository.save(browserList);
        return browserList;
    }

    public List<BrowserList> importBrowserLists(String resourcePath) {
        List<BrowserList> importedLists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream(), StandardCharsets.UTF_8))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);
                if (data.length >= 9) {
                    BrowserList browserList = new BrowserList();
                    FilePath filePath = new FilePath();
                    filePath.setPath(data[1].trim());
                    browserList.setFilePath(filePath);

                    // Save the entity to generate pageId
                    browserList = createAndReturnPageId(browserList);

                    // Continue setting other properties after obtaining the pageId
                    String pageTitle = data[2].isEmpty() ? filePath.getPath().substring(filePath.getPath().lastIndexOf('/') + 1) : data[2].trim();
                    browserList.setPageTitle(pageTitle);
                    browserList.setPageCategory(data[3].trim());
                    browserList.setIsFolder(!data[1].contains("."));
                    browserList.setTargetDay(LocalDateTime.parse(data[4].trim())); // Assume data is correct and parseable
                    browserList.setFinishedDay(LocalDateTime.parse(data[5].trim()));
                    browserList.setCreatedAt(LocalDateTime.now());
                    browserList.setModifiedAt(LocalDateTime.now());

                    // Final save with all properties set
                    browserList = browserListRepository.save(browserList);
                    importedLists.add(browserList);
                }
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
            throw new RuntimeException("Failed to import browser lists", e);
        }
        return importedLists;
    }


    /*public ResponseEntity<List<BrowserList>> importBrowserLists() throws IOException {
        List<BrowserList> importedLists = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:DB/directory_list - Sheet1.csv");

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/DB/directory_list - Sheet1.csv"))) {
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
    }*/

}
