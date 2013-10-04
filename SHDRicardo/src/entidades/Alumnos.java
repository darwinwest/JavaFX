package entidades;

public class Alumnos {
	
	private String nombre,paterno,materno,fecha,sexo;
	private int id;
	private Carrera oCarrera;
	
	

	public Alumnos() {
		
	}

	/*	public Alumnos(String nombre, String paterno, String materno, String fecha,
			String sexo, Carrera oCarrera) {
		super();
		setNombre(nombre);
		setPaterno(paterno);
		setMaterno(materno);
		setFecha(fecha);
		setSexo(sexo);
		setoCarrera(oCarrera);
	}*/ 
	// está de más...
	
	public Alumnos(String nombre, String paterno, String materno, String fecha,
			String sexo) {
		super();
		setNombre(nombre);
		setPaterno(paterno);
		setMaterno(materno);
		setFecha(fecha);
		setSexo(sexo);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String toString(){
		return this.nombre+" "+
				this.paterno+" "+
				this.materno+" "+
				this.fecha+" "+
				this.sexo;

	}

	public Carrera getoCarrera() {
		return oCarrera;
	}

	public void setoCarrera(Carrera oCarrera) {
		this.oCarrera = oCarrera;
	}
	
	

}
