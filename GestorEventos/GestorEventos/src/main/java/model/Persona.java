
package model;

import java.util.Date;
import java.util.Objects;
import model.enums.TipoDocumento;

public abstract class Persona {
    
    private int personaID;
    private String numDocumento;
    private TipoDocumento tipoDocumento;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private Date fechaNacimiento;
    private String direccion;

    public Persona() {
    }

    public Persona(int personaID, String numDocumento, TipoDocumento tipoDocumento, String nombre, String apellido, String celular, String correo, Date fechaNacimiento, String direccion) {
        this.personaID = personaID;
        this.numDocumento = numDocumento;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    public Persona(int personaID) {
        this.personaID = personaID;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.personaID;
        hash = 23 * hash + Objects.hashCode(this.numDocumento);
        hash = 23 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.apellido);
        hash = 23 * hash + Objects.hashCode(this.celular);
        hash = 23 * hash + Objects.hashCode(this.correo);
        hash = 23 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 23 * hash + Objects.hashCode(this.direccion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (this.personaID != other.personaID) {
            return false;
        }
        if (!Objects.equals(this.numDocumento, other.numDocumento)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (this.tipoDocumento != other.tipoDocumento) {
            return false;
        }
        return Objects.equals(this.fechaNacimiento, other.fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Persona{" + "personaID=" + personaID + ", numDocumento=" + numDocumento + ", tipoDocumento=" + tipoDocumento + ", nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular + ", correo=" + correo + ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + '}';
    }
    
    
    
}
