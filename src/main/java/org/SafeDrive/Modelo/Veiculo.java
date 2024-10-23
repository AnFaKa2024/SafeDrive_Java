package org.SafeDrive.Modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

/*Classe que representa um veículo*/

@Entity
@Table(name = "T_SafeDrive_VEICULO")
public class Veiculo extends EntidadeBase {

    @NotBlank(message = "O tipo de veículo é obrigatório.")
    @Column(name = "tipo_veiculo", nullable = false)
    private String tipoVeiculo;

    @NotBlank(message = "A marca é obrigatória.")
    @Column(name = "marca", nullable = false)
    private String marca;

    @NotBlank(message = "O modelo é obrigatório.")
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @NotBlank(message = "A placa é obrigatória.")
    @Size(min = 7, max = 7, message = "A placa deve ter 7 caracteres.")
    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @NotNull(message = "O ano de fabricação é obrigatório.")
    @Column(name = "ano_fabricacao", nullable = false)
    private LocalDate anoFabricacao;

    @NotNull(message = "A quantidade de eixos é obrigatória.")
    @Column(name = "qtd_eixo", nullable = false)
    private int qtdEixo;

    @Column(name = "tem_seguro", nullable = false)
    private boolean temSeguro;

    @Column(name = "numero_seguro")
    private String numeroSeguro;
    @Id
    private int id;

    // Construtor padrão
    public Veiculo() {
    }

    // Construtor completo
    public Veiculo(String tipoVeiculo, String marca, String modelo, String placa,
                   LocalDate anoFabricacao, int qtdEixo, boolean temSeguro, String numeroSeguro) {
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.qtdEixo = qtdEixo;
        this.temSeguro = temSeguro;
        this.numeroSeguro = numeroSeguro;
    }

    // Construtor com ID e estado de deletado
    public Veiculo(int id, boolean deletado, String tipoVeiculo, String marca, String modelo,
                   String placa, LocalDate anoFabricacao, int qtdEixo, boolean temSeguro,
                   String numeroSeguro) {
        super(id, deletado);
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.qtdEixo = qtdEixo;
        this.temSeguro = temSeguro;
        this.numeroSeguro = numeroSeguro;
    }

    // Getters e Setters
    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(LocalDate anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getQtdEixo() {
        return qtdEixo;
    }

    public void setQtdEixo(int qtdEixo) {
        this.qtdEixo = qtdEixo;
    }

    public boolean isTemSeguro() {
        return temSeguro;
    }

    public void setTemSeguro(boolean temSeguro) {
        this.temSeguro = temSeguro;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "tipoVeiculo='" + tipoVeiculo + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", qtdEixo=" + qtdEixo +
                ", temSeguro=" + temSeguro +
                ", numeroSeguro='" + numeroSeguro + '\'' +
                ", id=" + getId() +
                ", deletado=" + isDeletado() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return qtdEixo == veiculo.qtdEixo &&
                temSeguro == veiculo.temSeguro &&
                Objects.equals(tipoVeiculo, veiculo.tipoVeiculo) &&
                Objects.equals(marca, veiculo.marca) &&
                Objects.equals(modelo, veiculo.modelo) &&
                Objects.equals(placa, veiculo.placa) &&
                Objects.equals(anoFabricacao, veiculo.anoFabricacao) &&
                Objects.equals(numeroSeguro, veiculo.numeroSeguro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoVeiculo, marca, modelo, placa, anoFabricacao, qtdEixo, temSeguro, numeroSeguro);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
