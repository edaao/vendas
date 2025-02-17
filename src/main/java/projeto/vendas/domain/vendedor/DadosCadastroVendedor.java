package projeto.vendas.domain.vendedor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import projeto.vendas.domain.usuario.endereco.Endereco;

public record DadosCadastroVendedor(
         @NotBlank
         String nome,
         @NotBlank
         @Pattern(regexp = "\\d{11}")
         String cpf,
         @NotNull
         String telefone,
         @NotBlank
         @Email
         String email,
         @NotNull
         @Valid
         Endereco endereco

) {
}
