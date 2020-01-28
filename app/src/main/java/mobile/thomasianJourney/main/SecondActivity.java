package mobile.thomasianJourney.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.text.DateFormat;
import java.util.Calendar;

import mobile.thomasianJourney.main.EventDetails;
import mobile.thomasianJourney.main.MainActivity;
import mobile.thomasianJourney.main.MenuPortfolio;
import mobile.thomasianJourney.main.interfaces.AsyncResponse;
import mobile.thomasianJourney.main.register.async.RegisterFirstAsync;
import mobile.thomasianJourney.main.register.async.StudentDetails;
import mobile.thomasianJourney.main.register.utils.IntentExtrasAddresses;
import mobile.thomasianJourney.main.vieweventsfragments.R;


public class SecondActivity extends AppCompatActivity {

    ImageView img_help;

    CardView eventId;
    CardView portId;
    CardView stream1;

    Button streambtn1;

    NestedScrollView scrollhelp;

    LinearLayout layouthelp;

    TextView home_studentNumber, home_totalPoints, home_currentDate, home_Welcome;
    Dialog dialog_help;
    ImageView closeDialogHelp, img_help1;

    TextView txtContent1, txtContent2, txtContent3, txtContent4, txtContent5, txtContent6;
    Animation animationUp, animationUp1, animationUp2, animationUp3, animationUp4,animationUp5, animationUp6;
    Animation animationDown, animationDown1, animationDown2, animationDown3, animationDown4, animationDown5, animationDown6 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        home_studentNumber = findViewById(R.id.home_studentNumber);
        home_totalPoints = findViewById(R.id.home_totalPoints);
        home_currentDate = findViewById(R.id.home_currentDate);
        home_Welcome = findViewById(R.id.studname);
        img_help = findViewById(R.id.img_help);


        Intent intent = getIntent();

        home_studentNumber.setText(getStudentId() + "");
        home_totalPoints.setText("");
        home_currentDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime()));


        SharedPreferences sharedPreferences = getSharedPreferences("mobile.thomasianJourney.main" +
                ".register.USER_CREDENTIALS", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();

        img_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  ShowDialogHelp();
            }
        });

        if (sharedPreferences != null) {
            //String email = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_EMAIL_ADDRESS);
//            String studentId = intent.getStringExtra(IntentExtrasAddresses.INTENT_EXTRA_STUDENTS_ID);
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

                            home_studentNumber.setText("Student Number: "+studentsId + "");
                            home_totalPoints.setText("Accumulated Point/s: "+studPoints+"");
                            home_Welcome.setText("Welcome, "+studname);

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
                } else {
                    Toast.makeText(this, "No object",
                    Toast.LENGTH_SHORT).show();
                }
            } catch (JsonSyntaxException e) {
                //Toast.makeText(this, "Json Syntax",
                        //Toast.LENGTH_SHORT).show();
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


    private int getStudentId() {

        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);

        String email = sharedPreferences.getString("email", "");
        String mobile = sharedPreferences.getString("mobile", "");
        int studentId = sharedPreferences.getInt("studentsId", -1);

        if (!email.isEmpty() && !mobile.isEmpty())
            return studentId;
        else
            return -1;
    }

    /*public void ShowDialogHelp() {
        dialog_help.setContentView(R.layout.dialog_help);
        closeDialogHelp = (ImageView) dialog_help.findViewById(R.id.closeDialogHelp);
        txtContent1 = (TextView) dialog_help.findViewById(R.id.title_text1);
        TextView txtTitle1 = (TextView) dialog_help.findViewById(R.id.content_text1);
        txtContent1.setVisibility(View.GONE);

        animationUp1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent1.isShown()){
                    txtContent1.setVisibility(View.GONE);
                    txtContent1.startAnimation(animationUp1);
                }
                else{
                    txtContent1.setVisibility(View.VISIBLE);
                    txtContent1.startAnimation(animationDown1);
                }
            }
        });

        // help 2
        txtContent2 = (TextView) dialog_help.findViewById(R.id.title_text2);
        TextView txtTitle2 = (TextView) dialog_help.findViewById(R.id.content_text2);
        txtContent2.setVisibility(View.GONE);

        animationUp2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent2.isShown()){
                    txtContent2.setVisibility(View.GONE);
                    txtContent2.startAnimation(animationUp2);
                }
                else{
                    txtContent2.setVisibility(View.VISIBLE);
                    txtContent2.startAnimation(animationDown2);
                }
            }
        });

        // help 3
        txtContent3 = (TextView) dialog_help.findViewById(R.id.title_text3);
        TextView txtTitle3 = (TextView) dialog_help.findViewById(R.id.content_text3);
        txtContent3.setVisibility(View.GONE);

        animationUp3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent3.isShown()){
                    txtContent3.setVisibility(View.GONE);
                    txtContent3.startAnimation(animationUp3);
                }
                else{
                    txtContent3.setVisibility(View.VISIBLE);
                    txtContent3.startAnimation(animationDown3);
                }
            }
        });

        // help 4
        txtContent4 = (TextView) dialog_help.findViewById(R.id.title_text4);
        TextView txtTitle4 = (TextView) dialog_help.findViewById(R.id.content_text4);
        txtContent4.setVisibility(View.GONE);

        animationUp4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent4.isShown()){
                    txtContent4.setVisibility(View.GONE);
                    txtContent4.startAnimation(animationUp4);
                }
                else{
                    txtContent4.setVisibility(View.VISIBLE);
                    txtContent4.startAnimation(animationDown4);
                }
            }
        });

        // help 5
        txtContent5 = (TextView) dialog_help.findViewById(R.id.title_text5);
        TextView txtTitle5 = (TextView) dialog_help.findViewById(R.id.content_text5);
        txtContent5.setVisibility(View.GONE);

        animationUp5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent5.isShown()){
                    txtContent5.setVisibility(View.GONE);
                    txtContent5.startAnimation(animationUp5);
                }
                else{
                    txtContent5.setVisibility(View.VISIBLE);
                    txtContent5.startAnimation(animationDown5);
                }
            }
        });

        // help 6
        txtContent6 = (TextView) dialog_help.findViewById(R.id.title_text6);
        TextView txtTitle6 = (TextView) dialog_help.findViewById(R.id.content_text6);
        txtContent6.setVisibility(View.GONE);

        animationUp6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent6.isShown()){
                    txtContent6.setVisibility(View.GONE);
                    txtContent6.startAnimation(animationUp6);
                }
                else{
                    txtContent6.setVisibility(View.VISIBLE);
                    txtContent6.startAnimation(animationDown6);
                }
            }
        });




        closeDialogHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_help.dismiss();
            }


        });

        dialog_help.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_help.show();


    }*/



