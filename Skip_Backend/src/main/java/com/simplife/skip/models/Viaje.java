package com.simplife.skip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viaje")
@Entity
public class Viaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private Long id;

    @Column(name = "conductor_id")
    private Usuario conductor;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_llegada")
    private LocalTime horaLlegada;

    @Column(name = "hora_publicacion")
    private LocalTime horaPublicacion;

    @Column(name = "visualizacion_habilitada")
    private boolean visualizacionHabilitada;

    @Column(name = "ruta_id")
    private Ruta ruta;

    //Iniciado - Finalizado
    @Column(name = "estado_viaje")
    private String estadoViaje;



}
