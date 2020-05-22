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
@Table(name = "solicitud")
@Entity
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitud_id")
    private Long id;

    @Column(name = "mensaje")
    private String mensaje;

    //Enviada - Aceptada - Rechazada
    @Column(name = "estado_solicitud")
    private String estadoSolicitud;

    @Column(name = "fechaSolicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "horaSolicitud")
    private LocalTime horaSolicitud;

    //No sé que significa alv
    @Column(name = "parada_llegada")
    private boolean paradaLlegada;

    @Column(name = "distancia_total")
    private double distanciaTotal;

    @Column(name = "pasajero_id")
    private Usuario pasajero;

    @Column(name = "viaje_id")
    private Viaje viaje;

    @Column(name = "parada_id")
    private Parada parada;

}
