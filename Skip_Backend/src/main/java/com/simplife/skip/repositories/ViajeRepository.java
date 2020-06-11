package com.simplife.skip.repositories;

import com.simplife.skip.models.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v INNER JOIN Usuario u ON v.conductor.id = u.id" +
            " WHERE u.id = ?1 ORDER BY v.fechaViaje DESC")
    List<Viaje> listarPorConductor(Long usuarioConductorId);


    @Modifying
    @Query("UPDATE Viaje v SET v.fechaViaje = ?1 WHERE v.id = ?2")
    int actualizarFechaViaje(LocalDate fecha, Long viajeId);

}
