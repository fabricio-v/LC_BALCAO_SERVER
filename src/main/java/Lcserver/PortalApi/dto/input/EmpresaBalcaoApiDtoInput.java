/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.PortalApi.dto.input;

import Lcserver.Cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcsistemas.lc.LCBalcao.ConstantesEnum.PaisesEstaticos;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;

/**
 *
 * @author Jordan LC Sistemas
 */
public class EmpresaBalcaoApiDtoInput {

    private Integer id;
    private String cpfCnpj;
    private String nome;
    private String apelido;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-3")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-3")
    private Date emissao;
    private String orgao;
    private String rg;
    private String razaoSocial;
    private String endereco;
    private String numero;
    private String referencia;
    private String cep;
    private String bairro;
    private String email;
    private String telefone;
    private String telefone2;
    private String celular;
    private String estado;
    private String cidade;
    private String tipo; //recebe J ou F
    private String ie;
    private String ieIndicador;
    private String im;
    private String pais;
    private String ativo;

    private String detalhe;
    private String message;

    public EmpresaBalcaoApiDtoInput() {
    }

    public EmpresaBalcaoApiDtoInput(Cliente c) {
        this.id = c.getId();
        this.cpfCnpj = c.getCpfCnpj();
        this.tipo = c.getTipo();
        this.ie = c.getIe();
        this.ieIndicador = c.getIeIndicador();
        this.im = c.getIm();
        this.nome = c.getNome();
        this.apelido = c.getApelidoAdi();
        this.rg = c.getRg();
        this.orgao = c.getOrgao();
        this.emissao = c.getEmissao();
        this.razaoSocial = c.getRazaoSocial();
        this.endereco = c.getEndereco();
        this.numero = c.getNumero();
        this.referencia = c.getReferencia();
        this.cep = c.getCep();
        this.bairro = c.getBairro();
        this.email = c.getEmailAdi();
        this.telefone = c.getTelefone();
        this.telefone2 = c.getTelComercial();
        this.celular = c.getFax();
        this.estado = c.getEstado().getNome().toUpperCase();
        this.cidade = c.getCidade().getNome().toLowerCase();
        this.pais = c.getIdPais() == PaisesEstaticos.BRASIL.getId()
                ? PaisesEstaticos.BRASIL.name()
                : Arrays.asList(PaisesEstaticos.values())
                .stream().filter(p -> c.getIdPais() == p.getId())
                .findFirst().get().getDescricao();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
