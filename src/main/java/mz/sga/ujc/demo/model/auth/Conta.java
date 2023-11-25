/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sga.ujc.demo.model.auth;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    
    @Column(unique = true)
    private Integer codigo;

    @Column(name="nuit", unique=true)
    private int nuit;

    private String senha;
    
    @Column(unique = true)
    private String email;

    @Column(name = "telefone")
    private int telefone;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
