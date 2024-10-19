package usecase;

import exception.PilotoExistenteException;
import input.ICrearPiloto;
import model.Piloto;
import output.IPersistenciaPiloto;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.UUID;

public class CrearPilotoUseCase implements ICrearPiloto {
    IPersistenciaPiloto myDB;
    public CrearPilotoUseCase(IPersistenciaPiloto myDB) {
        this.myDB = myDB;
    }

    @Override
    public String crearPiloto(String id, String nombre, String documento, LocalDate fecha_nacimiento) throws RuntimeException {

        Piloto piloto =  Piloto.getInstance(id, nombre, documento, fecha_nacimiento);

        if (myDB.existePiloto(piloto)){
            throw new PilotoExistenteException("Piloto ya existente.");
        }

        myDB.guardarPiloto(piloto);
        return piloto.getId();
    }
}
