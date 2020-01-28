package mobile.thomasianJourney.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import mobile.thomasianJourney.main.interfaces.AsyncResponse;
import mobile.thomasianJourney.main.register.async.StudentDetails;
import mobile.thomasianJourney.main.register.utils.IntentExtrasAddresses;
import mobile.thomasianJourney.main.vieweventsfragments.R;

public class VerifyLoginCred extends AppCompatActivity {

    private TextView vtvone ;
    private TextView vtvtwo ;
    private LottieAnimationView LottieLoad;

    Intent i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_login_cred);
        Lottie();

        vtvone = (TextView) findViewById(R.id.vtvone);
        vtvtwo = (TextView) findViewById(R.id.vtvtwo);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        vtvone.startAnimation(myanim);
        vtvtwo.startAnimation(myanim);

        i2 = new Intent(VerifyLoginCred.this, VerLoginCredSuc.class);

        Intent intent = getIntent();

        String id = intent.getExtras().getString("activityId");
        i2.putExtra("activityId", id);


        SharedPreferences sharedPreferences = getSharedPreferences("mobile.thomasianJourney.main" +
                ".register.USER_CREDENTIALS", Context.MODE_PRIVATE);
        String studID = sharedPreferences.getInt(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, 0) + "";


        int studentId = sharedPreferences.getInt(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, 0);


        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void doWhenFinished(String output) {
                verifyCredentials(output);
            }
        };

        //RegisterFirstAsync registerFirstAsync = new RegisterFirstAsync(asyncResponse);

        StudentDetails studentDetails = new StudentDetails(asyncResponse);
        Log.i("asdajs","details =  "+getString(R.string.studentDetails)+" : "+ studentId);
        Log.i("asdajs","studId =   "+ studentId);
        studentDetails.execute(studentId+"", getString(R.string.studentDetails));

    }

    public void verifyCredentials(String s) {

        if (!TextUtils.isEmpty(s)) {
            Gson gson = new Gson();

            JsonObject jsonObject;

            try {
                jsonObject = gson.fromJson(s, JsonObject.class);

                if (jsonObject.has("data")) {

                    JsonObject dataObject = jsonObject.get("data").getAsJsonObject();

                    if (dataObject != null) {
                        if (dataObject.has("studregEmail") && dataObject.has("studregmobileNum") && dataObject.has("studentsId")) {
                            String emailAddress = dataObject.get("studregEmail").getAsString();
                            String mobileNumber = dataObject.get("studregmobileNum").getAsString();
                            //int studentsId = dataObject.get("studNumber").getAsInt();
                            String studentsId = dataObject.get("studNumber").getAsString();

                            int studPoints = dataObject.get("studPoints").getAsInt();
                            String studname = dataObject.get("studregName").getAsString();

                            Log.i("wordking","Gumagana !");
                            startActivity(i2);
                             finish();


//                            Thread timer = new Thread() {
//                                public void run () {
//                                    try {
//                                        sleep(5000) ;
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    finally {
//                                        startActivity(i);
//                                        finish();
//                                    }
//                                }
//                            };
//                            timer.start();


/*
                            Intent intent = new Intent(RegisterFirstLoading.this, RegisterSecond.class);
                            intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS, emailAddress);
                            intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER, mobileNumber);
                            intent.putExtra(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID, studentsId);
*/
                        } else {
                            Toast.makeText(this, "null json",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "No data",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Toast.makeText(this, "No object",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (JsonSyntaxException e) {
                Toast.makeText(this, "Json Syntax",
                Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Cannot find Student Details",
                    Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
            //startActivity(intent);
            //finish();

        }
    }

    public void Lottie() {

        LottieLoad = findViewById(R.id.registerFirstLoading_lottieAnimationView);

        LottieLoad.setScale(7f);
        LottieLoad.setVisibility(View.VISIBLE);
        LottieLoad.setAnimation(R.raw.load);
        LottieLoad.playAnimation();

    }
}
