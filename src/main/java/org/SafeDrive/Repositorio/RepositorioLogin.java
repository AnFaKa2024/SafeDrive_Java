package org.SafeDrive.Repositorio;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.SafeDrive.Modelo.Login;
import org.SafeDrive.InfraEstrutura.ConexaoBanco;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioLogin implements RepositorioGenerico<Login> {

    private static final Logger logger = LogManager.getLogger(RepositorioLogin.class);

    @Override
    public void adicionar(Login login) {
        String sql = "INSERT INTO T_AFK_LOGIN (email, senha) VALUES (?, ?)";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha()); // Aqui, a senha deve ser hashada antes de ser passada

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    login.setId(generatedKeys.getInt(1));
                    logger.info("Login cadastrado com sucesso com ID: " + login.getId());
                }
            }
        } catch (SQLException e) {
            logger.error("Erro ao adicionar login: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Login login) {
        String sql = "UPDATE T_AFK_LOGIN SET email = ?, senha = ? WHERE id_login = ?";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha()); // A senha deve ser hashada antes de passar
            stmt.setInt(3, login.getId());

            stmt.executeUpdate();
            logger.info("Login atualizado: " + login.getEmail());
        } catch (SQLException e) {
            logger.error("Erro ao atualizar login: " + e.getMessage());
        }
    }

    @Override
    public void remover(int id) {
        String sql = "DELETE FROM T_AFK_LOGIN WHERE id_login = ?";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Login removido com ID: " + id);
        } catch (SQLException e) {
            logger.error("Erro ao remover login: " + e.getMessage());
        }
    }

    @Override
    public Login buscarPorId(int id) {
        String sql = "SELECT * FROM T_AFK_LOGIN WHERE id_login = ?";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Login(
                        rs.getInt("id_login"),
                        false,
                        rs.getString("email"),
                        rs.getString("senha") // A senha aqui deve ser hash
                );
            }
        } catch (SQLException e) {
            logger.error("Erro ao buscar login por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Login> listar() {
        List<Login> logins = new ArrayList<>();
        String sql = "SELECT * FROM T_AFK_LOGIN";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Login login = new Login(
                        rs.getInt("id_login"),
                        false,
                        rs.getString("email"),
                        rs.getString("senha") // A senha aqui deve ser hash
                );
                logins.add(login);
            }
        } catch (SQLException e) {
            logger.error("Erro ao listar logins: " + e.getMessage());
        }
        return logins;
    }

    public Login buscarPorEmailSenha(String email, String senha) {
        String sql = "SELECT * FROM T_AFK_LOGIN WHERE email = ?";

        try (Connection conexao = ConexaoBanco.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Verifique se a senha informada corresponde à senha hashada no banco
                String senhaHash = rs.getString("senha");
//                if (BCrypt.checkpw(senha, senhaHash)) {
//                    return new Login(
//                            rs.getInt("id_login"),
//                            false,
//                            rs.getString("email"),
//                            senhaHash // Retorne a senha hasheada
//                    );
//                }
            }
        } catch (SQLException e) {
            logger.error("Erro ao buscar login por email: " + e.getMessage());
        }
        return null;
    }
}
