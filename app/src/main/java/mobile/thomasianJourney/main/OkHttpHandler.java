package mobile.thomasianJourney.main;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient;

public class OkHttpHandler extends AsyncTask<String, Void, String> {

    public String registerUrl = "https://thomasianjourney.website/register/registerUser";
    OkHttpClient client;
/*
    public OkHttpHandler( String email,String mobileno){

        //this.client = c;
        this.client = new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                .build();
        this.doInBackground(email, mobileno,registerUrl);

    }
*/
    @Override
    protected String doInBackground(String... params) {
        return doInBackgroundTask(params[1], params[2], params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //verifyCredentials(s);
    }

    public String doInBackgroundTask(String emailAddress, String mobileNumber, String url) {
        try {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("registerFirst_emailAddress", emailAddress)
                    .addFormDataPart("registerFirst_mobileNumber", mobileNumber)
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url(url)
                    .post(requestBody);
            Request request = builder.build();

            Response response = client.newCall(request).execute();

            System.out.print("Response: " + response.code());

            if (response.isSuccessful()) {

                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("data:", emailAddress + "  and  "+mobileNumber);
//            if (counter < 5) {
//                System.out.println("Counter is: " + counter);
//                counter++;
//                doInBackgroundTask(registerFirst_emailAddress, registerFirst_mobileNumber, url);
//            } else {
//                // five failed attempts
//            }
        }

        return "";
    }
    public void openVerifyCode(String s) {

        if (!TextUtils.isEmpty(s)) {

            Gson gson = new Gson();

            JsonObject jsonObject = new JsonObject();
            try {
                jsonObject = gson.fromJson(s, JsonObject.class);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();

                /*Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterFirstLoading.this, RegisterFirst.class);
                startActivity(intent);
                finish();*/
            }

            String email;
            String mobileNumber;
            int studentsId;

            if (jsonObject.has("data")) {

                JsonObject dataObject = jsonObject.get("data").getAsJsonObject();

                email = dataObject.get("studEmail").getAsString();
                mobileNumber = dataObject.get("studMobileNumber").getAsString();
                studentsId = dataObject.get("studentsId").getAsInt();
/*
                Intent intent = new Intent(RegisterFirstLoading.this, RegisterSecond.class);
                intent.putExtra("email", email);
                intent.putExtra("mobile", registerFirst_mobileNumber);
                intent.putExtra("studentsId", studentsId);
                startActivity(intent);
                finish();*/
            } else {
                /*Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterFirstLoading.this, RegisterFirst.class);
                startActivity(intent);
                finish();*/
            }
        }
    }

}


