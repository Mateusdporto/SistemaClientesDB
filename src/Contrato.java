public class Contrato {
    private int id;
    private int clienteId;
    private int servicoId;
    private int quantidadeEquipamentos;

    public Contrato(int id, int clienteId, int servicoId, int quantidadeEquipamentos) {
        this.id = id;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.quantidadeEquipamentos = quantidadeEquipamentos;
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public int getQuantidadeEquipamentos() {
        return quantidadeEquipamentos;
    }
}
