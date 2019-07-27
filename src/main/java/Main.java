import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void importFile(File file) {
		try {
			RegistroFileImporter fileImporter;
			String fileName = file.getName().toLowerCase();

			if (fileName.endsWith(".json"))			fileImporter = new JsonFileImporter();		//Se o nome do arquivo for um json
			else if (fileName.endsWith(".csv"))		fileImporter = new CsvFileImporter();	 //Se o nome do arquivo for um csv
			else 									throw new RuntimeException("Formato de arquivo nao suportado"); //Lança uma exception caso o arquivo não seja valido

			fileImporter.importFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
