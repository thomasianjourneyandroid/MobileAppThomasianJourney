package mobile.thomasianJourney.main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import mobile.thomasianJourney.main.vieweventsfragments.R;
import okhttp3.ConnectionSpec;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class EventDetails extends AppCompatActivity {
    private OkHttpClient client;
    private Button btnAttend;
    Dialog dialog_help;
    ImageView closeDialogHelp, img_help;
    TextView txtContent1, txtContent2, txtContent3, txtContent4, txtContent5, txtContent6;
    Animation animationUp, animationUp1, animationUp2, animationUp3, animationUp4,animationUp5, animationUp6;
    Animation animationDown, animationDown1, animationDown2, animationDown3, animationDown4, animationDown5, animationDown6 ;
    TextView tv_date, tv_title, tv_description, tv_venue, tv_time, tv_point;

    RelativeLayout eventdetails;
    public String url = "https://thomasianjourney.website/Register/eventTime";
    public String eventUrl = "https://thomasianjourney.website/Register/eventDetails";
    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
        setContentView(R.layout.activity_eventdetails);
        tv_date = findViewById(R.id.home_currentDate);
        tv_title = findViewById(R.id.title);
        tv_description = findViewById(R.id.description);
        tv_venue = findViewById(R.id.venue);
        tv_time = findViewById(R.id.time);
        tv_point = findViewById(R.id.point);

        OkHttpHandler2 okHttpHandler2 = new OkHttpHandler2();

        Intent i = getIntent();
        String id = i.getExtras().getString("activityId");
        String accountId = "1";
        okHttpHandler2.execute(eventUrl, id, accountId);


        btnAttend = (Button) findViewById(R.id.attend);

//        attend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openVerifyLoginCred();
//            }
//        });
        /*img_help = (ImageView) findViewById(R.id.img_help);
        img_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogHelp();
            }
        });*/

        dialog_help = new Dialog(this);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);


    }


//    public void openVerifyLoginCred() {
//        Intent intent = new Intent(this,VerifyLoginCred.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ShowDialogHelp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ShowDialogHelp() {
        dialog_help.setContentView(R.layout.dialog_help);
        closeDialogHelp = (ImageView) dialog_help.findViewById(R.id.closeDialogHelp);
