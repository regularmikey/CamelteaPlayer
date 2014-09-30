import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Files {
	
	private Main app;
	
	public Files(Main mainApp) {
         this.app = mainApp;
	}


	public String toMinutes(int duration) {

		int fin = duration / 60;
		int rem = duration % 60;

		String ret = fin + "" + ":" + rem + "";

		return ret;
	}

	public void openFile() {
    	FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showOpenDialog(null);
        
        if(file != null) {
        	
        	
        		try {
					AudioFile mp3File = AudioFileIO.read(file);
					AudioHeader header = mp3File.getAudioHeader();
					Tag t = mp3File.getTag();
					
					CModel track = new CModel(t.getFirst(FieldKey.ARTIST), 
							t.getFirst(FieldKey.ALBUM), 
							t.getFirst(FieldKey.TITLE), 
						toMinutes(header.getTrackLength()),
 file.toPath(),
						t.getFirst(FieldKey.TRACK)
							);
				
				app.getPersonData().add(track);
					
				} catch (CannotReadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (org.jaudiotagger.tag.TagException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ReadOnlyFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidAudioFrameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    

        
        
    }
        


}

	public void openFolder() {
		DirectoryChooser dirChooser = new DirectoryChooser();
		File selectedDir = dirChooser.showDialog(null);
		List<CModel> list;


		if (selectedDir != null) {
			list = getFileList(selectedDir.toPath(), "mp3", "mp3");
			app.getPersonData().addAll(list);



		}
	}

	public  List<CModel> getFileList(Path pmRoot, String pmExtName,
			String pmExt) {
		File lvDir = pmRoot.toFile();

		if (!lvDir.isDirectory()) {
			return new ArrayList<CModel>();
		}

		List<CModel> lvResult = new ArrayList<CModel>();

		FileNameExtensionFilter lvFilter = new FileNameExtensionFilter(
				pmExtName, pmExt);

		for (File file : lvDir.listFiles()) {
			if (file.isDirectory()) {
				lvResult.addAll(getFileList(file.toPath(), pmExtName, pmExt));
			} else {
				if (lvFilter.accept(file)) {
					try {
					AudioFile mp3File = AudioFileIO.read(file);
					AudioHeader header = mp3File.getAudioHeader();
					Tag t = mp3File.getTag();
					lvResult.add(new CModel(t.getFirst(FieldKey.ARTIST), 
							t.getFirst(FieldKey.ALBUM), 
							t.getFirst(FieldKey.TITLE), 
						toMinutes(header.getTrackLength()),
 file.toPath(),
						t.getFirst(FieldKey.TRACK)
));
				
				} catch (CannotReadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (org.jaudiotagger.tag.TagException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ReadOnlyFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidAudioFrameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}

		}
		return lvResult;
	}

}
