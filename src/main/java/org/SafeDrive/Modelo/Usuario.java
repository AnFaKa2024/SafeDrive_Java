package org.SafeDrive.Modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um usuário.
 */
@Entity
@Table(name = "T_SafeDrive_USUARIO")
public class Usuario extends EntidadeBase {

    @NotBlank(message = "O CPF é obrigatório.")
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "A CNH é obrigatória.")
    @Column(name = "cnh", nullable = false, unique = true)
    private String cnh;

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Column(name = "nm_usuario", nullable = false)
    private String nomeUsuario;

    @NotBlank(message = "O gênero é obrigatório.")
    @Column(name = "genero", nullable = false)
    private String genero;

    @NotBlank(message = "O telefone é obrigatório.")
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Column(name = "dt_nascimento", nullable = false)
    private Date dataNascimento;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;  // Relacionamento de um para muitos com Endereço

    @OneToOne
    @JoinColumn(name = "id_login", referencedColumnName = "id_login")
    private Login login;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;
    @Id
    private int id;

    public Usuario(int id, boolean b, Login loginId, String cpf, String telefone, List<Endereco> enderecos, String nomeCompleto, LocalDate dataNascimento, String cnh, List<Veiculo> veiculos) {
    }

    public Usuario(String cpf, String cnh, String nomeUsuario, String genero, String telefone, Date dataNascimento, Login login) {
        this.cpf = cpf;
        this.cnh = cnh;
        this.nomeUsuario = nomeUsuario;
        this.genero = genero;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.login = login;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento.toLocalDate();
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + getId() +
                ", cpf='" + cpf + '\'' +
                ", cnh='" + cnh + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", genero='" + genero + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", login=" + (login != null ? login.getEmail() : "N/A") + // Assuming Login class has getEmail()
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(cnh, usuario.cnh) &&
                Objects.equals(nomeUsuario, usuario.nomeUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, cnh, nomeUsuario);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
