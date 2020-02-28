/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author l11m13
 */
public class JavaApplication13 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/producto";
            String user = "root";
            String pwd = "mysqladmin";
            try (Connection conect = DriverManager.getConnection(url, user, pwd); Statement statement = conect.createStatement()) {
                String sql = "select * from dispositivos";
                try (ResultSet resultset = statement.executeQuery(sql)) {
                    while (resultset.next()) {
                       
                        String nombre = resultset.getString("nombre");
                        int precio = resultset.getInt("precio");
                        String tipo = resultset.getString("tipo");
                        System.out.println("nombre" + nombre);
                        System.out.println("precio" + precio);
                        System.out.println("tipo" + tipo);
                        System.out.println("==============================");
                    }
                    Scanner scan = new Scanner(System.in);
                    System.out.println("¿que desas hacer : insertar / actualizar /eliminar/procedure");
                    String accion = scan.nextLine();
                    if (accion.equals("insertar")) {
                       

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el tipo");
                        String tipo = scan.nextLine();
                        //
                        //panda
                        String query = "insert into dispositivos values(?,?,?)";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setString(1,nombre);
                        ps.setInt(2,Integer.parseInt(precio));
                        ps.setString(3, tipo);
                        ps.executeUpdate();

                    }
                    if (accion.equals("borrar")) {
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();
                        String query = "delete from dispositivos where nombre =?";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setString(1, nombre);
                        ps.executeUpdate();
                    }
                    if (accion.equals("actualizar")) {
                    

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el tipo");
                        String tipo = scan.nextLine();
                        //
                        String query = " update dispositivos set nombre=?, precio=? ,tipo=?";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setString(1, nombre);
                        ps.setInt(2, Integer.parseInt(precio));
                        ps.setString(3,tipo);
                        ps.executeUpdate();
                    }
                    if (accion.equals("procedure")) {
                     
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el tipo");
                        String tipo = scan.nextLine();
              
                        //
                        String query = "call insertar_dispositivos(?,?,?)";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setString(1, nombre);
                        ps.setInt(2, Integer.parseInt(precio));
                        ps.setString(3,tipo);
                        ps.executeUpdate();}
                     
                    resultset.close();
                    statement.close();
                    conect.close();
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }
    

