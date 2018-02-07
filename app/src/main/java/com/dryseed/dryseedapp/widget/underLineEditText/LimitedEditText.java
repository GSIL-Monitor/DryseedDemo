package com.dryseed.dryseedapp.widget.underLineEditText;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * EditText subclass created to enforce limit of the lines number in editable
 * text field
 */
public class LimitedEditText extends android.support.v7.widget.AppCompatEditText {

    /**
     * Max lines to be present in editable text field
     */
    private int maxLines = Integer.MAX_VALUE;

    /**
     * Max characters to be present in editable text field
     */
    private int maxCharacters = Integer.MAX_VALUE;

    /**
     * application context;
     */
    private Context context;

    private float mSpacingMult = 1f;

    private float mSpacingAdd = 0f;

    public int getMaxCharacters() {
        return maxCharacters;
    }

    public void setMaxCharacters(int maxCharacters) {
        this.maxCharacters = maxCharacters;
    }

    @Override
    public int getMaxLines() {
        return maxLines;
    }

    @Override
    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    public LimitedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public LimitedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public LimitedEditText(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        getLineSpacingAddAndLineSpacingMult();

        TextWatcher watcher = new TextWatcher() {

            private String text;
            private int beforeCursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                text = s.toString();
                beforeCursorPosition = start;
            }

            @Override
            public void afterTextChanged(Editable s) {

                /* turning off listener */
                removeTextChangedListener(this);

                /* handling lines limit exceed */
                if (LimitedEditText.this.getLineCount() > maxLines) {
                    LimitedEditText.this.setText(text);
                    LimitedEditText.this.setSelection(beforeCursorPosition);
                }

                /* handling character limit exceed */
                if (s.toString().length() > maxCharacters) {
                    LimitedEditText.this.setText(text);
                    LimitedEditText.this.setSelection(beforeCursorPosition);
                    Toast.makeText(context, "text too long", Toast.LENGTH_SHORT)
                            .show();
                }

                setLineSpacing(0f, 1f);
                setLineSpacing(mSpacingAdd, mSpacingMult);

                /* turning on listener */
                addTextChangedListener(this);

            }
        };

        this.addTextChangedListener(watcher);
    }

    private void getLineSpacingAddAndLineSpacingMult() {
        try {
            Field mSpacingAddField = TextView.class.getDeclaredField("mSpacingAdd");
            Field mSpacingMultField = TextView.class.getDeclaredField("mSpacingMult");
            mSpacingAddField.setAccessible(true);
            mSpacingMultField.setAccessible(true);
            mSpacingAdd = mSpacingAddField.getFloat(this);
            mSpacingMult = mSpacingMultField.getFloat(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
