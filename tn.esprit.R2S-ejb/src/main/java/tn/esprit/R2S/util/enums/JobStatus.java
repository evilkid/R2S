/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.R2S.util.enums;

/**
 *
 * @author Ouerghi Yassine
 */
public enum JobStatus {
    OPEN("Open"), CLOSED("Closed");

    private final String value;

    private JobStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
