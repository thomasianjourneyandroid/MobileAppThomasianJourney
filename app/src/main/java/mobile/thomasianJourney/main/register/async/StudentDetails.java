package mobile.thomasianJourney.main.register.async;

import android.os.AsyncTask;

import java.util.Arrays;

import mobile.thomasianJourney.main.interfaces.AsyncResponse;
import okhttp3.ConnectionSpec;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StudentDetails extends AsyncTask<String, Void, String> {

    private AsyncResponse asyncResponse;

    public StudentDetails(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            RequestBody requestBody =
                    new MultipartBody.Builder().
                            setType(MultipartBody.FORM).

                            addFormDataPart("studentsId", strings[0]).
                            build();

            Request.Builder builder = new Request.Builder();
            builder.url(strings[1]).post(requestBody);
            Request request = builder.build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS)).build();
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        asyncResponse.doWhenFinished(s);
    }
}
