package projeto.vendas.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import projeto.vendas.domain.ValidacaoException;
import projeto.vendas.integracao.ValidadorDados;

@Service
public class CpfCnpjValidationService {

    private final ValidadorDados validadorDados;

    @Value("${validador-dados.token}")
    private String token;

    public CpfCnpjValidationService(ValidadorDados validadorDados) {
        this.validadorDados = validadorDados;
    }

    public RetornoApiValidadorCpfCnpj validarCpfCnpj(String cpfCnpj, String tipoDados) {
        var retornoApi = validadorDados.buscarDados(token, cpfCnpj, tipoDados);
        if(retornoApi.valid() == "false") {
            throw new ValidacaoException("Cpf ou CNPJ invalido");
        }
        return retornoApi;
    }
}
