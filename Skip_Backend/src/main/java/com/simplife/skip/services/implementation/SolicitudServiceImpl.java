package com.simplife.skip.services.implementation;

import com.simplife.skip.models.Parada;
import com.simplife.skip.models.Solicitud;
import com.simplife.skip.models.Usuario;
import com.simplife.skip.repositories.ParadaRepository;
import com.simplife.skip.repositories.SolicitudRepository;
import com.simplife.skip.repositories.UsuarioRepository;
import com.simplife.skip.repositories.ViajeRepository;
import com.simplife.skip.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simplife.skip.payload.requests.SolicitudRequest;
import org.springframework.transaction.annotation.Transactional;
import com.simplife.skip.models.Viaje;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
class SolicitudServiceImpl implements SolicitudService {

    private SolicitudRepository solicitudRepository;
    private ParadaRepository paradaRepository;
    private UsuarioRepository usuarioRepository;
    private ViajeRepository viajeRepository;

    @Autowired
    public SolicitudServiceImpl(SolicitudRepository solicitudRepository,
                                ParadaRepository paradaRepository,
                                UsuarioRepository usuarioRepository,
                                ViajeRepository viajeRepository){
        this.solicitudRepository = solicitudRepository;
        this.usuarioRepository = usuarioRepository;
        this.viajeRepository = viajeRepository;
        this.paradaRepository = paradaRepository;
    }

    @Override
    public Solicitud procesarSolicitud(SolicitudRequest solicitud) throws Exception{
        String mensaje = solicitud.getMensaje();
        Long pasajeroId = solicitud.getPasajeroId();
        Long viajeId = solicitud.getViajeId();
        Long paradaId = solicitud.getParadaEncuentroId();
        Solicitud solicitudNueva;
        try{
            Parada parada = this.paradaRepository.findById(paradaId).get();
            if (parada == null){
                parada = this.paradaRepository.save(parada);
            }
            Usuario pasajero = this.usuarioRepository.findById(pasajeroId).get();
            Viaje viaje = this.viajeRepository.findById(viajeId).get();
            solicitudNueva = new Solicitud(mensaje, pasajero, viaje, parada);

            DateTimeFormatter dtfFecha = DateTimeFormatter.ofPattern("dd/mm/yyyy");

            LocalDate fechaSolicitud = LocalDate.parse(LocalDate.now().toString(), dtfFecha);
            DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime horaSolicitud = LocalTime.parse(LocalTime.now().toString(), dtfHora);


            solicitudNueva.setFechaSolicitud(fechaSolicitud);
            solicitudNueva.setHoraSolicitud(horaSolicitud);
            solicitudNueva.setEstadoPasajero("En lista");

            solicitudNueva = this.solicitudRepository.save(solicitudNueva);
        }catch(Exception e){
            throw e;
        }


        return solicitudNueva;
    }

    @Transactional
    public int actualizarEstadoPasajero(Long solicitudId, Long pasajeroId, String estado) throws Exception{

        return this.actualizarEstadoPasajero(solicitudId, pasajeroId, estado);

    }


}