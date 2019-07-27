import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvFileImporter extends RegistroFileImporter {

	@Override
	void readFile(File file) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
		registros = new ArrayList<Registro>();

		for(String line : lines){
			if(!line.equals("regiao|siglaEstado|siglaMunicipio|revendaInstalacao|codigoProduto|nomeProduto|dataDaColeta|valorDeCompra|valorDeVenda|unidadeDeMedida|bandeira")) {
				//Quebra a linha usando pipe (|)
				String[] colunas = line.split("\\|");
				//Monta o registro
				Date dataDaColeta;
				Double valorDeCompra,valorDeVenda;
				try {
					dataDaColeta = Registro.dateFormat.parse(colunas[6]);
				}catch(ParseException e) {
					throw new RuntimeException(e);
				}
				valorDeCompra = !colunas[7].equals("") ? Double.parseDouble(colunas[7]) : null;
				valorDeVenda = Double.parseDouble(colunas[8]);

				Registro registro = new Registro(colunas[0], colunas[1], colunas[2], colunas[3], colunas[4], colunas[5], colunas[9], colunas[10], valorDeCompra, valorDeVenda, dataDaColeta);
				registros.add(registro);
			}
		}

	}


}
