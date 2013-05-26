package dk.itu.travelapp;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CheckInActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_in);
		
		Button checkInButton = (Button) findViewById(R.id.checkInButton);
		
		checkInButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CheckInActivity.this, CheckOutActivity.class);
				// Add text to intent
				EditText textField = (EditText) findViewById(R.id.checkInStation);
				String first = textField.getText().toString();
				
				EditText textField2 = (EditText) findViewById(R.id.secondStation);
				String second = textField2.getText().toString();
				
				float dist = 0;
				String loc = "start ";
				
				if (Geocoder.isPresent()) {
					dist = 201;
					Geocoder geocoder = new Geocoder(CheckInActivity.this);
					try {
						List<Address> addresses = geocoder.getFromLocationName(first, 1);
						Address a;
						Location l;
						Address a2;
						Location l2;
						loc = "try ";
						
						if (addresses.size() > 0) {
							a = addresses.get(0);
							l = new Location(a.getAddressLine(0));
							loc = "first ";
							
							List<Address> addresses2 = geocoder.getFromLocationName(second, 1);
							if (addresses2.size() > 0) {
								a2 = addresses2.get(0);
								l2 = new Location(a2.getAddressLine(0));
								dist = l.distanceTo(l2);
								loc = l.toString() + l2.toString();
							}
						}
					} catch (IOException ioe) {
						loc = "err ";
					}
				} 
				
				intent.putExtra("checkInStation", loc + dist);
				
				startActivity(intent);
			}
		});
		
		
		/*checkOutButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent intent = new Intent(CheckInActivity.this, CheckOutActivity.class);
				// Add text to intent
				//EditText textField = (EditText) findViewById(R.id.checkInStation);
				//String str = textField.getText().toString();
				//intent.putExtra("checkInStation", str);
				
				//startActivity(intent);
			}
		}); */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_in, menu);
		return true;
	}

}
