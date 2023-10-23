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
public enum MaritalStatus {
    
    Solteiro("Solteiro"),
    Casado("Casado");
    private String maritalStatus;

    private MaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
}
