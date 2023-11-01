/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sga.ujc.demo.model.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mz.sga.ujc.demo.model.AbstractEntity;

/**
 *
 * @author Fenias Cossa
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "conta")
public class Conta extends AbstractEntity<Integer>{
    
    @Column(name="nuit", unique=true)
    private int nuit;

    @Column(unique = true)
    private  int codigo;

    private String senha;
    
    @Column(unique = true)
    private String email;

    @Column(name = "telefone")
    private int telefone;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    private static int autogenereteCodigo=1000001;
    public static int generateCodigo() {
        return ++autogenereteCodigo;
    }
}
