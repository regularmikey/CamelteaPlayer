import javafx.beans.property.SimpleStringProperty;

public class CModel {

	  private final SimpleStringProperty artistName;
	  private final SimpleStringProperty albumName;
	  private final SimpleStringProperty trackName;
	  private final SimpleStringProperty trackDuration;
	  private final SimpleStringProperty trackNumber;
	private final java.nio.file.Path filePath;

     public CModel(String artist, String album, String track,
 String dur,
			java.nio.file.Path path, String number) {
    	 this.artistName = new SimpleStringProperty(artist);
    	 this.albumName = new SimpleStringProperty(album);
    	 this.trackName = new SimpleStringProperty(track);
    	 this.trackDuration = new SimpleStringProperty(dur);
    	 this.trackNumber = new SimpleStringProperty(number);
		this.filePath = path;
     }

	public SimpleStringProperty artistName() {
		return artistName;
	}

	public SimpleStringProperty albumName() {
		return albumName;
	}

	public SimpleStringProperty trackName() {
		return trackName;
	}

	public SimpleStringProperty trackDuration() {
		return trackDuration;
	}

	public SimpleStringProperty trackNumber() {
		return trackNumber;
	}



	public String getArtistName() {
		return artistName.get();
	}
	
	public void setArtistName(String newArt) {
		this.artistName.set(newArt);
	}

	public String getAlbumName() {
		return albumName.get();
	}
	
	public void setAlbumName(String newAlb) {
		this.albumName.set(newAlb);
	}

	public String getTrackName() {
		return trackName.get();
	}
	
	public void setTrackName(String newTrck) {
		this.trackName.set(newTrck);
	}

	public String getTrackDuration() {
		return trackDuration.get();
		
	}
	
	public void setTrackDuration(String newDur) {
		this.trackDuration.set(newDur);
	}

	public String getTrackNumber() {
		return trackNumber.get();
	}
	
	public void setTrackNumber(String newNumb) {
		this.trackNumber.set(newNumb);
	}

	public java.nio.file.Path getFilePath() {
		return filePath;
	}

}

