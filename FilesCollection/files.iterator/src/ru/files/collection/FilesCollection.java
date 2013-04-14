package ru.files.collection;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class FilesCollection {

	private String path;
	private Collection<File> files = new LinkedList<File>();
	
	// by default filter accept all files
	private FilenameFilter filter = new FilenameFilter() {
		
		@Override
		public boolean accept(File dir, String name) {
			return true;
		}
	};

	
	public FilesCollection(String path) {
		this.path = path;
		
		File f =new File(path);
		if ( !f.exists() || !f.isDirectory() ) 
			throw new RuntimeException(String.format(" %s is not exist or not a Dir", path));
		
	}

	public FilesCollection(String path, FilenameFilter filter) {
		this(path);
		this.filter = filter;
	}

	private void parseDir () {
		
		File dir = new File(path);
		for ( File file : dir.listFiles(filter) ) {
			
			if( file.isDirectory() ) continue;
			files.add(file);
		}
	}
	
	
	public Iterator<File> getIterator() {
		
		parseDir();
		
		return new Iterator<File>() {

			Iterator<File> iterator = files.iterator();
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public File next() {
				return iterator.next();
			}

			@Override
			public void remove() {
				throw new RuntimeException("Not supported");				
			}
		};
	}

	
}
