package com.tangdj.hometest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tangdj.hometest.R;

import org.androidannotations.annotations.EFragment;

/**
 * Created by five on 2015-07-17.
 */
@EFragment
public class BankFragment extends Fragment {

    public static BankFragment newInstance() {
        BankFragment fragment = new BankFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank, container, false);
        return view;
    }
}
