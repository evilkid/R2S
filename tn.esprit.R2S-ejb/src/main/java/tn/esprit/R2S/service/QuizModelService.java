package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IAnswerService;
import tn.esprit.R2S.interfaces.IQuestionService;
import tn.esprit.R2S.interfaces.IQuizModelService;
import tn.esprit.R2S.model.Answer;
import tn.esprit.R2S.model.Question;
import tn.esprit.R2S.model.QuizModel;
import tn.esprit.R2S.util.enums.QuestionType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Stateless

public class QuizModelService extends AbstractService<QuizModel> implements IQuizModelService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;
    @EJB
    private IQuestionService questionService;
    @EJB
    private IAnswerService answerService;

    public QuizModelService() {
        super(QuizModel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void addRandomQuizModel(QuizModel quizModel) {
        Random random = new Random();
        List<Question> questions = questionService.findRandomQuestions(quizModel.getQuestionsNumber());
        List<Answer> keepedAnswer = new ArrayList();
        questions.stream().forEach(question -> {
            int randomInt = random.nextInt(quizModel.getAnswersNumber()) + 1;
            if(question.getType().equals(QuestionType.CHECKBOX)){
                keepedAnswer.addAll(answerService.findCorrectAnswers(question.getId()).subList(0, randomInt));
                keepedAnswer.addAll(
                        answerService.findWrongAnswers(question.getId()).subList(0, quizModel.getAnswersNumber() - randomInt)
                );
            }
            else{
                keepedAnswer.add(answerService.findCorrectAnswers(question.getId()).get(0));
                keepedAnswer.addAll(
                        answerService.findWrongAnswers(question.getId()).subList(0, quizModel.getAnswersNumber() - 1)
                );
            }

        });
        quizModel.setQuestions(questions);
        quizModel.setAnswers(keepedAnswer);
        create(quizModel);
    }
}
