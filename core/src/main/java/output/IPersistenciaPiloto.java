package output;

import model.Piloto;

public interface IPersistenciaPiloto {
    boolean existePiloto(Piloto p);
    boolean guardarPiloto(Piloto piloto);

}
