package org.prowikiq.object.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.object.domain.repository.AncestorRepository;
import org.prowikiq.object.domain.repository.StorageObjectRepository;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
import org.prowikiq.wiki.domain.entity.WikiPage;
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
    public StorageObject createObject(String dtoPath) {
        String name = dtoPath.substring(dtoPath.lastIndexOf('/') + 1).trim();
        boolean chkFolder = !name.contains(".");

        //time
        LocalDateTime now = LocalDateTime.now();

        StorageObject object = StorageObject.builder()
            .objectName(name)
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

    @Transactional
    public StorageObjectDto objectConvertToDto(StorageObject storageObject) {
        StorageObjectDto dto = StorageObjectDto.builder()
            .objectName(storageObject.getObjectName())
            .isFolder(storageObject.getIsFolder())
            .objectPath(storageObject.getObjectPath())
            .objectSize(storageObject.getObjectSize())
            .objectFormat(storageObject.getObjectFormat())
            .build();
        return dto;
    }

}
