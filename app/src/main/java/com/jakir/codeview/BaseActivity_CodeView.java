package com.jakir.codeview;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jakir.pref.Pref;

//
// Created by JAKIR HOSSAIN on 9/21/2025.
//
public class BaseActivity_CodeView extends AppCompatActivity {
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // only local theme for activity
        getDelegate().setLocalNightMode(Pref.getTheme_custom(Key.activityTheme_codeview,this)); //set theme
    }
}
