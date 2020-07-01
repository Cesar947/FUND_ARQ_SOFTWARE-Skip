package com.simplife.skip.repositories;

import com.simplife.skip.models.Usuario;
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

    @Modifying
    @Query("UPDATE Viaje v SET v.estadoViaje = ?1 WHERE v.id = ?2")
    int actualizarEstadoViaje(String estado, Long viajeId);

    @Query("SELECT u FROM Usuario u JOIN Solicitud s ON s.pasajero.id = u.id " +
            "JOIN Viaje v ON v.id = s.viaje.id WHERE v.id = ?1")
    List<Usuario> listarPasajerosRegistradosDelViaje(Long viajeId);

    @Query("UPDATE Viaje v SET v.numeroPasajeros = v.numeroPasajeros + 1 WHERE v.id = ?1")
    int actualizarNumeroPasajeros(Long viajeId);

   /* @Query("SELECT v FROM Viaje v JOIN Ruta r ON r.id = v.ruta.id "
    + "JOIN Itinerario i ON i.ruta.id = r.id")*/

}
