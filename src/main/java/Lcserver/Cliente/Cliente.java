/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Cliente;

import Lcserver.EstadoCidade.Cidades;
import Lcserver.EstadoCidade.Estados;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;
    @Column(name = "ie")
    private String ie;
    @Column(name = "ie_indicador")
    private String ieIndicador;
    @Column(name = "im")
    private String im;
    @Column(name = "nome")
    private String nome;
    @Column(name = "rg")
    private String rg;
    @Column(name = "razao_social")
    private String razaoSocial;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "numero")
    private String numero;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "cep")
    private String cep;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "tel_comercial")
    private String telComercial;
    @Column(name = "fax")
    private String fax;
    @Column(name = "limite_credito")
    private Double limiteCredito;
    @Column(name = "obs")
    private String obs;
    @Column(name = "endereco2")
    private String endereco2;
    @Column(name = "numero2")
    private String numero2;
    @Column(name = "referencia2")
    private String referencia2;
    @Column(name = "cep2")
    private String cep2;
    @Column(name = "bairro2")
    private String bairro2;
    @Column(name = "ativo")
    private String ativo;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "id_pais")
    private Integer idPais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado2")
    private int idEstado2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cidade2")
    private int idCidade2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresa")
    private int idEmpresa;
    @Column(name = "pai_adi")
    private String paiAdi;
    @Column(name = "mae_adi")
    private String maeAdi;
    @Column(name = "nascimento_adi")
    @Temporal(TemporalType.DATE)
    private Date nascimentoAdi;
    @Column(name = "sexo_adi")
    private String sexoAdi;
    @Column(name = "estcivil_adi")
    private String estcivilAdi;
    @Column(name = "apelido_adi")
    private String apelidoAdi;
    @Column(name = "email_adi")
    private String emailAdi;
    @Column(name = "id_cidades_adi")
    private Integer idCidadesAdi;
    @Column(name = "id_estados_adi")
    private Integer idEstadosAdi;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "fone_emp")
    private String foneEmp;
    @Column(name = "endereco_emp")
    private String enderecoEmp;
    @Column(name = "numero_emp")
    private String numeroEmp;
    @Column(name = "cep_emp")
    private String cepEmp;
    @Column(name = "bairro_emp")
    private String bairroEmp;
    @Column(name = "cargo_emp")
    private String cargoEmp;
    @Column(name = "renda_emp")
    private Double rendaEmp;
    @Column(name = "admissao_emp")
    @Temporal(TemporalType.DATE)
    private Date admissaoEmp;
    @Column(name = "id_cidades_emp")
    private Integer idCidadesEmp;
    @Column(name = "id_estados_emp")
    private Integer idEstadosEmp;
    @Column(name = "conjuje")
    private String conjuje;
    @Column(name = "cpf_conj")
    private String cpfConj;
    @Column(name = "rg_conj")
    private String rgConj;
    @Column(name = "nascimento_conj")
    @Temporal(TemporalType.DATE)
    private Date nascimentoConj;
    @Column(name = "empresa_conj")
    private String empresaConj;
    @Column(name = "fone_conj")
    private String foneConj;
    @Column(name = "endereco_conj")
    private String enderecoConj;
    @Column(name = "numero_conj")
    private String numeroConj;
    @Column(name = "cep_conj")
    private String cepConj;
    @Column(name = "bairro_conj")
    private String bairroConj;
    @Column(name = "cargo_conj")
    private String cargoConj;
    @Column(name = "renda_conj")
    private Double rendaConj;
    @Column(name = "admissao_conj")
    @Temporal(TemporalType.DATE)
    private Date admissaoConj;
    @Column(name = "id_cidades_conj")
    private Integer idCidadesConj;
    @Column(name = "id_estados_conj")
    private Integer idEstadosConj;
    @Column(name = "referencias")
    private String referencias;
    @Column(name = "comercial_1")
    private String comercial1;
    @Column(name = "comercial_2")
    private String comercial2;
    @Column(name = "comercial_3")
    private String comercial3;
    @Column(name = "bancaria_1")
    private String bancaria1;
    @Column(name = "bancaria_2")
    private String bancaria2;
    @Column(name = "emissao")
    @Temporal(TemporalType.DATE)
    private Date emissao;
    @Column(name = "orgao")
    private String orgao;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "foto")
    private String foto;
    @Column(name = "poupanca")
    private Double poupanca;
    @Column(name = "pode_aprazo")
    private String podeAprazo;
    @Column(name = "pode_cartacobranca")
    private String podeCartacobranca;
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Column(name = "numero_cartao")
    private String numeroCartao;
    @Column(name = "tabela_preco")
    private String tabelaPreco;
    @Column(name = "id_vendedor")
    private Integer idVendedor;
    @Column(name = "filiacao_fonemae")
    private String filiacaoFonemae;
    @Column(name = "filiacao_fonepai")
    private String filiacaoFonepai;
    @Column(name = "filiacao_endereco")
    private String filiacaoEndereco;
    @Column(name = "filiacao_referencia")
    private String filiacaoReferencia;
    @Column(name = "filiacao_numero")
    private String filiacaoNumero;
    @Column(name = "filiacao_cep")
    private String filiacaoCep;
    @Column(name = "filiacao_bairro")
    private String filiacaoBairro;
    @Column(name = "filiacao_idcidade")
    private Integer filiacaoIdcidade;
    @Column(name = "filiacao_idestado")
    private Integer filiacaoIdestado;
    @Column(name = "avalista_nome")
    private String avalistaNome;
    @Column(name = "avalista_cpf")
    private String avalistaCpf;
    @Column(name = "avalista_rg")
    private String avalistaRg;
    @Column(name = "avalista_nascimento")
    @Temporal(TemporalType.DATE)
    private Date avalistaNascimento;
    @Column(name = "avalista_fone")
    private String avalistaFone;
    @Column(name = "avalista_endereco")
    private String avalistaEndereco;
    @Column(name = "avalista_numero")
    private String avalistaNumero;
    @Column(name = "avalista_cep")
    private String avalistaCep;
    @Column(name = "avalista_bairro")
    private String avalistaBairro;
    @Column(name = "avalista_empresa")
    private String avalistaEmpresa;
    @Column(name = "avalista_cargo")
    private String avalistaCargo;
    @Column(name = "avalista_renda")
    private Double avalistaRenda;
    @Column(name = "avalista_admissao")
    @Temporal(TemporalType.DATE)
    private Date avalistaAdmissao;
    @Column(name = "avalista_idcidade")
    private Integer avalistaIdcidade;
    @Column(name = "avalista_idestado")
    private Integer avalistaIdestado;
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", referencedColumnName = "id", nullable = false)
    private Estados estado;
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", referencedColumnName = "id", nullable = false)
    private Cidades cidade;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "ie")
