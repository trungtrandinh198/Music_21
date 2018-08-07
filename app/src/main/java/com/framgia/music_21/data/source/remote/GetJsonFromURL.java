package com.framgia.music_21.data.source.remote;

import android.os.AsyncTask;
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
    private TempData mTempData;

    GetJsonFromURL(TempData tempData) {
        mTempData = tempData;
    }

    @Override
    protected String doInBackground(String... strings) {
        return getJsonFromULR(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            mTempData.onSuccess(s);
        }
    }

    private String getJsonFromULR(String ulrString) {
        StringBuilder stringBuffer = new StringBuilder();
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
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            mTempData.onFail(e);
        } catch (ProtocolException e) {
            mTempData.onFail(e);
        } catch (IOException e) {
            mTempData.onFail(e);
        }
        return stringBuffer.toString();
    }
}
