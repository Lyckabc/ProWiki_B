package org.prowikiq.object.domain.repository;

import org.prowikiq.object.domain.entity.Object;
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
public interface ObjectRepository extends JpaRepository<Object, Long> {

}
