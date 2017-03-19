package com.bernardino.frustratelife;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bernardino.frustratelife.persistence.DatabaseHelper;
import com.bernardino.frustratelife.persistence.dao.DaoUser;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {
    ImageView splashImage;
    DatabaseHelper db;
    SharedPreferences sp;
    SharedPreferences.Editor e;
    Activity splashActivity;
    AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashActivity = this;
        splashImage = (ImageView) findViewById(R.id.ivSplashId);
        db = new DatabaseHelper(this);
        sp = getSharedPreferences(Constants.MY_SHARED_PREFERENCES, MODE_PRIVATE);
        splashImage.setBackgroundResource(R.drawable.myanimation);
        frameAnimation = (AnimationDrawable) splashImage.getBackground();
        frameAnimation.start();
        LoadUserData task = new LoadUserData();
        task.execute();
    }

    private class LoadUserData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(Constants.URL_LOAD_USER);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() == 200) {
                    BufferedReader out = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = out.readLine()) != null) {
                        response.append(line);
                    }

                    conn.disconnect();

                    return response.toString();
                }
            } catch (Exception e) {
                Log.e(Constants.TAG_LOG, getString(R.string.ERROR_DATA_LOAD));
                return getString(R.string.ERROR_DATA_LOAD);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONObject response = new JSONObject(result);
                    DaoUser.deleteUser(splashActivity);
                    DaoUser.insertDataUser(splashActivity, response.getString("usuario"), response.getString("senha"));
                    if (frameAnimation.isRunning()) {
                        frameAnimation.stop();
                    }
                    Intent i = new Intent(splashActivity, LoginAcitivity.class);
                    finish();
                    startActivity(i);
                } catch (Exception e) {
                    Log.e(Constants.TAG_LOG, getString(R.string.ERROR_DATA_LOAD));
                }
            }
        }
    }
}

