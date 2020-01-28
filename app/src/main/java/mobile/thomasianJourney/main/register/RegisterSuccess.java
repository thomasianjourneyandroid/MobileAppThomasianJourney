package mobile.thomasianJourney.main.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import mobile.thomasianJourney.main.SecondActivity;
import mobile.thomasianJourney.main.register.utils.IntentExtrasAddresses;
import mobile.thomasianJourney.main.vieweventsfragments.R;

public class RegisterSuccess extends AppCompatActivity {
    private Button verifySuccess_continueButton;
    private LottieAnimationView registerSuccess_lottieAnimationView;

    private String mEmail, mMobile;
    private int mStudentsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success_layout);

        final Intent intent = getIntent();

        if (intent != null) {
            mEmail = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS);
            mMobile = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER);
            mStudentsId = intent.getIntExtra(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, -1);

            initializeViews();
        }
    }

    private void initializeViews() {
        HorizontalStepView stepview = findViewById(R.id.registerSuccess_stepView);

        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("",1);
        StepBean stepBean1 = new StepBean("",1);
        StepBean stepBean2 = new StepBean("",1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);

        stepview
                .setStepViewTexts(stepsBeanList)
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(RegisterSuccess.this, android.R.color.black))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(RegisterSuccess.this, R.color.uncompleted_text_color))
                .setStepViewComplectedTextColor(ContextCompat.getColor(RegisterSuccess.this, android.R.color.black))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(RegisterSuccess.this, R.color.uncompleted_text_color))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(RegisterSuccess.this, R.drawable.ic_check_black))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(RegisterSuccess.this, R.drawable.ic_radio))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(RegisterSuccess.this, R.drawable.tiger_rar));

        registerSuccess_lottieAnimationView = findViewById(R.id.registerSuccess_lottieAnimationView);

        registerSuccess_lottieAnimationView.setScale(6f);
        registerSuccess_lottieAnimationView.setVisibility(View.VISIBLE);
        registerSuccess_lottieAnimationView.setAnimation(R.raw.check);
        registerSuccess_lottieAnimationView.playAnimation();

        verifySuccess_continueButton = findViewById(R.id.verifySuccess_continueButton);
        verifySuccess_continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCredentialsToSharedPreferences(mEmail, mMobile, mStudentsId);

                Intent i = new Intent(RegisterSuccess.this, SecondActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void saveCredentialsToSharedPreferences(String emailAddress, String mobileNumber, int studentsId) {

        SharedPreferences sharedPreferences = getSharedPreferences("mobile.thomasianJourney.main.register.USER_CREDENTIALS", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS, emailAddress);
        editor.putString(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER, mobileNumber);
        editor.putInt(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, studentsId);

        editor.apply();
    }
}
