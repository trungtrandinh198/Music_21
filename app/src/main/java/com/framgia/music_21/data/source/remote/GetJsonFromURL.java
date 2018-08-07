package com.framgia.music_21.data.source.remote;

import android.os.AsyncTask;
import com.framgia.music_21.data.source.RequestData;
import com.framgia.music_21.data.source.TempData;
import com.framgia.music_21.utils.Constaint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetJsonFromURL extends AsyncTask<String, Void, String> {
    TempData mTempData;

    public GetJsonFromURL(TempData tempData) {
        mTempData = tempData;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        data = getJsonFromULR(strings[0]);
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    private String getJsonFromULR(String ulrString) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL ulr = new URL(ulrString);
            HttpURLConnection httpURLConnection;
            httpURLConnection = (HttpURLConnection) ulr.openConnection();
            httpURLConnection.setRequestMethod(Constaint.REQUEST_METHOD);
            httpURLConnection.setReadTimeout(Constaint.READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(Constaint.CONNECT_TIMEOUT);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(ulr.openStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
