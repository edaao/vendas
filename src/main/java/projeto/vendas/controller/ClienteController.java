package projeto.vendas.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.vendas.domain.cliente.*;
import projeto.vendas.enums.StatusEnum;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ValidacaoCliente validacaoCliente;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente cadastroCliente, UriComponentsBuilder builder) {

        validacaoCliente.validadorCpfCnpj(cadastroCliente.cnpj());
        var cliente = new Cliente(cadastroCliente);
        clienteRepository.save(cliente);

        var uri = builder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListarCliente(cliente));

    }

    @GetMapping
    public ResponseEntity <Page<DadosListarCliente>> listarCliente(@RequestParam(required = false) String cnpj,
                                                                   @RequestParam(required = false) String razaoSocial,
                                                                   @RequestParam(required = false)StatusEnum status,
                                                                   Pageable pageable) {

        var page = clienteRepository.consultaClienteCnpjOrRazaoSocial(cnpj, razaoSocial, status,pageable).map(DadosListarCliente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dadosCliente) {

        var cliente = validacaoCliente.validacaoCliente(dadosCliente);
        cliente.atualizaInformacoes(dadosCliente);

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Cliente atualizado com sucesso!");
        response.put("dados", new DadosListarCliente(cliente));

        return ResponseEntity.ok().body(response);
    }
}
