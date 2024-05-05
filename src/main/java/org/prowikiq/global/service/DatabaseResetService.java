package org.prowikiq.global.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class: DatabaseResetService Project: prowikiQ Package: org.prowikiq.global.service
 * <p>
 * Description: DatabaseResetService
 *
 * @author dong-hoshin
 * @date 5/6/24 02:51 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
public class DatabaseResetService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void resetDatabase() {
        // 모든 테이블을 삭제하거나 특정 데이터만 삭제
        entityManager.createNativeQuery("TRUNCATE TABLE your_table1 CASCADE").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE your_table2 CASCADE").executeUpdate();
        // 추가적으로 다른 테이블들에 대한 초기화 코드를 넣을 수 있습니다.

        // 필요하다면 특정 데이터를 다시 삽입
        //entityManager.createNativeQuery("INSERT INTO your_table1 (column1, column2) VALUES ('value1', 'value2')").executeUpdate();
        // 더 많은 데이터 삽입 코드를 추가할 수 있습니다.
    }

}
