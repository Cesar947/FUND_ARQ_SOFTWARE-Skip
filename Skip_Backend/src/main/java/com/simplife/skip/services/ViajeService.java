package com.simplife.skip.services;

import com.simplife.skip.models.Viaje;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ViajeService {

    //Métodos CRUD
    Viaje insertarViaje(Viaje viaje) throws Exception;
    List<Viaje> obtenerViajes() throws Exception;
        //usuarioConductorId es id de usuario de algún conductor
    List<Viaje> listarViajesPorConductor(Long usuarioConductorId) throws Exception;

    //Actualizar la fecha de viaje
    @Transactional
    int actualizarFechaViaje(String fechaViaje, Long viajeId) throws Exception;




}
