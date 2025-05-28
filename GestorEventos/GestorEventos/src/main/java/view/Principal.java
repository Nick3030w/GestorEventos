/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Artista_Banda;
import model.Cliente;
import model.Conection.SqlDB;
import model.DAO.Artista_BandaDAO;
import model.DAO.ClienteDAO;
import model.DAO.EventoDAO;
import model.DAO.LugaresDAO;
import model.DAO.PromotorDAO;
import model.DAO.TicketDAO;
import model.enums.BandaGenero;
import model.enums.EstadoTicket;
import model.enums.TipoPromotor;
import model.enums.TipoTicket;
import model.Evento;
import model.Lugares;
import model.Promotor;
import model.Ticket;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
    }

    private void initComponents() {
        // Código de inicialización de componentes (generado por NetBeans)
        // ... (mantener el código original de initComponents)
    }

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        int index = jTabbedPane1.getSelectedIndex();

        switch (index) {
            case 0:
                ClienteDAO clienteDAO = new ClienteDAO();
                List<Cliente> clientes = clienteDAO.readAll();
                DefaultTableModel modeloCliente = (DefaultTableModel) tblCliente.getModel();
                modeloCliente.setRowCount(0);
                for (Cliente c : clientes) {
                    modeloCliente.addRow(new Object[] { c.getClienteID(), c.getPersonaID() });
                }
                break;

            case 1:
                EventoDAO eventoDAO = new EventoDAO();
                List<Evento> eventos = eventoDAO.readAll();
                DefaultTableModel modeloEvento = (DefaultTableModel) tblEvento.getModel();
                modeloEvento.setRowCount(0);
                for (Evento e : eventos) {
                    modeloEvento.addRow(
                            new Object[] { e.getEventoID(), e.getLugarID(), e.getNombreEvento(), e.getFecha() });
                }
                break;

            case 2:
                TicketDAO ticketDAO = new TicketDAO();
                List<Ticket> tickets = ticketDAO.readAll();
                DefaultTableModel modeloTicket = (DefaultTableModel) tblTicket.getModel();
                modeloTicket.setRowCount(0);
                for (Ticket t : tickets) {
                    modeloTicket.addRow(new Object[] {
                            t.getTicketID(), t.getClienteID(), t.getEventoID(),
                            t.getTipo().name(), t.getPrecio(), t.getEstado().name()
                    });
                }
                break;

            case 3:
                Artista_BandaDAO artistaDAO = new Artista_BandaDAO();
                List<Artista_Banda> artistas = artistaDAO.readAll();
                DefaultTableModel modeloArtista = (DefaultTableModel) tblArtista.getModel();
                modeloArtista.setRowCount(0);
                for (Artista_Banda a : artistas) {
                    modeloArtista.addRow(new Object[] {
                            a.getArtistaID(), a.getNombre(), a.getGenero(), a.getIntegrantes()
                    });
                }
                break;

            case 4:
                PromotorDAO promotorDAO = new PromotorDAO();
                List<Promotor> promotores = promotorDAO.readAll();
                DefaultTableModel modeloPromotor = (DefaultTableModel) tblPromotor.getModel();
                modeloPromotor.setRowCount(0);
                for (Promotor p : promotores) {
                    modeloPromotor.addRow(new Object[] {
                            p.getPromotorID(), p.getNombre(), p.getTipo().name(), p.getCelular(), p.getCorreo()
                    });
                }
                break;

            case 5:
                LugaresDAO lugarDAO = new LugaresDAO();
                List<Lugares> lugares = lugarDAO.readAll();
                DefaultTableModel modeloLugar = (DefaultTableModel) tblLugar.getModel();
                modeloLugar.setRowCount(0);
                for (Lugares l : lugares) {
                    modeloLugar.addRow(new Object[] {
                            l.getLugarID(), l.getNombre(), l.getDireccion(), l.getCapacidad()
                    });
                }
                break;
        }
    }

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {
        int index = jTabbedPane1.getSelectedIndex();

        switch (index) {
            case 0: // Clientes
                String idPersonaStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la persona:");
                if (idPersonaStr == null || idPersonaStr.trim().isEmpty()) {
                    return;
                }
                try {
                    int idPersona = Integer.parseInt(idPersonaStr);
                    Cliente nuevoCliente = new Cliente();
                    nuevoCliente.setPersonaID(idPersona);
                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.create(nuevoCliente);
                    JOptionPane.showMessageDialog(this, "Cliente creado exitosamente.");
                    btnConsultarActionPerformed(evt);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "ID de persona inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 1: // Eventos
                String idLugarStr = JOptionPane.showInputDialog(this, "ID del lugar:");
                String nombreEvento = JOptionPane.showInputDialog(this, "Nombre del evento:");
                String fechaStr = JOptionPane.showInputDialog(this, "Fecha (yyyy-MM-dd):");
                if (idLugarStr == null || nombreEvento == null || fechaStr == null) {
                    return;
                }
                try {
                    int idLugar = Integer.parseInt(idLugarStr);
                    java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);
                    Evento nuevoEvento = new Evento();
                    nuevoEvento.setLugarID(idLugar);
                    nuevoEvento.setNombreEvento(nombreEvento);
                    nuevoEvento.setFecha(fecha);
                    EventoDAO eventoDAO = new EventoDAO();
                    eventoDAO.create(nuevoEvento);
                    JOptionPane.showMessageDialog(this, "Evento creado exitosamente.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 2: // Tickets
                String idClienteStr = JOptionPane.showInputDialog(this, "ID del cliente:");
                String idEventoStr = JOptionPane.showInputDialog(this, "ID del evento:");
                String tipo = (String) JOptionPane.showInputDialog(this, "Tipo:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null, new String[] { "General", "Vip", "Platino" }, "General");
                String precioStr = JOptionPane.showInputDialog(this, "Precio:");
                String estado = (String) JOptionPane.showInputDialog(this, "Estado:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null, new String[] { "Disponible", "Vendido" }, "Disponible");

                if (idClienteStr == null || idEventoStr == null || tipo == null || precioStr == null
                        || estado == null) {
                    return;
                }
                try {
                    int idCliente = Integer.parseInt(idClienteStr);
                    int idEvento = Integer.parseInt(idEventoStr);
                    float precio = Float.parseFloat(precioStr);
                    Ticket nuevoTicket = new Ticket();
                    nuevoTicket.setClienteID(idCliente);
                    nuevoTicket.setEventoID(idEvento);
                    nuevoTicket.setTipo(TipoTicket.valueOf(tipo.toUpperCase()));
                    nuevoTicket.setPrecio(precio);
                    nuevoTicket.setEstado(EstadoTicket.valueOf(estado.toUpperCase()));
                    TicketDAO ticketDAO = new TicketDAO();
                    ticketDAO.create(nuevoTicket);
                    JOptionPane.showMessageDialog(this, "Ticket creado exitosamente.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 3: // Artistas
                String nombreArtista = JOptionPane.showInputDialog(this, "Nombre del artista/banda:");
                String genero = (String) JOptionPane.showInputDialog(this, "Género:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null,
                        new String[] { "Rock", "Heavy_metal", "Pop", "Urbano", "Salsa" }, "Rock");
                String integrantes = JOptionPane.showInputDialog(this, "Integrantes:");

                if (nombreArtista == null || genero == null || integrantes == null) {
                    return;
                }
                try {
                    Artista_Banda nuevoArtista = new Artista_Banda();
                    nuevoArtista.setNombre(nombreArtista);
                    nuevoArtista.setGenero(BandaGenero.valueOf(genero));
                    nuevoArtista.setIntegrantes(integrantes);
                    Artista_BandaDAO artistaDAO = new Artista_BandaDAO();
                    artistaDAO.create(nuevoArtista);
                    JOptionPane.showMessageDialog(this, "Artista/Banda creado exitosamente.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 4: // Promotores
                String nombrePromotor = JOptionPane.showInputDialog(this, "Nombre del promotor:");
                String tipoPromotor = (String) JOptionPane.showInputDialog(this, "Tipo:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null, new String[] { "Comercial", "Patrocinador", "Recintos" },
                        "Comercial");
                String celular = JOptionPane.showInputDialog(this, "Celular:");
                String correo = JOptionPane.showInputDialog(this, "Correo:");

                if (nombrePromotor == null || tipoPromotor == null || celular == null || correo == null) {
                    return;
                }
                try {
                    Promotor nuevoPromotor = new Promotor();
                    nuevoPromotor.setNombre(nombrePromotor);
                    nuevoPromotor.setTipo(TipoPromotor.valueOf(tipoPromotor.toUpperCase()));
                    nuevoPromotor.setCelular(celular);
                    nuevoPromotor.setCorreo(correo);
                    PromotorDAO promotorDAO = new PromotorDAO();
                    promotorDAO.create(nuevoPromotor);
                    JOptionPane.showMessageDialog(this, "Promotor creado exitosamente.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 5: // Lugares
                String nombreLugar = JOptionPane.showInputDialog(this, "Nombre del lugar:");
                String direccion = JOptionPane.showInputDialog(this, "Dirección:");
                String capacidadStr = JOptionPane.showInputDialog(this, "Capacidad:");

                if (nombreLugar == null || direccion == null || capacidadStr == null) {
                    return;
                }
                try {
                    int capacidad = Integer.parseInt(capacidadStr);
                    Lugares nuevoLugar = new Lugares();
                    nuevoLugar.setNombre(nombreLugar);
                    nuevoLugar.setDireccion(direccion);
                    nuevoLugar.setCapacidad(capacidad);
                    LugaresDAO lugarDAO = new LugaresDAO();
                    lugarDAO.create(nuevoLugar);
                    JOptionPane.showMessageDialog(this, "Lugar creado exitosamente.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        int index = jTabbedPane1.getSelectedIndex();

        switch (index) {
            case 0: // Clientes
                int filaCliente = tblCliente.getSelectedRow();
                if (filaCliente == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idCliente = (int) tblCliente.getValueAt(filaCliente, 0);
                String nuevoIdPersonaStr = JOptionPane.showInputDialog(this, "Nuevo ID persona:",
                        tblCliente.getValueAt(filaCliente, 1));
                if (nuevoIdPersonaStr == null) {
                    return;
                }
                try {
                    int nuevoIdPersona = Integer.parseInt(nuevoIdPersonaStr);
                    Cliente cliente = new Cliente();
                    cliente.setClienteID(idCliente);
                    cliente.setPersonaID(nuevoIdPersona);
                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.update(cliente);
                    JOptionPane.showMessageDialog(this, "Cliente actualizado.");
                    btnConsultarActionPerformed(evt);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 1: // Eventos
                int filaEvento = tblEvento.getSelectedRow();
                if (filaEvento == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un evento.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idEvento = (int) tblEvento.getValueAt(filaEvento, 0);
                String nuevoLugarStr = JOptionPane.showInputDialog(this, "Nuevo ID lugar:",
                        tblEvento.getValueAt(filaEvento, 1));
                String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:",
                        tblEvento.getValueAt(filaEvento, 2));
                String nuevaFechaStr = JOptionPane.showInputDialog(this, "Nueva fecha (yyyy-MM-dd):",
                        tblEvento.getValueAt(filaEvento, 3));
                if (nuevoLugarStr == null || nuevoNombre == null || nuevaFechaStr == null) {
                    return;
                }
                try {
                    Evento evento = new Evento();
                    evento.setEventoID(idEvento);
                    evento.setLugarID(Integer.parseInt(nuevoLugarStr));
                    evento.setNombreEvento(nuevoNombre);
                    evento.setFecha(java.sql.Date.valueOf(nuevaFechaStr));
                    EventoDAO eventoDAO = new EventoDAO();
                    eventoDAO.update(evento);
                    JOptionPane.showMessageDialog(this, "Evento actualizado.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 2: // Tickets
                int filaTicket = tblTicket.getSelectedRow();
                if (filaTicket == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un ticket.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idTicket = (int) tblTicket.getValueAt(filaTicket, 0);
                String nuevoIdClienteStr = JOptionPane.showInputDialog(this, "Nuevo ID cliente:",
                        tblTicket.getValueAt(filaTicket, 1));
                String nuevoIdEventoStr = JOptionPane.showInputDialog(this, "Nuevo ID evento:",
                        tblTicket.getValueAt(filaTicket, 2));
                String nuevoTipo = (String) JOptionPane.showInputDialog(this, "Nuevo tipo:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null, new String[] { "General", "Vip", "Platino" },
                        tblTicket.getValueAt(filaTicket, 3));
                String nuevoPrecioStr = JOptionPane.showInputDialog(this, "Nuevo precio:",
                        tblTicket.getValueAt(filaTicket, 4));
                String nuevoEstado = (String) JOptionPane.showInputDialog(this, "Nuevo estado:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null, new String[] { "Disponible", "Vendido" },
                        tblTicket.getValueAt(filaTicket, 5));

                if (nuevoIdClienteStr == null || nuevoIdEventoStr == null || nuevoTipo == null || nuevoPrecioStr == null
                        || nuevoEstado == null) {
                    return;
                }
                try {
                    Ticket ticket = new Ticket();
                    ticket.setTicketID(idTicket);
                    ticket.setClienteID(Integer.parseInt(nuevoIdClienteStr));
                    ticket.setEventoID(Integer.parseInt(nuevoIdEventoStr));
                    ticket.setTipo(TipoTicket.valueOf(nuevoTipo.toUpperCase()));
                    ticket.setPrecio(Float.parseFloat(nuevoPrecioStr));
                    ticket.setEstado(EstadoTicket.valueOf(nuevoEstado.toUpperCase()));
                    TicketDAO ticketDAO = new TicketDAO();
                    ticketDAO.update(ticket);
                    JOptionPane.showMessageDialog(this, "Ticket actualizado.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 3: // Artistas
                int filaArtista = tblArtista.getSelectedRow();
                if (filaArtista == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un artista/banda.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idArtista = (int) tblArtista.getValueAt(filaArtista, 0);
                String nuevoNombreArtista = JOptionPane.showInputDialog(this, "Nuevo nombre:",
                        tblArtista.getValueAt(filaArtista, 1));
                String nuevoGenero = (String) JOptionPane.showInputDialog(this, "Nuevo género:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null,
                        new String[] { "Rock", "Heavy_metal", "Pop", "Urbano", "Salsa" },
                        tblArtista.getValueAt(filaArtista, 2));
                String nuevosIntegrantes = JOptionPane.showInputDialog(this, "Nuevos integrantes:",
                        tblArtista.getValueAt(filaArtista, 3));

                if (nuevoNombreArtista == null || nuevoGenero == null || nuevosIntegrantes == null) {
                    return;
                }
                try {
                    Artista_Banda artista = new Artista_Banda();
                    artista.setArtistaID(idArtista);
                    artista.setNombre(nuevoNombreArtista);
                    artista.setGenero(BandaGenero.valueOf(nuevosIntegrantes));
                    artista.setIntegrantes(nuevosIntegrantes);
                    Artista_BandaDAO artistaDAO = new Artista_BandaDAO();
                    artistaDAO.update(artista);
                    JOptionPane.showMessageDialog(this, "Artista/Banda actualizado.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 4: // Promotores
                int filaPromotor = tblPromotor.getSelectedRow();
                if (filaPromotor == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un promotor.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idPromotor = (int) tblPromotor.getValueAt(filaPromotor, 0);
                String nuevoNombrePromotor = JOptionPane.showInputDialog(this, "Nuevo nombre:",
                        tblPromotor.getValueAt(filaPromotor, 1));
                String nuevoTipoPromotor = (String) JOptionPane.showInputDialog(this, "Nuevo tipo:", "Seleccione",
                        JOptionPane.QUESTION_MESSAGE, null, new String[] { "Comercial", "Patrocinador", "Recintos" },
                        tblPromotor.getValueAt(filaPromotor, 2));
                String nuevoCelular = JOptionPane.showInputDialog(this, "Nuevo celular:",
                        tblPromotor.getValueAt(filaPromotor, 3));
                String nuevoCorreo = JOptionPane.showInputDialog(this, "Nuevo correo:",
                        tblPromotor.getValueAt(filaPromotor, 4));

                if (nuevoNombrePromotor == null || nuevoTipoPromotor == null || nuevoCelular == null
                        || nuevoCorreo == null) {
                    return;
                }
                try {
                    Promotor promotor = new Promotor();
                    promotor.setPromotorID(idPromotor);
                    promotor.setNombre(nuevoNombrePromotor);
                    promotor.setTipo(TipoPromotor.valueOf(nuevoTipoPromotor.toUpperCase()));
                    promotor.setCelular(nuevoCelular);
                    promotor.setCorreo(nuevoCorreo);
                    PromotorDAO promotorDAO = new PromotorDAO();
                    promotorDAO.update(promotor);
                    JOptionPane.showMessageDialog(this, "Promotor actualizado.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 5: // Lugares
                int filaLugar = tblLugar.getSelectedRow();
                if (filaLugar == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un lugar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idLugar = (int) tblLugar.getValueAt(filaLugar, 0);
                String nuevoNombreLugar = JOptionPane.showInputDialog(this, "Nuevo nombre:",
                        tblLugar.getValueAt(filaLugar, 1));
                String nuevaDireccion = JOptionPane.showInputDialog(this, "Nueva dirección:",
                        tblLugar.getValueAt(filaLugar, 2));
                String nuevaCapacidadStr = JOptionPane.showInputDialog(this, "Nueva capacidad:",
                        tblLugar.getValueAt(filaLugar, 3));

                if (nuevoNombreLugar == null || nuevaDireccion == null || nuevaCapacidadStr == null) {
                    return;
                }
                try {
                    int nuevaCapacidad = Integer.parseInt(nuevaCapacidadStr);
                    Lugares lugar = new Lugares();
                    lugar.setLugarID(idLugar);
                    lugar.setNombre(nuevoNombreLugar);
                    lugar.setDireccion(nuevaDireccion);
                    lugar.setCapacidad(nuevaCapacidad);
                    LugaresDAO lugarDAO = new LugaresDAO();
                    lugarDAO.update(lugar);
                    JOptionPane.showMessageDialog(this, "Lugar actualizado.");
                    btnConsultarActionPerformed(evt);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int index = jTabbedPane1.getSelectedIndex();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar registro?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            switch (index) {
                case 0: // Clientes
                    int filaCliente = tblCliente.getSelectedRow();
                    if (filaCliente == -1) {
                        throw new Exception("Seleccione un cliente");
                    }
                    int idCliente = (int) tblCliente.getValueAt(filaCliente, 0);
                    new ClienteDAO().eliminar(idCliente);
                    break;

                case 1: // Eventos
                    int filaEvento = tblEvento.getSelectedRow();
                    if (filaEvento == -1) {
                        throw new Exception("Seleccione un evento");
                    }
                    new EventoDAO().delete((int) tblEvento.getValueAt(filaEvento, 0));
                    break;

                case 2: // Tickets
                    int filaTicket = tblTicket.getSelectedRow();
                    if (filaTicket == -1) {
                        throw new Exception("Seleccione un ticket");
                    }
                    new TicketDAO().delete((int) tblTicket.getValueAt(filaTicket, 0));
                    break;

                case 3: // Artistas
                    int filaArtista = tblArtista.getSelectedRow();
                    if (filaArtista == -1) {
                        throw new Exception("Seleccione un artista/banda");
                    }
                    new Artista_BandaDAO().delete((int) tblArtista.getValueAt(filaArtista, 0));
                    break;

                case 4: // Promotores
                    int filaPromotor = tblPromotor.getSelectedRow();
                    if (filaPromotor == -1) {
                        throw new Exception("Seleccione un promotor");
                    }
                    new PromotorDAO().delete((int) tblPromotor.getValueAt(filaPromotor, 0));
                    break;

                case 5: // Lugares
                    int filaLugar = tblLugar.getSelectedRow();
                    if (filaLugar == -1) {
                        throw new Exception("Seleccione un lugar");
                    }
                    new LugaresDAO().delete((int) tblLugar.getValueAt(filaLugar, 0));
                    break;
            }
            btnConsultarActionPerformed(evt);
            JOptionPane.showMessageDialog(this, "Eliminado con éxito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblArtista;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblEvento;
    private javax.swing.JTable tblLugar;
    private javax.swing.JTable tblPromotor;
    private javax.swing.JTable tblTicket;
    // End of variables declaration
}