/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.SpringConfig.Database;

/**
 *
 * @author Fabricio
 */
public class Database {

    private String driver = "com.mysql.jdbc.Driver";
    private String ip = "";
    private String database = "";
    private String usuario = "";
    private String senha = "";
    private String porta = "";
    private String terminal_tipo = "";
    private String id_empresa_padrao = "";

    public Database() {
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getTerminal_tipo() {
        return terminal_tipo;
    }

    public void setTerminal_tipo(String terminal_tipo) {
        this.terminal_tipo = terminal_tipo;
    }

    public String getId_empresa_padrao() {
        return id_empresa_padrao;
    }

    public void setId_empresa_padrao(String id_empresa_padrao) {
        this.id_empresa_padrao = id_empresa_padrao;
    }
}
