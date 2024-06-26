package br.com.ocorteiz.comex.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    private BigDecimal desconto;
    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    public ItemPedido() {}

    public ItemPedido(Integer quantidade, TipoDesconto tipoDesconto, Produto produto, Pedido pedido) {
        this.precoUnitario = produto.getPreco();
        this.quantidade = quantidade;
        if(tipoDesconto == TipoDesconto.PROMOCAO) {
            this.desconto = new BigDecimal("10");
        } else {
            this.desconto = BigDecimal.ZERO;
        }
        this.tipoDesconto = tipoDesconto;
        this.produto = produto;
        this.pedido = pedido;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getDesconto() {
        BigDecimal valorBruto = this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade));
        BigDecimal percentualDesconto = this.desconto.divide(BigDecimal.valueOf(100));
        return valorBruto.multiply(percentualDesconto);
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getPrecoUnitarioComDesconto() {
        BigDecimal valorBruto = this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade));
        BigDecimal percentualDesconto = this.desconto.divide(BigDecimal.valueOf(100));
        this.desconto = valorBruto.multiply(percentualDesconto);

        return valorBruto.subtract(this.desconto);
    }
}
