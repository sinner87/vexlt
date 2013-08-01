package com.load.http;

public class DownloadItem {

	private long mRealSize;
	private long mCurrentByrtes;

	private CharSequence mDescription;
	private CharSequence mFsName;
	
	private boolean mUseRedirect;
	
	// so so
	private long mVersionCode;
	private String mVersionName;
		
	
	public DownloadItem setSize ( long size ) {
		mRealSize = size;
		return this;
	}
	
	public DownloadItem useRedirect ( boolean useRedirect ) {
		this.mUseRedirect = useRedirect;
		return this;
	}

	public DownloadItem setDescription ( CharSequence description) {
		this.mDescription = description;
		return this;
	}

	public long obtainLoadedSize ( ) {
		// TODO: read information from another place (DB / FileSystem / Preferences )
		return 0L;
	}
	
}
