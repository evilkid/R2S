/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.hrsmart.controller;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javax.ws.rs.core.Application;

/**
 *
 * @author Ouerghi Yassine
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }


    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<>();
        instances.add(new JacksonJsonProvider());
        return instances;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(tn.esprit.hrsmart.controller.AnswerController.class);
        resources.add(tn.esprit.hrsmart.controller.CandidateAnswerController.class);
        resources.add(tn.esprit.hrsmart.controller.CandidateController.class);
        resources.add(tn.esprit.hrsmart.controller.CandidateQuizModelController.class);
        resources.add(tn.esprit.hrsmart.controller.CategoryController.class);
        resources.add(tn.esprit.hrsmart.controller.CertificationController.class);
        resources.add(tn.esprit.hrsmart.controller.ChiefHumanResourcesOfficerController.class);
        resources.add(tn.esprit.hrsmart.controller.EducationController.class);
        resources.add(tn.esprit.hrsmart.controller.EmailModelController.class);
        resources.add(tn.esprit.hrsmart.controller.EmployeeController.class);
        resources.add(tn.esprit.hrsmart.controller.ExperienceController.class);
        resources.add(tn.esprit.hrsmart.controller.InterviewController.class);
        resources.add(tn.esprit.hrsmart.controller.JobController.class);
        resources.add(tn.esprit.hrsmart.controller.NotificationController.class);
        resources.add(tn.esprit.hrsmart.controller.QuestionController.class);
        resources.add(tn.esprit.hrsmart.controller.QuizModelController.class);
        resources.add(tn.esprit.hrsmart.controller.RecruitmentManagerController.class);
        resources.add(tn.esprit.hrsmart.controller.RewardController.class);
        resources.add(tn.esprit.hrsmart.controller.SkillController.class);
        resources.add(tn.esprit.hrsmart.controller.UsersController.class);
    }
    
}
