package org.prowikiq.browser.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.prowikiq.browser.domain.dto.BrowserListDto;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;

import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.object.domain.entity.StorageObject;

import org.prowikiq.object.domain.repository.StorageObjectRepository;
import org.prowikiq.object.service.StorageObjectService;
import org.prowikiq.todo.domain.dto.ToDoDto;
import org.prowikiq.todo.domain.entity.ToDo;
import org.prowikiq.todo.domain.repository.ToDoRepository;
import org.prowikiq.todo.service.ToDoService;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.prowikiq.user.service.UserService;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
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
        return browserListRepository.findById(pageId)
            .orElseThrow(() -> new RuntimeException("BrowserList not found"));
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
        Map<String, Integer> columnIndexMap = null;
        BufferedReader reader = null;
        try {
             reader = new BufferedReader(
                new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream(),
                    StandardCharsets.UTF_8));
            String headerLine = reader.readLine();
            columnIndexMap = createColumnIndexMap(headerLine);
            List<BrowserList> batchList = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                BrowserListDto dto = parseBrowserList(line, columnIndexMap);
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
        WikiPage wikiPage = wikiPageService.getWikiPagefromId(dto.getPageId().getPageId());
        StorageObject storageObject = storageObjectService.getStorageObjectFromId
            (dto.getStorageObjectId().getObjectId());
        User user = userService.getUserFromId(dto.getUserId().getUserId());
        ToDo toDo;
        if (wikiPage.getToDoId() != null) {
            toDo = toDoService.getToDoFromId(dto.getToDoId().getToDoId());
        } else {
            toDo = null;
        }
        BrowserList browserList = BrowserList.builder()
            .pageId(wikiPage)
//            .pageTitle(dto.getPageId().getPageTitle())
//            .pageCategory(dto.getPageCategory())
//            .pagePath(dto.getPagePath())
            .storageObjectId(storageObject)
//            .objectName(dto.getObjectName())
//            .isFolder(dto.getIsFolder())
//            .objectPath(dto.getObjectPath())
            .userId(user)
//            .userPhoneNum(dto.getUserPhoneNum())
//            .createdAtUserId(dto.getCreatedAtUserId())
//            .modifiedAtUserId(dto.getModifiedAtUserId())
            .toDoId(toDo)
