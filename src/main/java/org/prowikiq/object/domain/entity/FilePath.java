package org.prowikiq.object.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class: FilePath Project: prowikiQ Package: org.prowikiq.object.domain.entity
 * <p>
 * Description: FilePath Entity
 *
 * @author dong-hoshin
 * @date 4/29/24 21:35 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */

@Entity
@Table(name = "\"filePath\"")
public class FilePath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path", nullable = false, columnDefinition = "TEXT")
    private String path;
}
