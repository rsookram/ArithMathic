package com.merono.arithmathic;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends Activity {

	public static final String timeElap = null;
	private static final String TAG = "ResultsActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_layout);

		if (Build.VERSION.SDK_INT >= 14) {
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP,
					ActionBar.DISPLAY_HOME_AS_UP);
		}

		TextView typeQView = (TextView) findViewById(R.id.setting_typeq);
		TextView totalQView = (TextView) findViewById(R.id.setting_numq);
		TextView maxNumView = (TextView) findViewById(R.id.setting_maxnum);
		TextView timeView = (TextView) findViewById(R.id.time_taken);

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		String qType = pref.getString("question_type", "All");
		String totalQ = pref.getString("totalQuestions", "20");
		String maxNum = pref.getString("max_num", "12");

		typeQView.setText(qType);
		totalQView.setText(totalQ);
		maxNumView.setText(maxNum);

		String timeTaken = getIntent().getStringExtra(timeElap);
		timeView.setText(timeTaken);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			goToMainMenu(null);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		goToMainMenu(null);
	}

	public void retry(View view) {
		startActivity(new Intent(this, DrillActivity.class));
	}

	public void goToMainMenu(View view) {
		final Intent i = new Intent(this, MenuActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
}
