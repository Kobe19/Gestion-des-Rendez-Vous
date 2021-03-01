/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Daniel FOGUE et Yvan Doal
 */
public class DatabaseConfig {
    
    private final String databaseDriverClassPath;
    private final String url;
    private final String user;
    private final String password;

    public DatabaseConfig(String databaseDriverClassPath, String url, String user, String password) {
        this.databaseDriverClassPath = databaseDriverClassPath;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getDatabaseDriverClassPath() {
        return databaseDriverClassPath;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    
}
