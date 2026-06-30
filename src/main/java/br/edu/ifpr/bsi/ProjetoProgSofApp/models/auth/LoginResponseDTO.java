package br.edu.ifpr.bsi.ProjetoProgSofApp.models.auth;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteDetailDTO;

public record LoginResponseDTO(ClienteDetailDTO usuario, String token) {}