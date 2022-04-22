package com.app.akdemy.Exception;

public class ProfesorNotFound extends Exception{

    public ProfesorNotFound(){
        super("Profesor no encontrado");
    }

    public ProfesorNotFound(String message){
        super(message);
    }
    
}
