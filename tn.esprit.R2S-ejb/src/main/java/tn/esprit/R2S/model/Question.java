package tn.esprit.R2S.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import tn.esprit.R2S.util.enums.QuestionType;

@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String question;


    @Enumerated
    private QuestionType type;


    private String score;

    @ManyToOne(targetEntity = CandidateAnswer.class)
    private CandidateAnswer candidateAnswer;

    @OneToMany(targetEntity = Answer.class, mappedBy = "question")
    private List<Answer> answers;

    @ManyToMany(targetEntity = QuizModel.class)
    private List<QuizModel> quizModels;

    @ManyToMany(targetEntity = Category.class, mappedBy = "questions")
    private List<Category> categories;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return this.type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public CandidateAnswer getCandidateAnswer() {
        return this.candidateAnswer;
    }

    public void setCandidateAnswer(CandidateAnswer candidateAnswer) {
        this.candidateAnswer = candidateAnswer;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<QuizModel> getQuizModels() {
        return this.quizModels;
    }

    public void setQuizModels(List<QuizModel> quizModels) {
        this.quizModels = quizModels;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
