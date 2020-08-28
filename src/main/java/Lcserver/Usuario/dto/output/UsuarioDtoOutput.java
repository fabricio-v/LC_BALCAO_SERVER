/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Usuario.dto.output;

import Lcserver.Empresa.dto.output.EmpresaDtoOutput;
import Lcserver.Usuario.Usuario;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class UsuarioDtoOutput {

    private Integer id;
    private String login;
    private String nome;
    private Double descontoPermitido;
    private Double descontoPermitidoCR;
    private String permissoes;
    private EmpresaDtoOutput empresa;

    public UsuarioDtoOutput(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.nome = usuario.getNome();
        this.descontoPermitido = usuario.getDescontoPermitido();
        this.descontoPermitidoCR = usuario.getDescontoPermitidoCR();
        this.permissoes = usuario.getPermissoes();
        this.empresa = new EmpresaDtoOutput(usuario.getEmpresa());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getDescontoPermitido() {
        return descontoPermitido;
    }

    public void setDescontoPermitido(Double descontoPermitido) {
        this.descontoPermitido = descontoPermitido;
    }

    public Double getDescontoPermitidoCR() {
        return descontoPermitidoCR;
    }

    public void setDescontoPermitidoCR(Double descontoPermitidoCR) {
        this.descontoPermitidoCR = descontoPermitidoCR;
    }

    public String getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(String permissoes) {
        this.permissoes = permissoes;
    }

    public EmpresaDtoOutput getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDtoOutput empresa) {
        this.empresa = empresa;
    }
    
    
    
    
    

}
