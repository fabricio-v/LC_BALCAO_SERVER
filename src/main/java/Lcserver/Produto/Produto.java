/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Produto;

import Lcserver.Categoria.Categoria;
import Lcserver.Categoria.CategoriaSub;
import Lcserver.EstoqueSaldo.EstoqueSaldo;
import Lcserver.Fabricante.Fabricante;
import Lcserver.Unidade.Unidade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 20)
    @Column(name = "referencia")
    private String referencia;
    @Size(max = 20)
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Size(max = 200)
    @Column(name = "nome")
    private String nome;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_fabricante", referencedColumnName = "id")
    private Fabricante fabricante;
    //    @Column(name = "id_fornecedor")
    //    private Integer idFornecedor;
    @ManyToOne
    @JoinColumn(name = "id_unidade", referencedColumnName = "id")
    private Unidade unidade;
    //    @Column(name = "id_unidadeembalagem")
    //    private Integer idUnidadeembalagem;
    //    @Column(name = "id_subcategoria")
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(name = "id_subcategoria", referencedColumnName = "id")
    private CategoriaSub categoriaSub;
    //    @Basic(optional = false)
    //    @NotNull
    //    @Column(name = "id_empresa")
    //    private int idEmpresa;
    @Size(max = 1)
    @Column(name = "pode_desconto")
    private String podeDesconto;
    @Size(max = 1)
    @Column(name = "pode_balanca")
    private String podeBalanca;
    @Size(max = 1)
    @Column(name = "pode_fracionado")
    private String podeFracionado;
    @Size(max = 1)
    @Column(name = "pode_lote")
    private String podeLote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_compra")
    private Double precoCompra;
    @Column(name = "preco_custo")
    private Double precoCusto;
    @Column(name = "custo_medio")
    private Double custoMedio;
    @Column(name = "preco_venda")
    private Double precoVenda;
    @Column(name = "margem_lucro")
    private Double margemLucro;
    @Column(name = "desconto_max")
    private Double descontoMax;
    @Column(name = "preco_venda2")
    private Double precoVenda2;
    @Column(name = "margem_lucro2")
    private Double margemLucro2;
    @Column(name = "qtd_minimapv2")
    private Double qtdMinimapv2;
    @Column(name = "desconto_max2")
    private Double descontoMax2;
    @Column(name = "preco_venda3")
    private Double precoVenda3;
    @Column(name = "margem_lucro3")
    private Double margemLucro3;
    @Column(name = "qtd_minimapv3")
    private Double qtdMinimapv3;
    @Column(name = "desconto_max3")
    private Double descontoMax3;
    @Column(name = "preco_venda4")
    private Double precoVenda4;
    @Column(name = "margem_lucro4")
    private Double margemLucro4;
    @Column(name = "qtd_minimapv4")
    private Double qtdMinimapv4;
    @Column(name = "desconto_max4")
    private Double descontoMax4;
    @Column(name = "preco_antigo")
    private Double precoAntigo;
    @Column(name = "valor_frete")
    private Double valorFrete;
    //    @Column(name = "ipi")
    //    private Double ipi;
    @Column(name = "preco_promocao")
    private Double precoPromocao;

    @Column(name = "data_promocaoinicial")
    @Temporal(TemporalType.DATE)
    private Date dataPromocaoInicial;

    @Column(name = "data_promocaofinal")
    @Temporal(TemporalType.DATE)
    private Date dataPromocaoFinal;

    @Column(name = "comissao")
    private Double comissao;
//    @Column(name = "estoque")
//    private Double estoque;
    @Column(name = "estoque_minimo")
    private Double estoqueMinimo;
    @Column(name = "estoque_max")
    private Double estoqueMax;
    @Column(name = "qtd_embalagem")
    private Double qtdEmbalagem;
    @Size(max = 4)
    @Column(name = "qtd_diasvalidade")
    private String qtdDiasvalidade;
    @Column(name = "peso_bruto")
    private Double pesoBruto;
    @Column(name = "peso_liquido")
    private Double pesoLiquido;
    @Size(max = 10)
    @Column(name = "tipo_produto")
    private String tipoProduto;
    @Size(max = 1)
    @Column(name = "origem_produto")
    private String origemProduto;
    @Size(max = 3)
    @Column(name = "ex_tipi")
    private String exTipi;
    @Size(max = 1)
    @Column(name = "ativo")
    private String ativo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacoes")
    private String observacoes;
    @Size(max = 120)
    @Column(name = "foto")
    private String foto;
    @Size(max = 80)
    @Column(name = "local")
    private String local;
