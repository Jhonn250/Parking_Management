/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author atlas
 */
public class ConectionToDB {
   
    private DefaultTableModel model;
    private String url;
    private String user;
    private String psw;
    private String baseDeDatos;
    private Connection conexion;
    
    
    public ConectionToDB(){
        baseDeDatos = "bdejemplo";
        url  = "jdbc:postgresql://localhost:5432/";
        user = "postgres";
        psw = "postgres";
    
    }
    
    public ConectionToDB(String usuario, String password){
        baseDeDatos = "ejemplobd";
        baseDeDatos = "ejemplobd";
        url  = "jdbc:postgresql://localhost:5432/" + baseDeDatos;
        user = usuario;
        psw = password; 
    }
    
    
    
    public boolean PruebaConexion(){
        boolean band = conexion();
        if(band){
            try {
                this.endConexion();
            } catch (SQLException ex) {
                // Logger.getLogger(ConexionPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);              
            }
        }
        return band;
    }
    
    private boolean conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, user, psw);
            System.out.println("Conexion Correcta");
            return true;
            
        }
        catch(Exception e){
            //System.out.println("Error en la coexion");
            //System.out.println(e);
            return false;
        }
    }
    
    private void endConexion() throws SQLException{
        conexion.close();
    }
    
    /**
     * Ejecuta Scripts INSERT, DELETE, UPDATE
     * @return Regresa true si se tuvo exito, false y ocurrio un error
     */
    public boolean DMLInstruccion(String query){
        boolean band = true;
        
        try{
            System.out.println(query);
            conexion();
            Statement s = conexion.createStatement();
            s.executeUpdate(query);
            s.close();
            endConexion();
            
        }
        catch(Exception e){
            
            band  = false;
            //System.out.println(e);
        }
        
        return band;
        
    }
    
    /**
     * Regresa un modelo con los resultados de una consulta SQL
     * @param query
     * @return 
     */
    public DefaultTableModel SQLModel (String query){
        model = new DefaultTableModel();
        try{
            
            conexion();
            System.out.println(query);
            Statement s  = conexion.createStatement();
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData rsm = rs.getMetaData();
            int numCol = rsm.getColumnCount();
            for(int i = 1; i <= numCol; i++){
                model.addColumn(rsm.getColumnLabel(i));
                //System.out.println(rsm.getColumnLabel(i));
            }
            
            while(rs.next()){
                Object[] fila = new Object[numCol];
                for(int i = 0; i<numCol; i++){
                    fila[i] = rs.getObject(i+1);
                }
                model.addRow(fila);                
            }
            rs.close();
            s.close();
            endConexion();   
            //System.out.println("Ejecucion Correcta");
        } 
        catch(Exception e){    
            
            //System.out.println("Error en Ejecucion");
        
        }
        return model;
    }
}
    
