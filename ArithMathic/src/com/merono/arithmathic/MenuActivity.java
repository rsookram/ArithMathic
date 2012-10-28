package com.merono.arithmathic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	public void onStartButtonPressed(View view) {
		startActivity(new Intent(this, DrillActivity.class));
	}

	public void onSettingsButtonPressed(View view) {
		startActivity(new Intent(this, PreferencesActivity.class));
	}
}
