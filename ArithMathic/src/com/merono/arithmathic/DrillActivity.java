package com.merono.arithmathic;

import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DrillActivity extends Activity {
	private static final String TAG = "DrillActivity";

	String mAnswer;
	String mGuess;
	String mQuestionType;

	int totalQuestions;
	int mMaxOperand;
	int mQuestionsAnswered;

	long startTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drill_layout);

		if (Build.VERSION.SDK_INT >= 14) {
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP,
					ActionBar.DISPLAY_HOME_AS_UP);
		}

		setPreferences();

		nextQuestion();
		mQuestionsAnswered = 0;
		startTime = System.currentTimeMillis();
	}

	void setPreferences() {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		mQuestionType = pref.getString("question_type", "All");
		totalQuestions = Integer.parseInt(pref
				.getString("totalQuestions", "20"));
		mMaxOperand = Integer.parseInt(pref.getString("max_num", "12"));
	}

	void nextQuestion() {
		if (mQuestionsAnswered >= totalQuestions) {
			endDrill();
			return;
		}

		Random randGen = new Random();
		int choice = chooseOperation();
		int op1 = randGen.nextInt(mMaxOperand) + 1;
		int op2 = randGen.nextInt(mMaxOperand) + 1;

		TextView questionText = (TextView) findViewById(R.id.question_text);
		switch (choice) {
		case 0: // add
			questionText.setText(op1 + " + " + op2);
			mAnswer = Integer.toString(op1 + op2);
			break;
		case 1: // sub
			if (op1 < op2) {
				int temp = op1;
				op1 = op2;
				op2 = temp;
			}
			questionText.setText(op1 + " - " + op2);
			mAnswer = Integer.toString(op1 - op2);
			break;
		case 2: // mult
			questionText.setText(op1 + " x " + op2);
			mAnswer = Integer.toString(op1 * op2);
			break;
		default:
			Log.d(TAG, "choice error");
		}

		mQuestionsAnswered++;

		TextView answerText = (TextView) findViewById(R.id.answer_text);
		mGuess = "";
		answerText.setText(mGuess);
	}

	int chooseOperation() {
		int choice;
		Random randGen = new Random();

		if (mQuestionType.equals("Addition")) {
			choice = 0;
		} else if (mQuestionType.equals("Subtraction")) {
			choice = 1;
		} else if (mQuestionType.equals("Multiplication")) {
			choice = 2;
		} else {
			choice = randGen.nextInt(3);
		}

		return choice;
	}

	void checkAnswer() {
		if (mGuess.equals(mAnswer)) { // right
			nextQuestion();
		} else if (mGuess.length() >= mAnswer.length()) { // wrong
			mGuess = "";
		}
	}

	void endDrill() {
		long endTime = System.currentTimeMillis();
		double timeTaken = (endTime - startTime) / 1000.0;

		final Intent i = new Intent(this, ResultsActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra(com.merono.arithmathic.ResultsActivity.timeElap, timeTaken
				+ "s");
		startActivity(i);
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

	public void onNumberPressed(View view) {
		switch (view.getId()) {
		case R.id.button_0:
			mGuess = mGuess + "0";
			break;
		case R.id.button_1:
			mGuess = mGuess + "1";
			break;
		case R.id.button_2:
			mGuess = mGuess + "2";
			break;
		case R.id.button_3:
			mGuess = mGuess + "3";
			break;
		case R.id.button_4:
			mGuess = mGuess + "4";
			break;
		case R.id.button_5:
			mGuess = mGuess + "5";
			break;
		case R.id.button_6:
			mGuess = mGuess + "6";
			break;
		case R.id.button_7:
			mGuess = mGuess + "7";
			break;
		case R.id.button_8:
			mGuess = mGuess + "8";
			break;
		case R.id.button_9:
			mGuess = mGuess + "9";
			break;
		}

		checkAnswer();
		TextView answerText = (TextView) findViewById(R.id.answer_text);
		answerText.setText(mGuess);
	}
}
