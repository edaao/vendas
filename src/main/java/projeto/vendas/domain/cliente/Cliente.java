package projeto.vendas.domain.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import projeto.vendas.domain.usuario.endereco.Endereco;
import projeto.vendas.enums.StatusEnum;

import java.time.LocalDateTime;

@Table(name = "cliente")
@Entity(name = "Cliente")
@Data
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d{14}")
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ATIVO;

    @Embedded
    private Endereco endereco;

    public Cliente() {

    }

    public Cliente(DadosCadastroCliente dadosCadastroCliente) {
        this.cnpj = dadosCadastroCliente.cnpj();
        this.razaoSocial = dadosCadastroCliente.razaoSocial();
        this.nomeFantasia = dadosCadastroCliente.nomeFantasia();
        this.inscricaoEstadual = dadosCadastroCliente.inscricaoEstadual();
        this.dataCadastro = dataCadastro;
        this.status = status;
        this.endereco = dadosCadastroCliente.endereco();
    }

    public void atualizaInformacoes(DadosAtualizacaoCliente dadosAtualizacaoCliente) {
        if(dadosAtualizacaoCliente.nomeFantasia() != null) {
            this.nomeFantasia = dadosAtualizacaoCliente.nomeFantasia();
        }

        if(dadosAtualizacaoCliente.status() != null) {
            this.status = dadosAtualizacaoCliente.status();
        }

        if(dadosAtualizacaoCliente.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoCliente.endereco());
        }

    }
}
