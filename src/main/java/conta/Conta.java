package conta;

import java.math.BigDecimal;

public class Conta {
    private String usuario;
    private BigDecimal saldo;

    public Conta(String usuario, BigDecimal saldo) {
        this.usuario = usuario;
        this.saldo = BigDecimal.ZERO;
    }


    public String getUsuario() {
        return usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean possuiSaldo() {
        return this.saldo.compareTo(BigDecimal.ZERO) != 0;
    }

    public void sacar(BigDecimal valor) {

        this.saldo = this.saldo.subtract(valor);
    }

}
