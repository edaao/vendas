package projeto.vendas.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import projeto.vendas.integracao.ValidadorDados;

@Service
public class ConsultaCepService {

    private final ValidadorDados validadorDados;

    @Value("${validador-dados.token}")
    private String token;

    public ConsultaCepService(ValidadorDados validadorDados) {
        this.validadorDados = validadorDados;
    }

    public RetornoApiCep consultaCep(String cep) {
        return validadorDados.consultaCep(cep, token);
    }


}
