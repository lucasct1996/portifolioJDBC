package conta;

import cliente.Cliente;

import java.math.BigDecimal;
import java.sql.Connection;



public class ContaService {

    private ConnectionFactory connection;

    public ContaService(){
        this.connection = new ConnectionFactory();
    }

    public void abrir(Cliente cliente, Conta conta){
        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).salvar(cliente, conta);
    }

    public BigDecimal consultar(String usuario){
        Connection conn = connection.recuperarConexao();
        BigDecimal saldo = new ContaDAO(conn).verSaldo(usuario);
        return saldo;
    }

    public void realizarDeposito(String usuario, BigDecimal valor){
        var conta = buscarContaPorNumero(usuario);

        if(valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("O valor do deposito deve ser superior a zero!");
        }

        BigDecimal novoSaldo = valor.add(conta.getSaldo());
        alterar(conta.getUsuario(), novoSaldo);

    }

    public void realizarSaque(String usuario, BigDecimal valor){
        var conta = buscarContaPorNumero(usuario);

        if(valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("O valor do deposito deve ser superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("O valor nao pode ser maior que o seu saldo");
        }
        conta.sacar(valor);
        alterar(conta.getUsuario(), conta.getSaldo());

    }

    public void fazerPedido(String usuario, Produtos produto) {

        var conta = buscarContaPorNumero(usuario);
        BigDecimal precoPizza = produto.getPreco();

        if (precoPizza.compareTo(conta.getSaldo()) >= 0 ) {
            throw new RegraDeNegocioException("Você não possui saldo suficiente para comprar esta pizza. Seu saldo é de "
            + conta.getSaldo());
        }

        System.out.println("Pedido realizado com sucesso!");
        conta.sacar(produto.getPreco());
        alterar(conta.getUsuario(), conta.getSaldo());
        System.out.println("Seu saldo é de " + conta.getSaldo());
    }

    public void deleteConta(String usuario){
        var conta = consultar(usuario);

        if (conta != null && conta.compareTo(BigDecimal.ZERO) == 0) {
            Connection conn = connection.recuperarConexao();
            new ContaDAO(conn).deletar(usuario);
            System.out.println("Conta deletada com sucesso");
        }  else {
            System.out.println("Conta não pode ser encerrada pois ainda possui saldo!");
        }
    }

    private Conta buscarContaPorNumero(String usuario) {
        Connection conn = connection.recuperarConexao();

        Conta conta = new ContaDAO(conn).consultar(usuario);
        if(conta != null) {
            return conta;
        } else {
            throw new RegraDeNegocioException("Não existe conta cadastrada com esse número!");
        }
    }

    private void alterar(String usuario, BigDecimal novoSaldo) {
        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).alterar(usuario, novoSaldo);
    }


}



