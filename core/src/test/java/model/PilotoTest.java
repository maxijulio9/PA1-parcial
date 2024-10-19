package model;

import exception.DocumentoInvalidoException;
import exception.FechaNactoInvalidoException;
import exception.IdInvalidoException;
import exception.NombreInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PilotoTest {
    @Test
    public void testPiloto() {
        Piloto piloto = Piloto.getInstance("2304i03i5235", "Franco Colapinto","123534ABC" ,LocalDate.of(2000, 01,01));
        Assertions.assertEquals("2304i03i5235", piloto.getId());
    }
    @Test
    public void testPilotoNombreInvalido() {

        Assertions.assertThrows(NombreInvalidoException.class, ()-> Piloto.getInstance("2304i03i5235", " ","123534ABC" ,LocalDate.of(2000, 01,01)) );
    }

    @Test
    public void testPilotoIDInvalido() {

        Assertions.assertThrows(IdInvalidoException.class, ()-> Piloto.getInstance(" ", "Franco Colapinto","123534ABC" ,LocalDate.of(2000, 01,01)) );
    }

    @Test
    public void testPilotoDocumentoInvalido() {

        Assertions.assertThrows(DocumentoInvalidoException.class, ()-> Piloto.getInstance("2304i03i5235", "Franco Colapinto"," " ,LocalDate.of(2000, 01,01)) );
    }

    @Test
    public void testPilotoMenorDeEdad() {

        Assertions.assertThrows(FechaNactoInvalidoException.class, ()-> Piloto.getInstance("2304i03i5235", "Franco Colapinto"," 123534ABC" ,LocalDate.of(2020, 01,01)) );
    }
}
