package com.abdullah.beginner.csc464midterm_calculator;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.abdullah.beginner.csc464midterm_calculator.databinding.ActivityPortraitBinding;

import org.javacc.jjtree.Main;

public class MainActivity extends AppCompatActivity {

    ActivityPortraitBinding portrait_binding = null;
    ActivityPortraitBinding landscape_binding = null;

    private int setViewsAndBindings(@NonNull Configuration newConfig)
    {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            // load landscape layout
            // will crash lul
            landscape_binding =  DataBindingUtil.setContentView(this, 500);
            portrait_binding = null;
        }
        else
        {
            if (newConfig.orientation != Configuration.ORIENTATION_PORTRAIT)
                Log.w(getClass().getName(), "Orientation undefined! Panicking and setting as portrait. ");
            // load the portrait layout
            portrait_binding =  DataBindingUtil.setContentView(this, R.layout.activity_portrait);
            landscape_binding = null;
        }

        return newConfig.orientation;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int cur_orientation = setViewsAndBindings(newConfig);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int cur_orientation = setViewsAndBindings(this.getResources().getConfiguration());   // set Views and Bindings based on current orientation, this is called everytime we rotate screen

        // required to prevent keyboard being active when edittext is touched
        portrait_binding.calculationWorkspaceEditText.setShowSoftInputOnFocus(false);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ButtonConnector bc;
        if (cur_orientation == Configuration.ORIENTATION_PORTRAIT)
            bc = new ButtonConnector(portrait_binding);
        else
            bc = new ButtonConnector(landscape_binding);
        bc.connectAllButtons();

//        binding.resizebar.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                return false;
//            }
//        });




//        binding.calculationWorkspaceEditText.setText("2+2-1");
//        try {
//            InputStream in = new ByteArrayInputStream(binding.calculationWorkspaceEditText.getText().toString().getBytes("utf-8"));
//            CalculatorParser c = new CalculatorParser(in, "utf-8");
//            Result output = c.Start();
//            String s;
//            if (output.getType() == long.class)
//                s = Long.toString(output.getValue().longValue());
//            else
//                s = Double.toString(output.getValue().doubleValue());
//
//            binding.calculationOutputTextView.setText(s);
//        }
//        catch (Exception e)
//        {
//            throw new RuntimeException("Failed to run parser!");
//        }

//        binding.main.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                // add text to both
//                binding.calculationOutputTextView.append(binding.calculationOutputTextView.getText());
//                binding.calculationWorkspaceEditText.append(binding.calculationWorkspaceEditText.getText());
//
//                return false;
//            }
//        });

    }


}