package org.prowikiq.browser.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.prowikiq.browser.domain.dto.BrowserListCreateDto;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;
import org.prowikiq.global.BaseEntity;
import org.prowikiq.object.domain.dto.FilePathCreateDto;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.repository.FilePathRepository;
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
    private FilePathRepository filePathRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

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
        List<BrowserList> importedLists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream(), StandardCharsets.UTF_8))) {
            reader.readLine(); // Skip header
            List<BrowserList> batchList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                BrowserList browserList = parseBrowserList(line);
                if (browserList != null) {
                    batchList.add(browserList);
                    if (batchList.size() >= 50) {
                        browserListRepository.saveAll(batchList);
                        importedLists.addAll(batchList);
                        batchList.clear();
                    }
                }
            }
            if (!batchList.isEmpty()) {
                browserListRepository.saveAll(batchList);
                importedLists.addAll(batchList);
            }
        } catch (IOException | DataAccessException e) {
            logger.error("Failed during import operation", e);
            throw new RuntimeException("Failed to import browser lists", e);
        }
        return importedLists;
    }

    private BrowserList parseBrowserList(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 9) {
            logger.error("Insufficient data in line: {}", line);
            return null;
        }
        try {
            BrowserListCreateDto dto = new BrowserListCreateDto();
            FilePathCreateDto filePathDto = new FilePathCreateDto();

            filePathDto.setFilePath(data[1].trim()); // Assuming FilePath constructor exists
            dto.setPageTitle(data[2].isEmpty() ? data[1].substring(data[1].lastIndexOf('/') + 1).trim() : data[2].trim());
            dto.setPageCategory(data[3].trim());
            dto.setIsFolder(data[1].trim().contains("."));
            //dto.setTargetDay(LocalDateTime.parse(data[4].trim())); // Assuming date exists and is valid
            //dto.setFinishedDay(LocalDateTime.parse(data[5].trim())); // Assuming date exists and is valid
//            dto.setCreatedAt(LocalDateTime.parse(data[7].trim())); // Parse CreatedAt
//            dto.setModifiedAt(LocalDateTime.parse(data[8].trim())); // Parse ModifiedAt

            FilePath filePath = ensureFilePath(filePathDto);
            BrowserList browserList = dto.toBrowserList();
            browserList.setFilePath(filePath); // Set the FilePath to BrowserList

            return dto.toBrowserList();

        } catch (Exception e) {
            logger.error("Error parsing line: {}. Error: {}", line, e.getMessage());
            return null;
        }
    }
    private FilePath ensureFilePath(FilePathCreateDto dto) {
        if (dto.getFilePathId() != null) {
            return filePathRepository.findByFilePathId(dto.getFilePathId()).orElseGet(() -> filePathRepository.save(dto.toFilePath()));
        } else {
            return filePathRepository.save(dto.toFilePath());
        }
    }

    /*private BrowserList parseBrowserList(String line) {
        String[] data = line.split(",", -1);
        if (data.length >= 9) {
            BrowserList browserList = new BrowserList();
            FilePath filePath = new FilePath();
            filePath.setPath(data[1].trim());
            browserList.setFilePath(filePath);

            String pageTitle = data[2].isEmpty() ? filePath.getPath().substring(filePath.getPath().lastIndexOf('/') + 1) : data[2].trim();
            *//*String pageTitle;
            if (data[2].isEmpty()) {
                if (filePath != null && filePath.getPath() != null) {
                    pageTitle = filePath.getPath().substring(filePath.getPath().lastIndexOf('/') + 1);
                } else {
                    pageTitle = "DefaultPageTitle"; // filePath가 null일 경우 기본 제목 제공 또는 해당 경우 처리
                }
            } else {
                pageTitle = data[2].trim();
            }*//*
            browserList.setPageTitle(pageTitle);
            browserList.setPageCategory(data[3].trim());
            browserList.setIsFolder(data[1].trim().contains("."));
            if (!data[4].trim().isEmpty()) {
                try {
                    browserList.setTargetDay(LocalDateTime.parse(data[4].trim()));
                } catch (DateTimeParseException e) {
                    // Handle or log the exception if the date is not parseable
                }
            }
            if (!data[5].trim().isEmpty()) {
                try {
                    browserList.setFinishedDay(LocalDateTime.parse(data[5].trim()));
                } catch (DateTimeParseException e) {
                    // Handle or log the exception if the date is not parseable
                }
            }
            //browserList.setTargetDay(LocalDateTime.parse(data[4].trim())); // Assume data is correct and parseable
            //browserList.setFinishedDay(LocalDateTime.parse(data[5].trim()));
            browserList.setCreatedAt(LocalDateTime.now());
            browserList.setModifiedAt(LocalDateTime.now());

            return browserList;
        }
        return null;
    }*/

}
