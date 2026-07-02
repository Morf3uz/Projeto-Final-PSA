package br.edu.ifpr.bsi.ProjetoProgSofApp.controller;

import br.edu.ifpr.bsi.ProjetoProgSofApp.mappers.ClienteMapper;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.auth.LoginRequestDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.auth.LoginResponseDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.models.cliente.ClienteDetailDTO;
import br.edu.ifpr.bsi.ProjetoProgSofApp.security.TokenService;
import br.edu.ifpr.bsi.ProjetoProgSofApp.security.UserAdapter;
import br.edu.ifpr.bsi.ProjetoProgSofApp.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            UserAdapter userAdapter = (UserAdapter) autenticacaoService.loadUserByUsername(request.email());
            if (!passwordEncoder.matches(request.senha(), userAdapter.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            String token = tokenService.gerarToken(userAdapter);
            ClienteDetailDTO usuario = clienteMapper.entityToDetailDTO(userAdapter.getCliente());
            return ResponseEntity.ok(new LoginResponseDTO(usuario, token));
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}