/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Usuario.dto.input;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class UsuarioDtoInput {
    
    
    private String id;
    private String senha;

    public UsuarioDtoInput() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
       
}
