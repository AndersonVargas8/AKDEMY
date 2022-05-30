package com.app.akdemy.Exception;

public class AcudienteNotFound extends Exception{

    public AcudienteNotFound(){
        super("Acudiente no encontrado");
    }

    public AcudienteNotFound(String message){
        super(message);
    }
    
}
