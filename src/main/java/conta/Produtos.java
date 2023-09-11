package conta;

import java.math.BigDecimal;

public enum Produtos {
    BACON(new BigDecimal("75.00")),
    STROGONOF(new BigDecimal("90.00")),
    QUEIJO(new BigDecimal("60.00")),
    CAMARAO(new BigDecimal("100.00")),
    CORACAO(new BigDecimal("80.00")),
    CHOCOLATE(new BigDecimal("80.00")),
    SORVETE(new BigDecimal("88.00"));

    private BigDecimal preco;

    Produtos(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
