package com.example.madarsofttask.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.madarsofttask.R;
import com.example.madarsofttask.model.UserModel;
import com.example.madarsofttask.model.db.AppDatabase;
import com.example.madarsofttask.model.db.AppExecutors;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.madarsofttask.util.HelperMethod.AutoComplete;
import static com.example.madarsofttask.util.HelperMethod.Validate;
import static com.example.madarsofttask.util.HelperMethod.ValidateAuto;
import static com.example.madarsofttask.util.HelperMethod.HideSoftKeyboard;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name_editText)
    EditText name_editText;
    @BindView(R.id.age_editText)
    EditText age_editText;
    @BindView(R.id.jobTitle_editText)
    EditText jobTitle_editText;
    @BindView(R.id.gender_autoTxt)
    AutoCompleteTextView gender_autoTxt;

    List<String> genderList = new ArrayList<>();

    private AppDatabase appDatabase;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ButterKnife.bind(this);

        genderList.add("Male");
        genderList.add("Female");
        AutoComplete(gender_autoTxt, genderList, MainActivity.this);

        //Room DB
        appDatabase = AppDatabase.getsInstance(MainActivity.this);

        //ProgressDialog
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Saving...");
        progressDialog.setCancelable(false);
    }

    private void saveDataToDb() {

        String name = name_editText.getText().toString();
        int age = Integer.parseInt(age_editText.getText().toString());
        String jobTitle = jobTitle_editText.getText().toString();
        String gender = gender_autoTxt.getText().toString();

        UserModel userModel = new UserModel(name, age, jobTitle, gender);

        AppExecutors.getInstance().getDiskIO().execute(() -> {
                    appDatabase.UserDao().insertInvoice(userModel);
                    progressDialog.cancel();
                }
        );
    }

    @OnClick(R.id.saveData_btn)
    public void saveDataBtn() {
        if (Validate(name_editText) && Validate(age_editText) &&
                Validate(jobTitle_editText) && ValidateAuto(gender_autoTxt)) {
            progressDialog.show();
            HideSoftKeyboard(MainActivity.this);
            saveDataToDb();
        }
    }

    @OnClick(R.id.see_saved_users)
    public void seeSavedUsers() {
        startActivity(new Intent(MainActivity.this, UsersListActivity.class));
    }
}