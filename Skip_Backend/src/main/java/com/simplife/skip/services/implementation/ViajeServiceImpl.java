package com.simplife.skip.services.implementation;

import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.payload.requests.ViajeRequest;
import com.simplife.skip.repositories.*;
import com.simplife.skip.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService {

    private ViajeRepository viajeRepository;
    private UsuarioRepository usuarioRepository;
    private RutaRepository rutaRepository;
    private ItinerarioRepository itinerarioRepository;
    private ParadaRepository paradaRepository;

    @Autowired
    public ViajeServiceImpl(ViajeRepository viajeRepository, UsuarioRepository usuarioRepository){
        this.viajeRepository = viajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Viaje insertarViaje(ViajeRequest viaje) throws Exception {
        Viaje nuevoViaje;
        Usuario conductor;

        try{
            conductor = this.usuarioRepository.findById(viaje.getConductorId()).get();
            DateTimeFormatter dtfFecha = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            LocalDate nuevaFechaViaje = LocalDate.parse(viaje.getFechaViaje(), dtfFecha);
            DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime nuevaHoraInicio = LocalTime.parse(viaje.getHoraInicio(), dtfHora);
            LocalTime nuevaHoraLlegada = LocalTime.parse(viaje.getHoraLlegada(), dtfHora);
            nuevoViaje = new Viaje(conductor, viaje.getDescripcion(),
                    nuevaFechaViaje, nuevaHoraInicio, nuevaHoraLlegada);
            nuevoViaje.setFechaPublicacion(LocalDate.now());
            nuevoViaje.setHoraPublicacion(LocalTime.now());
            nuevoViaje.setVisualizacionHabilitada(true);
            nuevoViaje.setEstadoViaje("PUBLICADO");
            nuevoViaje.setEstadoTabla(true);

            nuevoViaje = this.viajeRepository.save(nuevoViaje);
        }catch(Exception e){
            throw e;
        }
        return nuevoViaje;
    }

    @Override
    public List<Viaje> obtenerViajes() throws Exception {
        List<Viaje> listaViajes;
        try{
            listaViajes = this.viajeRepository.findAll();
        }catch(Exception e){
            throw e;
        }
        return listaViajes;
    }

    @Override
    public List<Viaje> listarViajesPorConductor(Long usuarioConductorId) throws Exception {
        List<Viaje> listaViajesPorConductor;
        try{
            listaViajesPorConductor = this.viajeRepository.listarPorConductor(usuarioConductorId);
        }catch(Exception e){
            throw e;
        }
        return listaViajesPorConductor;
    }

    @Override
    @Transactional
    public int actualizarFechaViaje(String fechaViaje, Long viajeId) throws Exception {
        int viajeActualizado;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            LocalDate nuevaFechaViaje = LocalDate.parse(fechaViaje, dtf);
            viajeActualizado = this.viajeRepository.actualizarFechaViaje(nuevaFechaViaje, viajeId);
        }catch(Exception e){
            throw e;
        }
        return viajeActualizado;
    }
}
