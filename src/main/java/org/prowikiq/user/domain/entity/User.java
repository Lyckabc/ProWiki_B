package org.prowikiq.user.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prowikiq.global.BaseEntity;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Class: User Project: prowikiQ Package: org.prowikiq.user.domain.entity
 * <p>
 * Description: User
 *
 * @author dong-hoshin
 * @date 4/23/24 21:48 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String userPhoneNum; // Consider changing the data type in SQL to match Java String.

    private String userPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;

    @Column(nullable = true)
    private String userPasswordHash;

    @Column(nullable = true)
    private String useremail;

    @Column
    private String status;

    @Column
    private String clazz;
}
