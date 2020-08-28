/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Cliente.dto.output;

import Lcserver.Cliente.Cliente;
import Lcserver.EstadoCidade.Cidades;
import Lcserver.EstadoCidade.Estados;
import java.io.Serializable;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class ClienteDtoOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String cpfCnpj;
    private String nome;
    private String razaoSocial;
    private String numeroCartao;
    private String endereco;
    private String bairro;
    private String numero;
    private String cep;
    private Estados estado;
    private Cidades cidade;
    private String referencia;
    private String apelidoAdi;
    private String telefone;
    private String tel_comercial;
    private String fax;
    private String podeAprazo;
    private String ativo;
    private double limiteCredito;

    private double debito;
    private boolean isDevendo;

    public ClienteDtoOutput(Cliente cliente) {
        this.id = cliente.getId();
        this.cpfCnpj = cliente.getCpfCnpj();
        this.nome = cliente.getNome();
        this.razaoSocial = cliente.getRazaoSocial();
        this.numeroCartao = cliente.getNumeroCartao();
        this.endereco = cliente.getEndereco();
        this.bairro = cliente.getBairro();
        this.numero = cliente.getNumero();
        this.cep = cliente.getCep();
        this.estado = new Estados(cliente.getEstado().getId(), cliente.getEstado().getUf(), cliente.getEstado().getNome());
        this.cidade = new Cidades(cliente.getId(), cliente.getNome());
        this.referencia = cliente.getReferencia();
        this.apelidoAdi = cliente.getApelidoAdi();
        this.telefone = cliente.getTelefone();
        this.tel_comercial = cliente.getTelComercial();
        this.fax = cliente.getFax();
        this.podeAprazo = cliente.getPodeAprazo();
        this.ativo = cliente.getAtivo();
        this.limiteCredito = cliente.getLimiteCredito();
    }

    public ClienteDtoOutput(Cliente cliente, double debito, boolean isDevendo) {
        this.id = cliente.getId();
        this.cpfCnpj = cliente.getCpfCnpj();
        this.nome = cliente.getNome();
        this.razaoSocial = cliente.getRazaoSocial();
        this.numeroCartao = cliente.getNumeroCartao();
        this.endereco = cliente.getEndereco();
        this.bairro = cliente.getBairro();
        this.numero = cliente.getNumero();
        this.cep = cliente.getCep();
        this.estado = new Estados(cliente.getEstado().getId(), cliente.getEstado().getUf(), cliente.getEstado().getNome());
        this.cidade = new Cidades(cliente.getId(), cliente.getNome());
        this.referencia = cliente.getReferencia();
        this.apelidoAdi = cliente.getApelidoAdi();
        this.telefone = cliente.getTelefone();
        this.tel_comercial = cliente.getTelComercial();
        this.fax = cliente.getFax();
        this.podeAprazo = cliente.getPodeAprazo();
        this.limiteCredito = cliente.getLimiteCredito();
        this.ativo = cliente.getAtivo();
        this.debito = debito;
        this.isDevendo = isDevendo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getApelidoAdi() {
        return apelidoAdi;
    }

    public void setApelidoAdi(String apelidoAdi) {
        this.apelidoAdi = apelidoAdi;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTel_comercial() {
        return tel_comercial;
    }

    public void setTel_comercial(String tel_comercial) {
        this.tel_comercial = tel_comercial;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPodeAprazo() {
        return podeAprazo;
    }

    public void setPodeAprazo(String podeAprazo) {
        this.podeAprazo = podeAprazo;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public boolean isIsDevendo() {
        return isDevendo;
    }

    public void setIsDevendo(boolean isDevendo) {
        this.isDevendo = isDevendo;
    }

}
