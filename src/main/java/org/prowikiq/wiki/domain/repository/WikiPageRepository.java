package org.prowikiq.wiki.domain.repository;

import java.util.Optional;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.wiki.domain.entity.WikiPage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class: WikiPageRepository Project: prowikiQ Package: org.prowikiq.wiki.domain.repository
 * <p>
 * Description: WikiPageRepository
 *
 * @author dong-hoshin
 * @date 5/8/24 15:20 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public interface WikiPageRepository extends JpaRepository<WikiPage, Long> {
    Optional<WikiPage> findByPageId (Long pageId);
    Optional<WikiPage> findByPageTitle (String pageTitle);

}
