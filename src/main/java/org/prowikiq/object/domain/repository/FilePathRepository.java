package org.prowikiq.object.domain.repository;

<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72
import org.prowikiq.object.domain.entity.FilePath;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class: FilePathRepository Project: prowikiQ Package: org.prowikiq.object.domain.repository
 * <p>
 * Description: FilePathRepository
 *
 * @author dong-hoshin
 * @date 5/4/24 17:35 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public interface FilePathRepository extends JpaRepository<FilePath, Long> {
<<<<<<< HEAD
    Optional<FilePath> findByFilePathId(Long filePathId);
=======
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72

}
