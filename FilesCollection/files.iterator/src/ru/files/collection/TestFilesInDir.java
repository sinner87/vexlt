package ru.files.collection;

import java.io.File;
import java.util.Iterator;

import junit.framework.TestCase;

public class TestFilesInDir extends TestCase {

	private FilesCollection files;
	
	public void setUp () {
		this.files = new FilesCollection("D:\\video\\");
	}
	
	
	public void testInvalidDir () {

		try {
			
			new FilesCollection("D:\\7676video\\");
			assertTrue("No exception on invalid dir", false);
			
		} catch (Exception e ) {
			assertTrue(true);
		}

	}
	
	public void testFilesCollectionIterator () {
		
			try {
				
				Iterator<File> it =  files.getIterator();
				assertNotNull(it);
				
				while ( it.hasNext() ) {
					assertNotNull(it.next());
				}
				
				
			} catch (Exception e) {
				assertTrue("Exception must not thrown",false);
			}
		
	}
}
