package mobile.thomasianJourney.main.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import mobile.thomasianJourney.main.interfaces.AsyncResponse;
import mobile.thomasianJourney.main.register.async.RegisterFirstAsync;
import mobile.thomasianJourney.main.register.utils.IntentExtrasAddresses;
import mobile.thomasianJourney.main.vieweventsfragments.R;

public class RegisterFirstLoading extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_first_loading_layout);

		initializeViews();

		Intent intent = getIntent();

		if (intent != null) {
			String email = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS);
			String mobile = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER);

			AsyncResponse asyncResponse = new AsyncResponse() {
				@Override
				public void doWhenFinished(String output) {
					verifyCredentials(output);
				}
			};

			RegisterFirstAsync registerFirstAsync = new RegisterFirstAsync(asyncResponse);

			registerFirstAsync.execute(email, mobile, getString(R.string.registerURL));

			Log.d("RegisterFirstLoading", "Async part " + email + " " + mobile);
		}
	}

	private void initializeViews() {

		// StepView initialization
		HorizontalStepView stepview = findViewById(R.id.registerFirstLoading_stepView);

		List<StepBean> stepsBeanList = new ArrayList<>();
		StepBean stepBean0 = new StepBean("", 0);
		StepBean stepBean1 = new StepBean("", -1);
		StepBean stepBean2 = new StepBean("", -1);
		stepsBeanList.add(stepBean0);
		stepsBeanList.add(stepBean1);
		stepsBeanList.add(stepBean2);

		stepview.setStepViewTexts(stepsBeanList).setTextSize(12)//set textSize
				.setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(RegisterFirstLoading.this, android.R.color.black)).setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(RegisterFirstLoading.this, R.color.uncompleted_text_color)).setStepViewComplectedTextColor(ContextCompat.getColor(RegisterFirstLoading.this, android.R.color.black)).setStepViewUnComplectedTextColor(ContextCompat.getColor(RegisterFirstLoading.this, R.color.uncompleted_text_color)).setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(RegisterFirstLoading.this, R.drawable.ic_check_black)).setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(RegisterFirstLoading.this, R.drawable.ic_radio)).setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(RegisterFirstLoading.this, R.drawable.tiger_rar));

		// Lottie animation initialization
		LottieAnimationView registerFirstLoading_lottieAnimationView = findViewById(R.id.registerFirstLoading_lottieAnimationView);
		registerFirstLoading_lottieAnimationView.setScale(7f);
		registerFirstLoading_lottieAnimationView.setVisibility(View.VISIBLE);
		registerFirstLoading_lottieAnimationView.setAnimation(R.raw.load);
		registerFirstLoading_lottieAnimationView.playAnimation();

		Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
		TextView registerFirstLoading_titleTextView = findViewById(R.id.registerFirstLoading_titleTextView);
		TextView registerFirstLoading_subtitleTextView = findViewById(R.id.registerFirstLoading_subtitleTextView);
		registerFirstLoading_titleTextView.startAnimation(myanim);
		registerFirstLoading_subtitleTextView.startAnimation(myanim);
	}

	public void verifyCredentials(String s) {

		if (!TextUtils.isEmpty(s)) {
			Gson gson = new Gson();

			JsonObject jsonObject;

			try {
				jsonObject = gson.fromJson(s, JsonObject.class);

				if (jsonObject.has("data")) {

					JsonObject dataObject = jsonObject.get("data").getAsJsonObject();
					Log.d("RegisterFirstLoading", "Data exists" + dataObject);

					if (dataObject != null) {
						if (dataObject.has("studregEmail") && dataObject.has("studregmobileNum") && dataObject.has("studregId")) {
							String emailAddress = dataObject.get("studregEmail").getAsString();
							String mobileNumber = dataObject.get("studregmobileNum").getAsString();
							int studentsId = dataObject.get("studregId").getAsInt();

							//Log.d("RegisterFirstLoading", "Checking of Data + " + emailAddress + " " + mobileNumber + " " + studentsId);

							Intent intent = new Intent(RegisterFirstLoading.this, RegisterSecond.class);
							intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS, emailAddress);
							intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER, mobileNumber);
							intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, studentsId);

							startActivity(intent);
							finish();
						} else {
							Toast.makeText(this, "Incomplete data found. Please try again",
									Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(RegisterFirstLoading.this, RegisterFirst.class);
							startActivity(intent);
							finish();
						}
					} else {
						Toast.makeText(this, "No data found. Please try again",
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(RegisterFirstLoading.this, RegisterFirst.class);
						startActivity(intent);
						finish();
					}
				} else {
					Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(RegisterFirstLoading.this, RegisterFirst.class);
					startActivity(intent);
					finish();
				}
			} catch (JsonSyntaxException e) {
				e.printStackTrace();

				Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(RegisterFirstLoading.this, RegisterFirst.class);
				startActivity(intent);
				finish();
			}
		} else {
			Toast.makeText(this, "Request timeout, please try again.",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(RegisterFirstLoading.this, RegisterFirst.class);
			startActivity(intent);
			finish();
		}
	}
}







