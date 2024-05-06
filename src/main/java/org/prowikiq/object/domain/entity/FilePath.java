package org.prowikiq.object.domain.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import lombok.Setter;
import org.prowikiq.browser.domain.entity.BrowserList;
=======
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72

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
<<<<<<< HEAD
@Setter
=======
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72
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
<<<<<<< HEAD

    @Column(name = "filePath", columnDefinition = "TEXT")
    private String filePath;

    @OneToMany(mappedBy = "filePath")
    private List<BrowserList> browserLists;

    public FilePath(String filePath) {
        this.filePath = filePath;
    }

=======

    @Column(name = "filePath", columnDefinition = "TEXT")
    private String filePath;

    public void setPath(String path) {
        this.filePath = path;
    }

    public String getPath () { return filePath;}
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72
}
