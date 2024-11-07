package com.abdullah.beginner.csc464midterm_calculator;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.abdullah.beginner.csc464midterm_calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        EdgeToEdge.enable(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.resizebar.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });

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