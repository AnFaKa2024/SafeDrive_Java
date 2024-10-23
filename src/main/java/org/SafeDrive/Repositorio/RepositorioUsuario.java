package org.SafeDrive.Repositorio;

import org.SafeDrive.Modelo.Usuario;
import org.SafeDrive.Modelo.Login;
import org.SafeDrive.Modelo.Endereco;
import org.SafeDrive.Modelo.Veiculo;
import org.SafeDrive.InfraEstrutura.ConexaoBanco;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario implements RepositorioGenerico<Usuario> {

    private static final Logger logger = LogManager.getLogger(RepositorioUsuario.class);

    @Override
    public void adicionar(Usuario usuario) {
        String sql = "INSERT INTO T_USUARIO (login_id, cpf, telefone, nomeCompleto, dataNascimento, cnh) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, usuario.getLogin().getId());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getNomeUsuario());
            stmt.setDate(5, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(6, usuario.getCnh());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));  // Atribui o ID gerado
                    logger.info("Usuário cadastrado com sucesso com ID: " + usuario.getId());
                }
            }
        } catch (SQLException e) {
            logger.error("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE T_USUARIO SET login_id = ?, cpf = ?, telefone = ?, nomeCompleto = ?, dataNascimento = ?, cnh = ? WHERE id = ?";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getLogin().getId());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getNomeUsuario());
            stmt.setDate(5, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(6, usuario.getCnh());
            stmt.setInt(7, usuario.getId());

            stmt.executeUpdate();
            logger.info("Usuário atualizado: " + usuario.getNomeUsuario());
        } catch (SQLException e) {
            logger.error("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Override
    public void remover(int id) {
        String sql = "DELETE FROM T_USUARIO WHERE id = ?";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Usuário removido com ID: " + id);
        } catch (SQLException e) {
            logger.error("Erro ao remover usuário: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM T_USUARIO WHERE id = ?";
        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Aqui você deve tratar a obtenção do Login, Endereços e Veículos conforme a sua implementação
                return new Usuario(
                        id,
                        false, // O valor de deletado deve ser tratado conforme a sua lógica
                        buscarLoginPorId(rs.getInt("login_id")), // Método para buscar o Login associado
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        buscarEnderecosPorUsuarioId(id), // Método para buscar endereços
                        rs.getString("nomeCompleto"),
                        rs.getDate("dataNascimento").toLocalDate(),
                        rs.getString("cnh"),
                        buscarVeiculosPorUsuarioId(id) // Método para buscar veículos
                );
            }
        } catch (SQLException e) {
            logger.error("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM T_USUARIO";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        false, // O valor de deletado deve ser tratado conforme a sua lógica
                        buscarLoginPorId(rs.getInt("login_id")), // Método para buscar o Login associado
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        buscarEnderecosPorUsuarioId(rs.getInt("id")), // Método para buscar endereços
                        rs.getString("nomeCompleto"),
                        rs.getDate("dataNascimento").toLocalDate(),
                        rs.getString("cnh"),
                        buscarVeiculosPorUsuarioId(rs.getInt("id")) // Método para buscar veículos
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            logger.error("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Método auxiliar para buscar o Login pelo ID
    private Login buscarLoginPorId(int loginId) {
        // Implementar lógica para buscar o Login pelo loginId
        return null; // Placeholder
    }

    // Método auxiliar para buscar Endereços de um Usuário
    private List<Endereco> buscarEnderecosPorUsuarioId(int usuarioId) {
        // Implementar lógica para buscar endereços pelo ID do usuário
        return new ArrayList<>(); // Placeholder
    }

    // Método auxiliar para buscar Veículos de um Usuário
    private List<Veiculo> buscarVeiculosPorUsuarioId(int usuarioId) {
        // Implementar lógica para buscar veículos pelo ID do usuário
        return new ArrayList<>(); // Placeholder
    }
}
