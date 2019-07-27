import com.sun.tools.javac.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class XmlFileImporter extends RegistroFileImporter {
	@Override
	void readFile(File file) throws IOException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Registros.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Registros registros_aux = (Registros) jaxbUnmarshaller.unmarshal(file);
			registros = registros_aux.getRegistros();
		}catch(JAXBException e) {
			throw new IOException(e);
		}
	}
}

//classe para tornar possivel ler o arquivo xml completo num array
@XmlRootElement(name = "registros")
@XmlAccessorType(XmlAccessType.FIELD)
class Registros{
	@XmlElement(name = "registro")
	protected ArrayList<Registro> registros = null;

	public ArrayList<Registro> getRegistros() {
		return registros;
	}
	public void setRegistros(ArrayList<Registro> registros) {
		this.registros = registros;
	}
}
