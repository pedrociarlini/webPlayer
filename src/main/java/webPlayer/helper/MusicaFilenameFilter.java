package webPlayer.helper;

import java.io.File;
import java.io.FilenameFilter;

public class MusicaFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return name != null && name.toUpperCase().endsWith("MP3");
	}
}