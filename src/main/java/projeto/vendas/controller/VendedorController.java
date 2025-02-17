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
import projeto.vendas.domain.ValidacaoException;
import projeto.vendas.domain.vendedor.*;
import projeto.vendas.enums.StatusEnum;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vendedor")
@SecurityRequirement(name = "bearer-key")
public class VendedorController {

    @Autowired
    private VendedorRepository repository;

    @Autowired
    private ValidacaoVendedor validacaoVendedor;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVendedor dados, UriComponentsBuilder builder) {

        validacaoVendedor.validacaoCadastro(dados.cpf());
        var vendedor = new Vendedor(dados);
        repository.save(vendedor);

        var uri = builder.path("/vendedor/{id}").buildAndExpand(vendedor.getId()).toUri();
        //return ResponseEntity.created(uri).body(new DadosListaVendedor(vendedor));
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Vendedor cadastrado com sucesso!");
        response.put("dados", new DadosListaVendedor(vendedor));

        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping("/{cpf}")
    public ResponseEntity listarVendedorCpf(@PathVariable String cpf) {
        var vendedor = repository.findByCpf(cpf);

        if (vendedor == null) {
            throw new ValidacaoException("CPF inválido!");
        }

        return ResponseEntity.ok(new DadosListaVendedor(vendedor));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListaVendedor>> listarVendedorFiltros(@RequestParam(required = false) String cpf,
                                                                          @RequestParam(required = false) String nome,
                                                                          @RequestParam(required = false) StatusEnum status,
                                                                          Pageable pageable) throws ValidacaoException {

        var page = repository.consultaVendedorCpfOrNome(cpf, nome, status, pageable).map(DadosListaVendedor::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoVendedor dados) {

        var vendedor = validacaoVendedor.validacao(dados);
        vendedor.atualizarInformacoes(dados);


        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Vendedor atualizado com sucesso!");
        response.put("dados", new DadosListaVendedor(vendedor));
        return ResponseEntity.ok().body(response);
    }
}
