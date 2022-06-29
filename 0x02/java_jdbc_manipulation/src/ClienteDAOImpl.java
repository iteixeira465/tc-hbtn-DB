import java.sql.*;
import java.util.*;
import java.util.logging.*;


public class ClienteDAOImpl implements ClienteDAO {

    private static Connection connection;

    @Override
    public Connection connect(String urlConexao) {
        try {
            Class.forName("org.sqlite.JDBC");
            String[] db = urlConexao.split(":");
            connection = DriverManager.getConnection(db[0] + ":sqlite:" + db[1]);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void createTable(String urlConexao) {
        String sql = "CREATE TABLE IF NOT EXISTS cliente (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nome VARCHAR(255) NOT NULL, idade INTEGER NOT NULL, cpf VARCHAR(11) NOT NULL, rg VARCHAR(11) NOT NULL);\n";
        try {
            connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        String sql = "INSERT INTO `cliente`(`nome`, `idade`, `cpf`,`rg`) VALUES (?, ?, ?, ?);";
        try {
            connect(url_conexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, removeCaracteresEspeciais(cliente.getCpf()));
            stmt.setString(4, removeCaracteresEspeciais(cliente.getRg()));
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        String sql = "SELECT `id`,`nome`, `idade`, `cpf`,`rg` FROM `cliente`;";
        List<Cliente> listClientes = new ArrayList<>();
        try {
            connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setIdade(resultado.getInt("idade"));
                cliente.setCpf(formataDoc(resultado.getString("cpf")));
                cliente.setRg(formataDoc(resultado.getString("rg")));
                listClientes.add(cliente);
            }
            listClientes.forEach(System.out::println);
            resultado.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        String sql = "UPDATE `cliente` SET `nome`=?, `idade`=? WHERE `id`=?;";
        try {
            connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, idade);
            stmt.setInt(3, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        String sql = "DELETE FROM `cliente` WHERE `id`=?;";
        try {
            connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String removeCaracteresEspeciais(String doc) {
        if (doc.contains("\\D")) {
            doc = doc.replace("\\D", "");
        }
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains(",")) {
            doc = doc.replace(",", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        if (doc.contains("(")) {
            doc = doc.replace("(", "");
        }
        if (doc.contains(")")) {
            doc = doc.replace(")", "");
        }
        if (doc.contains(" ")) {
            doc = doc.replace(" ", "");
        }
        return doc;
    }

    public static String formataDoc(String doc) {
        doc = doc.substring(0, 3) + "." + doc.substring(3, 6) + "." + doc.substring(6, 9) + "-" + doc.substring(9, 11);
        return doc;
    }

}
