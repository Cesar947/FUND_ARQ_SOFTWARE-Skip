package com.simplife.skip.payload.requests;

public class SolicitudRequest {

    //Mensaje al conductor
    private String mensaje;
    //La distancia entre el punto de encuentro y el destino
    private double distainciaTotal;

    private Long pasajeroId;

    private Long viajeId;

    //Id del punto en donde se recogerá al pasajero
    private Long paradaEncuentroId;

}
