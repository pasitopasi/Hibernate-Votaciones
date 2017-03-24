package POJO;
// Generated 07-feb-2017 13:28:55 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import org.hibernate.annotations.ColumnTransformer;

/**
 * Votante generated by hbm2java
 */
public class Votante implements java.io.Serializable {

    @Column(columnDefinition = "BLOB", name = "Contrasena")
    @ColumnTransformer(
            read = "AES_DECRYPT(Contrasena, 'semilla')",
            write = "AES_ENCRYPT(?, 'semilla')")
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private byte[] contrasena;
    private String voto;

    public Votante() {
    }

    public Votante(String dni, String nombre, String apellido, byte[] contrasena, String voto) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.voto = voto;
    }

    public Votante(String dni, String nombre, String apellido, byte[] contrasena) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }

    public Votante(String dni, byte[] contrasena) {
        this.dni = dni;
        this.contrasena = contrasena;
    }

    public Votante(String dni) {
        this.dni = dni;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public byte[] getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(byte[] contrasena) {
        this.contrasena = contrasena;
    }

    public String getVoto() {
        return this.voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    @Override
    public String toString() {
        return "Votante: " + "dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasena=" + contrasena + ", voto=" + voto;
    }

}