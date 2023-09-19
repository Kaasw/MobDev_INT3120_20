package com.example.week2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button menuButton = findViewById(R.id.menuButton);
        registerForContextMenu(menuButton);

    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.relative) {
                    setContentView(R.layout.relative);

                } else if (id == R.id.linear) {
                    setContentView(R.layout.linear);

                } else if (id == R.id.table) {
                    setContentView(R.layout.table);

                } else if (id == R.id.constraint) {
                    setContentView(R.layout.constraint);
                } else if (id == R.id.home) {
                    setContentView(R.layout.activity_main);
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.relative) {
            setContentView(R.layout.relative);

        } else if (id == R.id.linear) {
            setContentView(R.layout.linear);

        } else if (id == R.id.table) {
            setContentView(R.layout.table);

        } else if (id == R.id.constraint) {
            setContentView(R.layout.constraint);
        } else if (id == R.id.home) {
            setContentView(R.layout.activity_main);
        }
        return super.onContextItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.relative) {
            setContentView(R.layout.relative);

        } else if (id == R.id.linear) {
            setContentView(R.layout.linear);

        } else if (id == R.id.table) {
            setContentView(R.layout.table);

        } else if (id == R.id.constraint) {
            setContentView(R.layout.constraint);
        } else if (id == R.id.home) {
            setContentView(R.layout.activity_main);
        }
        return super.onOptionsItemSelected(item);
    }


    public void onLayout(View view) {
        openContextMenu(view);
    }
}