//    private String ie;
//    @Column(name = "im")
//    private String im;
//    @Column(name = "nome")
//    private String nome;
//    @Column(name = "apelido_adi")
//    private String apelidoAdi;
//    @Column(name = "rg")
//    private String rg;
//    @Column(name = "razao_social")
//    private String razaoSocial;
//    @Column(name = "endereco")
//    private String endereco;
//    @Column(name = "numero")
//    private String numero;
//    @Column(name = "referencia")
//    private String referencia;
//    @Column(name = "cep")
//    private String cep;
//    @Column(name = "bairro")
//    private String bairro;
//    @Column(name = "telefone")
//    private String telefone;
//    @Column(name = "tel_comercial")
//    private String telComercial;
//    @Column(name = "fax")
//    private String fax;
//    @Column(name = "ativo")
//    private String ativo;
//    @Column(name = "tipo")
//    private String tipo;
//
//    @Column(name = "referencias")
//    private String referencias;
//    @Column(name = "comercial_1")
//    private String comercial1;
//    @Column(name = "numero_cartao")
//    private String numeroCartao;
//    @Column(name = "tabela_preco")
//    private String tabelaPreco;
//    @Column(name = "id_vendedor")
//    private Integer idVendedor;
//    @Column(name = "pode_aprazo")
//    private String podeAprazo;
//    @Column(name = "limite_credito")
//    private Double limiteCredito;
    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIeIndicador() {
        return ieIndicador;
    }

    public void setIeIndicador(String ieIndicador) {
        this.ieIndicador = ieIndicador;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelComercial() {
        return telComercial;
    }

    public void setTelComercial(String telComercial) {
        this.telComercial = telComercial;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEndereco2() {
        return endereco2;
    }

    public void setEndereco2(String endereco2) {
        this.endereco2 = endereco2;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    public String getReferencia2() {
        return referencia2;
    }

    public void setReferencia2(String referencia2) {
        this.referencia2 = referencia2;
    }

    public String getCep2() {
        return cep2;
    }

    public void setCep2(String cep2) {
        this.cep2 = cep2;
    }

    public String getBairro2() {
        return bairro2;
    }

    public void setBairro2(String bairro2) {
        this.bairro2 = bairro2;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Cidades getCidade() {
        return cidade;
    }

    public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }

    public int getIdEstado2() {
        return idEstado2;
    }

    public void setIdEstado2(int idEstado2) {
        this.idEstado2 = idEstado2;
    }

    public int getIdCidade2() {
        return idCidade2;
    }

    public void setIdCidade2(int idCidade2) {
        this.idCidade2 = idCidade2;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getPaiAdi() {
        return paiAdi;
    }

    public void setPaiAdi(String paiAdi) {
        this.paiAdi = paiAdi;
    }

    public String getMaeAdi() {
        return maeAdi;
    }

    public void setMaeAdi(String maeAdi) {
        this.maeAdi = maeAdi;
    }

    public Date getNascimentoAdi() {
        return nascimentoAdi;
    }

    public void setNascimentoAdi(Date nascimentoAdi) {
        this.nascimentoAdi = nascimentoAdi;
    }

    public String getSexoAdi() {
        return sexoAdi;
    }

    public void setSexoAdi(String sexoAdi) {
        this.sexoAdi = sexoAdi;
    }

    public String getEstcivilAdi() {
        return estcivilAdi;
    }

    public void setEstcivilAdi(String estcivilAdi) {
        this.estcivilAdi = estcivilAdi;
    }

    public String getApelidoAdi() {
        return apelidoAdi;
    }

    public void setApelidoAdi(String apelidoAdi) {
        this.apelidoAdi = apelidoAdi;
    }

    public String getEmailAdi() {
        return emailAdi;
    }

    public void setEmailAdi(String emailAdi) {
        this.emailAdi = emailAdi;
    }

    public Integer getIdCidadesAdi() {
        return idCidadesAdi;
    }

    public void setIdCidadesAdi(Integer idCidadesAdi) {
        this.idCidadesAdi = idCidadesAdi;
    }

    public Integer getIdEstadosAdi() {
        return idEstadosAdi;
    }

    public void setIdEstadosAdi(Integer idEstadosAdi) {
        this.idEstadosAdi = idEstadosAdi;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFoneEmp() {
        return foneEmp;
    }

    public void setFoneEmp(String foneEmp) {
        this.foneEmp = foneEmp;
    }

    public String getEnderecoEmp() {
        return enderecoEmp;
    }

    public void setEnderecoEmp(String enderecoEmp) {
        this.enderecoEmp = enderecoEmp;
    }

    public String getNumeroEmp() {
        return numeroEmp;
    }

    public void setNumeroEmp(String numeroEmp) {
        this.numeroEmp = numeroEmp;
    }

    public String getCepEmp() {
        return cepEmp;
    }

    public void setCepEmp(String cepEmp) {
        this.cepEmp = cepEmp;
    }

    public String getBairroEmp() {
        return bairroEmp;
    }

    public void setBairroEmp(String bairroEmp) {
        this.bairroEmp = bairroEmp;
    }

    public String getCargoEmp() {
        return cargoEmp;
    }

    public void setCargoEmp(String cargoEmp) {
        this.cargoEmp = cargoEmp;
    }

    public Double getRendaEmp() {
        return rendaEmp;
    }

    public void setRendaEmp(Double rendaEmp) {
        this.rendaEmp = rendaEmp;
    }

    public Date getAdmissaoEmp() {
        return admissaoEmp;
    }

    public void setAdmissaoEmp(Date admissaoEmp) {
        this.admissaoEmp = admissaoEmp;
    }

    public Integer getIdCidadesEmp() {
        return idCidadesEmp;
    }

    public void setIdCidadesEmp(Integer idCidadesEmp) {
        this.idCidadesEmp = idCidadesEmp;
    }

    public Integer getIdEstadosEmp() {
        return idEstadosEmp;
    }

    public void setIdEstadosEmp(Integer idEstadosEmp) {
        this.idEstadosEmp = idEstadosEmp;
    }

    public String getConjuje() {
        return conjuje;
    }

    public void setConjuje(String conjuje) {
        this.conjuje = conjuje;
    }

    public String getCpfConj() {
        return cpfConj;
    }

    public void setCpfConj(String cpfConj) {
        this.cpfConj = cpfConj;
    }

    public String getRgConj() {
        return rgConj;
    }

    public void setRgConj(String rgConj) {
        this.rgConj = rgConj;
    }

    public Date getNascimentoConj() {
        return nascimentoConj;
    }

    public void setNascimentoConj(Date nascimentoConj) {
        this.nascimentoConj = nascimentoConj;
    }

    public String getEmpresaConj() {
        return empresaConj;
    }

    public void setEmpresaConj(String empresaConj) {
        this.empresaConj = empresaConj;
    }

    public String getFoneConj() {
        return foneConj;
    }

    public void setFoneConj(String foneConj) {
        this.foneConj = foneConj;
    }

    public String getEnderecoConj() {
        return enderecoConj;
    }

    public void setEnderecoConj(String enderecoConj) {
        this.enderecoConj = enderecoConj;
    }

    public String getNumeroConj() {
        return numeroConj;
    }

    public void setNumeroConj(String numeroConj) {
        this.numeroConj = numeroConj;
    }

    public String getCepConj() {
        return cepConj;
    }

    public void setCepConj(String cepConj) {
        this.cepConj = cepConj;
    }

    public String getBairroConj() {
        return bairroConj;
    }

    public void setBairroConj(String bairroConj) {
        this.bairroConj = bairroConj;
    }

    public String getCargoConj() {
        return cargoConj;
    }

    public void setCargoConj(String cargoConj) {
        this.cargoConj = cargoConj;
    }

    public Double getRendaConj() {
        return rendaConj;
    }

    public void setRendaConj(Double rendaConj) {
        this.rendaConj = rendaConj;
    }

    public Date getAdmissaoConj() {
        return admissaoConj;
    }

    public void setAdmissaoConj(Date admissaoConj) {
        this.admissaoConj = admissaoConj;
    }

    public Integer getIdCidadesConj() {
        return idCidadesConj;
    }

    public void setIdCidadesConj(Integer idCidadesConj) {
        this.idCidadesConj = idCidadesConj;
    }

    public Integer getIdEstadosConj() {
        return idEstadosConj;
    }

    public void setIdEstadosConj(Integer idEstadosConj) {
        this.idEstadosConj = idEstadosConj;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getComercial1() {
        return comercial1;
    }

    public void setComercial1(String comercial1) {
        this.comercial1 = comercial1;
    }

    public String getComercial2() {
        return comercial2;
    }

    public void setComercial2(String comercial2) {
        this.comercial2 = comercial2;
    }

    public String getComercial3() {
        return comercial3;
    }

    public void setComercial3(String comercial3) {
        this.comercial3 = comercial3;
    }

    public String getBancaria1() {
        return bancaria1;
    }

    public void setBancaria1(String bancaria1) {
        this.bancaria1 = bancaria1;
    }

    public String getBancaria2() {
        return bancaria2;
    }

    public void setBancaria2(String bancaria2) {
        this.bancaria2 = bancaria2;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPoupanca() {
        return poupanca;
    }

    public void setPoupanca(Double poupanca) {
        this.poupanca = poupanca;
    }

    public String getPodeAprazo() {
        return podeAprazo;
    }

    public void setPodeAprazo(String podeAprazo) {
        this.podeAprazo = podeAprazo;
    }

    public String getPodeCartacobranca() {
        return podeCartacobranca;
    }

    public void setPodeCartacobranca(String podeCartacobranca) {
        this.podeCartacobranca = podeCartacobranca;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getTabelaPreco() {
        return tabelaPreco;
    }

    public void setTabelaPreco(String tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getFiliacaoFonemae() {
        return filiacaoFonemae;
    }

    public void setFiliacaoFonemae(String filiacaoFonemae) {
        this.filiacaoFonemae = filiacaoFonemae;
    }

    public String getFiliacaoFonepai() {
        return filiacaoFonepai;
    }

    public void setFiliacaoFonepai(String filiacaoFonepai) {
        this.filiacaoFonepai = filiacaoFonepai;
    }

    public String getFiliacaoEndereco() {
        return filiacaoEndereco;
    }

    public void setFiliacaoEndereco(String filiacaoEndereco) {
        this.filiacaoEndereco = filiacaoEndereco;
    }

    public String getFiliacaoReferencia() {
        return filiacaoReferencia;
    }

    public void setFiliacaoReferencia(String filiacaoReferencia) {
        this.filiacaoReferencia = filiacaoReferencia;
    }

    public String getFiliacaoNumero() {
        return filiacaoNumero;
    }

    public void setFiliacaoNumero(String filiacaoNumero) {
        this.filiacaoNumero = filiacaoNumero;
    }

    public String getFiliacaoCep() {
        return filiacaoCep;
    }

    public void setFiliacaoCep(String filiacaoCep) {
        this.filiacaoCep = filiacaoCep;
    }

    public String getFiliacaoBairro() {
        return filiacaoBairro;
    }

    public void setFiliacaoBairro(String filiacaoBairro) {
        this.filiacaoBairro = filiacaoBairro;
    }

    public Integer getFiliacaoIdcidade() {
        return filiacaoIdcidade;
    }

    public void setFiliacaoIdcidade(Integer filiacaoIdcidade) {
        this.filiacaoIdcidade = filiacaoIdcidade;
    }

    public Integer getFiliacaoIdestado() {
        return filiacaoIdestado;
    }

    public void setFiliacaoIdestado(Integer filiacaoIdestado) {
        this.filiacaoIdestado = filiacaoIdestado;
    }

    public String getAvalistaNome() {
        return avalistaNome;
    }

    public void setAvalistaNome(String avalistaNome) {
        this.avalistaNome = avalistaNome;
    }

    public String getAvalistaCpf() {
        return avalistaCpf;
    }

    public void setAvalistaCpf(String avalistaCpf) {
        this.avalistaCpf = avalistaCpf;
    }

    public String getAvalistaRg() {
        return avalistaRg;
    }

    public void setAvalistaRg(String avalistaRg) {
        this.avalistaRg = avalistaRg;
    }

    public Date getAvalistaNascimento() {
        return avalistaNascimento;
    }

    public void setAvalistaNascimento(Date avalistaNascimento) {
        this.avalistaNascimento = avalistaNascimento;
    }

    public String getAvalistaFone() {
        return avalistaFone;
    }

    public void setAvalistaFone(String avalistaFone) {
        this.avalistaFone = avalistaFone;
    }

    public String getAvalistaEndereco() {
        return avalistaEndereco;
    }

    public void setAvalistaEndereco(String avalistaEndereco) {
        this.avalistaEndereco = avalistaEndereco;
    }

    public String getAvalistaNumero() {
        return avalistaNumero;
    }

    public void setAvalistaNumero(String avalistaNumero) {
        this.avalistaNumero = avalistaNumero;
    }

    public String getAvalistaCep() {
        return avalistaCep;
    }

    public void setAvalistaCep(String avalistaCep) {
        this.avalistaCep = avalistaCep;
    }

    public String getAvalistaBairro() {
        return avalistaBairro;
    }

    public void setAvalistaBairro(String avalistaBairro) {
        this.avalistaBairro = avalistaBairro;
    }

    public String getAvalistaEmpresa() {
        return avalistaEmpresa;
    }

    public void setAvalistaEmpresa(String avalistaEmpresa) {
        this.avalistaEmpresa = avalistaEmpresa;
    }

    public String getAvalistaCargo() {
        return avalistaCargo;
    }

    public void setAvalistaCargo(String avalistaCargo) {
        this.avalistaCargo = avalistaCargo;
    }

    public Double getAvalistaRenda() {
        return avalistaRenda;
    }

    public void setAvalistaRenda(Double avalistaRenda) {
        this.avalistaRenda = avalistaRenda;
    }

    public Date getAvalistaAdmissao() {
        return avalistaAdmissao;
    }

    public void setAvalistaAdmissao(Date avalistaAdmissao) {
        this.avalistaAdmissao = avalistaAdmissao;
    }

    public Integer getAvalistaIdcidade() {
        return avalistaIdcidade;
    }

    public void setAvalistaIdcidade(Integer avalistaIdcidade) {
        this.avalistaIdcidade = avalistaIdcidade;
    }

    public Integer getAvalistaIdestado() {
        return avalistaIdestado;
    }

    public void setAvalistaIdestado(Integer avalistaIdestado) {
        this.avalistaIdestado = avalistaIdestado;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Cliente.Cliente[ id=" + id + " ]";
    }

}
