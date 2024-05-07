package org.prowikiq.object.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class ObjectCreateDto {
    private Boolean isFolder;
}
