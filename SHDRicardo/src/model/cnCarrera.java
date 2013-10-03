package model;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.Carrera;

public class cnCarrera {
	
	public void Guardar(Carrera car){
		Conexion con = new Conexion();
		try{
			con.un_pst=con.conexion.prepareStatement("SELECT idcarrera, nombre, " +
					"siglas, jdc, matricula, acreditada " +
					"FROM carrera where idcarrera=?;");
			con.un_pst.setInt(1, car.getIdCarrera());
			con.resultado=con.un_pst.executeQuery();
			System.out.println(con.un_pst);
			if(con.resultado.next()){
				if(con.resultado!=null){
					con.un_pst=con.conexion.prepareStatement("UPDATE carrera" +
							"   SET nombre=?, siglas=?, jdc=?, matricula=?, acreditada=?" +
							" WHERE idcarrera=?;");
					con.un_pst.setString(1, car.getNombre());
					con.un_pst.setString(2, car.getSiglas());
					con.un_pst.setString(3, car.getJDC());
					con.un_pst.setString(4, car.getMatricula());
					con.un_pst.setString(5, car.getAcreditada());
					con.un_pst.setInt(6, car.getIdCarrera());
					con.un_pst.executeUpdate();
					System.out.println(con.un_pst);
				}else{
					con.un_pst=con.conexion.prepareStatement("INSERT INTO carrera(idcarrera, nombre, siglas," +
							" jdc, matricula, acreditada)" +
							" VALUES (default, ?, ?, ?, ?, ?);");
					con.un_pst.setString(1, car.getNombre());
					con.un_pst.setString(2, car.getSiglas());
					con.un_pst.setString(3, car.getJDC());
					con.un_pst.setString(4, car.getMatricula());
					con.un_pst.setString(5, car.getAcreditada());
					con.un_pst.executeUpdate();
					System.out.println(con.un_pst);
				}
			}else{
				con.un_pst=con.conexion.prepareStatement("INSERT INTO carrera(idcarrera, nombre, siglas," +
						" jdc, matricula, acreditada)" +
						" VALUES (default, ?, ?, ?, ?, ?);");
				con.un_pst.setString(1, car.getNombre());
				con.un_pst.setString(2, car.getSiglas());
				con.un_pst.setString(3, car.getJDC());
				con.un_pst.setString(4, car.getMatricula());
				con.un_pst.setString(5, car.getAcreditada());
				con.un_pst.executeUpdate();
				System.out.println(con.un_pst);
			}
			con.Desconectar();
		}catch(Exception ex){
			ex.getMessage();
		}
	}
	
	public void Eliminar(int idpersona) throws ClassNotFoundException, SQLException{
		Conexion con = new Conexion();
		con.un_pst=con.conexion.prepareStatement("UPDATE carrera SET activo='false' WHERE idcarrera=?;");
		con.un_pst.setInt(1, idpersona);
		con.un_pst.executeUpdate();
		con.Desconectar();
	}
	
	public ObservableList<Carrera> listarcarrera () throws SQLException {
		Conexion con = new Conexion();
		ObservableList<Carrera> lsCarrera = FXCollections.observableArrayList();
		con.un_pst=con.conexion.prepareStatement("SELECT idcarrera, nombre, siglas, jdc, matricula, acreditada FROM carrera where activo='true';");
		con.resultado=con.un_pst.executeQuery();
		System.out.println(con.un_pst);
		while(con.resultado.next()){
			//Ciclo que se maneja para el agregado de los datos a una lista de tipo Persona.
			Carrera car = new Carrera();
			car.setIdCarrera(con.resultado.getInt("idcarrera"));
			car.setNombre(con.resultado.getString("nombre"));
			car.setSiglas(con.resultado.getString("siglas"));
			car.setJDC(con.resultado.getString("jdc"));
			car.setMatricula(con.resultado.getString("matricula"));
			car.setAcreditada(con.resultado.getString("acreditada"));
			lsCarrera.add(car);
		}
		con.Desconectar();
		return lsCarrera;
	}

}
