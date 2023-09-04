package cliente;

public class Cliente {
    private String nome;
    private Integer cpf;
    private Integer telefone;
    private String endereco;


    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = dados.endereco();
    }


    public String getNome() {
        return nome;
    }

    public Integer getCpf() {
        return cpf;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
