package org.prowikiq.browser.service;

import java.util.List;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Class: BrowserListService Project: prowikiQ Package: org.prowikiq.browser.service
 * <p>
 * Description: BrowserListService
 *
 * @author dong-hoshin
 * @date 4/29/24 20:49 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
public class BrowserListService {
    private final BrowserListRepository browserListRepository;

    @Autowired
    public BrowserListService(BrowserListRepository browserListRepository) {
        this.browserListRepository = browserListRepository;
    }

    @Transactional
    public BrowserList createBrowserList(BrowserList browserList) {
        return browserListRepository.save(browserList);
    }

    @Transactional(readOnly = true)
    public BrowserList getBrowserList(Long pageId) {
        return browserListRepository.findById(pageId).orElseThrow(() -> new RuntimeException("BrowserList not found"));
    }

    @Transactional(readOnly = true)
    public List<BrowserList> getAllBrowserLists() {
        return browserListRepository.findAll();
    }

    @Transactional
    public BrowserList updateBrowserList(BrowserList browserList) {
        return browserListRepository.save(browserList);
    }

    @Transactional
    public void deleteBrowserList(Long pageId) {
        browserListRepository.deleteById(pageId);
    }


}
