import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SistemaClientesDB extends Application {
    private DatabaseHandler dbHandler;
    private ObservableList<Cliente> clientesObservableList;
    private TableView<Cliente> tableView;

    @Override
    public void start(Stage stage) {
        dbHandler = new DatabaseHandler();

        // Layout principal
        BorderPane root = new BorderPane();

        // Criar tabela de clientes
        tableView = new TableView<>();
        TableColumn<Cliente, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));

        TableColumn<Cliente, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));

        TableColumn<Cliente, String> telefoneCol = new TableColumn<>("Telefone");
        telefoneCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTelefone()));

        TableColumn<Cliente, String> descricaoCol = new TableColumn<>("Descrição");
        descricaoCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));

        tableView.getColumns().addAll(nomeCol, emailCol, telefoneCol, descricaoCol);
        clientesObservableList = FXCollections.observableArrayList(dbHandler.getClientes());
        tableView.setItems(clientesObservableList);
        root.setCenter(tableView);

        // Formulário de inserção de cliente
        GridPane formCliente = new GridPane();
        formCliente.setPadding(new Insets(10));
        formCliente.setVgap(10);
        formCliente.setHgap(10);

        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField();
        Label descricaoLabel = new Label("Descrição:");
        TextArea descricaoField = new TextArea();
        descricaoField.setWrapText(true);

        Button addClienteButton = new Button("Adicionar Cliente");

        formCliente.add(nomeLabel, 0, 0);
        formCliente.add(nomeField, 1, 0);
        formCliente.add(emailLabel, 0, 1);
        formCliente.add(emailField, 1, 1);
        formCliente.add(telefoneLabel, 0, 2);
        formCliente.add(telefoneField, 1, 2);
        formCliente.add(descricaoLabel, 0, 3);
        formCliente.add(descricaoField, 1, 3);
        formCliente.add(addClienteButton, 1, 4);

        root.setTop(formCliente);

        // Ação do botão adicionar cliente
        addClienteButton.setOnAction(e -> adicionarCliente(nomeField, emailField, telefoneField, descricaoField));

        // Botão de remover cliente
        Button removeButton = new Button("Remover Cliente");
        removeButton.setOnAction(e -> removerCliente());

        // Botão de editar cliente
        Button editButton = new Button("Editar Cliente");
        editButton.setOnAction(e -> editarCliente(nomeField, emailField, telefoneField, descricaoField, addClienteButton));

        HBox buttonBox = new HBox(10, addClienteButton, removeButton, editButton);
        root.setBottom(buttonBox);

        // Configuração da cena
        Scene scene = new Scene(root, 800, 400);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Sistema de Clientes");
        stage.setScene(scene);
        stage.show();
    }

    private void adicionarCliente(TextField nomeField, TextField emailField, TextField telefoneField, TextArea descricaoField) {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();
        String descricao = descricaoField.getText();
        if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty() && !descricao.isEmpty()) {
            Cliente cliente = new Cliente(0, nome, email, telefone, descricao);
            dbHandler.addCliente(cliente);
            clientesObservableList.add(cliente);
            nomeField.clear();
            emailField.clear();
            telefoneField.clear();
            descricaoField.clear();
        }
    }

    private void removerCliente() {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            dbHandler.deleteCliente(cliente.getId());
            clientesObservableList.remove(cliente);
        }
    }

    private void editarCliente(TextField nomeField, TextField emailField, TextField telefoneField, TextArea descricaoField, Button addClienteButton) {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            nomeField.setText(cliente.getNome());
            emailField.setText(cliente.getEmail());
            telefoneField.setText(cliente.getTelefone());
            descricaoField.setText(cliente.getDescricao());

            addClienteButton.setOnAction(event -> {
                cliente.setNome(nomeField.getText());
                cliente.setEmail(emailField.getText());
                cliente.setTelefone(telefoneField.getText());
                cliente.setDescricao(descricaoField.getText());
                dbHandler.updateCliente(cliente);
                clientesObservableList.set(tableView.getSelectionModel().getSelectedIndex(), cliente);
                nomeField.clear();
                emailField.clear();
                telefoneField.clear();
                descricaoField.clear();
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
