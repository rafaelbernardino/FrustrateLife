package com.bernardino.frustratelife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bernardino.frustratelife.persistence.bean.User;
import com.bernardino.frustratelife.persistence.dao.DaoUser;

public class LoginAcitivity extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText etUser;
    EditText etPassword;
    CheckBox cbKeepConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);
        sp = getSharedPreferences(Constants.MY_SHARED_PREFERENCES, MODE_PRIVATE);

        //Se estiver marcado como logado, pular direto para a tela principal
        if (sp.getBoolean(Constants.KEEP_CONNECTED, false)) {
            Log.i(Constants.TAG_LOG, "Mantenha conectado é true");
            Intent i = new Intent(this, MainActivity.class);
            finish();
            startActivity(i);
        }

        etUser = (EditText) findViewById(R.id.etUserId);
        etPassword = (EditText) findViewById(R.id.etPasswordId);
        cbKeepConnected = (CheckBox) findViewById(R.id.cbKeepConnectedId);
    }

    public void login(View view) {
        if (etUser == null || etUser.getText().toString().trim().equals("")
                || etPassword == null || etPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.MSG_USR_PASS_FILL, Toast.LENGTH_LONG).show();
        } else {

            //BUSCAR REGISTRO DE USUARIO NO BANCO
            try {
                User user = DaoUser.getUser(this);

                if (user != null) {
                    if (user.getUser().equals(etUser.getText().toString())
                            && user.getPass().equals(etPassword.getText().toString())) {
                        editor = sp.edit();
                        editor.putBoolean(Constants.KEEP_CONNECTED, cbKeepConnected.isChecked());
                        editor.commit();

                        Intent i = new Intent(this, MainActivity.class);
                        finish();
                        startActivity(i);
                    } else {
                        Toast.makeText(this, getString(R.string.user_pass_incorrect), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, getString(R.string.ERROR_TRY_AGAIN_LATER), Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.e(Constants.TAG_LOG, getString(R.string.ERROR_LOGIN));
            }
        }
    }
}
