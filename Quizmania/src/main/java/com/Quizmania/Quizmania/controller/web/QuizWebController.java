package com.Quizmania.Quizmania.controller.web;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class QuizWebController {
    final QuizService quizService;

    public QuizWebController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home() { return "home"; }

    @GetMapping("/Create")
    public String create() { return "home"; }

    @PostMapping("/Create")
    public String create(Quiz quiz, BindingResult bindingResult,Model model){
        Quiz newQuiz;
        newQuiz = quizService.save(quiz);
        return "createQuiz";
    }

    @GetMapping("/search/{pageNumber}")
    public String getSearchPage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Quiz> page = quizService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<Quiz> quizzes = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("quizzes", quizzes);

        return "search";
    }

    @GetMapping("/search/{pageNumber}/{field}")
    public String getSearchPageWithSort(Model model, @PathVariable("pageNumber") int currentPage,
                                        @PathVariable String field, @PathParam("sortDir") String sortDir) {
        Page<Quiz> page = quizService.findWithSort(field, sortDir, currentPage);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        List<Quiz> quizzes = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("quizzes", quizzes);
        return "search";
    }

    @GetMapping("/search")
    public String getFirstSearchPage(Model model){
        return getSearchPage(model, 1);
    }
}
