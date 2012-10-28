package com.merono.arithmathic;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;

public class PreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= 14) {
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP,
					ActionBar.DISPLAY_HOME_AS_UP);
		}

		addPreferencesFromResource(R.xml.prefs);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			final Intent i = new Intent(this, MenuActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
