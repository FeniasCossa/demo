/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sga.ujc.demo.model.enums;

/**
 *
 * @author Fenias Cossa
 */
public enum Gender {
    
   Masculino("Masculino"),
   Femenino("Femenino");
   private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
   
}
