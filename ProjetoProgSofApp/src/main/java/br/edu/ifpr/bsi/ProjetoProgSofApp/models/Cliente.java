package br.edu.ifpr.bsi.ProjetoProgSofApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_cliente")
public class Cliente extends GenericModel {

    @Column(name = "nome_cliente", nullable = false)
    private String nome;

    @Column(name = "email_cliente", unique = true, nullable = false)
    private String email;

    @Column(name = "cpf_cliente", length = 11, unique = true)
    private String cpf;

    @Column(name = "senha_cliente")
    private String senha;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;
}