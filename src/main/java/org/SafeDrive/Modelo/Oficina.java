package org.SafeDrive.Modelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa uma oficina.
 */
public class Oficina extends EntidadeBase {

    @NotBlank(message = "O login é obrigatório.")
    private String login;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 caracteres.")
    private String cnpj;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotNull(message = "A lista de endereços não pode ser nula.")
    private List<String> enderecos; // Lista de endereços

    @NotBlank(message = "O nome da oficina é obrigatório.")
    private String nomeOficina;

    @NotBlank(message = "A especialidade é obrigatória.")
    private String especialidade;

    private String orcamento; // Considerando que o orçamento é um valor ou string, ajuste conforme necessário

    @NotBlank(message = "O nome do proprietário é obrigatório.")
    private String nomeProprietario; // Nome do proprietário da oficina

    public Oficina() {
        this.enderecos = new ArrayList<>(); // Inicializa a lista de endereços
    }

    public Oficina(String login, String cnpj, String telefone, List<String> enderecos,
                   String nomeOficina, String especialidade, String orcamento, String nomeProprietario) {
        this.login = login;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.enderecos = enderecos != null ? enderecos : new ArrayList<>(); // Inicializa a lista de endereços
        this.nomeOficina = nomeOficina;
        this.especialidade = especialidade;
        this.orcamento = orcamento;
        this.nomeProprietario = nomeProprietario;
    }

    public Oficina(int id, boolean deletado, String login, String cnpj, String telefone, List<String> enderecos,
                   String nomeOficina, String especialidade, String orcamento, String nomeProprietario) {
        super(id, deletado);
        this.login = login;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.enderecos = enderecos != null ? enderecos : new ArrayList<>(); // Inicializa a lista de endereços
        this.nomeOficina = nomeOficina;
        this.especialidade = especialidade;
        this.orcamento = orcamento;
        this.nomeProprietario = nomeProprietario;
    }

    // Getters e Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<String> enderecos) {
        if (enderecos != null && !enderecos.isEmpty()) {
            this.enderecos = enderecos;
        } else {
            throw new IllegalArgumentException("A lista de endereços não pode ser nula ou vazia.");
        }
    }

    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(String orcamento) {
        this.orcamento = orcamento;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    /**
     * Valida o CNPJ.
     *
     * @return true se o CNPJ é válido, false caso contrário.
     */
    public boolean isCnpjValido() {
        return cnpj != null && cnpj.matches("\\d{14}");
    }

    @Override
    public String toString() {
        return "Oficina{" +
                "login='" + login + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", enderecos=" + enderecos +
                ", nomeOficina='" + nomeOficina + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", orcamento='" + orcamento + '\'' +
                ", nomeProprietario='" + nomeProprietario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oficina)) return false;
        Oficina oficina = (Oficina) o;
        return Objects.equals(login, oficina.login) &&
                Objects.equals(cnpj, oficina.cnpj) &&
                Objects.equals(telefone, oficina.telefone) &&
                Objects.equals(enderecos, oficina.enderecos) &&
                Objects.equals(nomeOficina, oficina.nomeOficina) &&
                Objects.equals(especialidade, oficina.especialidade) &&
                Objects.equals(orcamento, oficina.orcamento) &&
                Objects.equals(nomeProprietario, oficina.nomeProprietario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, cnpj, telefone, enderecos, nomeOficina, especialidade, orcamento, nomeProprietario);
    }
}
