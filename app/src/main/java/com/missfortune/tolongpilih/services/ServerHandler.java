package com.missfortune.tolongpilih.services;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;

import com.missfortune.tolongpilih.config.Globals;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//https://stackoverflow.com/questions/35390928/how-to-send-json-object-to-the-server-from-my-android-app

public class ServerHandler extends AsyncTask<String, Void, JSONObject> {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected JSONObject doInBackground(String... params) {
        String data = "";

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes("PostData=" + params[1]);
            wr.flush();
            wr.close();

            InputStream in;
            if (httpURLConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                in = httpURLConnection.getInputStream();
            } else {
                in = httpURLConnection.getErrorStream();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        JSONObject result = new JSONObject();
        try {
            result.put("code", httpURLConnection.getResponseCode());
            result.put("body", data);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
