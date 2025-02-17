package projeto.vendas.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import projeto.vendas.domain.usuario.endereco.Endereco;

public record DadosCadastroCliente(
        @NotBlank
        String cnpj,
        @NotBlank
        String razaoSocial,
        String nomeFantasia,
        String inscricaoEstadual,
        Endereco endereco
) {
}
