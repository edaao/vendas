package projeto.vendas.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.vendas.enums.StatusEnum;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("""
            select c from Cliente c
            where (:cnpj IS NULL OR c.cnpj = :cnpj)
            and (:razaoSocial IS NULL OR c.razaoSocial like %:razaoSocial%)
            and (:status IS NULL OR c.status = :status)
            """)
    Page<Cliente> consultaClienteCnpjOrRazaoSocial(@Param("cnpj") String cnpj,
                                                   @Param("razaoSocial") String razaoSocial,
                                                   @Param("status")StatusEnum status,
                                                   Pageable pageable);

    Optional<Cliente> findByCnpj(String cnpj);
}
