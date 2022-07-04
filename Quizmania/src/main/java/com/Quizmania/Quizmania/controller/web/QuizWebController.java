package com.Quizmania.Quizmania.controller.web;

import com.Quizmania.Quizmania.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

@Controller
public class QuizWebController {
    final QuizService quizService;
    public QuizWebController(QuizService quizService){
        this.quizService = quizService;
    }

    @Autowired
    UserRepository userRepo;

    @GetMapping("/")
    public String home(HttpSession session) { return "home"; }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "/home";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/create")
    public String creation(){
        return "create";
    }

    @GetMapping("/createQuiz")
    public String createQuizForm(Model model) {
        List<Question> questionList = quizService.findAllUnassignedQuestions();
        model.addAttribute("questionList",questionList);
        model.addAttribute("quiz",new Quiz());

        return "createQuiz"; }

    @PostMapping("/createQuiz/save")
    public String createQuizPost(Quiz quiz){
        quizService.save(quiz);
        return "redirect:/search";
    }
    @GetMapping("/createQuestion")
    public String createQuestionForm(Model model){
        model.addAttribute("question",new Question());
        return"createQuestion";

    }
    @PostMapping("/createQuestion/save")
    public String createQuestionPost(Question question, HttpServletRequest request){
        String[] answersContent = request.getParameterValues("answerContent");
        String[] isCorrect = request.getParameterValues("answerIsCorrect");
        for(int i= 0; i < 3; i++){
            question.addAnswerToListString(answersContent[i],isCorrect[i]);
        }
        quizService.save(question);

        return "redirect:/createQuiz";
    }

    @GetMapping("/quiz/{id}/game")
    public String playQuizInit(Model model,
                               @PathVariable("id") Long id, HttpSession session) {
        Quiz quiz = quizService.find(id).get();
        quiz.incrementPopularity();
        quizService.save(quiz);
        List<Pair<List<String>, Integer>> givenAnswers =  new ArrayList<Pair<List<String>, Integer>>();
        session.setAttribute("givenAnswers", givenAnswers);
        session.setAttribute("questionIndex", -1);

        return "redirect:/quiz/{id}/play";
    }
    @PostMapping("/quiz/{id}/play/submit")
    public String submit(@PathVariable("id") Long id,
                         @RequestParam(name = "content", defaultValue = ("")) List<String> content,
                         HttpSession session){
        Question question = quizService.findQuestion(id, (Integer)session.getAttribute("questionIndex"));
        float all = (float)question.getAnswerList().size();
        List<String> allCorrect = question.getAnswerList().stream().filter(a -> a.isCorrect() == true).map(Answer::getContent).collect(Collectors.toList());
        float b = allCorrect.size()/all;
        float sk = max(0, ((content.size()/ all) - b) * (question.getPoints()/(1 - b)));
        List<String> correctAnswers = new ArrayList<>(content) ;
        correctAnswers.retainAll(allCorrect);
        float p = (question.getPoints()/ (float)allCorrect.size()) * correctAnswers.size();
        int total = new BigDecimal(p-sk).setScale(0, RoundingMode.HALF_DOWN).intValue();

        ((List<Pair>)session.getAttribute("givenAnswers")).add(Pair.of(content, max(0, total)));

        session.setAttribute("givenAnswers", session.getAttribute("givenAnswers"));
        return "redirect:/quiz/{id}/play";
    }
    @PostMapping("/quiz/{id}/play/submitopen")
    public String submitOpen(@PathVariable("id") Long id,
                             @RequestParam(name = "content", defaultValue = "") String content,
                             HttpSession session){
        Question question = quizService.findQuestion(id, (Integer)session.getAttribute("questionIndex"));
        //if(question.getAnswerList().stream().anyMatch(a -> a.getContent().equals(content))){

        if(question.getAnswerList().get(0).getContent().equals(content)){

            ((List<Pair>)session.getAttribute("givenAnswers")).add(Pair.of(content, question.getPoints()));
        }
        else {
            ((List<Pair>)session.getAttribute("givenAnswers")).add(Pair.of(content, 0));
        }
        session.setAttribute("givenAnswers", session.getAttribute("givenAnswers"));
        return "redirect:/quiz/{id}/play";
    }

    @GetMapping("/quiz/{id}/play")
    public String playQuiz(Model model,
                           @PathVariable("id") Long id, HttpSession session) {
        Quiz quiz = quizService.find(id).get();
        model.addAttribute("quiz", quiz);
        session.setAttribute("questionIndex",((Integer)session.getAttribute("questionIndex"))+1);

        if((Integer)session.getAttribute("questionIndex") >= quiz.getQuestionList().size()){
            int score = ((List<Pair>)session.getAttribute("givenAnswers")).stream().mapToInt(a -> (int) a.getSecond()).sum();
            ((List<Pair>)session.getAttribute("givenAnswers")).add(Pair.of("", 0));
            ((List<Pair>)session.getAttribute("givenAnswers")).add(Pair.of("", 0));

            model.addAttribute("score", score);
            return "result";
        }
        else {
            return "play";
        }
    }
    @GetMapping("/quiz/{id}/timeout")
    public String timeout(Model model,
                          @PathVariable("id") Long id, HttpSession session) {
        Quiz quiz = quizService.find(id).get();
        model.addAttribute("quiz", quiz);

        int score = ((List<Pair>)session.getAttribute("givenAnswers")).stream().mapToInt(a -> (int) a.getSecond()).sum();
        int size = quiz.getQuestionList().size() - ((List<?>) session.getAttribute("givenAnswers")).size();
        for(int i = 0; i<=size; i++) {
            ((List<Pair>) session.getAttribute("givenAnswers")).add(Pair.of("", 0));
        }
        model.addAttribute("score", score);
        return "result";

    }
}