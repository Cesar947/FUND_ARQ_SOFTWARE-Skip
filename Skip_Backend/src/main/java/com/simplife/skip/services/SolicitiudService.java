package com.simplife.skip.services;


import com.simplife.skip.models.Solicitud;
import org.springframework.transaction.annotation.Transactional;

public interface SolicitiudService {

    public Solicitud solicitar(Solicitud solicitud) throws Exception;

    @Transactional
    public int actualizarEstadoPasajero(Long solicitudId, Long pasajeroId, String estado) throws Exception;
}
