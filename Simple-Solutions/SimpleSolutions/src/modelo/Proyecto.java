package modelo;

public class Proyecto {
	
	private int cod;
	private String nombre;
	private int status;
	private String lenguaje;
	private int duracion;
	private int avance;
	Version ver = new Version();
	public Double efectividad;
	
	
	
	public Proyecto() {
		super();
	}
		
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getAvance() {
		return avance;
	}
	public void setAvance(int avance) {
		this.avance = avance;
	}

	public Double getEfectividad() {
		return efectividad;
	}

	public void setEfectividad(Double efectividad) {
		this.efectividad = efectividad;
	}
	
	//Metodo que calcula la eficiencia del proyecto
	public Double calcularEfProject(Double efe, int cant) {
		Double promEfiProject = 0.0;
		promEfiProject = efe/cant;
		return promEfiProject;
	}
	
	
	
}
