package org.prowikiq.browser.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.prowikiq.browser.domain.dto.BrowserListCreateDto;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;

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
@RequiredArgsConstructor
public class BrowserListService {
    private final BrowserListRepository browserListRepository;
    private final ResourceLoader resourceLoader;
    private final FilePathRepository filePathRepository;

    Logger logger = LoggerFactory.getLogger(getClass());


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

        if (data.length < 9) {
            logger.error("Insufficient data in line: {}", line);
            return null;
        }
        try {
            //BaseEntity about time
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // String to LocalDateTime format
            LocalDateTime now = LocalDateTime.now(); // For createdAt and modifiedAt
            //browserListId
            //Page
            String titleOfPage = data[1].isEmpty() ? data[7].substring(data[7].lastIndexOf('/') + 1).trim() : data[1].trim(); // Page id가 있을 경우 Page이름을 가져오고, 없을 경우 FilePath를 통해서 끝 데이터 즉, 파일 혹은 폴더명 입력

            //Time
            LocalDateTime atCreated = data[7].isEmpty() ? now : LocalDateTime.parse(data[7].trim(), formatter);
            LocalDateTime atModified = data[8].isEmpty() ? now : LocalDateTime.parse(data[8].trim(), formatter);

            //FilePath
            Long filePathId = ;
            FilePath filePathET = data[1].isEmpty() ? createFilePath(data[1].trim()): ; // OS의 FilePath를 가져와 filePathDto 생성 and filePathRepository 저장
            String pathOfFile = filePathET.getFilePath();


            String categoryOfPage = data[3].trim(); // PageCategory를 가져와 BrowserListDto에 입력
            LocalDateTime dayOfTarget = data[4].isEmpty() ? null : LocalDateTime.parse(data[4].trim(), formatter); //targetDay를 가져와 입력
            LocalDateTime dayOfFinished = data[5].isEmpty() ? null : LocalDateTime.parse(data[5].trim(), formatter); //finishedDay를 가져와 입력
            Boolean chkFolder = !data[1].substring(data[1].lastIndexOf('/') + 1).trim().contains("."); // Point(.)가 들어있는 경우 파일이기때문에 .이 없을(!) 경우 true contains 경우 false


            BrowserList browserList = BrowserList.builder()
                                                .filePathId(filePathET)
                                                .filePath(pathOfFile)
                                                .pageTitle(titleOfPage)
                                                .pageCategory(categoryOfPage)
                                                .targetDay(dayOfTarget)
                                                .finishedDay(dayOfFinished)
                                                .isFolder(chkFolder)
                .createdAt(atCreated)
                .modifiedAt(atModified)
                                                .build();

            return browserList;

        } catch (Exception e) {
            logger.error("Error parsing line: {}. Error: {}", line, e.getMessage());
            return null;
        }
    }
    public FilePath createFilePath(String dto) {
        FilePath filePath = FilePath.builder()
                                    .filePath(dto)
                                    .build();
        filePathRepository.save(filePath);
            return filePath;
    }

}
