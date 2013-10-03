package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	public Connection conexion;
	public PreparedStatement un_pst;
	public Statement un_st;
	public String un_cad;
	public DatabaseMetaData dbmd;
	public ResultSet resultado;
	
	public Conexion(){
		try {
			Class.forName("org.postgresql.Driver");
			conexion=DriverManager.getConnection("jdbc:postgresql://localhost:5432/SHDRichard",
					"postgres", "richard");
			dbmd=conexion.getMetaData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Desconectar(){
		try{
			conexion.close();
		}catch(Exception ex){
			ex.getMessage();
		}
	}

}
