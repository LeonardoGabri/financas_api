package com.financas.auth.api.controller;

import com.financas.auth.api.dto.LoginResponseDto;
import com.financas.auth.api.request.LoginRegisterRequest;
import com.financas.auth.api.request.LoginRequest;
import com.financas.auth.domain.model.Usuario;
import com.financas.auth.domain.service.UsuarioService;
import com.financas.auth.infra.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest login){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid LoginRegisterRequest loginRegister){
        if(usuarioService.findByLogin(loginRegister.getLogin()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptPassword = usuarioService.encriptPassword(loginRegister.getPassword());

        Usuario usuario = Usuario.builder()
                .login(loginRegister.getLogin())
                .password(encryptPassword)
                .role(loginRegister.getRole())
                .build();

        usuarioService.salvar(usuario);
        return ResponseEntity.ok().build();
    }
}
