import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

//A classe tem o papel de importar um arquivo para o banco de dados
public abstract class RegistroFileImporter {
	//Lista de registros a ser importado
	ArrayList<Registro> registros;

	public void importFile(File file) throws SQLException, IOException {
		registros = new ArrayList<>();	//garantindo que os registros estão vazios antes de uma nova importação
		readFile(file);
		uploadRegistros();
	}

	abstract void readFile(File file) throws IOException;

	//aqui envia os registros para o banco de dados
	private void uploadRegistros() throws SQLException {
		//Cria a conexão
		Connection connection = RegistroDataBank.createNewConnection();

		PreparedStatement st = connection.prepareStatement(
				"INSERT INTO precos(regiao, sigla_estado, sigla_municipio, revenda_instalacao, codigo_produto, nome_produto, unidade_de_medida, bandeira, valor_de_compra, valor_de_venda, data_da_coleta)\n" +
					"VALUES (?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?, ?)"
		);
		//Insere os registros no banco
		for(Registro registro : registros){
			//Adiciona os parametros no insert
			st.setString(1, registro.getRegiao());
			st.setString(2, registro.getSiglaEstado());
			st.setString(3, registro.getSiglaMunicipio());
			st.setString(4, registro.getRevendaInstalacao());
			st.setString(5, registro.getCodigoProduto());
			st.setString(6, registro.getNomeProduto());
			st.setString(7, registro.getUnidadeDeMedida());
			st.setString(8, registro.getBandeira());
			st.setObject(9, registro.getValorDeCompra());
			st.setDouble(10, registro.getValorDeVenda());
			st.setDate(11, new java.sql.Date(registro.getDataDaColeta().getTime()));
			//Executa o insert
			st.executeUpdate();
		}
		st.close();

		connection.close();
	}

}
