package projeto.vendas.domain.cliente;

import jakarta.validation.constraints.NotNull;
import projeto.vendas.domain.usuario.endereco.DadosEndereco;
import projeto.vendas.enums.StatusEnum;

public record DadosAtualizacaoCliente(String cnpj,
                                      String nomeFantasia,
                                      @NotNull
                                      StatusEnum status,
                                      DadosEndereco endereco) {
}
