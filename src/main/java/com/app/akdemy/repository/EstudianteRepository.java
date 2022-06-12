package com.app.akdemy.repository;

import java.util.List;
import java.util.Optional;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {
    public List<Estudiante> findByNombres(String nombres);

    public Optional<Estudiante> findByDocumento(String documento);

    public Optional<Estudiante> findByUsuario(User user);

    @Query(value = "SELECT id, est_apellidos, est_documento, est_fecha_nacimiento, est_nombres, est_eps,est_gsrh, est_tipo_doc, est_usuario FROM estudiante JOIN acudiente_estudiante ON estudiante.id = acudiente_estudiante.id_estudiante JOIN curso_estudiante ON estudiante.id = curso_estudiante.id_estudiante WHERE id_acudiente = :acudienteID", nativeQuery = true)
    Iterable<Estudiante> getEstudiantesByAcudiente(@Param("acudienteID") Long acudienteID);

    @Query(value = "SELECT id, est_apellidos, est_documento, est_fecha_nacimiento, est_nombres, est_eps,est_gsrh, est_tipo_doc, est_usuario FROM estudiante JOIN curso_estudiante ON estudiante.id = curso_estudiante.id_estudiante WHERE id_curso = :cursoID", nativeQuery = true)
    Iterable<Estudiante> getEstudiantesbyCurso(@Param("cursoID") Long cursoID);

    @Query(value = "SELECT * FROM estudiante JOIN acudiente_estudiante ae on estudiante.id = ae.id_estudiante WHERE id_acudiente = :acudienteID", nativeQuery = true)
    Iterable<Estudiante> getEstudiantesAcudiente(@Param("acudienteID") Long acudienteID);
}
