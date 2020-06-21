package com.simplife.skip.payload.requests;

import com.simplife.skip.models.Parada;
import lombok.Data;

import java.util.List;

@Data
public class ViajeRequest {

    private Long conductorId;
    private String sentidoRuta;
    private List<Parada> paradas;
    private String tiempoEstimado;
    private String descripcion;
    private String fechaViaje;
    private String horaInicio;
    private String horaLlegada;

}
