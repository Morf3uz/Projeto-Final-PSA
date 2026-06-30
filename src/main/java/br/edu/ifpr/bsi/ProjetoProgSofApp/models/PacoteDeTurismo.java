package br.edu.ifpr.bsi.ProjetoProgSofApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_pacote_turismo")
public class PacoteDeTurismo extends GenericModel {

    @Column(name = "nome_pacote")
    private String nome;

    @Column(name = "preco_pacote")
    private Double preco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destino_id")
    private Destino destino;
}