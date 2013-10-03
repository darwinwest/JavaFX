package entidades;

/**
 * Clase que crea a los valores de carrera.
 * 
 * @author Ricardo Contreras Juárez
 *
 */
public class Carrera {
	
	private int idCarrera;
	private String nombre;
	private String siglas;
	private String JDC;
	private String Matricula;
	private String Acreditada;
	
	/**
	 * Contructor vacío.
	 */
	public Carrera(){
	}
	
	public Carrera(String nombre, String siglas, String JDC,String matricula,String acreditada){
		setNombre(nombre);
		setSiglas(siglas);
		setJDC(JDC);
		setMatricula(matricula);
		setAcreditada(acreditada);
	}
	
	/**
	 * @return the idCarrera
	 */
	public int getIdCarrera() {
		return idCarrera;
	}
	/**
	 * @param idCarrera the idCarrera to set
	 */
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}
	/**
	 * @param siglas the siglas to set
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	/**
	 * @return the jDC
	 */
	public String getJDC() {
		return JDC;
	}
	/**
	 * @param jDC the jDC to set
	 */
	public void setJDC(String jDC) {
		JDC = jDC;
	}
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return Matricula;
	}
	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	/**
	 * @return the acreditada
	 */
	public String getAcreditada() {
		return Acreditada;
	}
	/**
	 * @param acreditada the acreditada to set
	 */
	public void setAcreditada(String acreditada) {
		Acreditada = acreditada;
	}
	
	/**
	 * Método que retorna el nombre de la carrera.
	 */
	public String toString(){
		return nombre;
	}

}
