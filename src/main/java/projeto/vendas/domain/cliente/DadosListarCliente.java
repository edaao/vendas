package projeto.vendas.domain.cliente;

import projeto.vendas.domain.usuario.endereco.Endereco;
import projeto.vendas.enums.StatusEnum;

public record DadosListarCliente(Long id,
                                 String cnpj,
                                 String razaoSocial,
                                 String nomeFantasia,
                                 String inscricaoEstadual,
                                 StatusEnum status,
                                 Endereco endereco) {

    public DadosListarCliente(Cliente cliente) {
        this(cliente.getId(),
                cliente.getCnpj(),
                cliente.getRazaoSocial(),
                cliente.getNomeFantasia(),
                cliente.getInscricaoEstadual(),
                cliente.getStatus(),
                cliente.getEndereco());
    }
}
