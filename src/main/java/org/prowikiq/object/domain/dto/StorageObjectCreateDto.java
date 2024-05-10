package org.prowikiq.object.domain.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prowikiq.object.domain.entity.FilePath;

/**
 * Class: ObjectCreateDto Project: prowikiQ Package: org.prowikiq.wiki.domain.dto
 * <p>
 * Description: ObjectCreateDto
 *
 * @author dong-hoshin
 * @date 5/7/24 19:43 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageObjectCreateDto {
    private String objectTitle;
    private Boolean isFolder;
    private FilePath objectPathId;
    private String objectPath;
    private BigInteger objectSize;
    private String objectFormat;

    //BaseEntity Time
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime latestedAt;

}
