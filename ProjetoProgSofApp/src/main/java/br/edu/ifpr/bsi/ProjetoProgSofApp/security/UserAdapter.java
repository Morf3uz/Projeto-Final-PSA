package br.edu.ifpr.bsi.ProjetoProgSofApp.security;

import br.edu.ifpr.bsi.ProjetoProgSofApp.models.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class UserAdapter implements UserDetails {

    private final Cliente cliente;

    public UserAdapter(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Boolean.TRUE.equals(cliente.getIsAdmin())) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return cliente.getSenha();
    }

    @Override
    public String getUsername() {
        return cliente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public Cliente getCliente() {
        return cliente;
    }
}