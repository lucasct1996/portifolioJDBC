package conta;

import java.math.BigDecimal;

public class Conta {
    private String usuario;
    private BigDecimal saldo;

    public Conta(String usuario, BigDecimal saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
    }


    public String getUsuario() {
        return usuario;
    }

    public BigDecimal getSaldo() {

        return saldo;
    }


    public void sacar(BigDecimal valor) {

        this.saldo = this.saldo.subtract(valor);
    }

}
