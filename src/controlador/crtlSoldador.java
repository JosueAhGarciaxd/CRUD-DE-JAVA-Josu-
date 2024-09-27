package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.soldador;
import vista.frmSoldadorN;

public class crtlSoldador extends MouseAdapter { 
    
    frmSoldadorN vista;
    soldador modelo; 

    // Constructor
    public crtlSoldador(frmSoldadorN Vista, soldador Modelo) {
        this.vista = Vista;
        this.modelo = Modelo;
        
        vista.btnAgregar.addMouseListener(this);
        modelo.MostrarBar(vista.tbSoladores);
        vista.btnEliminar.addMouseListener(this);
        vista.tbSoladores.addMouseListener(this);
        vista.btnEditar.addMouseListener(this);
        
        modelo.MostrarBar(vista.tbSoladores);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            modelo.setNombre(vista.txtNombreS.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdadS.getText()));
            modelo.setPeso(Integer.parseInt(vista.txtPesoS.getText()));
            modelo.setCorreo(vista.txtCorreoS.getText());

            modelo.limpiar(vista);
            modelo.GuardarBar();
            modelo.MostrarBar(vista.tbSoladores);
        }
        
        if (e.getSource() == vista.btnEliminar) {
            modelo.EliminarBar(vista.tbSoladores);
            modelo.MostrarBar(vista.tbSoladores);
            modelo.ActualizarBar(vista.tbSoladores);
        }
        
        if (e.getSource() == vista.tbSoladores) {
            modelo.cargarDatosTabla(vista);
        }
        
        if (e.getSource() == vista.btnEditar) {
    int filaSeleccionada = vista.tbSoladores.getSelectedRow();
    if (filaSeleccionada != -1) { // Verifica si hay una fila seleccionada
        modelo.setNombre(vista.txtNombreS.getText());
        modelo.setEdad(Integer.parseInt(vista.txtEdadS.getText()));
        modelo.setPeso(Integer.parseInt(vista.txtPesoS.getText()));
        modelo.setCorreo(vista.txtCorreoS.getText());

        modelo.ActualizarBar(vista.tbSoladores);
        modelo.MostrarBar(vista.tbSoladores); // Actualiza la tabla después de editar
    }
}

    }

    // Métodos no utilizados de MouseListener (puedes dejarlos vacíos)
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
