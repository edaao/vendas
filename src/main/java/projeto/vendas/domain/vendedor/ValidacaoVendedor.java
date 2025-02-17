package projeto.vendas.domain.vendedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.vendas.domain.ValidacaoException;
import projeto.vendas.utils.CpfCnpjValidationService;
import projeto.vendas.utils.RetornoApiValidadorCpfCnpj;

@Service
public class ValidacaoVendedor {

    @Autowired
    VendedorRepository vendedorRepository;

    @Autowired
    private CpfCnpjValidationService cpfCnpjValidationService;

    public Vendedor validacao(DadosAtualizacaoVendedor dados) {

        return vendedorRepository.findById(dados.id())
                .orElseThrow(() -> new ValidacaoException("Vendedor informado não existe!"));
    }

    public RetornoApiValidadorCpfCnpj validacaoCadastro(String cpf) {
        //var RetornoApiValidadorCpfCnpj = cpfCnpjValidationService.validarCpfCnpj(cpf,"cpf");
//        if(RetornoApiValidadorCpfCnpj.valid() == "false") {
//            throw new ValidacaoException("Cpf ou CNPJ invalido");
//        }
        //return RetornoApiValidadorCpfCnpj;

        return cpfCnpjValidationService.validarCpfCnpj(cpf,"cpf");
    }
}
