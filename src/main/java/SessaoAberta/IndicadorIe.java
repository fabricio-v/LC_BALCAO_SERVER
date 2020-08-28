/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessaoAberta;

/**
 *
 * @author Jordan LC Sistemas
 */
public enum IndicadorIe {

    CONTRIBUINTE_ICMS(1), CONTRIBUINTE_ISENTO(2), CONTRIBUINTE_NAO(9);
    
    private int codigo;

    private IndicadorIe(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
    
    

}
