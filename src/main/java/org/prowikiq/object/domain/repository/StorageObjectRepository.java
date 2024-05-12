package org.prowikiq.object.domain.repository;

import java.util.List;
import java.util.Optional;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.StorageObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class: ObjectRepository Project: prowikiQ Package: org.prowikiq.object.domain.repository
 * <p>
 * Description: ObjectRepository
 *
 * @author dong-hoshin
 * @date 5/8/24 15:32 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public interface StorageObjectRepository extends JpaRepository<StorageObject, Long> {
    List<StorageObject> findByObjectId(Long ObjectId);
    Optional<StorageObject> findByObjectName(String ObjectTitle);

    Optional<StorageObject> findByObjectPathId(FilePath ObjectPathId);

    List<StorageObject> findByObjectPath(String ObjectPath);

}
