package dk.itu.travelapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity {
	public static final String SELECTED_LIST_ITEM = "SELECTED_LIST_ITEM";
	public static final int SELECTED_START = 1;
	public static final int SELECTED_END = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		Button checkInButton = (Button) findViewById(R.id.checkInButton);
		checkInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, CheckInActivity.class);
				startActivity(intent);
			}
		});
		
		Button checkOutButton = (Button) findViewById(R.id.checkOutButton);
		checkOutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText startField = (EditText) findViewById(R.id.editStartLocation);
				EditText endField = (EditText) findViewById(R.id.editEndLocation);
				
				String startText = startField.getText().toString().trim();
				String endText = endField.getText().toString().trim();
				
				Boolean error = false;
				if(startText == null || startText.isEmpty()){
					Toast.makeText(HomeActivity.this, "Please fillin a start location", Toast.LENGTH_SHORT).show();
					error = true;
				}
				if(endText == null || endText.isEmpty()){
					Toast.makeText(HomeActivity.this, "Please fillin an end location", Toast.LENGTH_SHORT).show();
					error = true;
				}
				if(error) return;
				
				DbAdapter db = new DbAdapter(HomeActivity.this);
				db.log(startText, endText);
				Toast.makeText(HomeActivity.this, "Travel logged", Toast.LENGTH_SHORT).show();
			}
		});
		
		Button selectStartButton = (Button) findViewById(R.id.selectStartButton);
		selectStartButton.setOnClickListener(new selectClass(SELECTED_START));
		
		Button selectEndButton = (Button) findViewById(R.id.selectEndButton);
		selectEndButton.setOnClickListener(new selectClass(SELECTED_END));
		
		ImageView img = (ImageView) findViewById(R.id.img);
		img.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (HomeActivity.this, WebActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		MenuItem mItem = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Log");
		
		mItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int i, MenuItem mItem){
		if(mItem.getItemId() == Menu.FIRST){
			Intent intent = new Intent (HomeActivity.this, LogListing.class);
			startActivity(intent);
			
			return true;
		}
		else return super.onMenuItemSelected(i, mItem);
	}
	
	@Override
	public void onActivityResult (int requestCode, int resultCode, Intent data){
		if(resultCode != RESULT_OK) return;
		String selected;
		if(data.getExtras().containsKey(SELECTED_LIST_ITEM)){
			selected = data.getExtras().getString(SELECTED_LIST_ITEM);
		}else return;
		EditText text;
		if(requestCode == SELECTED_START){
			text = (EditText) findViewById(R.id.editStartLocation);
		} else if (requestCode == SELECTED_END){
			text = (EditText) findViewById(R.id.editEndLocation);
		} else return;
		text.setText(selected);
	}
	
	class selectClass implements View.OnClickListener{
		int requestCode;
		public selectClass(int requestCode){
			this.requestCode = requestCode;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, List.class);
			startActivityForResult(intent, requestCode);
		}
	}

}
