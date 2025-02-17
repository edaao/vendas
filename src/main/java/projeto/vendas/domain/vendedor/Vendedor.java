package projeto.vendas.domain.vendedor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import projeto.vendas.domain.usuario.endereco.Endereco;
import projeto.vendas.enums.StatusEnum;


@Table(name = "vendedor")
@Entity(name = "Vendedor")
@Data
@EqualsAndHashCode(of = "id")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ATIVO;

    public Vendedor() {
    }

    public Vendedor(DadosCadastroVendedor cadastroVendedor) {
        this.nome = cadastroVendedor.nome();
        this.cpf = cadastroVendedor.cpf();
        this.telefone = cadastroVendedor.telefone();
        this.email = cadastroVendedor.email();
        this.endereco = cadastroVendedor.endereco();
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoVendedor dadosAtualizacaoVendedor) {
        if(dadosAtualizacaoVendedor.id() != null) {
            this.id = dadosAtualizacaoVendedor.id();
        }

        if(dadosAtualizacaoVendedor.nome() != null) {
            this.nome = dadosAtualizacaoVendedor.nome();
        }

        if(dadosAtualizacaoVendedor.telefone() != null) {
            this.telefone = dadosAtualizacaoVendedor.telefone();
        }

        if(dadosAtualizacaoVendedor.email() != null) {
            this.email = dadosAtualizacaoVendedor.email();
        }

        if(dadosAtualizacaoVendedor.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoVendedor.endereco());
        }

        if(dadosAtualizacaoVendedor.status() != null) {
            this.status = dadosAtualizacaoVendedor.status();
        }
    }
}
