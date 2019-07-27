import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroDataBank {
	private final static String dbUrl = "jdbc:postgresql://localhost:5432/banco_precos";
	private final static String dbUser = "dbuser";
	private final static String dbPassword = "dbuser";

	public static Connection createNewConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}
	public static List<Registro> queryAllRegistros(){
		try {
			//Lista de registros a ser retornada
			List<Registro> registros = new ArrayList<>();
			//Cria a conexão
			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			//Cria a consulta
			PreparedStatement st = connection.prepareStatement(
					"SELECT * FROM precos"
			);
			//Executa a consulta
			ResultSet resultSet = st.executeQuery();
			//Itera o resultado ate não haver mais registros
			while (resultSet.next()) {
				//Monta o registro
				Registro registro = new Registro(
						resultSet.getString("regiao"),
						resultSet.getString("sigla_estado"),
						resultSet.getString("sigla_municipio"),
						resultSet.getString("revenda_instalacao"),
						resultSet.getString("codigo_produto"),
						resultSet.getString("nome_produto"),
						resultSet.getString("unidade_de_medida"),
						resultSet.getString("bandeira"),
						resultSet.getDouble("valor_de_compra"),
						resultSet.getDouble("valor_de_venda"),
						resultSet.getDate("data_da_coleta"));
				//Adiciona o registro na lista
				registros.add(registro);
			}
			st.close();
			resultSet.close();
			connection.close();
			return registros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
