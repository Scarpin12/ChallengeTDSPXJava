package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Coneccao {
public static Connection getConnection(){
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("url","user","090407");
        System.out.println("Conecção com o banco estabelecida");
    } catch (SQLException e) {
        System.err.println("Erro ao conectar com o banco de dados");
        throw new RuntimeException(e);
    }
    return conn;
}



}
