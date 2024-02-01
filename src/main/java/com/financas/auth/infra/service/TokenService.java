package com.financas.auth.infra.service;

import com.financas.auth.domain.model.Usuario;

public interface TokenService {
    public String generateToken(Usuario usuario);

    public String validateToken(String token);
}
