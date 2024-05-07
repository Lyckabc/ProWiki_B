package org.prowikiq.wiki.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class: WikiPageCreateDto Project: prowikiQ Package: org.prowikiq.wiki.domain.dto
 * <p>
 * Description: WikiPageCreateDto
 *
 * @author dong-hoshin
 * @date 5/7/24 19:45 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WikiPageCreateDto {
    private String pageContent;
    private String pageTitle;
    private String pageCategory;
}
