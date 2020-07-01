package com.simplife.skip.controllers;

import com.simplife.skip.models.Viaje;
import com.simplife.skip.payload.requests.ViajeRequest;
import com.simplife.skip.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/viajes")
public class ViajeController {

    private ViajeService viajeService;

    @Autowired
    public ViajeController(ViajeService viajeService){
        this.viajeService = viajeService;
    }

    @PostMapping("/publicacion")
    public Viaje publicarViaje(@RequestBody ViajeRequest viaje, Long conductorId) throws Exception{
        return null; //this.viajeService.insertarViaje(viaje);
    }

    @GetMapping
    public List<Viaje> visualizarViajes() throws Exception{
        return this.viajeService.obtenerViajes();
    }

    @PutMapping("/{viajeId}/actualizarFecha")
    public int actualizarFechaViaje(@RequestBody String fecha,
                                    @PathVariable("viajeId") Long viajeId) throws Exception{
        return this.viajeService.actualizarFechaViaje(fecha, viajeId);
    }

    @GetMapping("/conductor/{conductorId}")
    public List<Viaje> listarViajesPorConductor(@PathVariable("conductorId") Long conductorId) throws Exception{
        return this.viajeService.listarViajesPorConductor(conductorId);
    }

    @Transactional
    @PutMapping("/{viajeId}")
    public int actualizarEstadoDeViaje(@PathVariable("viajeId") Long viajeId, @RequestParam("estado") String estado){
        return 1;
    }

}
