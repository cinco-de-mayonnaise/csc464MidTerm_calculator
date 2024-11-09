package com.abdullah.beginner.csc464midterm_calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;

import com.abdullah.beginner.csc464midterm_calculator.databinding.ActivityPortraitBinding;
import com.abdullah.beginner.csc464midterm_calculator.parser.CalculatorParser;
import com.abdullah.beginner.csc464midterm_calculator.parser.ParseException;
import com.abdullah.beginner.csc464midterm_calculator.parser.TokenMgrException;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class ButtonConnector
{
    ActivityPortraitBinding activityPortraitBinding;

    public ButtonConnector(ViewDataBinding binding) {
        Log.d("What is this layout?", binding.toString());
        if (binding instanceof ActivityPortraitBinding)
            this.activityPortraitBinding = (ActivityPortraitBinding) binding;
        else if (true)
            throw new IllegalArgumentException("Received a layout not handled by ButtonConnector! ");
    }
    public void connectAllButtons()
    {
        if (activityPortraitBinding != null)
            connectAllButtons_ActivityPortraitBinding();
    }

    private void connectAllButtons_ActivityPortraitBinding()
    {
        EditText inputEditText = activityPortraitBinding.calculationWorkspaceEditText;
        TextView outputTextView = activityPortraitBinding.calculationOutputTextView;

        View.OnClickListener defaultListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Hello!!!", v.toString());
                Button b = (Button) v;
                inputEditText.append(b.getText());
            }
        };

        activityPortraitBinding.key0.setOnClickListener(defaultListener);
        activityPortraitBinding.key1.setOnClickListener(defaultListener);
        activityPortraitBinding.key2.setOnClickListener(defaultListener);
        activityPortraitBinding.key3.setOnClickListener(defaultListener);
        activityPortraitBinding.key4.setOnClickListener(defaultListener);
        activityPortraitBinding.key5.setOnClickListener(defaultListener);
        activityPortraitBinding.key6.setOnClickListener(defaultListener);
        activityPortraitBinding.key7.setOnClickListener(defaultListener);
        activityPortraitBinding.key8.setOnClickListener(defaultListener);
        activityPortraitBinding.key9.setOnClickListener(defaultListener);
        activityPortraitBinding.keyLparen.setOnClickListener(defaultListener);
        activityPortraitBinding.keyRparen.setOnClickListener(defaultListener);
        activityPortraitBinding.keyAdd.setOnClickListener(defaultListener);
        activityPortraitBinding.keySub.setOnClickListener(defaultListener);
        activityPortraitBinding.keyMul.setOnClickListener(defaultListener);
        activityPortraitBinding.keyDiv.setOnClickListener(defaultListener);
        activityPortraitBinding.keyDot.setOnClickListener(defaultListener);

        // tokubetsu jiken
        activityPortraitBinding.keyReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.setText("");
            }
        });

        activityPortraitBinding.keyBksp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = inputEditText.getText().length();
                if (length > 0)
                    inputEditText.getText().delete(length - 1, length);
            }
        });

        activityPortraitBinding.keyEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ok this is the fun part...

                Log.d("Check Current Text: ", inputEditText.getText().toString());

                CalculatorParser cp = null;
                try {
                    cp = new CalculatorParser(new ByteArrayInputStream(
                            inputEditText.getText().toString().getBytes("utf-8")
                    ), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                // use the parser to compute
                Result r;
                try{
                    r = cp.Start();
                }
                catch (ArithmeticException e)
                {
                    outputTextView.setText("Divide by zero!");
                    return;
                }
                catch (ParseException e)
                {
                    outputTextView.setText("Syntax error!");
                    return;
                }
                catch (TokenMgrException e)
                {
                    outputTextView.setText("Lexical error!");
                    return;
                }

                // now output the answer
                if (r.getValue() instanceof Double)
                {
                    // use DecimalFormatter, its gud
                    outputTextView.setText(String.format(Locale.getDefault(), "%g", r.getValue().doubleValue()));
                }
                else if (r.getValue() instanceof Long)
                    outputTextView.setText(String.format(Locale.getDefault(), "%d", r.getValue().longValue()));
                else
                {
                    Log.e("Result", "What the heck is this output type? Please check...");
                    Log.e("Result", r.toString());
                }
            }
        });
    }
}
