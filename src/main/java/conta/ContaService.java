package conta;

import cliente.Cliente;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;


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

    public void realizarDeposito(String usuario, BigDecimal valorDeposito){
        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).alterar(usuario, valorDeposito);
        if(valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("O valor do deposito deve ser superior a zero!");
        }
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
}



