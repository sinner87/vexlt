import java.awt.Dimension;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import ru.files.collection.FilesCollection;
import ru.files.image.util.Utils;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long begin = Calendar.getInstance().getTimeInMillis();
		System.out.println(String.format("Begin millis: %d", begin));
		

	
		FilesCollection files2 = new FilesCollection("D:\\img\\test\\", new FilenameFilter() {
			
			@Override
			public boolean accept(File path, String fname) {
				return fname.endsWith(".png");
			}
		});
		
		Iterator<File> it2 =  files2.getIterator();
		
		while ( it2.hasNext() ) {
			
			File image = it2.next();
		
			Dimension dimension = Utils.getImageDim(image);
			System.out.println(String.format(" %s - %dx%d ", image.getName(), dimension.width, dimension.height));
			
			
			try {
			    FileUtils.copyFile(image,new File("D:\\temp\\33\\"+image.getName()));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
		}
		
		
		System.out.println(String.format("End millis: %d\nSpend: %d", Calendar.getInstance().getTimeInMillis() ,
				Calendar.getInstance().getTimeInMillis() - begin));
	}
	
	


}
