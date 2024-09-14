package database;

public class Main {
    public static void main(String[] args) {
        // Testar a criação da tabela
        CreateTable.createNewTable();

        // Testar a inserção de produtos
        ProductManager.addProduct("Produto A", 10, 5, "Descrição do Produto A");

        // Listar todos os produtos
        ProductManager.listProducts();

        // Atualizar quantidade de um produto
        ProductManager.updateProductQuantity(1, 20);

        // Deletar um produto
        ProductManager.deleteProduct(1);

        // Listar produtos abaixo do limite mínimo
        ProductManager.listProductsBelowLimit();
    }
}
