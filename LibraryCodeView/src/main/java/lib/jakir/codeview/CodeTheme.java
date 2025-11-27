package lib.jakir.codeview;

import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;
import java.util.List;

public class CodeTheme {

    public static final CodeTheme ANDROIDSTUDIO = new CodeTheme("androidstudio");
    public static final CodeTheme HYBRID = new CodeTheme("hybrid");
    public static final CodeTheme TOMORROW = new CodeTheme("tomorrow");
    public static final CodeTheme TOMORROW_NIGHT = new CodeTheme("tomorrow_night");
    public static final CodeTheme TOMORROW_NIGHT_BRIGHT = new CodeTheme("tomorrow-night-bright");
    public static final CodeTheme VS = new CodeTheme("vs");

    public static final List<CodeTheme> ALL = Arrays.asList(ANDROIDSTUDIO, HYBRID, TOMORROW, VS);
    private final String name;

    public CodeTheme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return "file:///android_asset/highlightjs/styles/" + getName() + ".css";
    }
    public interface OnThemeSelectedListener {
        void onThemeSelected(CodeTheme theme);
    }

    public void dialog_show(Context context, CodeView mCodeView,OnThemeSelectedListener listener) {
        String[] themeNames = new String[CodeTheme.ALL.size()];
        for (int i = 0; i < CodeTheme.ALL.size(); i++) {
            themeNames[i] = CodeTheme.ALL.get(i).getName();
        }

        int currentIndex = 0;
        for (int i = 0; i < CodeTheme.ALL.size(); i++) {
            if (CodeTheme.ALL.get(i).getName().equalsIgnoreCase(name)) {
                currentIndex = i;
                break;
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("Select color theme");
        builder.setSingleChoiceItems(themeNames, currentIndex, (dialog, which) -> {
          CodeTheme selectedCodeTheme = CodeTheme.ALL.get(which);
            // Return selected theme name
            dialog.dismiss();
            if (listener != null) {
                listener.onThemeSelected(selectedCodeTheme);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
