package mobile.thomasianJourney.main.register.async;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.Arrays;

import mobile.thomasianJourney.main.interfaces.AsyncResponse;
import mobile.thomasianJourney.main.register.utils.IntentExtrasAddresses;
import okhttp3.ConnectionSpec;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterFirstAsync extends AsyncTask<String, Void, String> {

	private AsyncResponse asyncResponse;

	public RegisterFirstAsync(AsyncResponse asyncResponse) {
		this.asyncResponse = asyncResponse;
	}

	@Override
	protected String doInBackground(String... strings) {
		try {
			RequestBody requestBody =
					new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(
					        "emailAddress",
							strings[0]).addFormDataPart(IntentExtrasAddresses.INTENT_EXTRA_MOBILE_NUMBER, strings[1]).build();

			Request.Builder builder = new Request.Builder();
			builder.url(strings[2]).post(requestBody);
			Request request = builder.build();

			OkHttpClient okHttpClient = new OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS)).build();

			Response response = okHttpClient.newCall(request).execute();

			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				return "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	@Override
	protected void onPostExecute(String s) {
		asyncResponse.doWhenFinished(s);
	}
}
