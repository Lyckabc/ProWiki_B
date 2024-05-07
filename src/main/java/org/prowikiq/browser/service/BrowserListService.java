package org.prowikiq.browser.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

    private FilePathRepository filePathRepository;
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
            BrowserListCreateDto dto = new BrowserListCreateDto();
            FilePath filePath = createFilePath(data[1].trim()); // OS의 FilePath를 가져와 filePathDto 생성
            // data[0].trim() => Page가 있을 경우(PageId)를 가져와 기존 D.B값을 연결
            dto.setPageTitle(data[2].isEmpty() ? data[1].substring(data[1].lastIndexOf('/') + 1).trim() : data[2].trim()); // Page가 있을 경우 Page이름을 가져오고, 없을 경우 FilePath를 통해서 끝 데이터 즉, 파일 혹은 폴더명 입력
            dto.setPageCategory(data[3].trim()); // PageCategory를 가져와 BrowserListDto에 입력
            dto.setIsFolder(!data[1].substring(data[1].lastIndexOf('/') + 1).trim().contains(".")); // Point(.)가 들어있는 경우 파일이기때문에 .이 없을(!) 경우 true contains 경우 false
            //dto.setTargetDay(LocalDateTime.parse(data[4].trim())); // Assuming date exists and is valid
            //dto.setFinishedDay(LocalDateTime.parse(data[5].trim())); // Assuming date exists and is valid
//            dto.setCreatedAt(LocalDateTime.parse(data[7].trim())); // Parse CreatedAt
//            dto.setModifiedAt(LocalDateTime.parse(data[8].trim())); // Parse ModifiedAt



            BrowserList browserList = dto.toBrowserList();
            browserList.setFilePath(filePath); // Set the FilePath to BrowserList

            return dto.toBrowserList();

        } catch (Exception e) {
            logger.error("Error parsing line: {}. Error: {}", line, e.getMessage());
            return null;
        }
    }
    @Transactional
    public FilePath createFilePath(String dto) {
        FilePathCreateDto filePathDto = new FilePathCreateDto();
        filePathRepository.save(filePathDto.setFilePath(dto));

            return filePathRepository.save(filePathDto.setFilePath(dto));

    }

}
