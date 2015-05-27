package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;
import controller.ConnectionFactory;

public class UsuarioDAO {
	private Connection connection;

	public UsuarioDAO() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existeUsuario(Usuario usuario) {

		if (usuario == null) {
			throw new IllegalArgumentException("Usuário nao deve ser nulo");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from usuarios where login = ? and senha = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();

			this.connection.close();

			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insere(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Usuário nao deve ser nulo");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("insert into usuarios (login,senha) values (?,?)");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();

			this.connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
