package dk.itu.travelapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class CheckOutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_out);
		
		//Get any extras from intent
		String checkInWas = getIntent().getStringExtra("checkInStation");
		if(checkInWas != null && !checkInWas.trim().isEmpty()){
			EditText checkOut = (EditText) findViewById(R.id.checkOutStation);
			checkOut.setText(checkInWas);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_out, menu);
		return true;
	}

}
