package com.bernardino.frustratelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bernardino.frustratelife.persistence.bean.Frustration;
import com.bernardino.frustratelife.persistence.dao.DaoFrustration;

public class FrustrateActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etDescription;
    EditText etWhatToDo;
    Button btCreate;
    Button btEdit;
    Frustration editFrustration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frustrate);

        btCreate = (Button)findViewById(R.id.btCreateFrustrationId);
        btEdit = (Button) findViewById(R.id.btEditFrustrationId);

        etTitle = (EditText) findViewById(R.id.etTitleId);
        etDescription = (EditText) findViewById(R.id.etDescriptionId);
        etWhatToDo = (EditText) findViewById(R.id.etWhatToDoId);

        Bundle param = getIntent().getExtras();

        if (param != null && param.getString(Constants.ACTION) != null && param.getString(Constants.ACTION).equals("edit")) {
            editFrustration = param.getParcelable(Constants.OBJ_FRUSTRATION);

            etTitle.setText(editFrustration.getTitle());
            etDescription.setText(editFrustration.getDescription());
            etWhatToDo.setText(editFrustration.getWhatToDo());

            btEdit.setVisibility(View.VISIBLE);
            btCreate.setVisibility(View.GONE);
        }
    }

    public void createFrustration(View view) {
        if (etTitle == null || etTitle.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.field_title_must_filled), Toast.LENGTH_LONG).show();
            return;
        }

        if (etDescription == null || etDescription.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.field_desc_must_filled, Toast.LENGTH_LONG).show();
            return;
        }

        if (etWhatToDo == null || etWhatToDo.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.field_wtd_must_filled, Toast.LENGTH_LONG).show();
            return;
        }

        try {
            DaoFrustration.insertFrustration(this, etTitle.getText().toString(), etDescription.getText().toString(), etWhatToDo.getText().toString());
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Log.e(Constants.TAG_LOG, getString(R.string.error_create_frustration));
            Toast.makeText(this, getString(R.string.error_create_frustration), Toast.LENGTH_LONG).show();
        }
    }

    public void editFrustration(View view) {
        Log.i(Constants.TAG_LOG, "editFrustration");
        if (etTitle == null || etTitle.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.field_title_must_filled), Toast.LENGTH_LONG).show();
            return;
        }

        if (etDescription == null || etDescription.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.field_desc_must_filled, Toast.LENGTH_LONG).show();
            return;
        }

        if (etWhatToDo == null || etWhatToDo.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.field_wtd_must_filled, Toast.LENGTH_LONG).show();
            return;
        }

        try {
            editFrustration.setTitle(etTitle.getText().toString());
            editFrustration.setDescription(etDescription.getText().toString());
            editFrustration.setWhatToDo(etWhatToDo.getText().toString());
            DaoFrustration.updateFrustration(this, editFrustration);
            Intent i = new Intent(this, MainActivity.class);
            finish();
            startActivity(i);
        } catch (Exception e) {
            Log.e(Constants.TAG_LOG, getString(R.string.error_update_data));
            Toast.makeText(this, getString(R.string.error_update_data), Toast.LENGTH_LONG).show();
        }
    }
}
