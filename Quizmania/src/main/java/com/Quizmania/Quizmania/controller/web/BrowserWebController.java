package com.Quizmania.Quizmania.controller.web;

import com.Quizmania.Quizmania.service.BrowserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BrowserWebController {
    final BrowserService browserService;
    public BrowserWebController(BrowserService browserService){
        this.browserService = browserService;
    }

    @GetMapping("/search/{pageNumber}")
    public String getSearchPage(Model model, @PathVariable("pageNumber") int currentPage,
                                @RequestParam(value = "keyword", required = false) String keyword) {
        Page<Quiz> pageQuizzes;
        if(keyword == null || keyword.equals("")){
            pageQuizzes = browserService.findPageByKeyword("", currentPage);
        }
        else{
            pageQuizzes = browserService.findPageByKeyword(keyword, currentPage);
        }
        List<Quiz> quizzes = pageQuizzes.getContent();

        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", pageQuizzes.getTotalPages());
        model.addAttribute("totalItems", pageQuizzes.getTotalElements());
        model.addAttribute("quizzes", quizzes);

        return "search";
    }

    @GetMapping("/search/{pageNumber}/{field}")
    public String getSearchPageWithSort(Model model, @PathVariable("pageNumber") int currentPage,
                                        @PathVariable String field,
                                        @PathParam("sortDir") String sortDir,
                                        @RequestParam(value = "keyword", required = false) String keyword) {
        Page<Quiz> pageQuizzes;
        if(keyword == null || keyword.equals("")){
            pageQuizzes = browserService.findPageByKeywordWithSort("", field, sortDir, currentPage);
        }
        else{
            pageQuizzes = browserService.findPageByKeywordWithSort(keyword, field, sortDir, currentPage);
        }
        List<Quiz> quizzes = pageQuizzes.getContent();

        model.addAttribute("keyword", keyword);
        model.addAttribute("field", field);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", pageQuizzes.getTotalPages());
        model.addAttribute("totalItems", pageQuizzes.getTotalElements());
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("quizzes", quizzes);
        return "search";
    }

    @GetMapping("/search")
    public String getFirstSearchPage(Model model, String keyword){
        return getSearchPage(model, 1, keyword);
    }

}