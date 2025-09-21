package com.jakir.codeview;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.jakir.pref.Pref;

//
// Created by JAKIR HOSSAIN on 9/21/2025.
//
public class BaseActivity extends AppCompatActivity {
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int themeInt = Pref.getState(Key.isNight, this) ? 2 : 1;
        AppCompatDelegate.setDefaultNightMode(themeInt); //set theme
        //or
//        setTheme(Pref.getState(Key.isNight, this) ? lib.jakir.codeview.R.style.Theme_CodeViewDark : lib.jakir.codeview.R.style.Theme_CodeViewLight);
    }
}