//        title_text1 = (TextView) dialog_help.findViewById(R.id.title_text1);
//        title_text2 = (TextView) dialog_help.findViewById(R.id.title_text2);
//        title_text3 = (TextView) dialog_help.findViewById(R.id.title_text3);
//        title_text4 = (TextView) dialog_help.findViewById(R.id.title_text4);
//        title_text5 = (TextView) dialog_help.findViewById(R.id.title_text5);
//        title_text6 = (TextView) dialog_help.findViewById(R.id.title_text6);
//        content_text1 = (TextView) dialog_help.findViewById(R.id.content_text1);
//        content_text2 = (TextView) dialog_help.findViewById(R.id.content_text2);
//        content_text3 = (TextView) dialog_help.findViewById(R.id.content_text3);
//        content_text4 = (TextView) dialog_help.findViewById(R.id.content_text4);
//        content_text5 = (TextView) dialog_help.findViewById(R.id.content_text5);
//        content_text6 = (TextView) dialog_help.findViewById(R.id.content_text6);
//        txthelp = (TextView) dialog_help.findViewById(R.id.txthelp);
//        scrollhelp = (NestedScrollView) dialog_help.findViewById(R.id.scrollhelp);
//        layouthelp = (LinearLayout) dialog_help.findViewById(R.id.layouthelp);
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


    }
    public class OkHttpHandler extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                .build();


        @Override
        protected String doInBackground(String... params) {

            try {
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("activityId", params[1])
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0])
                        .post(requestBody);
                Request request = builder.build();

                Response response = client.newCall(request).execute();

                System.out.print("Response: " + response.code());

                if (response.isSuccessful()) {

                    return response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            textView.setText(s);
            openVerifyLoginCred(s);
//            Toast.makeText(EventDetails.this, ""+s, Toast.LENGTH_SHORT).show();

        }
    }
    public class OkHttpHandler2 extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                .build();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("activityId", params[1])
                        .addFormDataPart("accountId", params[2])
                        .build();

                Request.Builder builder = new Request.Builder();
                builder.url(params[0])
                        .post(requestBody);
                Request request = builder.build();

                Response response = client.newCall(request).execute();

                System.out.print("Response: " + response.code());

                if (response.isSuccessful()) {

                    return response.body().string();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.hide();
            getEventDetails(s);


        }
    }


    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy-MM-d");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getEventDetails(String s){
        dialog.hide();
        if(!TextUtils.isEmpty(s)){
            try {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
                if(jsonObject.has("data")){
                    JsonObject dataObject = jsonObject.get("data").getAsJsonObject();
                    String activityName = dataObject.get("activityName").getAsString();
                    String eventVenue = dataObject.get("eventVenue").getAsString();
                    String eventDate = dataObject.get("eventDate").getAsString();
                    String description = dataObject.get("description").getAsString();
                    String eventendDate = dataObject.get("eventendDate").getAsString();
                    String points = dataObject.get("points").getAsString();
                    String attend = dataObject.get("attend").getAsString();

                    String startTime = eventDate.split(" ")[1];
                    String endTime = eventendDate.split(" ")[1];
                    String splittedTime = startTime.split(":")[0] + ":" + startTime.split(":")[1];
                    String splittedEndTime = endTime.split(":")[0] + ":" + endTime.split(":")[1];
                    DateFormat dfTime = new SimpleDateFormat("hh:mm a");
                    SimpleDateFormat target = new SimpleDateFormat("h:mm a");
                    SimpleDateFormat source = new SimpleDateFormat("HH:mm");
                    String formattedTime = target.format(source.parse(splittedTime));
                    String formattedEndTime = target.format(source.parse(splittedEndTime));

                    String date = eventDate.split(" ")[0];
                    String month = date.split("-")[1];

                    String day = date.split("-")[2];
                    switch(month){
                        case "01":
                            month = "Jan";
                            break;
                        case "02":
                            month = "Feb";
                            break;
                        case "03":
                            month = "Mar";
                            break;
                        case "04":
                            month = "April";
                            break;
                        case "05":
                            month = "May";
                            break;
                        case "06":
                            month = "Jun";
                            break;
                        case "07":
                            month = "Jul";
                            break;
                        case "08":
                            month = "Aug";
                            break;
                        case "09":
                            month = "Sep";
                            break;
                        case "10":
                            month = "Oct";
                            break;
                        case "11":
                            month = "Nov";
                            break;
                        case "12":
                            month = "Dec";
                            break;

                    }

                    tv_date.setText(month + "\n" + day);
                    tv_title.setText(activityName);
                    tv_venue.setText(eventVenue);
                    tv_time.setText(formattedTime + " : " +formattedEndTime);
                    tv_description.setText(description);
                    tv_point.setText(points + " Points");
                    btnAttend = findViewById(R.id.attend);
                    if(attend.equals("1")){
                        btnAttend.setVisibility(View.INVISIBLE);
                    }



                }
            }catch (Exception err){
                Toast.makeText(this, ""+err, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void openVerifyLoginCred(String s)
    {
        RelativeLayout eventdetails = findViewById(R.id.eventdetails);
        if (!TextUtils.isEmpty(s)) {
//            Toast.makeText(EventDetails.this, ""+s, Toast.LENGTH_SHORT).show();
            try{
                Gson gson = new Gson();

                JsonObject jsonObject = gson.fromJson(s, JsonObject.class);


                if  (jsonObject.has("data")) {

                    JsonObject dataObject = jsonObject.get("data").getAsJsonObject();

                    String startDate = dataObject.get("startDate").getAsString();
                    String endDate = dataObject.get("endDate").getAsString();

                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date start = formatter.parse(startDate);
                    Date end = formatter.parse(endDate);
                    Date current = formatter.parse(getDate());

                    if(current.before(start)){
                        Snackbar snackbar =
                                Snackbar.make(eventdetails, "Event not yet available.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }else if(current.after(end)){
                        Snackbar snackbar =
                                Snackbar.make(eventdetails, "Event no longer available.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }else{
                        Snackbar snackbar =
                                Snackbar.make(eventdetails, "Event available.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent i = getIntent();
                        String id = i.getExtras().getString("activityId");

                        Intent intent = new Intent(this, GPSActivity.class);
                        intent.putExtra("activityId", id);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(this, "No data found.", Toast.LENGTH_SHORT).show();
                }

            }catch(Exception err){
                Toast.makeText(this, ""+err, Toast.LENGTH_SHORT).show();
            }
        }
    }

    //ANIMATION

    public void ScanAnim(View view) {
        if (view == findViewById(R.id.attend)) {
            btnAttend = findViewById(R.id.attend);
            btnAttend.setClickable(false);
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            //DITO PAPASOK YUNG ID NG EVENT SA VIEW EVENTS
            Intent i = getIntent();
            String id = i.getExtras().getString("activityId");

            okHttpHandler.execute(url, id);
            //check if current time is within time range
//            if(currentTime >= startTime && currentTime <= endTime)

            //open verifylogincred
//            startActivity(new Intent(this, VerifyLoginCred.class));
//            //add animation
//            Animatoo.animateSlideLeft(this);
//            finish();
            //else
            //if(currentTime <= startTime)
            //This event has not yet started
        }
    }
}
