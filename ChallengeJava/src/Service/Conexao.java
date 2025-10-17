package Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(Credenciais.driver);
            conn = DriverManager.getConnection(Credenciais.url, Credenciais.user, Credenciais.password);;
            System.out.println("Conexão com o banco estabelecida");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Falha na conexão com o banco de dados.");

            throw new RuntimeException("Não foi possível conectar ao banco de dados.", e);
        }
    }


}
