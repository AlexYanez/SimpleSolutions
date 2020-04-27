package modelo;

public class Version {
	private int idversion;
	private String nameVersion;
	private Double efiVersion;
	private int acumCasos;
	private Double acumFail;
	private Double acumTime;
	public int contPruebas;
	
	public Version() {
		super();
	}

	public int getIdversion() {
		return idversion;
	}

	public void setIdversion(int idversion) {
		this.idversion = idversion;
	}

	public String getNameVersion() {
		return nameVersion;
	}

	public void setNameVersion(String nameVersion) {
		this.nameVersion = nameVersion;
	}
	
	public int getContPruebas() {
		return contPruebas;
	}

	public void setContPruebas(int contPruebas) {
		this.contPruebas = contPruebas;
	}

	public Double getEfiVersion() {
		return efiVersion;
	}

	public void setEfiVersion(Double efiVersion) {
		this.efiVersion = efiVersion;
	}

	public int getAcumCasos() {
		return acumCasos;
	}

	public void setAcumCasos(int acum) {
		this.acumCasos = acum;
	}

	public Double getAcumFail() {
		return acumFail;
	}

	public void setAcumFail(Double acum) {
		this.acumFail = acum;
	}

	public Double getAcumTime() {
		return acumTime;
	}

	public void setAcumTime(Double acum) {
		this.acumTime = acum;
	}

	public void acumEfVersion(Double efiPrueba) {
		this.efiVersion = efiPrueba;
	}
		
	public Double calcularEfVersion(Double efVersion, int cont) {
		Double promEfiVersion = 0.0;
		promEfiVersion = efVersion/cont;
		return promEfiVersion;
	}
	
}

