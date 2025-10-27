package conectecare.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredenciaisService {
    private static Properties properties = new Properties();

    static {
        /// faz a leitura do arquivo ele referencia a classe depois carrega as classes depois faz a procura do arquivo buscado la em resources
        try (InputStream input = CredenciaisService.class.getClassLoader().getResourceAsStream("aplicacao.properties")) {

            if (input == null) {
                throw new RuntimeException("Arquivo aplicacao.properties não encontrado!");
            }
            /// faz a leitura se existir a pasta descrita acima
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar configurações do banco", e);
        }
    }
    /// pega os valores das credenciais
    public static final String url = properties.getProperty("db.url");
    public static final String user = properties.getProperty("db.user");
    public static final String password = properties.getProperty("db.password");
    public static final String driver = properties.getProperty("db.driver");



}
