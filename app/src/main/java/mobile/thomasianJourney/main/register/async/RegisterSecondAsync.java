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

public class RegisterSecondAsync extends AsyncTask<String, Void, String> {

	private AsyncResponse asyncResponse;

	public RegisterSecondAsync(AsyncResponse asyncResponse) {
		this.asyncResponse = asyncResponse;
	}

	@Override
	protected String doInBackground(String... strings) {
		try {
			RequestBody requestBody =
					new MultipartBody.Builder().
							setType(MultipartBody.FORM).
							addFormDataPart("numbercode", strings[0]).
							addFormDataPart("studentsId", strings[1]).
							build();

			Request.Builder builder = new Request.Builder();
			builder.url(strings[2]).post(requestBody);
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
