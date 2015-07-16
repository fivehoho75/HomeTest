package com.tangdj.hometest;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tangdj.hometest.fragment.BankFragment;
import com.tangdj.hometest.fragment.HomeFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    private Fragment fragment;

    @AfterViews
    void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().getThemedContext();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);

        SystemBarTintManager bundle;
        if (Build.VERSION.SDK_INT >= 19) {
            setTranslucentStatus(true);
            bundle = new SystemBarTintManager(this);
            bundle.setStatusBarTintEnabled(true);
            bundle.setNavigationBarTintEnabled(true);
            bundle.setStatusBarTintResource(R.color.bi_dark);
            bundle.setNavigationBarTintResource(R.color.bi_dark);
            final int i = bundle.getConfig().getStatusBarHeight();
            toolbar.post(new Runnable() {
                public void run() {
                    toolbar.setPadding(0, i, 0, 0);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (fragment == null) {
            fragment= getSupportFragmentManager().findFragmentByTag("fragment");

            menuClick(findViewById(R.id.txt_navi_home));
            Functions.Log("onResume");
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Click({R.id.txt_navi_home, R.id.txt_navi_account_group})
    public void menuClick(View view) {
        switch (view.getId()) {
            case R.id.txt_navi_home:
                Functions.Log("home");
                loadFragment(HomeFragment.newInstance());
                break;
            case R.id.txt_navi_account_group:
                Functions.Log("home2");
                loadFragment(BankFragment.newInstance());
                break;
        }

        close();
    }

    @UiThread(delay = 200)
    public void close() {
        if (drawerLayout != null) drawerLayout.closeDrawers();
    }

    private void loadFragment(Fragment f) {
        if (fragment != null) {
            try {
                Functions.Log("remove => add" + fragment.toString());
                getSupportFragmentManager().beginTransaction().disallowAddToBackStack()
                        .remove(fragment).add(R.id.container, f, "fragment").commitAllowingStateLoss();
            } catch (Exception excption) {
                Functions.Log("error fragment");
            }
        } else {
            try {
                Functions.Log("add");
                getSupportFragmentManager().beginTransaction().disallowAddToBackStack()
                        .add(R.id.container, f, "fragment").commit();
            } catch (Exception excption) {
                String str1 = getSupportActionBar().getTitle().toString();
                Functions.Log("error fragment2");
            }

            fragment = f;
        }
    }
}
