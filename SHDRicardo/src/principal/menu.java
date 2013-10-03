package principal;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class menu extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		Parent menu = FXMLLoader.load(getClass().getResource("../view/fxml/fxmlMenu.fxml"));
		
		Scene scene = new Scene(menu);
		scene.getStylesheets().add(menu.class.getResource("../view/style/Style.css").toExternalForm());
		arg0.setScene(scene);
		arg0.setTitle("Menú");
		arg0.show();
	}
	
	public static void main(String [] args){
		launch(args);
	}
	
	@FXML protected void ClickCarrera(ActionEvent evnt) throws IOException{
		Stage primaryStage = new Stage();
		Parent carrera = FXMLLoader.load(getClass().getResource("../view/fxml/fxmlCarrera.fxml"));
		
		Scene scene = new Scene(carrera);
		scene.getStylesheets().add(menu.class.getResource("../view/style/Style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Carreras");
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(((Node)evnt.getSource()).getScene().getWindow());
		primaryStage.show();
	}
	
	@FXML protected void ClickAlumno(ActionEvent evnt) throws IOException{
		Stage primaryStage = new Stage();
		Parent carrera = FXMLLoader.load(getClass().getResource("../view/fxml/fxmlAlumnos.fxml"));
		
		Scene scene = new Scene(carrera);
		scene.getStylesheets().add(menu.class.getResource("../view/style/Style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Alumnos");
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(((Node)evnt.getSource()).getScene().getWindow());
		primaryStage.show();
	}
	
	@FXML protected void ClickSalir(ActionEvent evnt) throws IOException{
		System.exit(0);
	}

}
