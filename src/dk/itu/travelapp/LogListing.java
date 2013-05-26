package dk.itu.travelapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.view.View;

public class LogListing extends ListActivity implements View.OnClickListener {
	private static final int FLAG_REGISTER_CONTENT_OBSERVER = 0x00000002;
	SimpleCursorAdapter ca;
	DbAdapter db;
	Cursor log;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new DbAdapter(this);
		log = db.getLog();
		
		ca = new SimpleCursorAdapter(this, R.layout.activity_two_collumns,
				log, 
				new String[] { "start", "end" }, 
				new int[] { R.id.firstCollumn, R.id.secondCollumn }, 
				FLAG_REGISTER_CONTENT_OBSERVER);
		setListAdapter(ca);
	}

	@Override
	public void onClick(View arg0) {
		finish();
	}
	
	@Override
	public void onDestroy(){
		if(db != null) db.close();
		if(log != null) log.close();
		
		super.onDestroy();
	}
}
