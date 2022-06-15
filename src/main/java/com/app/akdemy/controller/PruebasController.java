package com.app.akdemy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.akdemy.entity.Estudiante;

@Controller
public class PruebasController{
    @PostMapping("/guardarDatos")
    public ResponseEntity<Estudiante> guardarDatos(@RequestBody String dato){
        Estudiante est = new Estudiante();
        est.setNombres("Mundo");
        if(dato.equals("Hola"))
            return new ResponseEntity<>(est,HttpStatus.CREATED);
        
        est.setNombres("Cielo");
        return new ResponseEntity<>(est,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("/leeDatos")
    public ResponseEntity<Estudiante> leerDatos(Estudiante estudiante){
        if(estudiante == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        estudiante.setApellidos("Vargas");

        return new ResponseEntity<>(estudiante,HttpStatus.OK);
    }
}