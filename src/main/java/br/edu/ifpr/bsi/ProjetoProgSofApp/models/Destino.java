package br.edu.ifpr.bsi.ProjetoProgSofApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_destino")
public class Destino extends GenericModel {

    @Column(name = "nome_destino")
    private String nome;

    @Column(name = "descricao_destino")
    private String descricao;

    @Column(name = "duracao_dias")
    private Integer duracaoDeDias;

    @Column(name = "categoria_destino")
    private String categoria;

    @Column(name = "pais_destino")
    private String pais;

    @Column(name = "imagem_url")
    private String imagemUrl;
}