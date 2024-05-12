package org.prowikiq.browser.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;

import org.prowikiq.object.domain.dto.FilePathCreateDto;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.object.domain.repository.FilePathRepository;

import org.prowikiq.object.domain.repository.StorageObjectRepository;
import org.prowikiq.object.service.StorageObjectService;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.prowikiq.wiki.domain.dto.WikiPageBriefDTO;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.prowikiq.wiki.domain.repository.WikiPageRepository;
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
    private final FilePathRepository filePathRepository;
    private final StorageObjectService storageObjectService;
    private final StorageObjectRepository storageObjectRepository;
    private final WikiPageRepository wikiPageRepository;
    private final UserRepository userRepository;

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
        Long userId = Long.parseLong("1");
        if (data.length < 17) {
            logger.error("Insufficient data in line: {}", line);
            return null;
        }
        try {
            //BaseEntity about time
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // String to LocalDateTime format
            LocalDateTime now = LocalDateTime.now(); // For createdAt and modifiedAt
            //browserListId
            //Page id, title, Catgory, pagePathId(not made path link), pagePath(not made path link)
            String categoryOfPage = data[3].isEmpty() ? null : data[3].trim();
            String titleOfPage = data[2].isEmpty() ? data[7].substring(data[7].lastIndexOf('/') + 1).trim() : wikiPageRepository.findByPageId(Long.parseLong(data[1].trim())); // Page id가 없을 경우 Page를 통해서 끝 데이터 즉, 파일 혹은 폴더명 입력, 있을 경우 Page이름을 가져옴
            if (titleOfPage.contains(".")) {
                titleOfPage = titleOfPage.substring(0, titleOfPage.lastIndexOf('.'));
            }

            // PageCategory를 가져와 BrowserListDto에 입력

            //Time
            LocalDateTime atCreated = data[4].isEmpty() ? now : LocalDateTime.parse(data[4].trim(), formatter);
            LocalDateTime atModified = data[5].isEmpty() ? now : LocalDateTime.parse(data[5].trim(), formatter);

            //Object
            StorageObject objectET = data[8].isEmpty() ? createObject(data[7].trim()) : storageObjectService.getStorageObject(Long.parseLong(data[8].trim()));
            Boolean chkFolder = objectET.getIsFolder();
            String objectPath = objectET.getObjectPath();
            //File
            String nameOfFile = data[1].isEmpty() ? data[7].substring(data[7].lastIndexOf('/') + 1).trim() : data[1].trim(); // Object id가 없을 경우 ObjectFilePath를 통해서 끝 데이터 즉, 파일 혹은 폴더명 입력, 있을 경우 file이름을 가져옴
            String filePath = data[6].isEmpty() ? null : data[6].trim() ; // filePathId 가 없을 경우 import,  있으면 OS의 FilePath를 가져옴


            //User -> 5.권한 적용시키기 할 때 HTTP(JWT) data import -> transferTokenToUser 적용
            Long idOfUserLong = data[10].isEmpty() ? userId : Long.parseLong(data[10].trim());
            User idOfUser = userRepository.getById(idOfUserLong);
            Long userOfCreatedAt = data[11].isEmpty() ? idOfUser.getUserId() : Long.parseLong(data[11].trim());
            Long userOfModifiedAt = data[12].isEmpty() ? idOfUser.getUserId() : Long.parseLong(data[12].trim());
            Long userOfRequest = data[13].isEmpty() ? idOfUser.getUserId() : Long.parseLong(data[13].trim());
//            Long userOfsolver = data[14].isEmpty() ? idOfUser.getUserId() : Long.parseLong(data[14].trim());

            //Todo
//            ToDo idOfToDo = data[15].isEmpty() ? null : null;
//            LocalDateTime dayOfTarget = data[16].isEmpty() ? idOfToDo.getTargetDay() : LocalDateTime.parse(data[16].trim(), formatter); //targetDay를 가져와 입력
//            LocalDateTime dayOfFinished = data[17].isEmpty() ? idOfToDo.getFinishedDay() : LocalDateTime.parse(data[17].trim(), formatter); //finishedDay를 가져와 입력

            WikiPage page =  data[1].isEmpty() ? createPage(titleOfPage, categoryOfPage, idOfUser) : wikiPageRepository.findByPageId(Long.parseLong(data[1].trim())).orElseThrow();


            BrowserList browserList = BrowserList.builder()
                                                .pageId(page)
                                                .pageTitle(titleOfPage)
                                                .pageCategory(categoryOfPage)
                                                .createdAt(atCreated)
                                                .modifiedAt(atModified)
                                                .objectPath(filePath)
                                                .storageObjectId(objectET)
                                                .isFolder(chkFolder)
                                                .userId(idOfUser)
                                                .createdAtUserId(userOfCreatedAt)
                                                .modifiedAtUserId(userOfModifiedAt)
                                                .requestUserId(userOfRequest)
//                                                .solvedUserId(userOfsolver)
//                                                .toDoId(idOfToDo)
//                                                .targetDay(dayOfTarget)
//                                                .finishedDay(dayOfFinished)
                                                .build();

            return browserList;

        } catch (Exception e) {
            logger.error("Error parsing line: {}. Error: {}", line, e.getMessage());
            return null;
        }
    }

    private StorageObject createObject(String dtoPath) {
        String Name = dtoPath.substring(dtoPath.lastIndexOf('/') + 1).trim();
        boolean chkFolder = !Name.contains(".");

        List<StorageObject> objects = storageObjectRepository.findByObjectPath(dtoPath);
        if (objects.isEmpty()) {
            throw new IllegalStateException("No object found for the path: " + dtoPath);
        }

        StorageObject objectET = objects.get(0); // 예를 들어 첫 번째 객체를 사용


        //time
        LocalDateTime now = LocalDateTime.now();

        StorageObject object = StorageObject.builder()
                                .objectName(Name)
                                .isFolder(chkFolder)
                                .objectPath(dtoPath)
//            .objectSize()
//            .objectFormat()
                                .createdAt(now)
                                .modifiedAt(now)
                                .build();
        storageObjectRepository.save(object);
        return object;
    }
    public WikiPage createPage (String dtoPageTitle,String dtoPageCategory,User idOfUser) {
        //time
        LocalDateTime now = LocalDateTime.now();
        User user = idOfUser;

        WikiPage page = WikiPage.builder()
                                .pageTitle(dtoPageTitle)
                                .pageCategory(dtoPageCategory)
                                .createdAt(now)
                                .modifiedAt(now)
                                .latestedAt(now)
                                .userId(user)
                                .createdAtUserId(user.getUserId())
                                .modifiedAtUserId(user.getUserId())
                                .build();
        wikiPageRepository.save(page);
        return page;
    }


    public User transferTokenToUser(HttpServletRequest request) {
        User user = null;
//        String token = jwtTokenProvider.resolveToken(request);
//        String userId = jwtTokenProvider.getUserId(token);

        return user;
    }

}
