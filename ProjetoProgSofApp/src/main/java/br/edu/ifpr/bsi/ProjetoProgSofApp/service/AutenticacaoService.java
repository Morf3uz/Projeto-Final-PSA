package br.edu.ifpr.bsi.ProjetoProgSofApp.service;

import br.edu.ifpr.bsi.ProjetoProgSofApp.repositories.ClienteRepository;
import br.edu.ifpr.bsi.ProjetoProgSofApp.security.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clienteRepository.findByEmail(email)
                .map(UserAdapter::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}