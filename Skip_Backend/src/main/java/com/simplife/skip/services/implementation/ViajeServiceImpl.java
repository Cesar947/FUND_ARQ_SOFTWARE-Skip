package com.simplife.skip.services.implementation;

import com.simplife.skip.models.Viaje;
import com.simplife.skip.repositories.ViajeRepository;
import com.simplife.skip.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService {

    private ViajeRepository viajeRepository;

    @Autowired
    public ViajeServiceImpl(ViajeRepository viajeRepository){
        this.viajeRepository = viajeRepository;
    }

    @Override
    public Viaje insertarViaje(Viaje viaje) throws Exception {
        Viaje nuevoViaje;
        try{
            nuevoViaje = this.viajeRepository.save(viaje);
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
