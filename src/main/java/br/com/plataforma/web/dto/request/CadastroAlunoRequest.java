package br.com.plataforma.web.dto.request;

import br.com.plataforma.shared.enumeration.Periodo;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroAlunoRequest {

    // Credenciais
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 100)
    private String senha;

    // Dados pessoais
    @NotBlank
    @Size(max = 180)
    private String nomeCompleto;

    @NotBlank
    @Size(max = 14)
    private String cpf;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @Size(max = 20)
    private String telefone;

    // Dados acadêmicos
    @NotNull
    private Long cursoId;

    @NotNull
    private Long campusId;

    @NotBlank
    @Size(max = 30)
    private String matricula;

    @NotNull
    private Periodo periodo;

    @NotNull
    @PastOrPresent
    private LocalDate dataInicio;

    private LocalDate dataPrevisaoFim;

    // Tipo de aluno (TECNICO, SUPERIOR, POS_GRADUACAO)
    @NotNull
    private String tipoAluno;
}
