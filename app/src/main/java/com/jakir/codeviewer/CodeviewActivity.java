package com.jakir.codeviewer;

//
// Created by JAKIR HOSSAIN on 9/20/2025.
//

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.jakir.permissions.PermissionsRuntime_helper.REQUEST_CODE_FILESTORAGE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.OpenableColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakir.codeview.CodeLanguage;
import com.jakir.codeview.CodeTheme;
import com.jakir.codeview.CodeView;
import com.jakir.codeview.extra.Key;
import com.jakir.permissions.PermissionsRuntime;
import com.jakir.pref.Pref;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.Executors;


public class CodeviewActivity extends AppCompatActivity {
    CodeView mCodeView;
    LinearLayout add_file;
    FloatingActionButton fab;
    Dialog loadingDialog;
    RelativeLayout main;
    private boolean doubleBackPressed = false;
    private ActionBar actionBar;
    private String codeFileName;
    private Uri codeUri;
    private String codeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getDelegate().setLocalNightMode(Pref.getTheme_custom(Key.codeActivityTheme_codeview, this)); //set theme
        setContentView(R.layout.activity_codeview);
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
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.round_arrow_back_ios_24);
        }
        add_file = findViewById(R.id.add_file);
        fab = findViewById(R.id.fab);
        mCodeView = findViewById(R.id.code_view);
        main = findViewById(R.id.main);

        prepareLoadingDialog();
        checkPermissionThenGo();
        handleOnBackpressed(this);
    }

    private void handleOnBackpressed(Context context) {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // for doubleBackPressed function
                if (doubleBackPressed) {
//                    finishAffinity(); // Exit the app
                    finish(); // Exit the activity
                } else {
                    doubleBackPressed = true;
                    Toast.makeText(context, "Press once again to exit", Toast.LENGTH_SHORT).show();
                    new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackPressed = false, 2000);
                }
            }
        });

    }

    private void checkPermissionThenGo() {
        // Permission storage for sdk 30 or above
        if (!PermissionsRuntime.checkFileStoragePermission(this)) {
            PermissionsRuntime.requestFileStoragePermission(this, "", true, null);
        } else {
            getDataFromIntent();
        }
    }

    private void getDataFromIntent() {
        if (getIntent() != null) {
            String codeFilePath = getIntent().getStringExtra(Key.path);
            Uri codeUri_intent = getUriFromPath(codeFilePath);
            Uri codeUri_view = getIntent().getData(); //ACTION_VIEW
            Uri codeUri_send = getIntent().getParcelableExtra(Intent.EXTRA_STREAM); //ACTION_SEND
            codeString = getIntent().getStringExtra(Key.codeString);

            codeUri = (codeUri_intent != null ? codeUri_intent : (codeUri_view != null ? codeUri_view : (codeUri_send)));

            if (codeUri != null) {
                if (codeFileName == null) codeFileName = getFileNameFromUri(codeUri);
                fab.setVisibility(GONE);
                if (actionBar != null) {
                    actionBar.setTitle(getString(R.string.app_name));
                    actionBar.setSubtitle(codeFileName);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeAsUpIndicator(R.drawable.round_arrow_back_ios_24);
                }
                loadCodeFromFile(codeUri);
            } else if (codeString != null && !codeString.isEmpty()) {
                showCodeOnCodeView(codeString);
            } else {
                enableFilePicker();
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    private void enableFilePicker() {
        add_file.setVisibility(VISIBLE);
        mCodeView.setVisibility(GONE);
        add_file.setOnClickListener(v -> openSingleFilePicker());
        fab.setOnClickListener(v -> openSingleFilePicker());
    }

    private void openSingleFilePicker() {
        String[] mimeTypes = {"image/svg+xml", "application/xml", "text/xml", "text/html", "multipart/related", "text/css", "application/javascript", "application/json", "text/plain", "text/x-java-source", "text/x-csrc", "text/x-c++src", "text/x-python", "text/javascript"};

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*"); // must set */* if using EXTRA_MIME_TYPES
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityIfNeeded(intent, 101);

    }

    private void loadCodeFromFile(Uri uri) {
        if (uri != null) {
            try {
                startConversion(getContentResolver().openInputStream(uri));
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load SVG", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startConversion(final InputStream inputStream) {
        // Step 1: Show ProgressDialog
        showLoadingDialog();

        // Step 2: Run conversion in background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                final String result = convertInputStreamToString(inputStream);

                // Step 3: Update UI on main thread
                runOnUiThread(() -> {
                    hideLoadingDialog();

                    add_file.setVisibility(GONE);

                    showCodeOnCodeView(result);
                });
            } catch (final Exception e) {
                runOnUiThread(this::hideLoadingDialog);
            }
        });
    }

    private void showCodeOnCodeView(String result) {
        mCodeView.setVisibility(VISIBLE);

        String selectedCodeTheme = Pref.getString(Key.codeColorTheme_codeview, this).isEmpty() ? CodeTheme.TOMORROW.getName() : Pref.getString(Key.codeColorTheme_codeview, this);
        mCodeView.setTheme(isDayMode() ? new CodeTheme(selectedCodeTheme) : new CodeTheme(selectedCodeTheme + "_night")).setCode(result).setShowLineNumber(Pref.getState(Key.line, getApplicationContext())).setWrapLine(Pref.getStatep(Key.wrapline, getApplicationContext())).setLanguage(CodeLanguage.AUTO).setZoomEnabled(true).apply();

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

    }

    private String convertInputStreamToString(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }


    private Uri getUriFromPath(String codeFilePath) {
        if (codeFilePath != null) {
            File file = new File(codeFilePath);
            return FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
        }
        return null;
    }

    private String getFileNameFromUri(Uri uri) {
        String result = null;
        if (Objects.requireNonNull(uri.getScheme()).equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (index != -1) result = cursor.getString(index);
                }
            } catch (Exception ignored) {
            }
        }
        if (result == null) result = uri.getLastPathSegment();
        return result;
    }

    private void prepareLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new MaterialAlertDialogBuilder(this).setView(com.jakir.codeview.R.layout.dialog_loading).setCancelable(false).create();
        }
    }

    private void showLoadingDialog() {
        if (loadingDialog != null && !loadingDialog.isShowing() && !isFinishing() && !isDestroyed()) {
            loadingDialog.show();
        }
    }

    private void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing() && !isFinishing() && !isDestroyed()) {
            loadingDialog.dismiss();
        }
    }

    public boolean isDayMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        return nightModeFlags == Configuration.UI_MODE_NIGHT_NO;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.jakir.codeview.R.menu.menu_codeview_settings, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(com.jakir.codeview.R.id.show_line_number_action).setChecked(mCodeView.isShowLineNumber());
        menu.findItem(com.jakir.codeview.R.id.show_wrapline_action).setChecked(mCodeView.isWrapLine());
        menu.findItem(com.jakir.codeview.R.id.zoom_enabled_action).setChecked(mCodeView.isZoomEnabled());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (codeUri == null && (codeString == null || codeString.isEmpty())) return false;
        int id = item.getItemId();
        if (id == com.jakir.codeview.R.id.change_theme_action) {

            String[] themeOptions = getResources().getStringArray(com.jakir.codeview.R.array.theme_options);
            String[] themeValues = getResources().getStringArray(com.jakir.codeview.R.array.theme_values);

            int currentTheme = Pref.getTheme_custom(Key.codeActivityTheme_codeview, this);
            int currentIndex = 0;
            for (int i = 0; i < themeValues.length; i++) {
                if (Integer.parseInt(themeValues[i]) == currentTheme) {
                    currentIndex = i;
                    break;
                }
            }

            new MaterialAlertDialogBuilder(this).setTitle("Change Theme").setSingleChoiceItems(themeOptions, currentIndex, (dialog, which) -> {
                int selectedTheme = Integer.parseInt(themeValues[which]);
                Pref.setTheme_custom(Key.codeActivityTheme_codeview, selectedTheme, this);
                getDelegate().setLocalNightMode(selectedTheme);
                dialog.dismiss();
            }).setNegativeButton("Cancel", null).show()
            ;

            return true;
        } else if (id == com.jakir.codeview.R.id.change_color_action) {
            new CodeTheme(Pref.getString(Key.codeColorTheme_codeview, this)).dialog_show(this, mCodeView, selectedTheme -> {
                String themeName = selectedTheme.getName();
                Pref.setString(Key.codeColorTheme_codeview, themeName, this);
                mCodeView.setTheme(isDayMode() ? new CodeTheme(themeName) : new CodeTheme(themeName + "_night")).apply();
            });
            return true;
        } else if (id == com.jakir.codeview.R.id.show_line_number_action) {
            boolean newState = !item.isChecked();
            item.setChecked(newState);
            mCodeView.setShowLineNumber(newState).apply();
//            mCodeView.toggleLineNumber();
            Pref.setState(newState, Key.line, this);
            return true;
        } else if (id == com.jakir.codeview.R.id.show_wrapline_action) {
//            mCodeView.toggleWrapLine(); //or > mCodeView.setWrapLine(!mCodeView.isWrapLine()).apply();
            boolean newState = !item.isChecked();
            item.setChecked(newState);
            mCodeView.setWrapLine(newState).apply();
            Pref.setStatep(newState, Key.wrapline, this);
            return true;
        } else if (id == com.jakir.codeview.R.id.zoom_enabled_action) {
            boolean newState = !item.isChecked();
            item.setChecked(newState);
            mCodeView.setZoomEnabled(newState).apply();
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_FILESTORAGE) {
            if (PermissionsRuntime.checkFileStoragePermission(this)) {
                // Permission is granted
                getDataFromIntent();

                Toast.makeText(this, "File Storage permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "File Storage permission Denied", Toast.LENGTH_SHORT).show();

                showDialogForAgain(this);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_FILESTORAGE) {
            // Delay a bit because permission screen doesn't return result directly
            new Handler().postDelayed(() -> {
                if (PermissionsRuntime.checkFileStoragePermission(this)) {
                    // Permission granted
                    Toast.makeText(this, "File Access granted", Toast.LENGTH_SHORT).show();
                    getDataFromIntent();
                } else {
                    // Permission denied
                    showDialogForAgain(this);
                }

            }, 500); // 0.5 second delay
        } else if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            codeUri = data.getData();
            if (codeUri != null) {
                codeFileName = getFileNameFromUri(codeUri);
                fab.setVisibility(VISIBLE);
                if (actionBar != null) {
                    actionBar.setTitle(getString(R.string.app_name));
                    actionBar.setSubtitle(codeFileName);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeAsUpIndicator(R.drawable.round_arrow_back_ios_24);
                }
                loadCodeFromFile(codeUri);
            }

        }
    }

    private void showDialogForAgain(Context context) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("File permission is required");
        builder.setMessage("Please allow file access permission to continue.");
        builder.setPositiveButton("Try again", (dialog, which) -> checkPermissionThenGo());
        builder.setNegativeButton("Exit", (dialog, which) -> ((Activity) context).finishAffinity());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}