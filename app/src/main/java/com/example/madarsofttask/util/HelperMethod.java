package com.example.madarsofttask.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.List;

public class HelperMethod {

    //EditText Validation
    public static boolean Validate(@NonNull EditText editText) {

        if (editText.getText().toString().equals("")) {
            editText.setError("fill this field");
            return false;
        }
        return true;
    }

    //AutoCompleteTextView Validation
    public static boolean ValidateAuto(@NonNull AutoCompleteTextView completeTextView) {

        if (completeTextView.getText().toString().equals("")) {
            completeTextView.setError("fill this field");
            return false;
        }
        return true;
    }

    //AutoComplete
    public static void AutoComplete(final AutoCompleteTextView view, List<String> list, Context context) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);

        view.setAdapter(adapter);
        view.setOnClickListener(v -> {
            view.showDropDown();
            HideSoftKeyboard((Activity) context);
        });
    }

    //remove focus
    public static void HideSoftKeyboard(Activity activity) {
        if (activity == null) return;
        if (activity.getCurrentFocus() == null) return;

        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
