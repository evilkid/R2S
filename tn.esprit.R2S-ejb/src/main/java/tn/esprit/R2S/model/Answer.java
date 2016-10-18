/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author EvilKids
 */
@Entity
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String answer;


    private Boolean correct;

    @ManyToOne(targetEntity = Question.class)
    private Question question;

    @ManyToOne(targetEntity = CandidateAnswer.class)
    private CandidateAnswer candidateAnswer;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean isCorrect() {
        return this.correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public CandidateAnswer getCandidateAnswer() {
        return this.candidateAnswer;
    }

    public void setCandidateAnswer(CandidateAnswer candidateAnswer) {
        this.candidateAnswer = candidateAnswer;
    }

}
