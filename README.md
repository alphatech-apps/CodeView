# CodeView
### Android Code Highlighter 

[![](https://jitpack.io/v/tiagohm/CodeView.svg)](https://jitpack.io/#tiagohm/CodeView)

## Install
Add it in your root `build.gradle` at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
Add the dependency:
```gradle
compile 'com.github.alphatech-apps:CodeView:LATEST-VERSION
```

## Features
* Powered by Highlight.js
* 176 languages and 79 styles
* Day Night
* Wrap Line
* Theme
* Language Detection
* Zoom (Pinch gesture)
* Line Number
* Line Count
* Highlight current line (by click/tap)
* Highlight line
* Tap event of lines (get line number and your content)

## Usage

Add view to your layout:
```xml
    <lib.jakir.codeview.CodeView
    android:id="@+id/code_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_below="@id/toolbar" />
 ```
or full activity
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:layout_alignParentTop="true"
        android:background="@android:color/transparent"
        app:title="Settings" />

    <lib.jakir.codeview.CodeView
        android:id="@+id/code_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/toolbar" />

</RelativeLayout>
 ```
 ```java
    CodeView mCodeView = findViewById(R.id.codeView);

        mCodeView.setCode(JAVA_CODE)
                .setTheme(Theme.ARDUINO_LIGHT)
                .setLanguage(Language.AUTO)
                .setZoomEnabled(true)
                .apply();
 ```
or full >>>>
 ```java
    CodeView mCodeView = findViewById(R.id.codeView);

        mCodeView.setTheme(Theme.ARDUINO_LIGHT)
                .setCode(JAVA_CODE)
                .setLanguage(Language.AUTO)
                .setWrapLine(true)
                .setFontSize(14)
                .setZoomEnabled(true)
                .setShowLineNumber(true)
                .setStartLineNumber(1)
                .apply();
 ```

Listeners:

 ```java
 //Interface
private ProgressDialog mProgressDialog;

mCodeView.setOnHighlightListener(new CodeView.OnHighlightListener() {
    @Override
    public void onStartCodeHighlight() {
        mProgressDialog = ProgressDialog.show(getApplicationContext(), null, "Loading...", true);
    }

    @Override
    public void onFinishCodeHighlight() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
//                Toast.makeText(getApplicationContext(), "line count: " + mCodeView.getLineCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFontSizeChanged(int sizeInPx) {
//                Toast.makeText(getApplicationContext(), "Font size: " + sizeInPx, Toast.LENGTH_SHORT).show();
    }
});
 ```


## Other Methods
```java

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
        new Theme("").dialog_show(this, mCodeView);
        return true;
    } else if (id == lib.jakir.codeview.R.id.show_line_number_action) {
        boolean newState = !item.isChecked();
        item.setChecked(newState);
        mCodeView.setShowLineNumber(newState).apply();
//            mCodeView.toggleLineNumber();
        return true;
    } else if (id == lib.jakir.codeview.R.id.show_wrapline_action) {
//            mCodeView.toggleWrapLine(); //or > mCodeView.setWrapLine(!mCodeView.isWrapLine()).apply();
        boolean newState = !item.isChecked();
        item.setChecked(newState);
        mCodeView.setWrapLine(newState).apply();
        return true;
    } else if (id == lib.jakir.codeview.R.id.zoom_enabled_action) {
        boolean newState = !item.isChecked();
        item.setChecked(newState);
        mCodeView.setZoomEnabled(newState).apply();
        return true;
    } else if (id == lib.jakir.codeview.R.id.change_daynight_action) {
        boolean newState = !item.isChecked();
        item.setChecked(newState);
        if (newState) {
            mCodeView.setTheme(Theme.ANDROIDSTUDIO).apply();
        } else {
            mCodeView.setTheme(Theme.ARDUINO_LIGHT).apply();
        }
        return true;
    }

    return super.onOptionsItemSelected(item);
}
```



![](https://raw.githubusercontent.com/tiagohm/CodeView/master/1.png)
