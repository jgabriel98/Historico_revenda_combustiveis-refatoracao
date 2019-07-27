import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class JsonFileImporter extends RegistroFileImporter {


	@Override
	void readFile(File file) throws IOException {
		//Responsavel por converter o arquivo json em lista de registros
		ObjectMapper mapper = new ObjectMapper();
		//Configura o formatador de date no conversor de json
		mapper.setDateFormat(Registro.dateFormat);

		mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
									 .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
									 .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
									 .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
									 .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

		//Monta o tipo de retorno do conversor de json para retornar uma lista de registro
		CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Registro.class);
		//Converte o arquivo em uma lista de registro
		registros = mapper.readValue(file.toURI().toURL(), collectionType);
	}
}
