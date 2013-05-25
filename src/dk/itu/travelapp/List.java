package dk.itu.travelapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List extends ListActivity {
	String[] list = {
			"Copenhagen",
			"Aalborg",
			"Rainbow",
			"Somewhere"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		String value = list[(int)id];
		Intent intent = new Intent().putExtra(HomeActivity.SELECTED_LIST_ITEM, value);
		setResult(RESULT_OK, intent);
		finish();
	}

}
