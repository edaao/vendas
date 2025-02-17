package projeto.vendas.domain.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.vendas.domain.ValidacaoException;
import projeto.vendas.utils.CpfCnpjValidationService;
import projeto.vendas.utils.RetornoApiValidadorCpfCnpj;

@Service
public class ValidacaoCliente {

    @Autowired
    private CpfCnpjValidationService validationService;

    @Autowired
    private ClienteRepository clienteRepository;

    public RetornoApiValidadorCpfCnpj validadorCpfCnpj(String cnpj) {

        return validationService.validarCpfCnpj(cnpj,"cnpj");

    }

    public Cliente validacaoCliente(DadosAtualizacaoCliente cliente) {

        return clienteRepository.findByCnpj(cliente.cnpj())
                .orElseThrow(() -> new ValidacaoException("Cliente informado não existe"));
    }
}
