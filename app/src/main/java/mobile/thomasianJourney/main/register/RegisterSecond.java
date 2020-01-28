package mobile.thomasianJourney.main.register;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

import mobile.thomasianJourney.main.interfaces.AsyncResponse;
import mobile.thomasianJourney.main.register.async.RegisterFirstAsync;
import mobile.thomasianJourney.main.register.utils.IntentExtrasAddresses;
import mobile.thomasianJourney.main.vieweventsfragments.R;


public class RegisterSecond extends AppCompatActivity {

	private TextView registerSecond_emailTextView;
	private TextView registerSecond_resendTextView;
	private Button resendDialog_okButton;
	private ImageView resendDialog_closeImageView;
	private PinEntryEditText registerSecond_verificationCodePinEntryEditText;

	private Dialog mDialogResend;

	private boolean isReadyToResend;
	private long emailRequestStart;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_second_layout);

		initializeViews();

		isReadyToResend = true;
		emailRequestStart = 300000;

		Intent intent = getIntent();

		if (intent != null) {
			final String emailAddress = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS);
			final String mobileNumber = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER);
			final int studentsId = intent.getIntExtra(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, 0);

			registerSecond_emailTextView.setText(emailAddress);

			registerSecond_resendTextView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showResendCodeDialog(emailAddress, mobileNumber);
				}
			});

			Button registerSecond_cancelButton = findViewById(R.id.registerSecond_cancelButton);
			registerSecond_cancelButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					toRegisterFirst();
				}
			});

			Button registerSecond_verifyButton = findViewById(R.id.registerSecond_verifyButton);
			registerSecond_verifyButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String verificationCode = registerSecond_verificationCodePinEntryEditText.getText().toString();
					if (!verificationCode.isEmpty()) {

						Intent intent = new Intent(RegisterSecond.this, RegisterSecondLoading.class);
						intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS, emailAddress);
						intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER, mobileNumber);
						intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, studentsId);
						intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_VERIFICATION_CODE, verificationCode);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(RegisterSecond.this, "Code is empty", Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
	}

	private void initializeViews() {
		// StepView
		HorizontalStepView stepview = findViewById(R.id.registerSecond_stepView);

		List<StepBean> stepsBeanList = new ArrayList<>();
		StepBean stepBean0 = new StepBean("", 1);
		StepBean stepBean1 = new StepBean("", 0);
		StepBean stepBean2 = new StepBean("", -1);
		stepsBeanList.add(stepBean0);
		stepsBeanList.add(stepBean1);
		stepsBeanList.add(stepBean2);

		stepview.setStepViewTexts(stepsBeanList)//总步骤
				.setTextSize(12)//set textSize
				.setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(RegisterSecond.this, android.R.color.black)).setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(RegisterSecond.this, R.color.uncompleted_text_color)).setStepViewComplectedTextColor(ContextCompat.getColor(RegisterSecond.this, android.R.color.black)).setStepViewUnComplectedTextColor(ContextCompat.getColor(RegisterSecond.this, R.color.uncompleted_text_color)).setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(RegisterSecond.this, R.drawable.ic_check_black)).setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(RegisterSecond.this, R.drawable.ic_radio)).setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(RegisterSecond.this, R.drawable.tiger_rar));

		// Lottie animation
		LottieAnimationView registerSecond_lottieAnimationView = findViewById(R.id.registerSecond_lottieAnimationView);
		registerSecond_lottieAnimationView.setScale(1.5f);
		registerSecond_lottieAnimationView.setVisibility(View.VISIBLE);
		registerSecond_lottieAnimationView.setAnimation(R.raw.mail);
		registerSecond_lottieAnimationView.playAnimation();

		mDialogResend = new Dialog(RegisterSecond.this);

		mDialogResend.setContentView(R.layout.resend_dialog_layout);

		resendDialog_closeImageView = mDialogResend.findViewById(R.id.resendDialog_closeImageView);
		resendDialog_okButton = mDialogResend.findViewById(R.id.resendDialog_okButton);

		registerSecond_resendTextView = findViewById(R.id.registerSecond_resendTextView);
		registerSecond_emailTextView = findViewById(R.id.registerSecond_emailTextView);
		registerSecond_verificationCodePinEntryEditText = findViewById(R.id.registerSecond_verificationCodePinEntryEditText);

	}

	public void showResendCodeDialog(final String email, final String mobileno) {
		resendDialog_okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resendCode(email, mobileno);
				mDialogResend.dismiss();
			}
		});

		resendDialog_closeImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDialogResend.dismiss();
			}
		});

		mDialogResend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		try {
			if (isReadyToResend && 300000 - emailRequestStart == 0) {
				isReadyToResend = false;

				Handler handler = new Handler();

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						isReadyToResend = true;
					}
				}, 300000);

				emailRequestStart = System.nanoTime();

				mDialogResend.show();
			} else {
				Toast.makeText(RegisterSecond.this, "Please wait another five minutes before requesting another verification code",
						Toast.LENGTH_LONG).show();
				mDialogResend.dismiss();
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	private void resendCode(String email, String mobileno) {
		AsyncResponse asyncResponse = new AsyncResponse() {
			@Override
			public void doWhenFinished(String output) {
				Toast.makeText(RegisterSecond.this, "New verification sent. Please wait for a few minutes before requesting again",
						Toast.LENGTH_LONG).show();
			}
		};

		RegisterFirstAsync registerFirstAsync = new RegisterFirstAsync(asyncResponse);

		registerFirstAsync.execute(email, mobileno, getString(R.string.registerURL));
	}

	public void toRegisterFirst() {
		Intent intent = new Intent(this, RegisterFirst.class);
		startActivity(intent);
		finish();
	}

}

