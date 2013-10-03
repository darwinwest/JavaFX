package view.controller;

import java.awt.TrayIcon;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.cnAlumno;
import entidades.Alumnos;
import entidades.Carrera;

public class cdAlumno implements Initializable{
	
	@FXML private TextField txtNombre;
	@FXML private TextField txtApaterno;
	@FXML private TextField txtAmaterno;
	@FXML private TextField txtFecha;
	@FXML private TableView<Alumnos> tblalumnos;
	@FXML private ComboBox<String> cboSexo;
	@FXML private Label lblMensaje;
	@FXML private ComboBox<Carrera> cboCarrera;
	
	private cnAlumno nuevoAlumno = new cnAlumno();
	private Alumnos nalumno=null;
	private int contadorposicion;
	private ObservableList<Alumnos> listaalumno = FXCollections.observableArrayList(); 
	private ObservableList<Carrera> lis = FXCollections.observableArrayList();

	
	@FXML protected void Guardar(ActionEvent evnt) throws SQLException{
		if(txtNombre.getText().trim().isEmpty() || txtApaterno.getText().trim().isEmpty() || txtAmaterno.getText().trim().isEmpty()
				|| txtFecha.getText().trim().isEmpty()){
			lblMensaje.setText("Se han dejado campos Vacío");
		}else{
			if(nalumno==null){
				nuevoAlumno.Guardar(new Alumnos(txtNombre.getText(), txtApaterno.getText(), txtAmaterno.getText(),
						txtFecha.getText(), cboSexo.getValue(), (Carrera)cboCarrera.getValue()));
				lblMensaje.setText("Datos duardados");
			}else{
				nalumno.setNombre(txtNombre.getText());
				nalumno.setPaterno(txtApaterno.getText());
				nalumno.setMaterno(txtAmaterno.getText());
				nalumno.setFecha(txtFecha.getText());
				nalumno.setSexo(cboSexo.getValue());
				nalumno.setoCarrera((Carrera)cboCarrera.getValue());
				nuevoAlumno.Guardar(nalumno);
				lblMensaje.setText("Datos actualizados");
			}
			Table();
		}
	}
	
	@FXML protected void Eliminar(ActionEvent evnt) throws ClassNotFoundException, SQLException{
		if(txtNombre.getText().trim().isEmpty() || txtApaterno.getText().trim().isEmpty() || txtAmaterno.getText().trim().isEmpty()
				|| txtFecha.getText().trim().isEmpty()){
			lblMensaje.setText("Se han dejado campos Vacío");
		}else{
			if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar al Alumno?","confirmación",
					TrayIcon.MessageType.INFO.ordinal())==0){
				nuevoAlumno.Eliminar(contadorposicion);
				Table();		
				Nuevo(evnt);
				lblMensaje.setText("Se a eliminado correctamente al alumno");
			}
		}
	}
	
	@FXML protected void Nuevo(ActionEvent evnt) throws SQLException{
		txtNombre.setText("");
		txtApaterno.setText("");
		txtAmaterno.setText("");
		txtFecha.setText("");
		cboSexo.setValue("");
		cboCarrera.setValue(null);
		nalumno=null;
		contadorposicion=0;
	}
	
	private final ListChangeListener<Alumnos> select = new ListChangeListener<Alumnos>(){

		@Override
		public void onChanged(
				javafx.collections.ListChangeListener.Change<? extends Alumnos> arg0) {
			poneralumnos();
		}
	};
	
	public Alumnos gettblAlumnosSelect (){
		if(tblalumnos != null){
			List<Alumnos> tabla = tblalumnos.getSelectionModel().getSelectedItems();
			if(tabla.size() == 1){
				final Alumnos competicionseleccionada = tabla.get(0);
				return competicionseleccionada;
			}
		}
		return null;
	}
	
	private void poneralumnos(){
		final Alumnos alumno = gettblAlumnosSelect();
		contadorposicion = listaalumno.indexOf(alumno);
		if(alumno != null){
			contadorposicion=alumno.getId();
			nalumno=alumno;
			txtNombre.setText(alumno.getNombre());
			txtApaterno.setText(alumno.getPaterno());
			txtAmaterno.setText(alumno.getMaterno());
			txtFecha.setText(alumno.getFecha());
			cboSexo.setValue(alumno.getSexo());
			cboCarrera.setValue(alumno.getoCarrera());
		}
	}
	
	private void Table() throws SQLException{
		
		listaalumno=nuevoAlumno.listarAlumno();
		tblalumnos.setItems(listaalumno);
		tblalumnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Alumnos, String> nombre = new TableColumn<>("Nombre");
		TableColumn<Alumnos, String> amaterno = new TableColumn<>("Apaterno");
		TableColumn<Alumnos, String> apaterno = new TableColumn<>("Amaterno");
		TableColumn<Alumnos, String> fecha = new TableColumn<>("fecha");
		TableColumn<Alumnos, String> sexo = new TableColumn<>("Sexo");
		nombre.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
		apaterno.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("paterno"));
		amaterno.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("materno"));
		fecha.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("Fecha"));
		sexo.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("sexo"));
		tblalumnos.getColumns().addAll(nombre,apaterno,amaterno,fecha,sexo);
		try {
			Table();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		final ObservableList<Alumnos> tablaPersonaSel = tblalumnos.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(select);
		try {
			lis = nuevoAlumno.listarcarrera();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cboCarrera.setItems(lis);
			
	}
	
	

}
