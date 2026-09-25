package br.com.plataforma.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroEmpresaRequest {

    // Credenciais
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 100)
    private String senha;

    // Dados da empresa
    @NotBlank
    @Size(max = 180)
    private String razaoSocial;

    @Size(max = 180)
    private String nomeFantasia;

    @NotBlank
    @Size(max = 18)
    private String cnpj;

    @Size(max = 500)
    private String descricao;

    @Size(max = 255)
    private String site;

    @Size(max = 255)
    private String logoUrl;

    @Size(max = 100)
    private String cidade;

    @Size(max = 2)
    private String uf;

    // Responsável
    @Size(max = 180)
    private String responsavelNome;

    @Size(max = 100)
    private String responsavelCargo;

    @Size(max = 180)
    private String responsavelEmail;

    @Size(max = 20)
    private String responsavelTelefone;
}
