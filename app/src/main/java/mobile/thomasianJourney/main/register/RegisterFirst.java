package mobile.thomasianJourney.main.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

import mobile.thomasianJourney.main.register.utils.IntentExtrasAddresses;
import mobile.thomasianJourney.main.vieweventsfragments.R;

public class RegisterFirst extends AppCompatActivity {

	private EditText registerFirst_emailAddress, registerFirst_mobileNumber;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_first_layout);


		intializeViews();
	}
	private void intializeViews() {

		// StepView initialization
		HorizontalStepView registerFirst_stepView = findViewById(R.id.registerFirst_stepView);

		List<StepBean> stepsBeanList = new ArrayList<>();
		StepBean stepBean0 = new StepBean("", 0);
		StepBean stepBean1 = new StepBean("", -1);
		StepBean stepBean2 = new StepBean("", -1);
		stepsBeanList.add(stepBean0);
		stepsBeanList.add(stepBean1);
		stepsBeanList.add(stepBean2);

		registerFirst_stepView.setStepViewTexts(stepsBeanList)//总步骤
				.setTextSize(12)//set textSize
				.setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(RegisterFirst.this, android.R.color.black)).setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(RegisterFirst.this, R.color.uncompleted_text_color)).setStepViewComplectedTextColor(ContextCompat.getColor(RegisterFirst.this, android.R.color.black)).setStepViewUnComplectedTextColor(ContextCompat.getColor(RegisterFirst.this, R.color.uncompleted_text_color)).setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(RegisterFirst.this, R.drawable.ic_check_black)).setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(RegisterFirst.this, R.drawable.ic_radio)).setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(RegisterFirst.this, R.drawable.tiger_rar));

		registerFirst_emailAddress = findViewById(R.id.registerFirst_emailAddress);
		registerFirst_mobileNumber = findViewById(R.id.registerFirst_mobileNumber);

		Button registerFirst_registerButton = findViewById(R.id.registerFirst_registerButton);
		registerFirst_registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = registerFirst_emailAddress.getText().toString();
				String mobile = registerFirst_mobileNumber.getText().toString();

				if (!email.isEmpty() && !mobile.isEmpty()) {
					Intent intent = new Intent(RegisterFirst.this, RegisterFirstLoading.class);
					intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS, email);
					intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER, mobile);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(RegisterFirst.this, "Email and Mobile number cannot be empty ", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
