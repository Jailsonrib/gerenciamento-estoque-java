package database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {

    // Método para criar a tabela de produtos no banco de dados
    public static void createNewTable() {
        // SQL para criar uma nova tabela
        String sql = "CREATE TABLE IF NOT EXISTS produtos (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    nome TEXT NOT NULL,\n"
                + "    quantidade INTEGER,\n"
                + "    limite_minimo INTEGER,\n"
                + "    descricao TEXT\n"
                + ");";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            // Executa a criação da tabela
            stmt.execute(sql);
            System.out.println("Tabela de produtos criada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
