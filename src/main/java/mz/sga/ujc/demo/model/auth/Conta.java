/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sga.ujc.demo.model.auth;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Conta implements Serializable{
    
    @Id
    @NotNull(message = "O campo nuit nao Pode ser nulo")
    @Column(name = "nuit", nullable =false, unique = true)
    private int nuit;

    @Column(unique = true)
    private  int codigo;

    private String senha;
    @Column(unique = true)
    private String email;

    @Column(name = "telefone")
    private int telefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    
    private static int autogenereteCodigo=1000001;
    public static int generateCodigo() {
        return ++autogenereteCodigo;
    }
}
