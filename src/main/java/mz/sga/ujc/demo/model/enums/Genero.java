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
public enum Genero {
    
   Masculino("Masculino"),
   Femenino("Femenino");
   private String genero;

    private Genero(String genero) {
        this.genero = genero;
    }

    public String getGenenro() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
   
}
