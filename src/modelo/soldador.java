/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmSoldadorN;

/**
 *
 * @author Estudiante
 */
public class soldador {
    
    //Los Parametros
    
        private String nombre; 
        private int edad;
        private int peso;   
        private String correo;
        
        public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
   

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    //----Guardar-----//
    
    public void GuardarBar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseconexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbSoldador(UUID_Soldador, Nombre_Soldador, Edad_Soldador, Peso_Soldador, Correo_Soldador) VALUES (?, ?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre());
            pstmt.setInt(3, getEdad());
            pstmt.setInt(4, getPeso());
            pstmt.setString(5, getCorreo());
            

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
           
    }
    
    
    //----Mostrat----//
   
          public void MostrarBar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = claseconexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Soldador", "Nombre_Soldador", "Edad_Soldador", "Peso_Soldador", "Correo_Soldador"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbSoldador");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Soldador"), 
                    rs.getString("Nombre_Soldador"), 
                    rs.getInt("Edad_Soldador"), 
                    rs.getInt("Peso_Soldador"),
                    rs.getString("Correo_Soldador"), 
                    
                      
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
        
          }
          
        //----Mostrat----//
         
        public void EliminarBar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseconexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbBarbero where UUID_Soldador = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }

        }
        
        
        //----Cargar----//
        
         public void cargarDatosTabla(frmSoldadorN vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbSoladores.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.tbSoladores.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.tbSoladores.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.tbSoladores.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.tbSoladores.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.tbSoladores.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombreS.setText(NombreDeTB);
            vista.txtEdadS.setText(EdadDeTb);
            vista.txtPesoS.setText(PesoDeTB);
            vista.txtCorreoS.setText(CorreoDeTB);
        }
         
        }
         
        public void ActualizarBar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = claseconexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbSoldador set Nombre_Soldador= ?, Edad_Soldador = ?, Peso_Soldador = ?, Correo_Soldador = ? where UUID_Soldador = ?");

                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setInt(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
        
        
      public void limpiar(frmSoldadorN vista) {
        vista.txtNombreS.setText("");
        vista.txtEdadS.setText("");
        vista.txtPesoS.setText("");
        vista.txtCorreoS.setText("");
    }
         
}
