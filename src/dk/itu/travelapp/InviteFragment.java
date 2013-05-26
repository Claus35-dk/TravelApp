package dk.itu.travelapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class InviteFragment extends Fragment {
	public static final String INVITE_FRAGMENT_TAG = "inviteFragmentTag";
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.fragment_invite, container, false);
		return view;
	}
}
