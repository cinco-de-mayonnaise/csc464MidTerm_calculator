package com.abdullah.beginner.csc464midterm_calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;

import com.abdullah.beginner.csc464midterm_calculator.databinding.ActivityLandscapeBinding;
import com.abdullah.beginner.csc464midterm_calculator.databinding.ActivityPortraitBinding;
import com.abdullah.beginner.csc464midterm_calculator.parser.CalculatorParser;
import com.abdullah.beginner.csc464midterm_calculator.parser.ParseException;
import com.abdullah.beginner.csc464midterm_calculator.parser.TokenMgrException;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Locale;

public class ButtonConnector
{
    ActivityPortraitBinding activityPortraitBinding;
    ActivityLandscapeBinding activityLandscapeBinding;
    EditText inputEditText;
    TextView outputTextView;

    View.OnClickListener defaultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;

            // get cursor position
            int start = inputEditText.getSelectionStart();
            int end = inputEditText.getSelectionEnd();

            Log.d("Cursor pos: ", String.format("%d %d", start, end));
            inputEditText.getText().replace(start, end, b.getText(), 0, b.getText().length());
        }
    };

    View.OnClickListener bkspListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // get cursor position
            int start = inputEditText.getSelectionStart();
            int end = inputEditText.getSelectionEnd();

            if (start > 0 && start == end)
                inputEditText.getText().replace(start - 1, end, "", 0, 0); // text not selected, just delete last char.
            else
                inputEditText.getText().replace(start, end, "", 0, 0);    // text selected, delete it.
        }
    };

    View.OnClickListener equalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // ok this is the fun part...

            Log.d("Check Current Text", inputEditText.getText().toString());

            CalculatorParser cp = null;
            cp = new CalculatorParser(new ByteArrayInputStream(
                    inputEditText.getText().toString().getBytes(StandardCharsets.UTF_8)
            ), "utf-8");

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
            if (r.getValue() instanceof Double)  // not necessary turns out DecimalFormat handles
            {
                // use DecimalFormatter, its gud
                //outputTextView.setText(String.format(Locale.getDefault(), "%g", r.getValue().doubleValue()));
                DecimalFormat df;
                if (Math.abs(r.getValue().doubleValue()) < 1E11)
                    df = new DecimalFormat("##########.##########");
                else
                    df = new DecimalFormat("0.##########E00");

                outputTextView.setText(df.format(r.getValue()));
            }
            else if (r.getValue() instanceof Long)
                outputTextView.setText(String.format(Locale.getDefault(), "%d", r.getValue().longValue()));
            else
            {
                Log.e("Result", "What the heck is this output type? Please check...");
                Log.e("Result", r.toString());
            }
        }
    };

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            inputEditText.setText("");
        }
    };

    View.OnClickListener notImplementedYetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast t = Toast.makeText(v.getContext().getApplicationContext(), "Not implemented yet :(", Toast.LENGTH_SHORT);
            t.show();
        }
    };

    private View.OnClickListener customTextInserter_Listener(String to_insert)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;

                // get cursor position
                int start = inputEditText.getSelectionStart();
                int end = inputEditText.getSelectionEnd();

                Log.d("Cursor pos: ", String.format("%d %d", start, end));
                inputEditText.getText().replace(start, end, to_insert, 0, to_insert.length());
            }
        };
    }

    public ButtonConnector(ViewDataBinding binding) {
        Log.d("What is this layout?", binding.toString());
        if (binding instanceof ActivityPortraitBinding)
        {
            this.activityPortraitBinding = (ActivityPortraitBinding) binding;
            this.inputEditText = this.activityPortraitBinding.calculationWorkspaceEditText;
            this.outputTextView = this.activityPortraitBinding.calculationOutputTextView;
        }
        else if (binding instanceof ActivityLandscapeBinding)
        {
            this.activityLandscapeBinding = (ActivityLandscapeBinding) binding;
            this.inputEditText = this.activityLandscapeBinding.calculationWorkspaceEditText;
            this.outputTextView = this.activityLandscapeBinding.calculationOutputTextView;
        }
        else
            throw new IllegalArgumentException("Received a layout not handled by ButtonConnector! ");
    }
    public void connectAllButtons()
    {
        if (activityPortraitBinding != null)
            connectAllButtons_ActivityPortraitBinding();
        else
        {
            assert activityLandscapeBinding != null;
            connectAllButtons_ActivityLandscapeBinding();
        }
    }

    private void connectAllButtons_ActivityLandscapeBinding()
    {
        activityLandscapeBinding.key0.setOnClickListener(defaultListener);
        activityLandscapeBinding.key1.setOnClickListener(defaultListener);
        activityLandscapeBinding.key2.setOnClickListener(defaultListener);
        activityLandscapeBinding.key3.setOnClickListener(defaultListener);
        activityLandscapeBinding.key4.setOnClickListener(defaultListener);
        activityLandscapeBinding.key5.setOnClickListener(defaultListener);
        activityLandscapeBinding.key6.setOnClickListener(defaultListener);
        activityLandscapeBinding.key7.setOnClickListener(defaultListener);
        activityLandscapeBinding.key8.setOnClickListener(defaultListener);
        activityLandscapeBinding.key9.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyLparen.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyRparen.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyAdd.setOnClickListener(defaultListener);
        activityLandscapeBinding.keySub.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyMul.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyDiv.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyDot.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyMod.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyE.setOnClickListener(defaultListener);
        activityLandscapeBinding.keyPi.setOnClickListener(defaultListener);

        // tokubetsu jiken
        activityLandscapeBinding.keyReset.setOnClickListener(resetListener);
        activityLandscapeBinding.keyBksp.setOnClickListener(bkspListener);
        activityLandscapeBinding.keyEq.setOnClickListener(equalListener);

        activityLandscapeBinding.keyAbs.setOnClickListener(customTextInserter_Listener("abs("));
        activityLandscapeBinding.keySin.setOnClickListener(customTextInserter_Listener("sin("));
        activityLandscapeBinding.keyCos.setOnClickListener(customTextInserter_Listener("cos("));
        activityLandscapeBinding.keyTan.setOnClickListener(customTextInserter_Listener("tan("));
        activityLandscapeBinding.keySqrt.setOnClickListener(customTextInserter_Listener("sqrt("));
        activityLandscapeBinding.keyExp.setOnClickListener(customTextInserter_Listener("exp("));
        activityLandscapeBinding.keyExp10.setOnClickListener(customTextInserter_Listener("10^"));
        activityLandscapeBinding.keyLog.setOnClickListener(customTextInserter_Listener("log("));
        activityLandscapeBinding.keyLn.setOnClickListener(customTextInserter_Listener("ln("));
        activityLandscapeBinding.keyPow.setOnClickListener(customTextInserter_Listener("^"));
        activityLandscapeBinding.keyPow2.setOnClickListener(customTextInserter_Listener("^2"));


        // Not Implemented yet
        activityLandscapeBinding.keyAns.setOnClickListener(notImplementedYetListener);
        activityLandscapeBinding.keyCbrt.setOnClickListener(notImplementedYetListener);
        activityLandscapeBinding.keyHist.setOnClickListener(notImplementedYetListener);
        activityLandscapeBinding.keyFact.setOnClickListener(notImplementedYetListener);
    }

    private void connectAllButtons_ActivityPortraitBinding()
    {
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
        activityPortraitBinding.keyReset.setOnClickListener(resetListener);
        activityPortraitBinding.keyBksp.setOnClickListener(bkspListener);
        activityPortraitBinding.keyEq.setOnClickListener(equalListener);

    }


}
