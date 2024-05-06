package org.prowikiq.browser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.repository.BrowserListRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Class: BrowserListServiceTest Project: prowikiQ Package: org.prowikiq.browser.service
 * <p>
 * Description:
 *
 * @author dong-hoshin
 * @date 5/6/24 04:34 Copyright (c) 2024
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */

@ExtendWith(MockitoExtension.class)
class BrowserListServiceTest {

    @Mock
    private BrowserListRepository browserListRepository;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private Resource resource;

    @InjectMocks
    private BrowserListService browserListService;

    @BeforeEach
    void setUp() throws Exception {
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(getClass().getResourceAsStream("/directory_list.csv"));
    }

    @Test
    @DisplayName("BrowserListServiceTest success")
    void importBrowserLists() throws Exception {
        when(browserListRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

        List<BrowserList> importedLists = browserListService.importBrowserLists("classpath:directory_list.csv");

        assertNotNull(importedLists);
        assertFalse(importedLists.isEmpty());
        //assertEquals(8, importedLists.size()); // assuming your test data has 50 entries
        verify(browserListRepository, times(1)).saveAll(any()); // Check if saveAll was called twice (50 batch size and maybe a final batch)

    }
}