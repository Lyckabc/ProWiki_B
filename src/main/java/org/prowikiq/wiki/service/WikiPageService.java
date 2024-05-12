package org.prowikiq.wiki.service;

import lombok.RequiredArgsConstructor;
import org.prowikiq.wiki.domain.repository.WikiPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class: WikiPageService Project: prowikiQ Package: org.prowikiq.wiki.service
 * <p>
 * Description: WikiPageService
 *
 * @author dong-hoshin
 * @date 5/12/24 11:55 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
@RequiredArgsConstructor
public class WikiPageService {
    private final WikiPageRepository wikiPageRepository;

    @Transactional
    public
}
