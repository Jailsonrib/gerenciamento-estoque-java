module GerenciamentoEstoque {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    exports database;
    opens database to javafx.graphics, javafx.fxml;
}
