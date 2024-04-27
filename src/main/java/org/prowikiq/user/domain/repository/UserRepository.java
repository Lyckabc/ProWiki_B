package org.prowikiq.user.domain.repository;

import java.util.Optional;
import org.prowikiq.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class: UserRepository Project: prowikiQ Package: org.prowikiq.user.domain.repository
 * <p>
 * Description: UserRepository
 *
 * @author dong-hoshin
 * @date 4/27/24 17:27 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserPhoneNum(String userPhoneNum);

    boolean existsByUserPhoneNum(String userPhoneNum);
}

