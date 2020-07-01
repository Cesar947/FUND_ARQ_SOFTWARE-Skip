package com.simplife.skip.services.implementation;

import com.simplife.skip.models.*;
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
        Parada partida = viaje.getPartida();
        Parada destino = viaje.getDestino();
        Itinerario itinerario1;
        Itinerario itinerario2;
        try{
            if (this.paradaRepository.buscarPorLatYLong(partida.getLatitud(), partida.getLongitud()) == null){
                partida = this.paradaRepository.save(partida);
            }
            if (this.paradaRepository.buscarPorLatYLong(destino.getLatitud(), destino.getLongitud()) == null){
                destino = this.paradaRepository.save(destino);
            }

            Ruta ruta = new Ruta(viaje.getTiempoEstimado(), viaje.isSentidoRuta(), viaje.getDistancia(), true);
            itinerario1 = new Itinerario(ruta, partida, 1);
            itinerario2 = new Itinerario(ruta, destino, 2);
            this.itinerarioRepository.save(itinerario1);
            this.itinerarioRepository.save(itinerario2);

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
            nuevoViaje.setRuta(ruta);
            nuevoViaje.setNumeroPasajeros(0);


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

    @Override
    @Transactional
    public int actualizarEstadoViaje(String estado, Long viajeId) throws Exception{
        return this.viajeRepository.actualizarEstadoViaje(estado, viajeId);
    }

    @Override
    public List<Usuario> listarPasajerosPorViaje(Long viajeId) throws Exception{
        return this.viajeRepository.listarPasajerosRegistradosDelViaje(viajeId);
    }

    @Override
    public Viaje listarViajePorId(Long viajeId) throws Exception{
        return viajeRepository.findById(viajeId).get();
    }
}
