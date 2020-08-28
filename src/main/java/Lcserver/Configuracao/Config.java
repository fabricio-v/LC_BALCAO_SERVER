/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Configuracao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresa")
    private int idEmpresa;
//    @Size(max = 200)
//    @Column(name = "pc")
//    private String pc;
//    @Size(max = 3)
//    @Column(name = "pdv_pode_desconto_final")
//    private String pdvPodeDescontoFinal;
//    @Size(max = 3)
//    @Column(name = "pdv_pode_arredondamento")
//    private String pdvPodeArredondamento;
//    @Size(max = 7)
//    @Column(name = "pdv_tipo_solicitar_vendedor")
//    private String pdvTipoSolicitarVendedor;
//    @Size(max = 3)
//    @Column(name = "pdv_comp_perguntar_antes_imprimir")
//    private String pdvCompPerguntarAntesImprimir;
//    @Size(max = 3)
//    @Column(name = "pdv_comp_remove_cabecalho")
//    private String pdvCompRemoveCabecalho;
//    @Size(max = 3)
//    @Column(name = "pdv_comp_imprimir_fabricante")
//    private String pdvCompImprimirFabricante;
//    @Size(max = 50)
//    @Column(name = "pdv_comp_nome_titulo")
//    private String pdvCompNomeTitulo;
//    @Lob
//    @Size(max = 2147483647)
//    @Column(name = "pdv_comp_venda_obs")
//    private String pdvCompVendaObs;
//    @Column(name = "pdv_comp_venda_numero_vias")
//    private Integer pdvCompVendaNumeroVias;
//    @Column(name = "pdv_comp_rec_titulo_numero_vias")
//    private Integer pdvCompRecTituloNumeroVias;
//    @Column(name = "pdv_comp_nota_promissoria_numero_vias")
//    private Integer pdvCompNotaPromissoriaNumeroVias;
//    @Size(max = 20)
//    @Column(name = "pdv_cotrole_entrega_modo")
//    private String pdvCotroleEntregaModo;
//    @Size(max = 3)
//    @Column(name = "balcao_comp_perguntar_antes_imprimir")
//    private String balcaoCompPerguntarAntesImprimir;
    @Size(max = 50)
    @Column(name = "balcao_comp_nome_titulo")
    private String balcaoCompNomeTitulo;
    @Size(max = 3)
    @Column(name = "balcao_comp_remove_cabecalho")
    private String balcaoCompRemoveCabecalho;
    @Size(max = 3)
    @Column(name = "balcao_comp_imprimir_fabricante")
    private String balcaoCompImprimirFabricante;
    @Size(max = 150)
    @Column(name = "balcao_comp_obs")
    private String balcaoCompObs;
    @Size(max = 3)
    @Column(name = "balcao_pode_desconto_final")
    private String balcaoPodeDescontoFinal;
    @Size(max = 3)
    @Column(name = "balcao_pode_arredondamento")
    private String balcaoPodeArredondamento;
//    @Size(max = 7)
//    @Column(name = "balcao_tipo_solicitar_vendedor")
//    private String balcaoTipoSolicitarVendedor;
//    @Size(max = 3)
//    @Column(name = "balcao_controle_sexta_bolsa")
//    private String balcaoControleSextaBolsa;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome1")
//    private String osObjetoNome1;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome2")
//    private String osObjetoNome2;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome3")
//    private String osObjetoNome3;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome4")
//    private String osObjetoNome4;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome5")
//    private String osObjetoNome5;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome6")
//    private String osObjetoNome6;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome7")
//    private String osObjetoNome7;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome8")
//    private String osObjetoNome8;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome9")
//    private String osObjetoNome9;
//    @Size(max = 20)
//    @Column(name = "os_objeto_nome10")
//    private String osObjetoNome10;
    @Size(max = 3)
    @Column(name = "estoque_vender_sem_estoque")
    private String estoqueVenderSemEstoque;// sim ou n
    @Size(max = 20)
    @Column(name = "estoque_nome_tab_preco1")
    private String estoqueNomeTabPreco1;
    @Size(max = 20)
    @Column(name = "estoque_nome_tab_preco2")
    private String estoqueNomeTabPreco2;
    @Size(max = 20)
    @Column(name = "estoque_nome_tab_preco3")
    private String estoqueNomeTabPreco3;
    @Size(max = 20)
    @Column(name = "estoque_nome_tab_preco4")
    private String estoqueNomeTabPreco4;
    @Size(max = 120)
    @Column(name = "estoque_foto_local_produto")
    private String estoqueFotoLocalProduto;
