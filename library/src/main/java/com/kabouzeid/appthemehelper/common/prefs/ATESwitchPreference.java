package com.kabouzeid.appthemehelper.common.prefs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.kabouzeid.appthemehelper.ATH;
import com.kabouzeid.appthemehelper.R;
import com.kabouzeid.appthemehelper.ThemeStore;

import java.lang.reflect.Field;

/**
 * @author Aidan Follestad (afollestad)
 */
public class ATESwitchPreference extends SwitchPreference {

    public ATESwitchPreference(Context context) {
        super(context);
        init(context, null);
    }

    public ATESwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ATESwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ATESwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutResource(R.layout.ate_preference_custom);

        try {
            Field canRecycleLayoutField = Preference.class.getDeclaredField("mCanRecycleLayout");
            canRecycleLayoutField.setAccessible(true);
            canRecycleLayoutField.setBoolean(this, true);
        } catch (Exception ignored) {
        }

        try {
            Field hasSpecifiedLayout = Preference.class.getDeclaredField("mHasSpecifiedLayout");
            hasSpecifiedLayout.setAccessible(true);
            hasSpecifiedLayout.setBoolean(this, true);
        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        View parentSwitch = findSwitchView(view);
        if (parentSwitch != null) {
            ATH.setTint(parentSwitch, ThemeStore.accentColor(view.getContext()));
        }
    }

    @Nullable
    private View findSwitchView(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof Switch || child instanceof SwitchCompat) {
                    return child;
                } else if (child instanceof ViewGroup) {
                    View potentialSwitch = findSwitchView(child);
                    if (potentialSwitch != null) return potentialSwitch;
                }
            }
        } else if (view instanceof Switch || view instanceof SwitchCompat) {
            return view;
        }
        return null;
    }
}