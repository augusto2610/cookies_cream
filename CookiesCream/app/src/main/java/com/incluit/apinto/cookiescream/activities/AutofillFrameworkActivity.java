package com.incluit.apinto.cookiescream.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.incluit.apinto.cookiescream.R;

public class AutofillFrameworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autofill_framework);
        initViews();
    }

    private void initViews() {
        TextView phoneTextView = findViewById(R.id.phone_field);
        phoneTextView.setAutofillHints(View.AUTOFILL_HINT_PHONE);
    }
}
