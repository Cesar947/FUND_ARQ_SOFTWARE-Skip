package com.simplife.skip.controllers;

import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> visualizarUsuarios() throws Exception{
        return this.usuarioService.listarUsuarios();
    }

    @PostMapping("/registro")
    public Usuario registrarUsuarioPrueba(@RequestBody Usuario usuario) throws Exception{
        return this.usuarioService.insertarUsuario(usuario);
    }

}