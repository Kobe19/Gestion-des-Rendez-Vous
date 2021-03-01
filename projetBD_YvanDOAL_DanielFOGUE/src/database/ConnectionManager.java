/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Doal YVAN & FOGUE TSAKOU
 */
public class ConnectionManager {
    
    private static Connection con = null;
    
    public static DatabaseConfig databaseConfig;

    static {
        databaseConfig = new DatabaseConfig("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/projet_bd20", "root", "");
    }
    

    public static Connection getConnection(DatabaseConfig config) {
        if (con != null){ // on teste si la connexion marche 
            return con;
        }
        try {
            Class.forName(config.getDatabaseDriverClassPath());// driver necessaire a la connexion 
            con = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());// ici on set les info relatif a la connexion a la base de donnée
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            System.out.println(ex); //Afficher l'erreur
        }

        return con;
    }
}
