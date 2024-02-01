package com.financas.auth.domain.service;

import com.financas.auth.domain.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {

    public UserDetails findByLogin(String login);

    public String encriptPassword(String password);

    public Usuario salvar(Usuario usuario);

}
