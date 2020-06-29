package com.simplife.skip.controllers;

        import com.simplife.skip.models.Usuario;
        import com.simplife.skip.services.SolicitudService;
        import com.simplife.skip.services.UsuarioService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("skip/solicitudes")
public class SolicitudController {

    private SolicitudService solicitudService;

    private UsuarioService usuarioService;

    @Autowired
    public SolicitudController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public Usuario registrarUsuarioPrueba(@RequestBody Usuario usuario) throws Exception{
        return this.usuarioService.insertarUsuario(usuario);
    }

}
