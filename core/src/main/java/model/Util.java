package model;

import exception.DocumentoInvalidoException;
import exception.FechaNactoInvalidoException;
import exception.IdInvalidoException;
import exception.NombreInvalidoException;

import java.security.PublicKey;
import java.time.LocalDate;

public class Util {

    public static void validarId(String id) throws IdInvalidoException {
        if (id == null || id.isEmpty() || id.trim().isEmpty()) {
            throw new IdInvalidoException("ID invalido");
        }

    }

    public static void validarNombre(String nombre) throws NombreInvalidoException {
        if (nombre.isEmpty() || nombre.trim().isEmpty() || nombre ==null){
            throw new NombreInvalidoException("El nombre es inválido");
        }
    }

    public static void validarDocumento(String documento) throws DocumentoInvalidoException {
        if(documento.isEmpty() || documento.trim().isEmpty() || documento == null){
            throw new DocumentoInvalidoException("El documento es inválido.");
        }
    }
    public static void validarFecha(LocalDate fecha_nacimiento) throws FechaNactoInvalidoException {
        //Validar edad


       if((LocalDate.now().getYear() - fecha_nacimiento.getYear()) <18 || fecha_nacimiento == null){
            throw new FechaNactoInvalidoException("El piloto debe ser mayor de 18 años");

        }
    }

}
