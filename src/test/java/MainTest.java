import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainTest {

	@Before
	public void init() throws SQLException {
		Connection connection = RegistroDataBank.createNewConnection();
		PreparedStatement st = connection.prepareStatement(
				"DELETE FROM precos"
		);
		st.execute();
		st.close();
		connection.close();
	}

	private int getDbQuantidade() throws SQLException {
		Connection connection = RegistroDataBank.createNewConnection();
		PreparedStatement st = connection.prepareStatement(
				"SELECT count(*) AS quantidade FROM precos"
		);
		ResultSet resultSet = st.executeQuery();
		Assert.assertTrue(resultSet.next());
		int quantidade = resultSet.getInt("quantidade");

		resultSet.close();
		st.close();
		connection.close();
		return quantidade;
	}

	@Test
	public void importCsvFile() throws SQLException, IOException {
		File file = new File("./src/test/resources/dados_combustiveis.csv");
		new CsvFileImporter().importFile(file);

		Assert.assertEquals(111, getDbQuantidade());
	}

	@Test
	public void importJsonFile() throws SQLException, IOException {
		File file = new File("./src/test/resources/dados_combustiveis.json");
		new JsonFileImporter().importFile(file);

		Assert.assertEquals(111, getDbQuantidade());
	}

	@Test
	public void importXmlFile() throws SQLException, IOException {
		File file = new File("./src/test/resources/dados_combustiveis.xml");
		new XmlFileImporter().importFile(file);

		Assert.assertEquals(111, getDbQuantidade());
	}

	//tenta importar os registros com qualquer RegistroFileImporter que funcione.
	//fileImportes é uma lista de pares de RegistroFileImporter com o seu File.
	private void importWithAnyFile(List<Map.Entry<RegistroFileImporter, File>> fileImporters ){
		try{
			RegistroFileImporter fileImporter = fileImporters.get(0).getKey();
			File file = fileImporters.get(0).getValue();
			fileImporter.importFile(file);	//tenta importar com o primeiro fileImporter da lista
			return;
		}catch(Exception e){	//se o fileImporter falhar, tenta com o proximo
			if(fileImporters.isEmpty())
				throw new RuntimeException("could not import file");

			importWithAnyFile(fileImporters.subList(1, fileImporters.size()));
		}
	}

	@Test
	public void listAll() {
		//cria lista de RegistroFileImporters, para tentar importar com qualquer um deles que funcione
		ArrayList<Map.Entry<RegistroFileImporter, File>> fileImporters = new ArrayList<Map.Entry<RegistroFileImporter, File>>();
		fileImporters.add( new AbstractMap.SimpleEntry<RegistroFileImporter, File>(
					new CsvFileImporter(), new File("./src/test/resources/dados_combustiveis.csv")));
		fileImporters.add( new AbstractMap.SimpleEntry<RegistroFileImporter, File>(
					new JsonFileImporter(), new File("./src/test/resources/dados_combustiveis.json")));
		fileImporters.add( new AbstractMap.SimpleEntry<RegistroFileImporter, File>(
					new XmlFileImporter(), new File("./src/test/resources/dados_combustiveis.xml")));

		importWithAnyFile(fileImporters);

		List<Registro> registros = RegistroDataBank.queryAllRegistros();
		Assert.assertFalse(registros.isEmpty());
		Assert.assertEquals(111, registros.size());
	}
}
