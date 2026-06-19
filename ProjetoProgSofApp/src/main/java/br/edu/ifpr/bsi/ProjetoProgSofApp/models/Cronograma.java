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
@Table(name = "tb_cronograma")
public class Cronograma extends GenericModel {

    @Column(name = "descricao_cronograma")
    private String descricao;

    @Column(name = "horario_cronograma")
    private String horario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pacote_id")
    private PacoteDeTurismo pacote;
}