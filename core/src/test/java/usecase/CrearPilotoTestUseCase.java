package usecase;

import exception.*;
import model.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.listeners.MockitoListener;
import output.IPersistenciaPiloto;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoTestUseCase {
    @Mock
    IPersistenciaPiloto myDB;


    @Test
    public void testCrearPilotoExitosamenteYGuardarEnDB() {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        when(myDB.existePiloto(Mockito.any(Piloto.class))).thenReturn(false);
        String id =  crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", "123456ABC", LocalDate.of(2000, 01,01));
        Assertions.assertEquals("cabbd417-1841-4b25-8798-e8d54df1416e", id);

        Mockito.verify(myDB, Mockito.times(1)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.times(1)).guardarPiloto(Mockito.any(Piloto.class));
    }

/*
    @Test
    public void testCrearPilotoExitosamenteYNoGuardarEnDB()  {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        when(myDB.existePiloto(Mockito.any(Piloto.class))).thenReturn(false);
        when(myDB.guardarPiloto(Mockito.any(Piloto.class))).thenReturn(false);

        String id =  crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", "123456ABC", LocalDate.of(2000, 01,01));

        Assertions.assertEquals(id, crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", "123456ABC", LocalDate.of(2000, 01,01)));
        Assertions.assertThrows(ErrorGuardarPilotoEnDBException.class, ()->  crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", "123456ABC", LocalDate.of(2000, 01,01)));


      //  Mockito.verify(myDB, Mockito.times(2)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.times(1)).guardarPiloto(Mockito.any(Piloto.class));
    }

 */

    @Test
    public void testPilotoExistente()  {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        when(myDB.existePiloto(Mockito.any(Piloto.class))).thenReturn(true);
        Assertions.assertThrows(PilotoExistenteException.class, () -> crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", "123456ABC", LocalDate.of(2000, 01,01)) );

        Mockito.verify(myDB, Mockito.times(1)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.never()).guardarPiloto(Mockito.any(Piloto.class));
    }

    @Test
    public void testPilotoNombreInvalido()  {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        Assertions.assertThrows(NombreInvalidoException.class, () -> crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", " ", "123456ABC", LocalDate.of(2020, 01,01)) );
        Mockito.verify(myDB, Mockito.times(0)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.never()).guardarPiloto(Mockito.any(Piloto.class));

    }
    @Test
    public void testPilotoNombreValido() throws RemoteException {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        Assertions.assertDoesNotThrow( () -> crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", "123456ABC", LocalDate.of(2000, 01,01)) );
        Mockito.verify(myDB, Mockito.times(1)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.times(1)).guardarPiloto(Mockito.any(Piloto.class));
    }

    @Test
    public void testPilotoMenorDeEdad() {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        Assertions.assertThrows(FechaNactoInvalidoException.class, () -> crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", "123456ABC", LocalDate.of(2010, 01,01)) );
        Mockito.verify(myDB, Mockito.times(0)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.never()).guardarPiloto(Mockito.any(Piloto.class));

    }

    @Test
    public void testPilotoDocumentoInvalido() {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        Assertions.assertThrows(DocumentoInvalidoException.class, () -> crearPilotoUseCase.crearPiloto("cabbd417-1841-4b25-8798-e8d54df1416e", "Franco Colapinto", " ",  LocalDate.of(2000, 01,01)) );
        Mockito.verify(myDB, Mockito.times(0)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.never()).guardarPiloto(Mockito.any(Piloto.class));


    }
    @Test
    public void testPilotoIdInvalido() {
        CrearPilotoUseCase crearPilotoUseCase = new CrearPilotoUseCase(myDB);

        Assertions.assertThrows(IdInvalidoException.class, () -> crearPilotoUseCase.crearPiloto(" ", "Franco Colapinto", "123456AB",  LocalDate.of(2000, 01,01)) );
        Mockito.verify(myDB, Mockito.times(0)).existePiloto(Mockito.any(Piloto.class));
        Mockito.verify(myDB, Mockito.never()).guardarPiloto(Mockito.any(Piloto.class));


    }





}
