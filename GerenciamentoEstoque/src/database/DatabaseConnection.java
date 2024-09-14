package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Método para conectar ao banco de dados SQLite
    public static Connection connect() {
        Connection conn = null;
        try {
            // Caminho do banco de dados SQLite
            String url = "jdbc:sqlite:C:/sqlite/estoque.db"; // Ajuste o caminho conforme necessário
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return conn;
    }
}
