package org.prowikiq.object.domain.dto;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.object.domain.entity.FilePath;


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

    public FilePath toFilePath() {
        FilePath filePath = new FilePath();
        filePath.setFilePathId(this.filePathId);
        filePath.setFilePath(this.filePath);
        return filePath;
    }

}
