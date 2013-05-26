package dk.itu.travelapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.app.Activity;
import android.view.Menu;

public class PrefFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
	}
}
