package com.example.test13;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main); // Get a reference to the main view
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        System.out.println("a");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.op_normal) {
            Toast.makeText(this, "일반 메뉴가 선택됨.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.op_icon) {
            Toast.makeText(this, "아이콘 메뉴가 선택됨.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.op_check) {
            if (item.isChecked()) {
                item.setChecked(false);
                Toast.makeText(this, "체크가 해제됨", Toast.LENGTH_SHORT).show();
            } else {
                item.setChecked(true);
                Toast.makeText(this, "체크 되었음.", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (itemId == R.id.sub1) {
            Toast.makeText(this, "서브 메뉴 1번 선택됨.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.sub2) {
            Toast.makeText(this, "서브 메뉴 2번 선택됨.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem iconMenuItem = menu.findItem(R.id.op_icon);
        if (iconMenuItem != null) {
            iconMenuItem.setEnabled(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}