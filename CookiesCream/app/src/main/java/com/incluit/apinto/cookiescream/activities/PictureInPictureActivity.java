package com.incluit.apinto.cookiescream.activities;

import android.app.PictureInPictureParams;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Rational;
import android.widget.VideoView;

import com.incluit.apinto.cookiescream.R;

public class PictureInPictureActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private VideoView mVideoView;

    private final PictureInPictureParams.Builder pictureInPictureParamsBuilder =
            new PictureInPictureParams.Builder();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_in_picture_mode);
        getSupportActionBar().setTitle("Picture In Picture mode");
        initViews();
    }

    private void initViews() {
        mVideoView = findViewById(R.id.video_view);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.big_buck_bunny;
        mVideoView.setVideoURI(Uri.parse(path));
    }

    private void enterInPIPMode() {
        Rational aspectRatio = new Rational(mVideoView.getWidth(), mVideoView.getHeight());
        pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
    }

    @Override
    protected void onUserLeaveHint() {
        Log.d(TAG, "onUserLeaveHint");
        if (!isInPictureInPictureMode()) {
            Log.d(TAG, "onUserLeaveHint - not in pip mode. Entering in pip.");
            enterInPIPMode();
        }
        super.onUserLeaveHint();
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        if (isInPictureInPictureMode) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @Override
    protected void onStop() {
        if (mVideoView.isPlaying()) {
            mVideoView.stopPlayback();
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @Override
    public void onBackPressed() {
        enterInPIPMode();
    }

}
