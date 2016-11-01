package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IAnswerService;
import tn.esprit.R2S.interfaces.ICandidateQuizModelService;
import tn.esprit.R2S.model.Answer;
import tn.esprit.R2S.model.CandidateQuizModel;
import tn.esprit.R2S.model.Question;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless

public class CandidateQuizModelService extends AbstractService<CandidateQuizModel> implements ICandidateQuizModelService {

    @EJB
    IAnswerService answerService;
    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateQuizModelService() {
        super(CandidateQuizModel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public double calculateScore(CandidateQuizModel candidateQuizModel) {
        List<Answer> candidateAnswers = candidateQuizModel.getCandidateAnswer().getAnswers();
        List<Question> quizModelQuestions = candidateQuizModel.getQuizModel().getQuestions();
        double totalScore = quizModelQuestions.stream().distinct().mapToDouble(q -> q.getScore().doubleValue()).sum();
        final double[] totalNote = {0};
        Map<Question, List<Answer>> questionListMap = candidateAnswers.stream()
                .collect(Collectors.groupingBy(Answer::getQuestion));
        questionListMap.forEach((question, answerList) -> {
            double questionNote = calculateQuestionNote(question, answerList,
                                            candidateQuizModel.getQuizModel().isPenalty(), totalScore);
            totalNote[0] += questionNote;
        });
        return totalNote[0];
    }

    @Override
    public double calculateSingleQuestionNote(CandidateQuizModel candidateQuizModel, Question question) {
        List<Answer> candidateAnswers = candidateQuizModel.getCandidateAnswer().getAnswers();
        List<Question> quizModelQuestions = candidateQuizModel.getQuizModel().getQuestions();
        double totalScore = quizModelQuestions.stream().distinct().mapToDouble(q -> q.getScore().doubleValue()).sum();
        List<Answer> answerList = candidateAnswers.stream()
                        .filter(answer -> answer.getQuestion().equals(question)).collect(Collectors.toList());
        double questionNote = calculateQuestionNote(question, answerList,
                                            candidateQuizModel.getQuizModel().isPenalty(), totalScore);
        return questionNote;
    }

    @Override
    public Map<CandidateQuizModel, Double> getHistorique() {
        Map<CandidateQuizModel, Double> scoreQuizMap = new LinkedHashMap();
        List<CandidateQuizModel> candidateQuizModelList = findAll();
        candidateQuizModelList.sort((c1, c2) -> Double.compare(calculateScore(c1), calculateScore(c2)));
        candidateQuizModelList.forEach(candidateQuizModel -> scoreQuizMap.put(candidateQuizModel, calculateScore(candidateQuizModel)));
        return scoreQuizMap;
    }

    public double calculateQuestionNote(Question question, List<Answer> answers, boolean penalty, double totalScore){
        double numberOfGoodAnswers = answerService.findCorrectAnswers(question.getId()).size();
        final double[] questionNote = {0};
        answers.forEach(answer -> {
            if(answer.isCorrect()){
                questionNote[0] += 1 / numberOfGoodAnswers;
            }else{
                questionNote[0] -= 1 / numberOfGoodAnswers;
            }
        });
        if(!penalty && questionNote[0] < 0){
            questionNote[0] = 0;
        }
        questionNote[0] *= 100 * question.getScore() / totalScore;
        return questionNote[0];
    }


}
