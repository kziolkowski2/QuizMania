package com.Quizmania.Quizmania.controller.web;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/create")
    public String create() { return "home"; }

    @PostMapping("/create")
    public String create(Quiz quiz, BindingResult bindingResult,Model model){
        Quiz newQuiz;
        newQuiz = quizService.save(quiz);
        return "createQuiz";
    }
    @GetMapping("/search/{pageNumber}")
    public String getSearchPage(Model model, @PathVariable("pageNumber") int currentPage,
                                @RequestParam(value = "keyword", required = false) String keyword) {
        Page<Quiz> pageQuizzes;
        if(keyword == null || keyword.equals("")){
            pageQuizzes = quizService.findPageByKeyword("", currentPage);
        }
        else{
            pageQuizzes = quizService.findPageByKeyword(keyword, currentPage);
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
            pageQuizzes = quizService.findPageByKeywordWithSort("", field, sortDir, currentPage);
        }
        else{
            pageQuizzes = quizService.findPageByKeywordWithSort(keyword, field, sortDir, currentPage);
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

    @GetMapping("/quiz/{id}/game")
    public String playQuizInit(Model model,
                           @PathVariable("id") Long id, HttpSession session) {
        Quiz quiz = quizService.find(id).get();

        session.setAttribute("questionIndex", -1);
        session.setAttribute("score", 0);
        model.addAttribute("quiz", quiz);

        return "redirect:/quiz/{id}/play";
    }
    @GetMapping("/quiz/{id}/play")
    public String playQuiz(Model model,
                           @PathVariable("id") Long id, HttpSession session) {
        Quiz quiz = quizService.find(id).get();

        model.addAttribute("quiz", quiz);
        session.setAttribute("questionIndex",((Integer)session.getAttribute("questionIndex"))+1);


        return "play";
    }
    @GetMapping("/quiz/{id}/play/increment")
    public String playQuizIncrement(Model model,
                            @PathVariable("id") Long id, HttpSession session) {
        Quiz quiz = quizService.find(id).get();

        session.setAttribute("score", ((Integer)session.getAttribute("score"))+1);
        model.addAttribute("quiz", quiz);
        session.getAttribute("questionIndex");

        return "redirect:/quiz/{id}/play";
    }
}
