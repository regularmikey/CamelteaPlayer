import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;


public class Main extends Application {
	ObservableList<CModel> modelData = FXCollections.observableArrayList();
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			SplitPane root = (SplitPane) loader.load(getClass().getResource(
					"Player.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			
			PlayerController controller = loader.getController();
			
			controller.setData(this);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<CModel> getPersonData() {
		return modelData;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
