package org.prowikiq.object.domain.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.wiki.domain.entity.WikiPage;

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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"filePath\"")
public class FilePath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_path_id")
    private Long filePathId;

    @Column(name = "file_path", columnDefinition = "TEXT")
    private String filePath;

    @OneToMany(mappedBy = "page_path_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WikiPage> wikiPages;

    @OneToMany(mappedBy = "object_path_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StorageObject> storageObjects;

}