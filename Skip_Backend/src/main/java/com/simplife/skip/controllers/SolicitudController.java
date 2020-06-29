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
    public SolicitudController(SolicitudService solicitudService){
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public Solicitud procesarSolicitud(@RequestBody SolicitudRequest solicitud) throws Exception{
        return this.solicitudService.procesarSolicitud(solicitud);
    }

    @PutMapping("/{solicitudId}")
    @Transactional
    public int actualizarEstadoPasajero(@PathVariable("solicitudId") Long solicitudId,
                                        @RequestParam("pasajeroId") Long pasajeroId,
                                        @RequestParam("estado") String estado) throws Exception{
        return this.solicitudService.actualizarEstadoPasajero(solicitudId,pasajeroId,estado);
    }


}
