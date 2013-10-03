package model;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.Alumnos;
import entidades.Carrera;

/**
 * 
 * @author Ricardo Contreras Juarez.
 *
 */
public class cnAlumno {
	
	public void Guardar(Alumnos value) throws SQLException{
		Conexion con = new Conexion();
		System.out.println("entro");
			con.un_pst=con.conexion.prepareStatement("SELECT idalumno, nombre, apaterno, amaterno, fechanac, sexo FROM alumno"
					+ " where idalumno=?;");
			con.un_pst.setInt(1, value.getId());
			con.resultado=con.un_pst.executeQuery();
			if(con.resultado.next()){
				if(con.resultado!=null){
					con.un_pst=con.conexion.prepareStatement("UPDATE alumno SET nombre=?, apaterno=?, amaterno=?, "
							+ "fechanac=?, sexo=?, carrera=? WHERE idalumno=?;");
					con.un_pst.setString(1, value.getNombre());
					con.un_pst.setString(2, value.getPaterno());
					con.un_pst.setString(3, value.getMaterno());
					con.un_pst.setString(4, value.getFecha());
					con.un_pst.setString(5, value.getSexo());
					con.un_pst.setInt(6, value.getoCarrera().getIdCarrera());
					con.un_pst.setInt(7, value.getId());
					con.un_pst.executeUpdate();
					System.out.println(con.un_pst);
				}else{
					con.un_pst=con.conexion.prepareStatement("INSERT INTO alumno( idalumno, nombre, apaterno, amaterno, fechanac, "
							+ "sexo, carrera)  VALUES (default, ?, ?, ?, ?, ?, ?);");
					con.un_pst.setString(1, value.getNombre());
					con.un_pst.setString(2, value.getPaterno());
					con.un_pst.setString(3, value.getMaterno());
					con.un_pst.setString(4, value.getFecha());
					con.un_pst.setString(5, value.getSexo());
					con.un_pst.setInt(6, value.getoCarrera().getIdCarrera());
					con.un_pst.executeUpdate();
				}
			}else{
				con.un_pst=con.conexion.prepareStatement("INSERT INTO alumno( idalumno, nombre, apaterno, amaterno, fechanac, "
						+ "sexo, carrera)  VALUES (default, ?, ?, ?, ?, ?, ?);");
				con.un_pst.setString(1, value.getNombre());
				con.un_pst.setString(2, value.getPaterno());
				con.un_pst.setString(3, value.getMaterno());
				con.un_pst.setString(4, value.getFecha());
				con.un_pst.setString(5, value.getSexo());
				con.un_pst.setInt(6, value.getoCarrera().getIdCarrera());
				con.un_pst.executeUpdate();
			}
			con.Desconectar();
	}
	
	public void Eliminar(int idalumno) throws ClassNotFoundException, SQLException{
		Conexion con = new Conexion();
		con.un_pst=con.conexion.prepareStatement("UPDATE alumno SET activo='false' WHERE idalumno=?;");
		con.un_pst.setInt(1, idalumno);
		con.un_pst.executeUpdate();
		con.Desconectar();
	}
	
	public ObservableList<Alumnos> listarAlumno() throws SQLException {
		Conexion con = new Conexion();
		ObservableList<Alumnos> lsAlumno = FXCollections.observableArrayList();
		con.un_pst=con.conexion.prepareStatement("SELECT idalumno, nombre, apaterno, amaterno, fechanac, sexo , carrera FROM alumno"
					+ " where activo='true';");
		con.resultado=con.un_pst.executeQuery();
		while(con.resultado.next()){
			//Ciclo que se maneja para el agregado de los datos a una lista de tipo Persona.
			Alumnos alumn = new Alumnos();
			alumn.setId(con.resultado.getInt("idalumno"));
			alumn.setNombre(con.resultado.getString("nombre"));
			alumn.setPaterno(con.resultado.getString("apaterno"));
			alumn.setMaterno(con.resultado.getString("amaterno"));
			alumn.setFecha(con.resultado.getString("fechanac"));
			alumn.setSexo(con.resultado.getString("sexo"));
			alumn.setoCarrera(BuscarCarrera(con.resultado.getInt("carrera")));
			lsAlumno.add(alumn);
		}
		con.Desconectar();
		return lsAlumno;
	}
	
	public Carrera BuscarCarrera(int idcarrera){
		Conexion con = new Conexion();
		Carrera car=null;
		try {
			con.un_pst=con.conexion.prepareStatement("SELECT idcarrera, nombre, siglas, jdc, matricula, acreditada "
				+ "FROM carrera where idcarrera=?;");
			con.un_pst.setInt(1, idcarrera);
			con.resultado=con.un_pst.executeQuery();
			while(con.resultado.next()){
				//Ciclo que se maneja para el agregado de los datos a una lista de tipo Persona.
				car = new Carrera();
				car.setIdCarrera(con.resultado.getInt("idcarrera"));
				car.setNombre(con.resultado.getString("nombre"));
			}
			con.Desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return car;
	}
	
	//Darwin jesus arroyo luna 
	public ObservableList<Carrera> listarcarrera() throws SQLException{
		Conexion con = new Conexion();
		ObservableList<Carrera> lsCarrera = FXCollections.observableArrayList();
		con.un_pst=con.conexion.prepareStatement("SELECT idcarrera, nombre, siglas, jdc, matricula, acreditada "
				+ "FROM carrera where activo='true';");
		con.resultado=con.un_pst.executeQuery();
		System.out.println(con.un_pst);
		while(con.resultado.next()){
			//Ciclo que se maneja para el agregado de los datos a una lista de tipo Persona.
			Carrera car = new Carrera();
			car.setIdCarrera(con.resultado.getInt("idcarrera"));
			car.setNombre(con.resultado.getString("nombre"));
			lsCarrera.add(car);
		}
		con.Desconectar();
		return lsCarrera;
	}
}
