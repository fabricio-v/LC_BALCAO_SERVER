/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Jordan LC Sistemas
 */
public  class FuncoesFormataTexto {
    
    
    public static String formataCpf_Cnpj(String cpfCnpj) {
        cpfCnpj = removeMascaras(cpfCnpj);
        if (cpfCnpj.length() == 14) {
            cpfCnpj = cpfCnpj.substring(0, 2) + "." + cpfCnpj.substring(2, 5) + "." + cpfCnpj.substring(5, 8) + "/" + cpfCnpj.substring(8, 12) + "-" + cpfCnpj.substring(12, 14);
        } else if (cpfCnpj.length() == 11) {
            cpfCnpj = cpfCnpj.substring(0, 3) + "." + cpfCnpj.substring(3, 6) + "." + cpfCnpj.substring(6, 9) + "-" + cpfCnpj.substring(9, 11);
        }
        return cpfCnpj;
    }
    
    
    public static String removeMascaras(String txt) {
        if (txt != null) {
            txt = txt.replaceAll("\\.", "").replace("(", "").replace(")", "").replace("-", "").replaceAll("/", "");
            txt = txt.trim();
        }
        return txt;
    }
    
}
