package webPlayer.helper;

import java.io.File;
import java.io.FileFilter;

public class PastaFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		return pathname.isDirectory();
	}
}