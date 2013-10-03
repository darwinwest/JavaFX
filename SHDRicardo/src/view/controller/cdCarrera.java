package view.controller;

import java.awt.TrayIcon;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import model.cnCarrera;
import entidades.Carrera;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class cdCarrera implements Initializable{

	@FXML private TextField txtNombre;
	@FXML private TextField txtSiglas;
	@FXML private TextField txtJDC;
	@FXML private TextField txtMatricula;
	@FXML private ComboBox<String> cboAcreditada;
	@FXML private Label lblMensaje;
	@FXML private TableView<Carrera> tblCarrera;
	@FXML private Button btnGuardar;
	@FXML private Button btnElimar;
	@FXML private Button btnModificar;
	
	private cnCarrera nuevacarrera = new cnCarrera();
	private Carrera micarrera=null;
	private int contadorposicion;
	private ObservableList<Carrera> listacarrera = FXCollections.observableArrayList();
	
	@FXML protected void Guardar(ActionEvent evnt){
		if(txtNombre.getText().trim().isEmpty() || txtSiglas.getText().trim().isEmpty()
				|| txtJDC.getText().trim().isEmpty() || txtMatricula.getText().trim().isEmpty()){
			lblMensaje.setText("Usted a dejado campos vacíos.");
		}else{
			if(micarrera==null){
				nuevacarrera.Guardar(new Carrera(txtNombre.getText(), txtSiglas.getText(),
						txtJDC.getText(), txtMatricula.getText(), cboAcreditada.getValue()));
				lblMensaje.setText("Los datos de la carrera se guardan correctamente");
			}else{
				micarrera.setNombre(txtNombre.getText());
				micarrera.setSiglas(txtSiglas.getText());
				micarrera.setJDC(txtJDC.getText());
				micarrera.setMatricula(txtMatricula.getText());
				micarrera.setAcreditada(cboAcreditada.getValue());
				nuevacarrera.Guardar(micarrera);
				lblMensaje.setText("Los datos de la carrera se actualizaron correctamente");
			}
			try {
				Table();
				Nuevo(evnt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML protected void Actualizar(ActionEvent evnt) throws SQLException{
		
	}
	
	@FXML protected void Nuevo(ActionEvent evnt) throws SQLException{
		txtNombre.setText("");
		txtSiglas.setText("");
		txtJDC.setText("");
		txtMatricula.setText("");
		cboAcreditada.setValue("");
		micarrera=null;
		contadorposicion=0;
	}
	
	@FXML protected void Eliminar(ActionEvent evnt) throws ClassNotFoundException, SQLException{
		if(txtNombre.getText().trim().isEmpty() || txtSiglas.getText().trim().isEmpty()
				|| txtJDC.getText().trim().isEmpty() || txtMatricula.getText().trim().isEmpty()){
			lblMensaje.setText("Usted a dejado campos vacíos.");
		}else {
			if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar a la Carrera?","confirmación",
					TrayIcon.MessageType.INFO.ordinal())==0){
				nuevacarrera.Eliminar(contadorposicion);
				Table();		
				Nuevo(evnt);
			}
		}
	}
	
	private final ListChangeListener<Carrera> select = new ListChangeListener<Carrera>(){

		@Override
		public void onChanged(
				javafx.collections.ListChangeListener.Change<? extends Carrera> arg0) {
			ponercarrera();
		}
	};
	
	public Carrera gettblCarreraSelect (){
		if(tblCarrera != null){
			List<Carrera> tabla = tblCarrera.getSelectionModel().getSelectedItems();
			if(tabla.size() == 1){
				final Carrera competicionseleccionada = tabla.get(0);
				return competicionseleccionada;
			}
		}
		return null;
	}
	
	private void ponercarrera(){
		final Carrera carrera = gettblCarreraSelect();
		contadorposicion = listacarrera.indexOf(carrera);
		if(carrera != null){
			contadorposicion=carrera.getIdCarrera();
			micarrera=carrera;
			txtNombre.setText(carrera.getNombre());
			txtSiglas.setText(carrera.getSiglas());
			txtJDC.setText(carrera.getJDC());
			txtMatricula.setText(carrera.getMatricula());
			cboAcreditada.setValue(carrera.getAcreditada());
		}
	}
	
	private void Table() throws SQLException{
		
		listacarrera=nuevacarrera.listarcarrera();
		tblCarrera.setItems(listacarrera);
		tblCarrera.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnModificar.setVisible(false);
		TableColumn<Carrera, String> nombre = new TableColumn<>("Nombre");
		TableColumn<Carrera, String> siglas = new TableColumn<>("Siglas");
		TableColumn<Carrera, String> jefe = new TableColumn<>("Jefe");
		TableColumn<Carrera, String> matricula = new TableColumn<>("Matricula");
		TableColumn<Carrera, String> acreditada = new TableColumn<>("Acreditada");
		nombre.setCellValueFactory(new PropertyValueFactory<Carrera, String>("Nombre"));
		siglas.setCellValueFactory(new PropertyValueFactory<Carrera, String>("Siglas"));
		jefe.setCellValueFactory(new PropertyValueFactory<Carrera, String>("JDC"));
		matricula.setCellValueFactory(new PropertyValueFactory<Carrera, String>("Matricula"));
		acreditada.setCellValueFactory(new PropertyValueFactory<Carrera, String>("Acreditada"));
		tblCarrera.getColumns().addAll(nombre,siglas,jefe,matricula,acreditada);
		try {
			Table();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final ObservableList<Carrera> tablaPersonaSel = tblCarrera.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(select);
        
	}

}
