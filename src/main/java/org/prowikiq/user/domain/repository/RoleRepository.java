package org.prowikiq.user.domain.repository;

import java.util.Optional;
import org.prowikiq.user.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class: RoleRepository Project: prowikiQ Package: org.prowikiq.user.domain.repository
 * <p>
 * Description: RoleRepository
 *
 * @author dong-hoshin
 * @date 5/8/24 15:26 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String role);

}
