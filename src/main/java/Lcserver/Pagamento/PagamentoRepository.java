/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Pagamento;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author JORDAN QUEIROGA
 */
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
    
    @Query("select p from Pagamento p where p.ativo = 1 and p.podeRpt in ('T','R')")
    List<Pagamento> getListaPagamentoReceber();
    
}
