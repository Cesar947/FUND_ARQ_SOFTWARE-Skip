package com.simplife.skip.payload.requests;

import lombok.Data;

@Data
public class SolicitudRequest {

    //Mensaje al conductor
    private String mensaje;

    private Long pasajeroId;

    private Long viajeId;

    //Id del punto en donde se recogerá al pasajero
    private Long paradaEncuentroId;

}
