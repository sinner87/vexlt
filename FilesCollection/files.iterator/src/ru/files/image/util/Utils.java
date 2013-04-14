package ru.files.image.util;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

public class Utils {

	
	public static Dimension getImageDim(final File file) {
	    Dimension result = new Dimension(0, 0);
	    String suffix = file.getName().substring(file.getName().lastIndexOf('.') + 1); // plus 1 really need. Suffix will not contain dot symbol.
	    
	    Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
	    if (iter.hasNext()) {
	        ImageReader reader = iter.next();
	        try {
	            ImageInputStream stream = new FileImageInputStream(file);
	            reader.setInput(stream);
	            int width = reader.getWidth(reader.getMinIndex());
	            int height = reader.getHeight(reader.getMinIndex());
	            result = new Dimension(width, height);
	        } catch (IOException e) {
	        	System.out.println(e.getMessage());
	        } finally {
	            reader.dispose();
	        }
	    } else {
	    	System.out.println("No reader found for given format: " + suffix);
	    }
	    return result;
	}
	
}
