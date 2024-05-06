package org.prowikiq.object.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class: FilePath Project: prowikiQ Package: org.prowikiq.object.domain.entity
 * <p>
 * Description: FilePath Entity
 *
 * @author dong-hoshin
 * @date 4/29/24 21:35 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"filePath\"")
public class FilePath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filePathId")
    private Long filePathId;

    @Column(name = "filePath", columnDefinition = "TEXT")
    private String filePath;

    public void setPath(String path) {
        this.filePath = path;
    }

    public String getPath () { return filePath;}
}
