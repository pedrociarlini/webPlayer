package webPlayer.helper;

import java.io.File;
import java.io.FilenameFilter;

public class PastaFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return dir.isDirectory();
	}
}