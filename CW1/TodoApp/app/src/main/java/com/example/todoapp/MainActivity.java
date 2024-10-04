package com.example.todoapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView todoList;
    private EditText todoEditText;
    private Button addButton;
    private ArrayList<String> todoItems;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
            Toolbar myToolbar = findViewById(R.id.tb22);
            setSupportActionBar(myToolbar);

            todoList = findViewById(R.id.todo_list);
            todoEditText = findViewById(R.id.todo_edit_text);
            addButton = findViewById(R.id.add_button);

            todoItems = new ArrayList<>();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItems);
            todoList.setAdapter(adapter);

            addButton.setOnClickListener(v -> {
                String todoItem = todoEditText.getText().toString();
                if (!todoItem.isEmpty()) {
                    todoItems.add(todoItem);
                    adapter.notifyDataSetChanged();
                    todoEditText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar,menu);
        return true;
    }
}