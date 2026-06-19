package br.edu.ifpr.bsi.ProjetoProgSofApp.security;

import br.edu.ifpr.bsi.ProjetoProgSofApp.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AutenticacaoService autenticacaoService;

    public JwtAuthenticationFilter(TokenService tokenService, AutenticacaoService autenticacaoService) {
        this.tokenService = tokenService;
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extrairToken(request);
            if (token != null) {
                String email = tokenService.validarToken(token);
                if (email != null) {
                    UserDetails userDetails = autenticacaoService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            // BLOCO TRY-CATCH QUE O CLAUDE APAGOU.
            // Se der erro no token, ele ignora silenciosamente em vez de dar Erro 403.
        }
        
        filterChain.doFilter(request, response);
    }

    private String extrairToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}