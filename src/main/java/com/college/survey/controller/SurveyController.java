package com.college.survey.controller;

import com.college.survey.model.Survey;
import com.college.survey.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyRepository repository;

    @PostMapping("/submit")
    public Survey submitSurvey(@RequestBody Survey survey) {
        return repository.save(survey);
    }

    @GetMapping("/all")
    public List<Survey> getAllSurveys() {
        return repository.findAll();
    }
}