//            .toDoTitle(dto.getToDoTitle() != null ? dto.getToDoTitle() : null)
//            .targetDay(dto.getTargetDay())
//            .finishedDay(dto.getFinishedDay())
//            .requestUserId(dto.getRequestUserId() != null ? dto.getRequestUserId() : null)
//            .solvedUserId(dto.getSolvedUserId() != null ? dto.getSolvedUserId() : null)
            .build();
        return browserList;
    }

    public BrowserListDto getBrowserListDto(Long id) {
        BrowserList browserList = browserListRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("BrowserList not found"));
        WikiPageDto wikiPageDto = wikiPageService.wikiConvertToDto(browserList.getPageId());
        UserDto userDto = browserList.getUserId().toDto();
        ToDoDto toDoDto = toDoService.toDoConvertToDto(browserList.getToDoId());

        BrowserListDto dto = BrowserListDto.builder()
            .browserListId(browserList.getBrowserListId())
            .pageId(wikiPageDto)
            .userId(userDto)
            .toDoId(toDoDto)
            .build();

        return dto;
    }

    private BrowserListDto parseBrowserList(String line,Map<String, Integer> columnIndexMap) {
        String[] data = line.split(",", -1);
        Long userId = Long.parseLong("3");
        if (data.length < 17) {
            logger.error("Insufficient data in line: {}", line);
            return null;
        }
        try {
            //1.Variable Designation
            Long browserListId = getLongFromData(data, columnIndexMap, "browserListId");
            //// Page
            Long pageId = getLongFromData(data, columnIndexMap, "PageId");
            String pageTitle = getStringFromData(data, columnIndexMap, "PageTitle");
            String pageCategory = getStringFromData(data, columnIndexMap, "PageCategory");
            String pagePath = getStringFromData(data, columnIndexMap, "PagePath");
            //// Base
            LocalDateTime createdAt = getDateTimeFromData(data, columnIndexMap, "CreatedAt");
            LocalDateTime modifiedAt = getDateTimeFromData(data, columnIndexMap, "ModifiedAt");
            LocalDateTime latestedAt = getDateTimeFromData(data, columnIndexMap, "LatestedAt");
            //// Object
            Long objectId = getLongFromData(data, columnIndexMap, "ObjectId");
            String objectName = getStringFromData(data, columnIndexMap, "ObjectName");
            Boolean isFolder = getBooleanFromData(data, columnIndexMap, "IsFolder");
            String objectPath = getStringFromData(data, columnIndexMap, "ObjectPath");
            //// User
//            Long userId = getLongFromData(data, columnIndexMap, "UserId");
            String userPhoneNum = getStringFromData(data, columnIndexMap, "UserPhoneNum");
            //// ToDo
            Long toDoId = getLongFromData(data, columnIndexMap, "ToDoId");
            String toDoTitle = getStringFromData(data, columnIndexMap, "ToDoTitle");

            //BaseEntity about time
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime now = LocalDateTime.now();

            //2.Insert Variable
            ////Time
            LocalDateTime atCreated = (createdAt != null) ? createdAt : now;
            LocalDateTime atModified = (modifiedAt != null) ? modifiedAt : now;
            LocalDateTime atLatested = (latestedAt != null) ? latestedAt : now;

            ////Object
            StorageObject object =
                storageObjectService.handleStorageObject(objectId, objectName, isFolder,
                    objectPath);
            StorageObjectDto dtoObject = storageObjectService.objectConvertToDto(object);

            ////User -> 5.권한 적용시키기 할 때 HTTP(JWT) data import -> transferTokenToUser 적용
            User user = userService.getUserFromId(userId);
            UserDto dtoUser = user.toDto();

            ////Todo
            ToDoDto dtoToDo = null;
            ToDo toDo = null;
            if (!(toDoId == null && toDoTitle == null)) {
                dtoToDo = toDoService.handleToDo(toDoId, toDoTitle);
                toDo = dtoToDo != null ? toDoService.getToDoFromId(toDoId) : null;
            }

            ////Page
            WikiPageDto wikiPage = wikiPageService.handleWikiPage
                (pageId, pageTitle, pageCategory, pagePath, object, user, toDo);

            // PageCategory를 가져와 BrowserListDto에 입력

            BrowserListDto browserListDto = BrowserListDto.builder()
                .browserListId(browserListId)
                .storageObjectId(dtoObject)
                .userId(dtoUser)
                .toDoId(dtoToDo)
                .pageId(wikiPage)
                .build();
            if (browserListId != null) {
                browserListDto = getBrowserListDto(browserListId);
            }
            return browserListDto;

        } catch (Exception e) {
            logger.error("Error parsing line: {}. Error: {}", line, e.getMessage());
            return null;
        }
    }

    public Map<String, Integer> createColumnIndexMap(String headerLine) {
        String[] headers = headerLine.split(",", -1);
        Map<String, Integer> columnIndexMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            columnIndexMap.put(headers[i].trim(), i);
        }
        return columnIndexMap;
    }

    private String getStringFromData(String[] data, Map<String, Integer> map, String columnName) {
        return map.containsKey(columnName) && !data[map.get(columnName)].isEmpty() ?
            data[map.get(columnName)].trim() : null;
    }

    private Long getLongFromData(String[] data, Map<String, Integer> map, String columnName) {
        return map.containsKey(columnName) && !data[map.get(columnName)].isEmpty() ?
            Long.parseLong(data[map.get(columnName)].trim()) : null;
    }

    private boolean getBooleanFromData(String[] data, Map<String, Integer> map, String columnName) {
        if (!map.containsKey(columnName) || data[map.get(columnName)].isEmpty()) {
            return false;
        }
        String path = data[map.get(columnName)].trim();
        String name = path.substring(path.lastIndexOf('/') + 1).trim();
        return !name.contains(".");
    }

    private LocalDateTime getDateTimeFromData(String[] data, Map<String, Integer> map,
        String columnName) {
        if (map.containsKey(columnName) && !data[map.get(columnName)].isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return LocalDateTime.parse(data[map.get(columnName)].trim(), formatter);
        }
        return null;
    }



    public User transferTokenToUser (HttpServletRequest request) {
        User user = null;
//        String token = jwtTokenProvider.resolveToken(request);
//        String userId = jwtTokenProvider.getUserId(token);

        return user;
    }


}
