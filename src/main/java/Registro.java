import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement(name = "registro")
@XmlAccessorType (XmlAccessType.FIELD)
public class Registro implements Serializable {
	//Formatador de datas configurado para o padrão brasileiro de dia mes e ano separado por barra
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private String regiao;
	private String siglaEstado;
	private String siglaMunicipio;
	private String revendaInstalacao;
	private String codigoProduto;
	private String nomeProduto;
	private String unidadeDeMedida;
	private String bandeira;
	private Double valorDeCompra;
	private Double valorDeVenda;
	private Date dataDaColeta;

	public Registro(){
		super();
		setRegiao("");
		setSiglaEstado("");
		setSiglaMunicipio("");
		setRevendaInstalacao("");
		setCodigoProduto("");
		setNomeProduto("");
		setUnidadeDeMedida("");
		setBandeira("");
		setValorDeCompra(null);
		setValorDeVenda(0.0);
		setDataDaColeta(new Date());
	}

	public Registro(String regiao, String siglaEstado, String siglaMunicipio, String revendaInstalacao, String codigoProduto, String nomeProduto,
					String unidadeDeMedida, String bandeira, Double valorDeCompra, Double valorDeVenda, Date dataDaColeta)
	{
		super();
		setRegiao(regiao);
		setSiglaEstado(siglaEstado);
		setSiglaMunicipio(siglaMunicipio);
		setRevendaInstalacao(revendaInstalacao);
		setCodigoProduto(codigoProduto);
		setNomeProduto(nomeProduto);
		setUnidadeDeMedida(unidadeDeMedida);
		setBandeira(bandeira);
		setValorDeCompra(valorDeCompra);
		setValorDeVenda(valorDeVenda);
		setDataDaColeta(dataDaColeta);
	}


	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		if(regiao == null )
			throw new RuntimeException("'regiao' field  cannot be null");
		this.regiao = regiao;
	}


	public String getSiglaEstado() {
		return siglaEstado;
	}
	public void setSiglaEstado(String siglaEstado) {
		if(siglaEstado == null )
			throw new RuntimeException("'siglaEstado' field  cannot be null");
		this.siglaEstado = siglaEstado;
	}


	public String getSiglaMunicipio() {
		return siglaMunicipio;
	}
	public void setSiglaMunicipio(String siglaMunicipio) {
		if(siglaMunicipio == null )
			throw new RuntimeException("'siglaMunicipio' field  cannot be null");
		this.siglaMunicipio = siglaMunicipio;
	}


	public String getRevendaInstalacao() {
		return revendaInstalacao;
	}
	public void setRevendaInstalacao(String revendaInstalacao) {
		if(revendaInstalacao == null )
			throw new RuntimeException("'revendaInstalacao' field  cannot be null");
		this.revendaInstalacao = revendaInstalacao;
	}


	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		if(codigoProduto == null )
			throw new RuntimeException("'codigoProduto' field  cannot be null");
		this.codigoProduto = codigoProduto;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		if(nomeProduto == null )
			throw new RuntimeException("'nomeProduto' field  cannot be null");
		this.nomeProduto = nomeProduto;
	}


	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}
	public void setUnidadeDeMedida(String unidadeDeMedida) {
		if(unidadeDeMedida == null )
			throw new RuntimeException("'unidadeDeMedida' field  cannot be null");
		this.unidadeDeMedida = unidadeDeMedida;
	}


	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}


	public Double getValorDeCompra() {
		return valorDeCompra;
	}
	public void setValorDeCompra(Double valorDeCompra) {
		if( valorDeCompra != null && valorDeCompra < 0)
			throw new RuntimeException("'valorDeCompra' field  cannot be less than 0");
		this.valorDeCompra = valorDeCompra;
	}


	public Double getValorDeVenda() {
		return valorDeVenda;
	}
	public void setValorDeVenda(Double valorDeVenda) {
		if(valorDeVenda == null )
			throw new RuntimeException("'valorDeVenda' field  cannot be null");
		else if(valorDeVenda < 0)
			throw new RuntimeException("'valorDeVenda' field  cannot be less than 0");
		this.valorDeVenda = valorDeVenda;
	}


	public Date getDataDaColeta() {
		return dataDaColeta;
	}
	public void setDataDaColeta(Date dataDaColeta) {
		if(dataDaColeta == null )
			throw new RuntimeException("'dataDaColeta' field  cannot be null");
		this.dataDaColeta = dataDaColeta;
	}
}

