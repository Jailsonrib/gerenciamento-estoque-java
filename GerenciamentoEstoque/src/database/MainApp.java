package database;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private ListView<String> productList;
    private ObservableList<String> products;
    private TextField nameField, quantityField, limitField, descriptionField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciamento de Estoque");

        // Configuração inicial dos componentes da interface
        productList = new ListView<>();
        products = FXCollections.observableArrayList();
        productList.setItems(products);
        refreshProductList(); // Atualiza a lista de produtos ao iniciar

        // Campos de texto
        nameField = new TextField();
        quantityField = new TextField();
        limitField = new TextField();
        descriptionField = new TextField();

        // Labels
        Label nameLabel = new Label("Nome:");
        Label quantityLabel = new Label("Quantidade:");
        Label limitLabel = new Label("Limite Mínimo:");
        Label descriptionLabel = new Label("Descrição:");

        // Botões
        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> addProduct());

        Button updateButton = new Button("Atualizar");
        updateButton.setOnAction(e -> updateProduct());

        Button deleteButton = new Button("Excluir");
        deleteButton.setOnAction(e -> deleteProduct());

        // Layout
        VBox inputLayout = new VBox(10, nameLabel, nameField, quantityLabel, quantityField, 
                                    limitLabel, limitField, descriptionLabel, descriptionField);
        HBox buttonLayout = new HBox(10, addButton, updateButton, deleteButton);
        VBox mainLayout = new VBox(10, productList, inputLayout, buttonLayout);

        Scene scene = new Scene(mainLayout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para adicionar produto
    private void addProduct() {
        try {
            String nome = nameField.getText();
            int quantidade = Integer.parseInt(quantityField.getText());
            int limiteMinimo = Integer.parseInt(limitField.getText());
            String descricao = descriptionField.getText();

            ProductManager.addProduct(nome, quantidade, limiteMinimo, descricao);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto adicionado com sucesso!");
            refreshProductList();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Entrada", "Por favor, insira números válidos para quantidade e limite.");
        }
    }

    // Método para atualizar produto selecionado
    private void updateProduct() {
        String selectedProduct = productList.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                int id = getIdFromSelectedProduct(selectedProduct);
                String nome = nameField.getText();
                int quantidade = Integer.parseInt(quantityField.getText());
                int limiteMinimo = Integer.parseInt(limitField.getText());
                String descricao = descriptionField.getText();

                ProductManager.updateProductQuantity(id, quantidade); // Atualiza apenas a quantidade como exemplo
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto atualizado com sucesso!");
                refreshProductList();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Erro de Entrada", "Por favor, insira números válidos para quantidade e limite.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Atenção", "Nenhum produto selecionado.");
        }
    }

    // Método para excluir produto selecionado
    private void deleteProduct() {
        String selectedProduct = productList.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            int id = getIdFromSelectedProduct(selectedProduct);
            ProductManager.deleteProduct(id);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto excluído com sucesso!");
            refreshProductList();
        } else {
            showAlert(Alert.AlertType.WARNING, "Atenção", "Nenhum produto selecionado.");
        }
    }

    // Método para obter o ID do produto selecionado na lista
    private int getIdFromSelectedProduct(String selectedProduct) {
        String[] parts = selectedProduct.split(",");
        String idPart = parts[0].split(":")[1].trim(); // Extrai o ID da string
        return Integer.parseInt(idPart);
    }

    // Método para atualizar a lista de produtos
    private void refreshProductList() {
        products.clear();
        products.addAll(ProductManager.getProductDescriptions());
    }

    // Método para limpar os campos de entrada
    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        limitField.clear();
        descriptionField.clear();
    }

    // Método para mostrar alertas
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
