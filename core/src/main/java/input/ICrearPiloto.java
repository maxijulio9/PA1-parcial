package input;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.UUID;

public interface ICrearPiloto {
    String crearPiloto(String id, String nombre, String documento, LocalDate fecha_nacimiento) throws RemoteException;
}
