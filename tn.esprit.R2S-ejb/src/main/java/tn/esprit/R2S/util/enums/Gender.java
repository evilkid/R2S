/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.R2S.util.enums;

import java.io.Serializable;

/**
 *
 * @author Ouerghi Yassine
 */
public enum Gender implements Serializable{
    MALE("Male"), FEMALE("Female");

    private final String value; 

    private Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }


    
}
