import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSQLite {

    private static Connection connection;
    private static String DB_NOME = "sqlite_database_2022.db";

    public static void main(String[] args) {
        connection = initConnection();

        closeConnection(connection);
    }

    private static Connection initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", DB_NOME));
            System.out.println("Conexão com o Banco de Dados <" + DB_NOME + "> Estabelecida.");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
            System.out.println("Conexão com o Banco de Dados Encerrada.");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
