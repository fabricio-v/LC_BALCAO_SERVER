/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessaoAberta;

import Lcserver.Conexao.Database;
import Lcserver.Configuracao.Config;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class SessaoAberta {

    public static final String versao = "v1.0.4 MOBILE";
    private static int qntMobilePermitida = 0;
    private static String cnpj = "";
    private static Config config;
    private static Database database;

    public static String getVersao() {
        return versao;
    }

    public static int getQntMobilePermitida() {
        return qntMobilePermitida;
    }

    public static void setQntMobilePermitida(int qntMobilePermitida) {
        SessaoAberta.qntMobilePermitida = qntMobilePermitida;
    }

    public static String getCnpj() {
        return cnpj;
    }

    public static void setCnpj(String cnpj) {
        SessaoAberta.cnpj = cnpj;
    }

    public static Config getConfig() {
        return config;
    }

    public static void setConfig(Config config) {
        SessaoAberta.config = config;
    }

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(Database database) {
        SessaoAberta.database = database;
    }

}
