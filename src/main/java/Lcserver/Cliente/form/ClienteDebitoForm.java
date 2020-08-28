/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Cliente.form;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class ClienteDebitoForm {
    private Double valorOrigiral;
 
    private Integer dias;

    public ClienteDebitoForm(Double valorOrigiral,  Integer dias) {
        this.valorOrigiral = valorOrigiral;

        this.dias = dias;
    }

    public Double getValorOrigiral() {
        return valorOrigiral;
    }

    public void setValorOrigiral(Double valorOrigiral) {
        this.valorOrigiral = valorOrigiral;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }
    
    
    
}
