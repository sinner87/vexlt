package ru.expandable.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class NetLog {

	private static PrintStream ps = null; 
	private static String sLogFile = null;
	private static String TAG = null;
	
	public static PrintStream Init(String tag,String sLogFileName,boolean removeIfExists) {
		try {
			TAG = tag;
			sLogFile = Environment.getExternalStorageDirectory() + "/" + sLogFileName;
			File file = new File(sLogFile);
			//if ( file.exists() && removeIfExists ) {
			//	Log.v(TAG,"file removed");
			//	file.delete();
			//}
			
			if ( ps == null ) {
				ps = new PrintStream(new FileOutputStream(file,removeIfExists == false));
				NetLog.w("********* LOGGER STARTED ********* \n");
			} else
				NetLog.w("********* LOGGER ACQUIRED ********* \n");

			
			return ps;
		} catch ( Exception e ) {
			Log.v(TAG,"NetLog Error " + e.toString());
		}
		return null;
	}
	
	public static int RemoveOldFile ( String sLogFileName ){
	
		sLogFile = Environment.getExternalStorageDirectory() + "/" + sLogFileName;
		File file = new File(sLogFile);
		if ( file.exists() ) {
			file.delete();
			return 0;
		}
		return 1;
	}
	
	
	public static String getTimeStamp(String dateFormat) {
        Date currentTime = new Date();
		SimpleDateFormat df = new SimpleDateFormat(dateFormat == null?"dd.MM HH:mm:ss ":dateFormat);
		return df.format(currentTime);
	}

	public static void writeToFile(String severety,String fmt,Object ... args) {
		if ( NetLog.ps == null ) 
			return;
		synchronized (NetLog.ps) {
			
		NetLog.ps.printf("%s | %s | ",NetLog.getTimeStamp(null),severety);
		String msg = String.format(fmt, args);
		NetLog.ps.printf(fmt,args);
		if ( !msg.endsWith("\n") )
			NetLog.ps.printf("\n");
		}
	}
	
	public static void v(String fmt,Object ... args) {
		NetLog.writeToFile("V",fmt,args);
		Log.v(TAG,String.format(fmt, args));
	}

	public static void e(String fmt,Object ... args) {
		NetLog.writeToFile("E",fmt,args);
		Log.e(TAG,String.format(fmt, args));
	}

	public static void w(String fmt,Object ... args) {
		NetLog.writeToFile("W",fmt,args);
		Log.w(TAG,String.format(fmt, args));
	}

	public static void i(String fmt,Object ... args) {
		NetLog.writeToFile("I",fmt,args);
		Log.i(TAG,String.format(fmt, args));
	}
	
	
	public static void Dump() {
		File file = new File(sLogFile);
		try {
			String line = null;
			BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(file)));
			while ((line = br.readLine()) != null ) 
				Log.v(TAG,line);
			br.close();
		} catch ( Exception e ) {
			Log.v(TAG,"NetLog.Dump()=>"+e.toString());
		}
	}
	
	public static void MsgBox(Context ctx,String sTitle,String fmt,Object ... args) {
		String msg = String.format(fmt, args);
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ctx);                      
	    dlgAlert.setTitle(sTitle);
	    dlgAlert.setMessage(msg);
	    dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	        }
	   });
	    dlgAlert.setCancelable(true);
	    dlgAlert.create().show();
	}
	
	public static void MsgBox(Context ctx, DialogInterface.OnClickListener onClick, String sTitle,String fmt,Object ... args) {
		String msg = String.format(fmt, args);
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ctx);                      
	    dlgAlert.setTitle(sTitle); 
	    dlgAlert.setMessage(msg); 
	    dlgAlert.setPositiveButton("OK", onClick);
	    dlgAlert.setCancelable(true);
	    dlgAlert.create().show();
	}
	
	
	public static void Toast(Context ctx,String fmt, Object ... args) {
		String msg = String.format(fmt, args);
		Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();
	}
};