//    public void ShowDialogHelp() {
//        dialog_help.setContentView(R.layout.dialog_help);
//        closeDialogHelp = (ImageView) dialog_help.findViewById(R.id.closeDialogHelp);
////        title_text1 = (TextView) dialog_help.findViewById(R.id.title_text1);
////        title_text2 = (TextView) dialog_help.findViewById(R.id.title_text2);
////        title_text3 = (TextView) dialog_help.findViewById(R.id.title_text3);
////        title_text4 = (TextView) dialog_help.findViewById(R.id.title_text4);
////        title_text5 = (TextView) dialog_help.findViewById(R.id.title_text5);
////        title_text6 = (TextView) dialog_help.findViewById(R.id.title_text6);
////        content_text1 = (TextView) dialog_help.findViewById(R.id.content_text1);
////        content_text2 = (TextView) dialog_help.findViewById(R.id.content_text2);
////        content_text3 = (TextView) dialog_help.findViewById(R.id.content_text3);
////        content_text4 = (TextView) dialog_help.findViewById(R.id.content_text4);
////        content_text5 = (TextView) dialog_help.findViewById(R.id.content_text5);
////        content_text6 = (TextView) dialog_help.findViewById(R.id.content_text6);
////        txthelp = (TextView) dialog_help.findViewById(R.id.txthelp);
////        scrollhelp = (NestedScrollView) dialog_help.findViewById(R.id.scrollhelp);
////        layouthelp = (LinearLayout) dialog_help.findViewById(R.id.layouthelp);
//        txtContent1 = (TextView) dialog_help.findViewById(R.id.title_text1);
//        TextView txtTitle1 = (TextView) dialog_help.findViewById(R.id.content_text1);
//        txtContent1.setVisibility(View.GONE);
//
//        animationUp1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        animationDown1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//
//        txtTitle1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtContent1.isShown()){
//                    txtContent1.setVisibility(View.GONE);
//                    txtContent1.startAnimation(animationUp1);
//                }
//                else{
//                    txtContent1.setVisibility(View.VISIBLE);
//                    txtContent1.startAnimation(animationDown1);
//                }
//            }
//        });
//
//        // help 2
//        txtContent2 = (TextView) dialog_help.findViewById(R.id.title_text2);
//        TextView txtTitle2 = (TextView) dialog_help.findViewById(R.id.content_text2);
//        txtContent2.setVisibility(View.GONE);
//
//        animationUp2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        animationDown2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//
//        txtTitle2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtContent2.isShown()){
//                    txtContent2.setVisibility(View.GONE);
//                    txtContent2.startAnimation(animationUp2);
//                }
//                else{
//                    txtContent2.setVisibility(View.VISIBLE);
//                    txtContent2.startAnimation(animationDown2);
//                }
//            }
//        });
//
//        // help 3
//        txtContent3 = (TextView) dialog_help.findViewById(R.id.title_text3);
//        TextView txtTitle3 = (TextView) dialog_help.findViewById(R.id.content_text3);
//        txtContent3.setVisibility(View.GONE);
//
//        animationUp3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        animationDown3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//
//        txtTitle3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtContent3.isShown()){
//                    txtContent3.setVisibility(View.GONE);
//                    txtContent3.startAnimation(animationUp3);
//                }
//                else{
//                    txtContent3.setVisibility(View.VISIBLE);
//                    txtContent3.startAnimation(animationDown3);
//                }
//            }
//        });
//
//        // help 4
//        txtContent4 = (TextView) dialog_help.findViewById(R.id.title_text4);
//        TextView txtTitle4 = (TextView) dialog_help.findViewById(R.id.content_text4);
//        txtContent4.setVisibility(View.GONE);
//
//        animationUp4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        animationDown4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//
//        txtTitle4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtContent4.isShown()){
//                    txtContent4.setVisibility(View.GONE);
//                    txtContent4.startAnimation(animationUp4);
//                }
//                else{
//                    txtContent4.setVisibility(View.VISIBLE);
//                    txtContent4.startAnimation(animationDown4);
//                }
//            }
//        });
//
//        // help 5
//        txtContent5 = (TextView) dialog_help.findViewById(R.id.title_text5);
//        TextView txtTitle5 = (TextView) dialog_help.findViewById(R.id.content_text5);
//        txtContent5.setVisibility(View.GONE);
//
//        animationUp5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        animationDown5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//
//        txtTitle5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtContent5.isShown()){
//                    txtContent5.setVisibility(View.GONE);
//                    txtContent5.startAnimation(animationUp5);
//                }
//                else{
//                    txtContent5.setVisibility(View.VISIBLE);
//                    txtContent5.startAnimation(animationDown5);
//                }
//            }
//        });
//
//        // help 6
//        txtContent6 = (TextView) dialog_help.findViewById(R.id.title_text6);
//        TextView txtTitle6 = (TextView) dialog_help.findViewById(R.id.content_text6);
//        txtContent6.setVisibility(View.GONE);
//
//        animationUp6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        animationDown6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//
//        txtTitle6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtContent6.isShown()){
//                    txtContent6.setVisibility(View.GONE);
//                    txtContent6.startAnimation(animationUp6);
//                }
//                else{
//                    txtContent6.setVisibility(View.VISIBLE);
//                    txtContent6.startAnimation(animationDown6);
//                }
//            }
//        });
//
//        closeDialogHelp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog_help.dismiss();
//            }
//
//
//        });
//
//        dialog_help.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog_help.show();
//
//
//    }

    public void EventsAnim(View view) {
        if (view == findViewById(R.id.eventId)) {
            //open viewevents
            startActivity(new Intent(this, MainActivity.class));
            //add animation

            Animatoo.animateCard(this);
        }
    }
    public void PortAnim(View view) {
        if (view == findViewById(R.id.portId)) {
            //open viewevents
            startActivity(new Intent(this, MenuPortfolio.class));
            //add animation
            Animatoo.animateCard(this);
        }
    }

    public void StreamAnim1(View view) {
        if (view == findViewById(R.id.stream1)) {
            //open viewevents
            startActivity(new Intent(this, EventDetails.class));
            //add animation
            Animatoo.animateCard(this);
        }

    }
}