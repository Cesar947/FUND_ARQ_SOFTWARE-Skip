package com.simplife.skip.repositories;

import com.simplife.skip.models.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

   @Query("UPDATE Itinerario i SET i.numOrden = ?1 WHERE i.id = ?2")
    int actualizarNumeroDeOrden(int numOrden, Long itinerarioId);

}
