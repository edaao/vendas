package projeto.vendas.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import projeto.vendas.utils.ConsultaCepService;
import projeto.vendas.utils.RetornoApiCep;

@RestController
@RequestMapping("/utils")
@SecurityRequirement(name = "bearer-key")
public class UtilsController {

    @Autowired
    private ConsultaCepService consultaCepService;

    @GetMapping
    public ResponseEntity consultaCep(@RequestParam ("cep") String cep) {

        var retornoCep = consultaCepService.consultaCep(cep);

        return ResponseEntity.ok(new RetornoApiCep(retornoCep));

    }
}
