package org.prowikiq.object.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.object.domain.repository.AncestorRepository;
import org.prowikiq.object.domain.repository.StorageObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class: ObjectService Project: prowikiQ Package: org.prowikiq.object.service
 * <p>
 * Description: ObjectService
 *
 * @author dong-hoshin
 * @date 5/8/24 15:51 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
@RequiredArgsConstructor
public class StorageObjectService {
    private final AncestorRepository ancestorRepository;
    private final StorageObjectRepository storageObjectRepository;
    Logger logger = LoggerFactory.getLogger(getClass());



    @Transactional
    public StorageObject getStorageObjectFromId(Long objectId) {
        Optional<StorageObject> object = storageObjectRepository.findByObjectId(objectId);

        // 결과가 하나만 있는 경우를 기대했으나 여러 개 처리하는 방식 선택
        return object.orElseThrow(() -> new NoSuchElementException("해당 objectId에 대한 저장 객체가 없습니다."));
    }

    @Transactional
    public StorageObject getStorageObjectFromPath(String filePath) {
        Optional<StorageObject> object = storageObjectRepository.findByObjectPath(filePath);

        return object.orElseThrow(() -> new NoSuchElementException("해당 objectId에 대한 저장 객체가 없습니다."));
    }
    @Transactional
    public StorageObjectDto getObjectDtoById(Long objectId) {
        Optional<StorageObject> object = storageObjectRepository.findByObjectId(objectId);

        return object.map(storageObject -> {
            // Build and return a new StorageObjectDto with properties from StorageObject
            return StorageObjectDto.builder()
                .objectName(storageObject.getObjectName())
                .isFolder(storageObject.getIsFolder())
                .objectPath(storageObject.getObjectPath())
                .objectSize(storageObject.getObjectSize())
                .objectFormat(storageObject.getObjectFormat())
                .createdAt(storageObject.getCreatedAt())
                .modifiedAt(storageObject.getModifiedAt())
                .latestedAt(storageObject.getLatestedAt())
                .build();
        }).orElseGet(() -> StorageObjectDto.builder()
            .objectName("Not Found")
            .build());
    }

    @Transactional
    public StorageObject handleStorageObject(Long objectId, String objectName, Boolean isFolder, String objectPath) {
        try {
        StorageObject storageObject;

        if (objectName == null && objectPath != null) {
            objectName = objectPath.substring
                (objectPath.lastIndexOf('/') + 1).trim();
        }
        if (objectId != null) {
            storageObject = getStorageObjectFromId(objectId);
            if (objectName != null) storageObject.setObjectName(objectName);
            if (isFolder != null) storageObject.setIsFolder(isFolder);
            if (objectPath != null) storageObject.setObjectPath(objectPath);
        } else {
            storageObject = createAndSaveStorageObject(objectName, isFolder, objectPath);
        }

        return storageObject;} catch (Exception e) {
            // Log the specific exception and the state that caused it
            logger.error("Failed to handle storage object: Object ID: {}, Object Name: {}, Is Folder: {}, Object Path: {}",
                objectId, objectName, isFolder, objectPath, e);
            throw e; // Rethrow the exception or handle it based on your application's requirements
        }
    }

    private StorageObject createAndSaveStorageObject(String objectName, Boolean isFolder, String objectPath) {

        LocalDateTime now = LocalDateTime.now();

        StorageObject newObject = StorageObject.builder()
            .objectName(objectName)
            .isFolder(isFolder)
            .objectPath(objectPath)
            .createdAt(now)
            .modifiedAt(now)
            .latestedAt(now)
            .build();
        System.out.println("Before save: " + newObject.getObjectId()); // Should print 'null'
        newObject = storageObjectRepository.save(newObject);
        System.out.println("After save: " + newObject.getObjectId()); // Should print the new ID
        return newObject; // Assuming you have a repository method to save the object
    }

    @Transactional
    public StorageObject createObject(StorageObject storageObject) {

        //time
        LocalDateTime now = LocalDateTime.now();

        storageObject.setCreatedAt(now);
        storageObject.setModifiedAt(now);
        storageObject.setLatestedAt(now);

        storageObjectRepository.save(storageObject);
        return storageObject;
    }

    @Transactional
    public StorageObjectDto objectConvertToDto(StorageObject storageObject) {
        StorageObjectDto dto = StorageObjectDto.builder()
            .objectId(storageObject.getObjectId())
            .objectName(storageObject.getObjectName())
            .isFolder(storageObject.getIsFolder())
            .objectPath(storageObject.getObjectPath())
            .objectSize(storageObject.getObjectSize())
            .objectFormat(storageObject.getObjectFormat())
            .createdAt(storageObject.getCreatedAt())
            .modifiedAt(storageObject.getModifiedAt())
            .latestedAt(storageObject.getLatestedAt())
            .build();
        return dto;
    }

}
