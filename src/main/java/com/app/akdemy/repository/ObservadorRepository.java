package com.app.akdemy.repository;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Observador;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ObservadorRepository extends CrudRepository<Observador, Long> {

    Iterable<Observador> findByEstudiante(Estudiante estudiante);

    @Query(value = "SELECT Observador.id, obs_fecha, obs_descargos, obs_estudiante, obs_profesor FROM estudiante JOIN acudiente_estudiante JOIN observador ON estudiante.id = acudiente_estudiante.id_estudiante AND estudiante.id = obs_estudiante WHERE id_acudiente = :acudienteID", nativeQuery = true)
    Iterable<Observador> getObservadorEstudianteAcudiente(@Param("acudienteID") Long acudienteID);
}
