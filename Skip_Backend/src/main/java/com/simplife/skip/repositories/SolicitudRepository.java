package com.simplife.skip.repositories;

import com.simplife.skip.models.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Modifying
    @Query("UPDATE Solicitud s SET s.estadoPasajero = ?1 where s.id = ?2 and s.pasajero.id = ?3")
    int actualizarEstadoPasajero(String estado, Long solicitudId, Long clienteId);

}
