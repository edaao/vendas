package projeto.vendas.domain.vendedor;

import jakarta.validation.constraints.NotNull;
import projeto.vendas.domain.usuario.endereco.DadosEndereco;
import projeto.vendas.enums.StatusEnum;

public record DadosAtualizacaoVendedor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco,
        @NotNull
        StatusEnum status) {
}
