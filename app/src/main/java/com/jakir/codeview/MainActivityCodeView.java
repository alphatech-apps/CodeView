package com.jakir.codeview;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jakir.pref.Pref;

import lib.jakir.codeview.CodeLanguage;
import lib.jakir.codeview.CodeTheme;
import lib.jakir.codeview.CodeView;

public class MainActivityCodeView extends BaseActivity_CodeView {
    //    private static final String JAVA_CODE = "package com.example.android.bluetoothchat;\n" + "\n" + "import android.os.Bundle;\n" + "import android.support.v4.app.FragmentTransaction;\n" + "import android.view.Menu;\n" + "import android.view.MenuItem;\n" + "import android.widget.ViewAnimator;\n" + "\n" + "import com.example.android.common.activities.SampleActivityBase;\n" + "import com.example.android.common.logger.Log;\n" + "import com.example.android.common.logger.LogFragment;\n" + "import com.example.android.common.logger.LogWrapper;\n" + "import com.example.android.common.logger.MessageOnlyLogFilter;\n" + "\n" + "/**\n" + " * A simple launcher activity containing a summar og framework.\n" + "        LogWrapper logWrapper = new LogWrapper();\n" + "        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.\n" + "        Log.setLogNode(logWrapper);\n" + "\n" + "        // Filter strips out everything except the message text.\n" + "        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();\n" + "        logWrapper.setNext(msgFilter);\n" + "\n" + "        // On screen logging via a fragment with a TextView.\n" + "        LogFragment logFragment = (LogFragment) getSupportFragmentManager()\n" + "                .findFragmentById(R.id.log_fragment);\n" + "        msgFilter.setNext(logFragment.getLogView());\n" + "\n" + "        Log.i(TAG, \"Ready\");\n" + "    }\n" + "}";
    private static final String JAVA_CODE = "package com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.ospackage com.example.android.bluetoothchat;\n" + "\n" + "import android.os.Bundle;\n" + "import android.support.v4.app.FragmentTransaction;\n" + "import android.view.Menu;\n" + "import android.view.MenuItem;\n" + "import android.widget.ViewAnimator;\n" + "\n" + "import com.example.android.common.activities.SampleActivityBase;\n" + "import com.example.android.common.logger.Log;\n" + "import com.example.android.common.logger.LogFragment;\n" + "import com.example.android.common.logger.LogWrapper;\n" + "import com.example.android.common.logger.MessageOnlyLogFilter;\n" + "\n" + "/**\n" + " * A simple launcher activity containing a summar og framework.\n" + "        LogWrapper logWrapper = new LogWrapper();\n" + "        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.\n" + "        Log.setLogNode(logWrapper);\n" + "\n" + "        // Filter strips out everything except the message text.\n" + "        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();\n" + "        logWrapper.setNext(msgFilter);\n" + "\n" + "        // On screen logging via a fragment with a TextView.\n" + "        LogFragment logFragment = (LogFragment) getSupportFragmentManager()\n" + "                .findFragmentById(R.id.log_fragment);\n" + "        msgFilter.setNext(logFragment.getLogView());\n" + "\n" + "        Log.i(TAG, \"Ready\");\n" + "    }\n" + "}";
    CodeView mCodeView;
    Dialog loadingDialog;
    private boolean doubleBackPressed = false;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
        }

        mCodeView = findViewById(R.id.code_view);

        String selectedCodeTheme = Pref.getString(Key.codeTheme, this).isEmpty() ? CodeTheme.TOMORROW.getName() : Pref.getString(Key.codeTheme, this);
        mCodeView.setTheme(isDayMode() ? new CodeTheme(selectedCodeTheme) : new CodeTheme(selectedCodeTheme + "_night")).setCode(JAVA_CODE).setShowLineNumber(Pref.getState(Key.line, getApplicationContext())).setWrapLine(Pref.getStatep(Key.wrapline, getApplicationContext())).setLanguage(CodeLanguage.AUTO).setZoomEnabled(true).apply();

        mCodeView.setOnHighlightListener(new CodeView.OnHighlightListener() {
            @Override
            public void onStartCodeHighlight() {
                runOnUiThread(() -> showLoadingDialog());
            }

            @Override
            public void onFinishCodeHighlight() {
                runOnUiThread(() -> hideLoadingDialog());
            }

            @Override
            public void onFontSizeChanged(int sizeInPx) {
//                Toast.makeText(getApplicationContext(), "Font size: " + sizeInPx, Toast.LENGTH_SHORT).show();
            }
        });

        prepareLoadingDialog();
        handleBackpressed(this);
    }

    public boolean isDayMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        return nightModeFlags == Configuration.UI_MODE_NIGHT_NO;
    }

    private void handleBackpressed(Context context) {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // for doubleBackPressed function
                if (doubleBackPressed) {
                    finish(); // Exit the activity
//                    finishAffinity(); // Exit the app
                } else {
                    doubleBackPressed = true;
                    Toast.makeText(context, "Press once again to exit", Toast.LENGTH_SHORT).show();
                    new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackPressed = false, 2000);
                }
            }
        });

    }


    // Show progress dialog
    private void prepareLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new MaterialAlertDialogBuilder(this).setView(R.layout.dialog_progress_loading).setCancelable(false).create();
        }
    }

    // Show progress dialog
    private void showLoadingDialog() {
        if (loadingDialog != null && !loadingDialog.isShowing() && !isFinishing() && !isDestroyed()) {
            loadingDialog.show();
        }
    }

    // Hide progress dialog
    private void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing() && !isFinishing() && !isDestroyed()) {
            loadingDialog.dismiss();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(lib.jakir.codeview.R.menu.menu_codeview_settings, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(lib.jakir.codeview.R.id.show_line_number_action).setChecked(mCodeView.isShowLineNumber());
        menu.findItem(lib.jakir.codeview.R.id.show_wrapline_action).setChecked(mCodeView.isWrapLine());
        menu.findItem(lib.jakir.codeview.R.id.zoom_enabled_action).setChecked(mCodeView.isZoomEnabled());
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == lib.jakir.codeview.R.id.change_theme_action) {

            String[] themeOptions = getResources().getStringArray(lib.jakir.codeview.R.array.theme_options);
            String[] themeValues = getResources().getStringArray(lib.jakir.codeview.R.array.theme_values);

            int currentTheme = Pref.getTheme_custom(Key.activityTheme_codeview, this);
            int currentIndex = 0;
            for (int i = 0; i < themeValues.length; i++) {
                if (Integer.parseInt(themeValues[i]) == currentTheme) {
                    currentIndex = i;
                    break;
                }
            }

            new MaterialAlertDialogBuilder(this).setTitle("Change Theme").setSingleChoiceItems(themeOptions, currentIndex, (dialog, which) -> {
                int selectedTheme = Integer.parseInt(themeValues[which]);
                Pref.setTheme_custom(Key.activityTheme_codeview, selectedTheme, this);
                getDelegate().setLocalNightMode(selectedTheme);
                dialog.dismiss();
            }).setNegativeButton("Cancel", null).show();

            return true;
        } else if (id == lib.jakir.codeview.R.id.change_color_action) {
            new CodeTheme(Pref.getString(Key.codeTheme, this)).dialog_show(this, mCodeView, selectedTheme -> {
                String themeName = selectedTheme.getName();
                Pref.setString(Key.codeTheme, themeName, this);
                mCodeView.setTheme(isDayMode() ? new CodeTheme(themeName) : new CodeTheme(themeName + "_night")).apply();
            });
            return true;
        } else if (id == lib.jakir.codeview.R.id.show_line_number_action) {
            boolean newState = !item.isChecked();
            item.setChecked(newState);
            mCodeView.setShowLineNumber(newState).apply();
//            mCodeView.toggleLineNumber();
            Pref.setState(newState, Key.line, this);
            return true;
        } else if (id == lib.jakir.codeview.R.id.show_wrapline_action) {
//            mCodeView.toggleWrapLine(); //or > mCodeView.setWrapLine(!mCodeView.isWrapLine()).apply();
            boolean newState = !item.isChecked();
            item.setChecked(newState);
            mCodeView.setWrapLine(newState).apply();
            Pref.setStatep(newState, Key.wrapline, this);
            return true;
        } else if (id == lib.jakir.codeview.R.id.zoom_enabled_action) {
            boolean newState = !item.isChecked();
            item.setChecked(newState);
            mCodeView.setZoomEnabled(newState).apply();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
