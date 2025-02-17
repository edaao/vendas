package projeto.vendas.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import projeto.vendas.utils.RetornoApiCep;
import projeto.vendas.utils.RetornoApiValidadorCpfCnpj;

@FeignClient(name = "validadorDadosClient", url = "${validador-dados.url}")
public interface ValidadorDados {

    @GetMapping("/validator")
    RetornoApiValidadorCpfCnpj buscarDados(@RequestParam("token") String token,
                                           @RequestParam("value")String cpf,
                                           @RequestParam("type") String type);

    @GetMapping("/cep/{cep}")
    RetornoApiCep consultaCep(@PathVariable("cep") String cep,
                              @RequestParam ("token") String token);

}