//    @ManyToOne
//    @JoinColumn(name = "id", referencedColumnName = "id_produto")
    @Transient
    private double estoque;

    public Produto() {
        this.estoque = 0;
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public CategoriaSub getCategoriaSub() {
        return categoriaSub;
    }

    public void setCategoriaSub(CategoriaSub categoriaSub) {
        this.categoriaSub = categoriaSub;
    }

    public String getPodeDesconto() {
        return podeDesconto;
    }

    public void setPodeDesconto(String podeDesconto) {
        this.podeDesconto = podeDesconto;
    }

    public String getPodeBalanca() {
        return podeBalanca;
    }

    public void setPodeBalanca(String podeBalanca) {
        this.podeBalanca = podeBalanca;
    }

    public String getPodeFracionado() {
        return podeFracionado;
    }

    public void setPodeFracionado(String podeFracionado) {
        this.podeFracionado = podeFracionado;
    }

    public String getPodeLote() {
        return podeLote;
    }

    public void setPodeLote(String podeLote) {
        this.podeLote = podeLote;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(Double custoMedio) {
        this.custoMedio = custoMedio;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Double getDescontoMax() {
        return descontoMax;
    }

    public void setDescontoMax(Double descontoMax) {
        this.descontoMax = descontoMax;
    }

    public Double getPrecoVenda2() {
        return precoVenda2;
    }

    public void setPrecoVenda2(Double precoVenda2) {
        this.precoVenda2 = precoVenda2;
    }

    public Double getMargemLucro2() {
        return margemLucro2;
    }

    public void setMargemLucro2(Double margemLucro2) {
        this.margemLucro2 = margemLucro2;
    }

    public Double getQtdMinimapv2() {
        return qtdMinimapv2;
    }

    public void setQtdMinimapv2(Double qtdMinimapv2) {
        this.qtdMinimapv2 = qtdMinimapv2;
    }

    public Double getDescontoMax2() {
        return descontoMax2;
    }

    public void setDescontoMax2(Double descontoMax2) {
        this.descontoMax2 = descontoMax2;
    }

    public Double getPrecoVenda3() {
        return precoVenda3;
    }

    public void setPrecoVenda3(Double precoVenda3) {
        this.precoVenda3 = precoVenda3;
    }

    public Double getMargemLucro3() {
        return margemLucro3;
    }

    public void setMargemLucro3(Double margemLucro3) {
        this.margemLucro3 = margemLucro3;
    }

    public Double getQtdMinimapv3() {
        return qtdMinimapv3;
    }

    public void setQtdMinimapv3(Double qtdMinimapv3) {
        this.qtdMinimapv3 = qtdMinimapv3;
    }

    public Double getDescontoMax3() {
        return descontoMax3;
    }

    public void setDescontoMax3(Double descontoMax3) {
        this.descontoMax3 = descontoMax3;
    }

    public Double getPrecoVenda4() {
        return precoVenda4;
    }

    public void setPrecoVenda4(Double precoVenda4) {
        this.precoVenda4 = precoVenda4;
    }

    public Double getMargemLucro4() {
        return margemLucro4;
    }

    public void setMargemLucro4(Double margemLucro4) {
        this.margemLucro4 = margemLucro4;
    }

    public Double getQtdMinimapv4() {
        return qtdMinimapv4;
    }

    public void setQtdMinimapv4(Double qtdMinimapv4) {
        this.qtdMinimapv4 = qtdMinimapv4;
    }

    public Double getDescontoMax4() {
        return descontoMax4;
    }

    public void setDescontoMax4(Double descontoMax4) {
        this.descontoMax4 = descontoMax4;
    }

    public Double getPrecoAntigo() {
        return precoAntigo;
    }

    public void setPrecoAntigo(Double precoAntigo) {
        this.precoAntigo = precoAntigo;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getPrecoPromocao() {
        return precoPromocao;
    }

    public void setPrecoPromocao(Double precoPromocao) {
        this.precoPromocao = precoPromocao;
    }

    public Date getDataPromocaoInicial() {
        return dataPromocaoInicial;
    }

    public void setDataPromocaoInicial(Date dataPromocaoInicial) {
        this.dataPromocaoInicial = dataPromocaoInicial;
    }

    public Date getDataPromocaoFinal() {
        return dataPromocaoFinal;
    }

    public void setDataPromocaoFinal(Date dataPromocaoFinal) {
        this.dataPromocaoFinal = dataPromocaoFinal;
    }

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public Double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Double getEstoqueMax() {
        return estoqueMax;
    }

    public void setEstoqueMax(Double estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    public Double getQtdEmbalagem() {
        return qtdEmbalagem;
    }

    public void setQtdEmbalagem(Double qtdEmbalagem) {
        this.qtdEmbalagem = qtdEmbalagem;
    }

    public String getQtdDiasvalidade() {
        return qtdDiasvalidade;
    }

    public void setQtdDiasvalidade(String qtdDiasvalidade) {
        this.qtdDiasvalidade = qtdDiasvalidade;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public Double getPesoLiquido() {
        return pesoLiquido;
    }

    public void setPesoLiquido(Double pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getOrigemProduto() {
        return origemProduto;
    }

    public void setOrigemProduto(String origemProduto) {
        this.origemProduto = origemProduto;
    }

    public String getExTipi() {
        return exTipi;
    }

    public void setExTipi(String exTipi) {
        this.exTipi = exTipi;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Produto.Produto[ id=" + id + " ]";
    }

}
