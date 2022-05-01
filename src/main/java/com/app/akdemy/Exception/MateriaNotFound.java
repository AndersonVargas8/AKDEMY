package com.app.akdemy.Exception;

public class MateriaNotFound extends Exception{

    public MateriaNotFound(){
        super("Materia no encontrada");
    }

    public MateriaNotFound(String message){
        super(message);
    }
    
}
