package com.afollestad.appthemeengine.inflation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;

import com.afollestad.appthemeengine.ATE;
import com.afollestad.appthemeengine.ATEActivity;
import com.afollestad.appthemeengine.tagprocessors.TextColorTagProcessor;
import com.afollestad.appthemeengine.tagprocessors.TintTagProcessor;

/**
 * @author Aidan Follestad (afollestad)
 */
public class ATESwitch extends SwitchCompat implements ViewInterface {

    public ATESwitch(Context context) {
        super(context);
        init(context, null);
    }

    public ATESwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, null);
    }

    public ATESwitch(Context context, AttributeSet attrs, @Nullable ATEActivity keyContext) {
        super(context, attrs);
        init(context, keyContext);
    }

    private void init(Context context, @Nullable ATEActivity keyContext) {
        if (getTag() == null)
            setTag(String.format("%s|accent_color,%s|primary_text", TintTagProcessor.PREFIX, TextColorTagProcessor.PREFIX));
        try {
            ATEViewUtil.init(keyContext, this, context);
        } catch (Throwable t) {
            throw new RuntimeException(t.getMessage(), t);
        }
    }

    public void setKey(String key) {
        ATE.themeView(getContext(), this, key);
    }

    @Override
    public boolean isShown() {
        return getParent() != null && getVisibility() == View.VISIBLE;
    }

    @Override
    public boolean setsStatusBarColor() {
        return false;
    }

    @Override
    public boolean setsToolbarColor() {
        return false;
    }
}
