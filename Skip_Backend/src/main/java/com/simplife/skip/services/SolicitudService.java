package com.simplife.skip.services;


import com.simplife.skip.models.Solicitud;
import com.simplife.skip.payload.requests.SolicitudRequest;
import org.springframework.transaction.annotation.Transactional;

public interface SolicitudService {

    Solicitud procesarSolicitud(SolicitudRequest solicitud) throws Exception;

    @Transactional
    int actualizarEstadoPasajero(Long solicitudId, Long pasajeroId, String estado) throws Exception;
}
