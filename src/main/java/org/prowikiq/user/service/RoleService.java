package org.prowikiq.user.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.prowikiq.user.domain.entity.Role;
import org.prowikiq.user.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

/**
 * Class: RoleService Project: prowikiQ Package: org.prowikiq.user.service
 * <p>
 * Description: RoleService
 *
 * @author dong-hoshin
 * @date 5/17/24 17:01 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    @Transactional
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public Role makeRole(String roleName, Long parentRoleId) {
        Role parentRole = null;
        if (parentRoleId != null) {
            parentRole = roleRepository.findById(parentRoleId)
                .orElseThrow(() -> new RuntimeException("Parent role not found"));
        }

        Role role = new Role();
        role.setRoleName(roleName);
        role.setParentRole(parentRole);
        return roleRepository.save(role);
    }

    public Optional<Role> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);

    }

}
