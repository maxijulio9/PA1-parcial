package model;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public class Piloto {
    private String id;
    private String nombre;
    private String documento;
    private LocalDate fecha_nacimiento;

    private Piloto(String id, String nombre, String documento, LocalDate fecha_nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public static Piloto  getInstance(String id, String nombre, String documento, LocalDate fecha_nacimiento) {

        //validadores
        Util.validarId(id);
        Util.validarNombre(nombre);
        Util.validarDocumento(documento);
        Util.validarFecha(fecha_nacimiento);

        Piloto piloto = new Piloto(id,nombre, documento, fecha_nacimiento);
        return piloto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
