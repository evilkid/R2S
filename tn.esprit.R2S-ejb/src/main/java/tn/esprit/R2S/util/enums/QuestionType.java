/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.R2S.util.enums;

/**
 * @author Ouerghi Yassine
 */
public enum QuestionType {
    CHECKBOX("checkbox"), RADIOBOX("radiobox");

    private final String value;

    private QuestionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
