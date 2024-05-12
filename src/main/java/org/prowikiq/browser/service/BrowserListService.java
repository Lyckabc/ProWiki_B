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
import org.prowikiq.browser.domain.dto.BrowserListDto;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;

import org.prowikiq.object.domain.entity.StorageObject;

import org.prowikiq.object.domain.repository.StorageObjectRepository;
import org.prowikiq.object.service.StorageObjectService;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.todo.domain.repository.ToDoRepository;
import org.prowikiq.todo.service.ToDoService;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.prowikiq.user.service.UserService;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.prowikiq.wiki.domain.repository.WikiPageRepository;
import org.prowikiq.wiki.service.WikiPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
public class BrowserListService {
    private final BrowserListRepository browserListRepository;
    private final ResourceLoader resourceLoader;
    private final StorageObjectService storageObjectService;
    private final StorageObjectRepository storageObjectRepository;
    private final WikiPageRepository wikiPageRepository;
    private final WikiPageService wikiPageService;
    private final UserRepository userRepository;
    private final ToDoRepository toDoRepository;
    private final UserService userService;
    private final ToDoService toDoService;

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
            reader = new BufferedReader(
                new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream(),
                    StandardCharsets.UTF_8));
            reader.readLine();
            List<BrowserList> batchList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                BrowserListDto dto = parseBrowserList(line);
                if (dto != null) {
                    BrowserList browserList = createBrowserListFromDto(dto);
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
        } catch (IOException e) {
            logger.error("Failed to read file", e);
            throw new RuntimeException("Failed to read file", e);
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

    private BrowserList createBrowserListFromDto(BrowserListDto dto) {
        BrowserList browserList = BrowserList.builder()
            .pageId(wikiPageService.getWikiPagefromId(dto.getPageId()))
            .pageTitle(dto.getPageTitle())
            .pageCategory(dto.getPageCategory())
            .pagePath(dto.getPagePath())
            .storageObjectId(storageObjectService.getStorageObjectFromId(dto.getStorageObjectId()))
            .objectName(dto.getObjectName())
            .isFolder(dto.getIsFolder())
            .objectPath(dto.getObjectPath())
            .userId(userService.getUserFromId(dto.getUserId()))
            .userPhoneNum(dto.getUserPhoneNum())
            .createdAtUserId(dto.getCreatedAtUserId())
            .modifiedAtUserId(dto.getModifiedAtUserId())
            .toDoId(toDoService.getToDoFromId(dto.getToDoId()) != null ? toDoService.getToDoFromId(dto.getToDoId()) : null)
            .toDoTitle(dto.getToDoTitle() != null ? dto.getToDoTitle() : null)
            .targetDay(dto.getTargetDay())
            .finishedDay(dto.getFinishedDay())
            .requestUserId(dto.getRequestUserId() != null ? dto.getRequestUserId() : null)
            .solvedUserId(dto.getSolvedUserId() != null ? dto.getSolvedUserId() : null)
            .build();
        return browserList;
    }


    private BrowserListDto parseBrowserList(String line) {
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

            //Time
            LocalDateTime atCreated = data[4].isEmpty() ? now : LocalDateTime.parse(data[4].trim(), formatter);
            LocalDateTime atModified = data[5].isEmpty() ? now : LocalDateTime.parse(data[5].trim(), formatter);

            //Object
            StorageObject object = data[8].isEmpty() ? storageObjectService.createObject(data[7].trim()) : storageObjectService.getStorageObjectFromId(Long.parseLong(data[8].trim()));
            Boolean chkFolder = object.getIsFolder();
            String objectPath = object.getObjectPath();
            String nameOfFile = data[1].isEmpty() ? data[7].substring(data[7].lastIndexOf('/') + 1).trim() : data[1].trim(); // Object id가 없을 경우 ObjectFilePath를 통해서 끝 데이터 즉, 파일 혹은 폴더명 입력, 있을 경우 file이름을 가져옴
            String filePath = data[6].isEmpty() ? null : data[6].trim() ; // filePathId 가 없을 경우 import,  있으면 OS의 FilePath를 가져옴


            //User -> 5.권한 적용시키기 할 때 HTTP(JWT) data import -> transferTokenToUser 적용
            Long idOfUserLong = data[10].isEmpty() ? userId : Long.parseLong(data[10].trim());
            User user = userRepository.getById(idOfUserLong);
            Long userOfCreatedAt = data[11].isEmpty() ? user.getUserId() : Long.parseLong(data[11].trim());
            Long userOfModifiedAt = data[12].isEmpty() ? user.getUserId() : Long.parseLong(data[12].trim());
            Long userOfRequest = data[13].isEmpty() ? user.getUserId() : Long.parseLong(data[13].trim());
//            Long userOfsolver = data[14].isEmpty() ? idOfUser.getUserId() : Long.parseLong(data[14].trim());

            //Todo
            ToDo toDo = data[15].isEmpty() ? null : toDoService.getToDoFromId(Long.parseLong(data[15].trim()));
//            LocalDateTime dayOfTarget = data[16].isEmpty() ? idOfToDo.getTargetDay() : LocalDateTime.parse(data[16].trim(), formatter); //targetDay를 가져와 입력
//            LocalDateTime dayOfFinished = data[17].isEmpty() ? idOfToDo.getFinishedDay() : LocalDateTime.parse(data[17].trim(), formatter); //finishedDay를 가져와 입력

            //Page
            String categoryOfPage = data[3].isEmpty() ? null : data[3].trim();
            String titleOfPage = data[2].isEmpty() ? data[7].substring(data[7].lastIndexOf('/') + 1).trim() : data[2].trim(); // Page id가 없을 경우 Page를 통해서 끝 데이터 즉, 파일 혹은 폴더명 입력, 있을 경우 Page이름을 가져옴
            //Page id, title, Catgory, pagePathId(not made path link), pagePath(not made path link)
            if (titleOfPage.contains(".")) {
                titleOfPage = titleOfPage.substring(0, titleOfPage.lastIndexOf('.'));
            }
            WikiPage page =  data[1].isEmpty() ? wikiPageService.createPage(titleOfPage, categoryOfPage, user, object,toDo) : wikiPageRepository.findByPageId(Long.parseLong(data[1].trim())).orElseThrow();


            // PageCategory를 가져와 BrowserListDto에 입력

            BrowserListDto browserListDto = BrowserListDto.builder()
//                .browserListId(null)
                .pageId(page != null ? page.getPageId() : null)
                .pageTitle(page != null ? page.getPageTitle() : null)
                .pageCategory(page != null ? page.getPageCategory() : null)
                .pagePath(page != null ? page.getPagePath() : null)
                .storageObjectId(object != null ? object.getObjectId() : null)
                .objectName(object != null ? object.getObjectName() : null)
                .isFolder(object != null ? object.getIsFolder() : null)
                .objectPath(object != null ? object.getObjectPath() : null)
                .userId(user.getUserId())
                .userPhoneNum(user != null ? user.getUserPhoneNum() : null)
                .createdAtUserId(user.getUserId())
                .modifiedAtUserId(user.getUserId())
                .requestUserId(toDo != null ? toDo.getRequestUserId() : null)
                .solvedUserId(toDo != null ? toDo.getSolvedUserId() : null)
                .toDoId(toDo != null ? toDo.getToDoId() : null)
                .toDoTitle(toDo != null ? toDo.getToDoTitle() : null)
                .createdAt(atCreated)
                .modifiedAt(atModified)
                .latestedAt(now)
                .build();

            return browserListDto;

        } catch (Exception e) {
            logger.error("Error parsing line: {}. Error: {}", line, e.getMessage());
            return null;
        }
    }



    public User transferTokenToUser(HttpServletRequest request) {
        User user = null;
//        String token = jwtTokenProvider.resolveToken(request);
//        String userId = jwtTokenProvider.getUserId(token);

        return user;
    }

}
