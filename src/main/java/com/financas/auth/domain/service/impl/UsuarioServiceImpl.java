package com.financas.auth.domain.service.impl;

import com.financas.auth.domain.model.Usuario;
import com.financas.auth.domain.repository.UsuarioRepository;
import com.financas.auth.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(user);
    }

    @Override
    public UserDetails findByLogin(String login) {
        return this.usuarioRepository.findByLogin(login);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
