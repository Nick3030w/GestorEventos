
package model;


public class Cliente extends Persona {
    
    private int clienteID;
    private int personaID;

    public Cliente() {
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    @Override
    public String toString() {
        return "Cliente{" + "clienteID=" + clienteID + ", personaID=" + personaID + '}';
    }
    
    
    
}
