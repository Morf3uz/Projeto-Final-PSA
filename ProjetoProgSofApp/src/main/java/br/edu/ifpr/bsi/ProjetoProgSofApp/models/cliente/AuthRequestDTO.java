package br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    private String email;
    private String senha;
}