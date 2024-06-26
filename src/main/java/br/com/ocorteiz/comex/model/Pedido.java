package br.com.ocorteiz.comex.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final LocalDate data = LocalDate.now();
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private BigDecimal valorTotalComDesconto = BigDecimal.ZERO;
    private BigDecimal desconto = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {}

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void addItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        this.desconto = item.getDesconto();
        this.valorTotalComDesconto = this.valorTotalComDesconto.add(item.getPrecoUnitarioComDesconto());
        this.valorTotal = this.valorTotal.add(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", data=" + data +
                ", valorTotal=" + valorTotal +
                ", valorTotalComDesconto=" + valorTotalComDesconto +
                ", desconto=" + desconto +
                ", cliente=" + cliente +
                ", itens=" + itens +
                '}';
    }
}
