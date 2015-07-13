package com.tangdj.hometest;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity(R.layout.activity_loading)
public class LoadingActivity extends Activity {

    @AfterViews
    public void initView() {
        background();
    }

    @Background
    public void background() {
        loadGoOn(true);
    }

    @UiThread(delay=800)
    public void loadGoOn(Boolean result) {
        if (result) {
            Intent mainview = new Intent(getApplicationContext(), MainActivity_.class);
            mainview.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainview);

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "데이터에 문제가 있어 실행 할 수 없습니다.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
