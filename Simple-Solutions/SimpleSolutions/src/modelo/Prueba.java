package modelo;

public class Prueba {
	
	private int codPrueba = 0;
	private String descripcion = "";
	private int tipoprueba = 0; //Funcionales, No-Funcionales, Estructurales, Regresión
	private int cantCasosPrueba = 0; //Cifra
	private Double casosFallidos = 0.0; //(%)
	private Double timeResponse = 0.0; //Cifra
	private Double rapidez = 0.0; //Tiempo de respuesta/ CantCasosPrueba
	private Double efiPrueba = 0.0; // Fórmula de todo

	public int getCodPrueba() {
		return codPrueba;
	}

	public void setCodPrueba(int codPrueba) {
		this.codPrueba = codPrueba;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipoprueba() {
		return tipoprueba;
	}

	public void setTipoprueba(int tipoprueba) {
		this.tipoprueba = tipoprueba;
	}

	public int getCantCasosPrueba() {
		return cantCasosPrueba;
	}

	public void setCantCasosPrueba(int cantCasosPrueba) {
		this.cantCasosPrueba = cantCasosPrueba;
	}

	public Double getCasosFallidos() {
		return casosFallidos;
	}

	public void setCasosFallidos(Double casosFallidos) {
		this.casosFallidos = casosFallidos;
	}

	public Double getTimeResponse() {
		return timeResponse;
	}

	public void setTimeResponse(Double timeResponse) {
		this.timeResponse = timeResponse;
	}

	public Double getRapidez() {
		return rapidez;
	}

	public void setRapidez(Double rapidez) {
		this.rapidez = rapidez;
	}

	public Double getEfiPrueba() {
		return efiPrueba;
	}

	public void setEfiPrueba(Double efiPrueba) {
		this.efiPrueba = efiPrueba;
	}

	public Double calcularIndrapidez(int casos, Double tiempo) {
		Double indrapidez = 0.0;
		Double c = Double.valueOf(casos);
		indrapidez = tiempo/c;
		return indrapidez;
	}
	
	public Double calcularEfiPrueba(Double indrapidez, Double fail) {
		Double ePrueba = 0.0;
		ePrueba = 1000 - (indrapidez*100 + fail*10);
		return ePrueba;
	}
	
	
}
