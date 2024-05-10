package org.prowikiq.object.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.object.domain.repository.AncestorRepository;
import org.prowikiq.object.domain.repository.FilePathRepository;
import org.prowikiq.object.domain.repository.StorageObjectRepository;
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
    private final FilePathRepository filePathRepository;
    private final AncestorRepository ancestorRepository;
    private final StorageObjectRepository storageObjectRepository;

    @Transactional
    public FilePath getFilePathIdBy(Long id) {
        //Optional<FilePath> filePath = filePathRepository.findByFilePathId(id); for Long
        //filePath.map(FilePath::getFilePathId).orElseThrow(() -> new RuntimeException("File path not found with id: " + id)); for Long
        Optional<FilePath> filePath = filePathRepository.findById(id);
        return filePath.orElseThrow(() -> new RuntimeException("FilePath not found for id: " + id));
    }


}
