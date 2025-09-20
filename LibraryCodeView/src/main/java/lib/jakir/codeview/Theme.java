package lib.jakir.codeview;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;
import java.util.List;

public class Theme {

    public static final Theme ANDROIDSTUDIO = new Theme("androidstudio");
    public static final Theme HYBRID = new Theme("hybrid");
    public static final Theme TOMORROW = new Theme("tomorrow");
    public static final Theme TOMORROW_NIGHT = new Theme("tomorrow-night");
    public static final Theme TOMORROW_NIGHT_BRIGHT = new Theme("tomorrow-night-bright");
    public static final Theme VS = new Theme("vs");

    public static final List<Theme> ALL = Arrays.asList(ANDROIDSTUDIO, HYBRID, TOMORROW, TOMORROW_NIGHT, TOMORROW_NIGHT_BRIGHT, VS);

    private final String name;

    public Theme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return "file:///android_asset/highlightjs/styles/" + getName() + ".css";
    }

    public void dialog_show(Context context, CodeView mCodeView) {
        String[] themeNames = new String[Theme.ALL.size()];
        for (int i = 0; i < Theme.ALL.size(); i++) {
            themeNames[i] = i + 1 + ".  " + Theme.ALL.get(i).getName();
        }

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("Select a Theme");
        builder.setItems(themeNames, (dialog, which) -> {
            Theme selectedTheme = Theme.ALL.get(which);
            mCodeView.setTheme(selectedTheme).apply();
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
