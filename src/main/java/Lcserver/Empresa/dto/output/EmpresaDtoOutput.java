/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Empresa.dto.output;

import Lcserver.Empresa.Empresa;
import Lcserver.EstadoCidade.Cidades;
import Lcserver.EstadoCidade.Estados;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class EmpresaDtoOutput {

    private Integer id;
    private String fantasia;
    private String razaoSocial;
    private String cnpj;
    private String ie;
    private String im;
    private String endereco;
    private String numero;
    private String telefone;
    private Estados estado;
    private Cidades cidade;

    public EmpresaDtoOutput(Empresa empresa) {
        this.id = empresa.getId();
        this.fantasia = empresa.getFantasia();
        this.razaoSocial = empresa.getRazaoSocial();
        this.cnpj = empresa.getCnpj();
        this.ie = empresa.getIe();
        this.im = empresa.getIm();
        this.endereco = empresa.getEndereco();
        this.numero = empresa.getNumero();
        this.telefone = empresa.getTelefone();
        this.cidade = empresa.getCidade();
        this.estado = empresa.getEstado();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

}
