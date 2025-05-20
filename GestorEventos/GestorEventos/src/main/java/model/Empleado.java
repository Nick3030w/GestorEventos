
package model;

import model.enums.Cargo;
import model.enums.Contrato;


public class Empleado extends Persona {
    
    private int empleadoID;
    private int personaID;
    private Cargo cargo;
    private Contrato contrato;
    private String eps;

    public Empleado() {
    }

    public int getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    @Override
    public String toString() {
        return "Empleado{" + "empleadoID=" + empleadoID + ", personaID=" + personaID + ", cargo=" + cargo + ", contrato=" + contrato + ", eps=" + eps + '}';
    }
    
    
    
    
    
    
}
