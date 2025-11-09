package conectecare.conexao;//package conectecare.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Credenciais {
    public static Properties properties = new Properties();

    static {
        try (InputStream input = Credenciais.class.getClassLoader().getResourceAsStream("aplicacao.properties")) {

            if (input == null) {
                throw new RuntimeException("Arquivo aplicacao.properties não encontrado!");
            }
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar configurações do banco", e);
        }
    }
    public static final String url = properties.getProperty("db.url");
    public static final String user = properties.getProperty("db.user");
    public static final String password = properties.getProperty("db.password");
    public static final String driver = properties.getProperty("db.driver");

}

