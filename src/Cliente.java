public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone; // Novo atributo
    private String descricao; // Novo atributo

    public Cliente(int id, String nome, String email, String telefone, String descricao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone; // Inicializa telefone
        this.descricao = descricao; // Inicializa descrição
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone; // Novo getter
    }

    public String getDescricao() {
        return descricao; // Novo getter
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone; // Novo setter
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao; // Novo setter
    }
}
