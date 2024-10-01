package com.example.lengthunitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextNumber = findViewById(R.id.editTextNumber);
        Button btnConvert = findViewById(R.id.btnConvert);
        Spinner spinnerItem = findViewById(R.id.spItem);
        Spinner spinnerItem2 = findViewById(R.id.spConvertItem);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Length,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItem.setAdapter(adapter);
        spinnerItem2.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstUnit = spinnerItem.getSelectedItem().toString();
                String secondUnit = spinnerItem2.getSelectedItem().toString();
                if(editTextNumber.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please select a unit", Toast.LENGTH_SHORT).show();
                    return;
                }
                double value = Double.parseDouble(editTextNumber.getText().toString());
                double result = convertLength(value, firstUnit, secondUnit);
                Toast.makeText(MainActivity.this, "Converted value: " + result , Toast.LENGTH_SHORT).show();

            }
        });
    }

    private double convertLength(double value, String from_Unit, String to_Unit) {
        double[][] conversionFactors = {
                // Metre
                {1, 1000, 0.000621371, 3.28084},
                // Millimetre
                {0.001, 1, 0.000000621371, 0.00328084},
                // Mile
                {1609.34, 1609340, 1, 5280},
                // Foot
                {0.3048, 304.8, 0.000189394, 1}
        };

        int fromIndex = getUnitIndex(from_Unit);
        int toIndex = getUnitIndex(to_Unit);

        return value * conversionFactors[fromIndex][toIndex];
    }

    private int getUnitIndex(String unit) {
        String[] units = {"Metre", "Millimetre", "Mile", "Foot"};
        for (int i = 0; i < units.length; i++) {
            if (units[i].equals(unit)) {
                return i;
            }
        }
        return -1;
    }
}