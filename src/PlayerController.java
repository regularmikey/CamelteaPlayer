import java.net.URI;
import java.nio.file.Path;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;

public class PlayerController {

	@FXML
	private TableView<CModel> cTable;
	
	@FXML
	private TableColumn<CModel, String> artCol;
	
	@FXML
	private TableColumn<CModel, String> albCol;
	
	@FXML
	private TableColumn<CModel, String> trNameCol;
	
	@FXML
	private TableColumn<CModel, String> trNoCol;
	
	@FXML
	private TableColumn<CModel, String> durCol;
	
	@FXML
	private Menu addFile;

	@FXML
	private Menu addFolder;

	@FXML
	private Menu savePlayL;

	@FXML
	private Menu loadPlayL;

    
    private Files files;
	
	private URI u;
	private Media media;
	private MediaPlayer mediaPlayer;
    
	private Main main;

	private Path filePath;
	

	public PlayerController() {


	}

	@FXML
	private void initialize() {


		artCol.setCellFactory(cellFactory);
		artCol.setCellValueFactory(new PropertyValueFactory<CModel, String>(
				"artistName"));
		
		albCol.setCellFactory(cellFactory);
		albCol.setCellValueFactory(new PropertyValueFactory<CModel, String>(
				"albumName"));
		trNameCol.setCellFactory(cellFactory);
		trNameCol.setCellValueFactory(new PropertyValueFactory<CModel, String>(
				"trackName"));
		
		trNoCol.setCellFactory(cellFactory);
		trNoCol.setCellValueFactory(new PropertyValueFactory<CModel, String>(
				"trackNumber"));

		durCol.setCellFactory(cellFactory);
		durCol.setCellValueFactory(new PropertyValueFactory<CModel, String>(
				"trackDuration"));

	}
	

    @FXML 
    protected void handleAddFile(ActionEvent event) {
		files = new Files(main);
    	files.openFile();
    }

	@FXML
	protected void handleAddFolder(ActionEvent event) {
		files = new Files(main);
		files.openFolder();
	}

	@FXML
	protected void handlePlayButton(ActionEvent event) {
		filePath = cTable.getSelectionModel().getSelectedItem()
				.getFilePath();

		u = filePath.toUri();
		media = new Media(u.toString());

		if (mediaPlayer != null) {
			if (!mediaPlayer.isMute()) {
				mediaPlayer.stop();
			}

		}
		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.play();

	}


	public void setData(Main mainApp) {
		this.main = mainApp;

		cTable.setItems(main.getPersonData());
	}

	Callback<TableColumn<CModel, String>, TableCell<CModel, String>> cellFactory = new Callback<TableColumn<CModel, String>, TableCell<CModel, String>>() {
		public TableCell<CModel, String> call(TableColumn<CModel, String> p) {
			TableCell<CModel, String> cell = new TableCell<CModel, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(empty ? null : getString());
					setGraphic(null);
				}

				private String getString() {
					return getItem() == null ? "" : getItem().toString();
				}
			};

			cell.addEventFilter(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							filePath = cTable.getSelectionModel()
									.getSelectedItem().getFilePath();

							u = filePath.toUri();
							media = new Media(u.toString());

							if (mediaPlayer != null) {
								if (!mediaPlayer.isMute()) {
									mediaPlayer.stop();
								}

							}
							mediaPlayer = new MediaPlayer(media);

							mediaPlayer.play();
						}
					});
			return cell;
		}
	};
}
