package org.prowikiq.object.domain.dto;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.object.domain.entity.FilePath;
import org.prowikiq.object.domain.entity.StorageObject;
import org.prowikiq.wiki.domain.dto.WikiPageBriefDTO;
import org.prowikiq.wiki.domain.dto.WikiPageCreateDto;
import org.prowikiq.wiki.domain.entity.WikiPage;


/**
 * Class: FilePathCreateDto Project: prowikiQ Package: org.prowikiq.object.domain.dto
 * <p>
 * Description: FilePathCreateDto
 *
 * @author dong-hoshin
 * @date 5/4/24 17:41 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilePathCreateDto {
    private Long filePathId;
    private String filePath;
    private List<StorageObjectCreateDto> storageObjects;
    private List<WikiPageCreateDto> wikiPages;

    }

}