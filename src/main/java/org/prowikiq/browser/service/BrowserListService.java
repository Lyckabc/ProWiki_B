package org.prowikiq.browser.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;
import org.prowikiq.object.domain.entity.FilePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
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
    public List<BrowserList> importBrowserLists(String resourcePath) {
        Logger logger = LoggerFactory.getLogger(getClass());
        List<BrowserList> importedLists = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream(), StandardCharsets.UTF_8));
            reader.readLine(); // Skip header
            List<BrowserList> batchList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                BrowserList browserList = parseBrowserList(line);
                if (browserList != null) {
                    batchList.add(browserList);
                    if (batchList.size() >= 50) { // Batch size of 50, adjust based on your environment
                        if (browserList.getBrowserListId() == null) {
                            logger.error("browser_list_id is null for browserList: " + browserList);
                        }
                        browserListRepository.saveAll(batchList);
                        logger.info("Batch of 50 browser lists saved successfully");
                        importedLists.addAll(batchList);
                        batchList.clear(); // Clear the batch list for next batch
                    }
                }
            }
            if (!batchList.isEmpty()) {
                browserListRepository.saveAll(batchList);
                logger.info("Final batch of {} browser lists saved successfully", batchList.size());
                importedLists.addAll(batchList);
            }
        } catch (IOException e) {
            logger.error("Failed to read file", e);
            throw new RuntimeException("Failed to read file", e);
        } catch (DataAccessException e) {
            logger.error("Failed to save browser lists", e);
            throw new RuntimeException("Failed to save browser lists", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("Failed to close reader", e);
                }
            }
        }
        return importedLists;
    }

    private BrowserList parseBrowserList(String line) {
        String[] data = line.split(",", -1);
        if (data.length >= 9) {
            BrowserList browserList = new BrowserList();
            FilePath filePath = new FilePath();
            filePath.setPath(data[1].trim());
            browserList.setFilePath(filePath);

            String pageTitle = data[2].isEmpty() ? filePath.getPath().substring(filePath.getPath().lastIndexOf('/') + 1) : data[2].trim();
            /*String pageTitle;
            if (data[2].isEmpty()) {
                if (filePath != null && filePath.getPath() != null) {
                    pageTitle = filePath.getPath().substring(filePath.getPath().lastIndexOf('/') + 1);
                } else {
                    pageTitle = "DefaultPageTitle"; // filePath가 null일 경우 기본 제목 제공 또는 해당 경우 처리
                }
            } else {
                pageTitle = data[2].trim();
            }*/
            browserList.setPageTitle(pageTitle);
            browserList.setPageCategory(data[3].trim());
            browserList.setIsFolder(data[1].trim().contains("."));
            //browserList.setTargetDay(LocalDateTime.parse(data[4].trim())); // Assume data is correct and parseable
            //browserList.setFinishedDay(LocalDateTime.parse(data[5].trim()));
            browserList.setCreatedAt(LocalDateTime.now());
            browserList.setModifiedAt(LocalDateTime.now());

            return browserList;
        }
        return null;
    }

    /*@Transactional
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
    }*/

}
