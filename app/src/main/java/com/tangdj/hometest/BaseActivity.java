package com.tangdj.hometest;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by five on 2015-07-14.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarTint();
    }

    protected void setSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setNavigationBarTintEnabled(true);
            systemBarTintManager.setStatusBarTintResource(R.color.bi_dark);
            systemBarTintManager.setNavigationBarTintResource(R.color.bi_dark);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTranslucentStatus(boolean paramBoolean) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (paramBoolean) {
            winParams.flags |=  bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        fixToolBarFontType(toolbar);
        super.setSupportActionBar(toolbar);
    }

    public void fixToolBarFontType(Toolbar toolbar) {
        if (toolbar == null) return;

        if (toolbar instanceof ViewGroup) {
            int i = 0;
            while (i < toolbar.getChildCount()) {
                View localView = toolbar.getChildAt(0);
                if (((localView instanceof TextView)) && (HomeTestApplication.sNormalTypeface != null)) {
                    ((TextView) localView).setTypeface(HomeTestApplication.sNormalTypeface);
                }
                i += 1;
            }
        }
    }
}
