package com.Quizmania.Quizmania.service;

import com.Quizmania.Quizmania.repository.QuizRepository;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrowserServiceTest {
    
    @Mock
    QuizRepository quizRepository;
    
    @InjectMocks
    BrowserService browserService;
    
    @Test
    public void testFindPageWithKeyword() {
        Pageable pageable = PageRequest.of(1, 8);
        
        
        when(quizRepository.findByNameContaining("test", pageable)).thenReturn(null);
        
        assertEquals(browserService.findPageByKeyword("test", 1), 
                quizRepository.findByNameContaining("test", pageable));
        
    }
    @Test
    public void testFindPageWithKeywordWithSort() {
        Pageable pageable = PageRequest.of(1, 8, Sort.by("Category").ascending());


        when(quizRepository.findByNameContaining("test", pageable)).thenReturn(null);

        assertEquals(browserService.findPageByKeywordWithSort("test", "Category", "asc" ,1),
                quizRepository.findByNameContaining("test", pageable));

    }
}
