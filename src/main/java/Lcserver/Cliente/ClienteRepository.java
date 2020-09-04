/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Cliente;

import Lcserver.Cliente.form.ClienteDebitoForm;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "select now() as data", nativeQuery = true)
    Date getDataServer();
    
    @Query(value = "select * from Cliente where (id like ?1 or cpf_cnpj like ?2 or razao_social like ?2 or nome like ?2 or apelido_adi like ?2) and id>2 and ativo = 1 order by nome limit 20", nativeQuery = true)
    List<Cliente> getClienteDetalhado(String id, String nome);
    
    @Query(value = "select * from Cliente where numero_cartao like ?1 and ativo = 1 limit 20", nativeQuery = true)
    Cliente getClienteCartao(String cartao);
    
    @Query(value = "select c from Cliente c where c.cpfCnpj like ?1")
    Optional<Cliente> getClienteByCpfCnpj(String cpfCnpj);
    
    //@Query(value = "select * from Cliente where id = ?1 or cpf_cnpj= ?1 or c.numero_cartao =1? or razao_social like ?2 or nome like ?2 or apelido like ?2 or telefone like ?3 limit 20", nativeQuery = true)
    //List<Cliente> getCliente(String id, String nome, String telefone);

    @Query("select new Lcserver.Cliente.form.ClienteDebitoForm (r.valorOriginal, datediff(r.vencimento, curdate()) ) from Receber r where r.idCliente = ?1 and r.tipo <> 'CC' and r.status = 'CA' and r.pagamento.podeVercredito = 'S' ")
    List<ClienteDebitoForm> getDebitoCliente(int idCliente);
    
    @Query("select r.id from Receber r where r.pagamento.podeVercredito = 'S' and (datediff(r.vencimento,curdate())) < 0 and r.tipo <> 'CC' and r.status = 'CA' and r.idCliente = ?1")
    List<Integer> listaIdReceberAtrasos(int idCliente);
    
}
