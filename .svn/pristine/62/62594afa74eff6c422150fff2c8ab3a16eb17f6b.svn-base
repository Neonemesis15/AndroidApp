package com.org.seratic.lucky.comunicacion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpConnector {

	String str;

	public String sendWithPOST(String parameters, String url) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);

		request.setHeader("Content-type", "text/xml");
		request.setEntity(new StringEntity(parameters));
		
		HttpResponse response = client.execute(request);
		return processServerResponse(response);

	}

	public String processServerResponse(HttpResponse response) throws IOException {
		InputStreamReader in = null;
		in = new InputStreamReader(response.getEntity().getContent(), "ISO-8859-1");

		ByteArrayOutputStream bStrm = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1)
			bStrm.write(ch);
		str = new String(bStrm.toByteArray());
		bStrm.close();

		in.close();
		return str;
	}

	public static boolean isNetworkAvailable(Context context) {
		
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {

			NetworkInfo[] info = connectivity.getAllNetworkInfo();

			if (info != null) {

				for (int i = 0; i < info.length; i++) {

					if (info[i].getState() == NetworkInfo.State.CONNECTED) {

						return true;

					}

				}

			}

		}

		return false;

	}

}