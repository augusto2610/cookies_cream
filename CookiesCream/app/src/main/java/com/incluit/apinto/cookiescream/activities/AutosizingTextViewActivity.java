package com.incluit.apinto.cookiescream.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.incluit.apinto.cookiescream.R;

public class AutosizingTextViewActivity extends AppCompatActivity {

    private TextView mGranularTextView;
    private TextView mPresetTexrView;
    private Button mAddWordsButton;
    private Button mRemoveWordsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autosizing_text_view);
        initViews();
    }

    private void initViews() {
        mGranularTextView = findViewById(R.id.granular_autosize_textview);
        mPresetTexrView = findViewById(R.id.preset_autosize_textview);
        mAddWordsButton = findViewById(R.id.add_text_button);
        mAddWordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGranularTextView.setText(mGranularTextView.getText().toString().concat(mGranularTextView.getText().toString()));
                mPresetTexrView.setText(mPresetTexrView.getText().toString().concat(mPresetTexrView.getText().toString()));
            }
        });

        mRemoveWordsButton = findViewById(R.id.remove_text_button);
        mRemoveWordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGranularTextView.getText().toString().length() > 3) {
                    mGranularTextView.setText(mGranularTextView.getText().toString().substring(0, mGranularTextView.getText().toString().length() - 3));
                }

                if (mPresetTexrView.getText().toString().length() > 3) {
                    mPresetTexrView.setText(mPresetTexrView.getText().toString().substring(0, mPresetTexrView.getText().toString().length() - 3));
                }

            }
        });
    }
}
