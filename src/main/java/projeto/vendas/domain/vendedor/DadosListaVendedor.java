package projeto.vendas.domain.vendedor;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import projeto.vendas.domain.usuario.endereco.Endereco;
import projeto.vendas.enums.StatusEnum;

public record DadosListaVendedor(Long id,
                                 String nome,
                                 String cpf,
                                 String telefone,
                                 String email,
                                 Endereco endereco,
                                 StatusEnum status) {

    public DadosListaVendedor(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getCpf(), vendedor.getTelefone(), vendedor.getEmail(), vendedor.getEndereco(), vendedor.getStatus());
    }
}