//    @Column(name = "financeiro_id_pc_vendas")
//    private Integer financeiroIdPcVendas;
//    @Column(name = "financeiro_id_pc_multa")
//    private Integer financeiroIdPcMulta;
//    @Column(name = "financeiro_id_pc_juros")
//    private Integer financeiroIdPcJuros;
//    @Column(name = "financeiro_id_pc_compras")
//    private Integer financeiroIdPcCompras;
//    @Column(name = "financeiro_id_pc_transferencias")
//    private Integer financeiroIdPcTransferencias;
//    @Size(max = 20)
//    @Column(name = "balanca_modo_leitura")
//    private String balancaModoLeitura;
//    @Column(name = "balanca_n_digitos_codigo")
//    private Integer balancaNDigitosCodigo;
//    @Size(max = 25)
//    @Column(name = "aparencia_tipo")
//    private String aparenciaTipo;
//    @Size(max = 3)
//    @Column(name = "cadastros_cliente_obrigatorio_cpf")
//    private String cadastrosClienteObrigatorioCpf;

    public Config() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getBalcaoCompNomeTitulo() {
        return balcaoCompNomeTitulo;
    }

    public void setBalcaoCompNomeTitulo(String balcaoCompNomeTitulo) {
        this.balcaoCompNomeTitulo = balcaoCompNomeTitulo;
    }

    public String getBalcaoCompRemoveCabecalho() {
        return balcaoCompRemoveCabecalho;
    }

    public void setBalcaoCompRemoveCabecalho(String balcaoCompRemoveCabecalho) {
        this.balcaoCompRemoveCabecalho = balcaoCompRemoveCabecalho;
    }

    public String getBalcaoCompImprimirFabricante() {
        return balcaoCompImprimirFabricante;
    }

    public void setBalcaoCompImprimirFabricante(String balcaoCompImprimirFabricante) {
        this.balcaoCompImprimirFabricante = balcaoCompImprimirFabricante;
    }

    public String getBalcaoCompObs() {
        return balcaoCompObs;
    }

    public void setBalcaoCompObs(String balcaoCompObs) {
        this.balcaoCompObs = balcaoCompObs;
    }

    public String getBalcaoPodeDescontoFinal() {
        return balcaoPodeDescontoFinal;
    }

    public void setBalcaoPodeDescontoFinal(String balcaoPodeDescontoFinal) {
        this.balcaoPodeDescontoFinal = balcaoPodeDescontoFinal;
    }

    public String getBalcaoPodeArredondamento() {
        return balcaoPodeArredondamento;
    }

    public void setBalcaoPodeArredondamento(String balcaoPodeArredondamento) {
        this.balcaoPodeArredondamento = balcaoPodeArredondamento;
    }

    public String getEstoqueVenderSemEstoque() {
        return estoqueVenderSemEstoque;
    }

    public void setEstoqueVenderSemEstoque(String estoqueVenderSemEstoque) {
        this.estoqueVenderSemEstoque = estoqueVenderSemEstoque;
    }

    public String getEstoqueNomeTabPreco1() {
        return estoqueNomeTabPreco1;
    }

    public void setEstoqueNomeTabPreco1(String estoqueNomeTabPreco1) {
        this.estoqueNomeTabPreco1 = estoqueNomeTabPreco1;
    }

    public String getEstoqueNomeTabPreco2() {
        return estoqueNomeTabPreco2;
    }

    public void setEstoqueNomeTabPreco2(String estoqueNomeTabPreco2) {
        this.estoqueNomeTabPreco2 = estoqueNomeTabPreco2;
    }

    public String getEstoqueNomeTabPreco3() {
        return estoqueNomeTabPreco3;
    }

    public void setEstoqueNomeTabPreco3(String estoqueNomeTabPreco3) {
        this.estoqueNomeTabPreco3 = estoqueNomeTabPreco3;
    }

    public String getEstoqueNomeTabPreco4() {
        return estoqueNomeTabPreco4;
    }

    public void setEstoqueNomeTabPreco4(String estoqueNomeTabPreco4) {
        this.estoqueNomeTabPreco4 = estoqueNomeTabPreco4;
    }

    public String getEstoqueFotoLocalProduto() {
        return estoqueFotoLocalProduto;
    }

    public void setEstoqueFotoLocalProduto(String estoqueFotoLocalProduto) {
        this.estoqueFotoLocalProduto = estoqueFotoLocalProduto;
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
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Configuracao.Config[ id=" + id + " ]";
    }

}
