package conta;



import cliente.Cliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ContaDAO {

    private Connection conn;

    public ContaDAO(Connection conn) {

        this.conn = conn;
    }

    public void salvar(Cliente cliente, Conta conta) {

        String sql = "INSERT INTO conta(usuario, saldo, nome, cpf, telefone, endereco)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, conta.getUsuario());
        preparedStatement.setBigDecimal(2, conta.getSaldo());
        preparedStatement.setString(3, cliente.getNome());
        preparedStatement.setInt(4, cliente.getCpf());
        preparedStatement.setInt(5, cliente.getTelefone());
        preparedStatement.setString(6, cliente.getEndereco());

        preparedStatement.execute();
        preparedStatement.close();
        conn.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public BigDecimal verSaldo(String usuario) {
        PreparedStatement ps;
        ResultSet resultSet;

        String sql = "SELECT saldo FROM conta WHERE usuario = ?;";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                BigDecimal saldo = resultSet.getBigDecimal("saldo");
                resultSet.close();
                ps.close();
                return saldo; // Retorna o saldo se encontrado
            } else {
                resultSet.close();
                ps.close();
                return BigDecimal.ZERO; // Retorna zero se a conta não for encontrada
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar saldo: " + e.getMessage(), e);
        }
    }

    public void alterar(String usuario, BigDecimal valorDeposito) {
        PreparedStatement ps;

        String sql = "UPDATE conta SET saldo = saldo + ? WHERE usuario = ?";

        try  {
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, valorDeposito);
            ps.setString(2, usuario);
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void deletar(String usuario) {
        PreparedStatement ps;

        String sql = "DELETE FROM conta WHERE usuario = ?";

        try  {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}






