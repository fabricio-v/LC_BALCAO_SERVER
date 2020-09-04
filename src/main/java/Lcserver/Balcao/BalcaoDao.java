/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao;

import Lcserver.Exception.OperacaoInvalidaException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface BalcaoDao extends JpaRepository<Balcao, Integer> {

    @Query(value = "select now() as data", nativeQuery = true)
    Date getDataServer();

    //@Query(value = "select * from Balcao b inner join Cliente c on b.id_cliente = c.id where (b.id like ?1 or c.nome like ?2 or c.cpf_cnpj like ?2 or c.razao_social like ?2) and b.status!='PF' order by b.id desc limit 100", nativeQuery = true)
    @Query(value = "select * from Balcao b INNER JOIN Cliente c ON b.id_cliente = c.id where (b.id like ?1 or c.nome like ?2 or c.cpf_cnpj like ?2 or c.razao_social like ?2) and b.status!='EX' order by b.id desc limit 100", nativeQuery = true)
    List<Balcao> getBalcaoListByIdByClienteNomeByCpfCnpjByRazaoAndPF(String id, String nome);

    @Query(value = "select new Balcao (b.id,b.empresa,b.usuario,b.cliente,b.dataHora,b.total,b.descTipo,b.descForma,b.desconto,b.valorDesconto,b.arredondamento,b.subTotal,b.status,b.obs) from Balcao b WHERE (b.id like str(?1) or b.cliente.nome like ?1 or b.cliente.cpfCnpj like ?1 or b.cliente.razaoSocial like ?1) and b.usuario.id like str(?2) and b.empresa.id = ?3 and b.status!='PF' and b.status!='EX' order by b.id desc")
    List<Balcao> getBalcaoListSemDetByIdByClienteNomeByCpfCnpjByRazaoAndPFByUser(String nome, String idUsuario, Integer idEmpresa, Pageable pageable);

    //@Query(value = "select new Balcao (b.id,b.empresa,b.usuario,b.cliente,b.idBalcaosb,b.idOrcamento,b.dataHora,b.total,b.descTipo,b.descForma,b.desconto,b.valorDesconto,b.arredondamento,b.subTotal,b.status) from Balcao b WHERE (b.id like str(?1) or b.cliente.nome like ?1 or b.cliente.cpfCnpj like ?1 or b.cliente.razaoSocial like ?1) and b.status!='PF' order by b.id desc")
    //List<Balcao> getBalcaoListSemDetByIdByClienteNomeByCpfCnpjByRazaoAndPFByUser(String nome, Pageable pageable);
    //@Query(value = "select new Balcao (b.id,b.empresa,b.usuario,b.cliente,b.idBalcaosb,b.idOrcamento,b.dataHora,b.total,b.descTipo,b.descForma,b.desconto,b.valorDesconto,b.arredondamento,b.subTotal,b.status) from Balcao b WHERE (b.id like str(?1) or b.cliente.nome like ?1 or b.cliente.cpfCnpj like ?1 or b.cliente.razaoSocial like ?1) and b.usuario.id = ?2 and b.status!='PF' order by b.id desc")
    //List<Balcao> getBalcaoListSemDetByIdByClienteNomeByCpfCnpjByRazaoAndPFByUser(String nome, Integer user, Pageable pageable);
    @Transactional
    public Balcao save(Balcao balcao) throws OperacaoInvalidaException;

    @Query(value = "select b from Balcao b WHERE b.id like str(?1) and b.empresa.id = ?2 and b.status!='PF' and b.status!='EX' order by b.id desc")
    public Optional<Balcao> getBalcaoById(Integer id, Integer idEmpresa);

}
