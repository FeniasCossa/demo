
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
