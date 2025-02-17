package projeto.vendas.domain.vendedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.vendas.enums.StatusEnum;

public interface VendedorRepository extends JpaRepository <Vendedor, Long> {
    Page<Vendedor> findAll(Pageable pageable);

    Vendedor findByCpf(String cpf);

    @Query("""
            select v from Vendedor v
            where (:cpf IS NULL OR v.cpf = :cpf)
            and (:nome is null or v.nome like %:nome%)
            and (:status is null or v.status = :status)
            order by v.nome
            """)
    Page<Vendedor> consultaVendedorCpfOrNome(@Param("cpf") String cpf,
                                             @Param("nome") String nome,
                                             @Param("status")StatusEnum status,
                                             Pageable pageable);
}
