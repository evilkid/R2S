/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.R2S.controller;

import java.util.HashSet;
import java.util.Set;
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
        return instances;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(tn.esprit.R2S.controller.AnswerController.class);
        resources.add(tn.esprit.R2S.controller.CandidateAnswerController.class);
        resources.add(tn.esprit.R2S.controller.CandidateController.class);
        resources.add(tn.esprit.R2S.controller.CandidateQuizModelController.class);
        resources.add(tn.esprit.R2S.controller.CategoryController.class);
        resources.add(tn.esprit.R2S.controller.CertificationController.class);
        resources.add(tn.esprit.R2S.controller.ChiefHumanResourcesOfficerController.class);
        resources.add(tn.esprit.R2S.controller.EducationController.class);
        resources.add(tn.esprit.R2S.controller.EmailModelController.class);
        resources.add(tn.esprit.R2S.controller.EmployeeController.class);
        resources.add(tn.esprit.R2S.controller.ExperienceController.class);
        resources.add(tn.esprit.R2S.controller.InterviewController.class);
        resources.add(tn.esprit.R2S.controller.JobController.class);
        resources.add(tn.esprit.R2S.controller.NotificationController.class);
        resources.add(tn.esprit.R2S.controller.QuestionController.class);
        resources.add(tn.esprit.R2S.controller.QuizModelController.class);
        resources.add(tn.esprit.R2S.controller.RecruitmentManagerController.class);
        resources.add(tn.esprit.R2S.controller.RewardController.class);
        resources.add(tn.esprit.R2S.controller.SkillController.class);
        resources.add(tn.esprit.R2S.controller.UsersController.class);
    }
    
}
