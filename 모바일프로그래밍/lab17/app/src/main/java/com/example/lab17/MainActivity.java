package com.example.lab17;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    HashMap<String, String[]> movieMap; // HashMap to store movie information

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        movieMap = new HashMap<>(); // Initialize HashMap

        // 프래그먼트 초기화
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LabFragment())
                    .commit();
        }

        // 리스트뷰의 아이템을 클릭했을 때 해당 영화 정보를 LabFragment로 전달
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String movieName = adapter.getItem(position);
            String[] movieInfo = getMovieInfo(movieName);
            if (movieInfo != null) {
                LabFragment fragment = (LabFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (fragment != null) {
                    fragment.displayMovieInfo(movieInfo);
                }
            }
        });
    }

    // 리스트뷰에 아이템 추가하는 메소드
    public void addItemToList(String[] item) {
        String movieName = item[0];
        movieMap.put(movieName, item); // Add item to HashMap
        adapter.add(movieName);
        adapter.notifyDataSetChanged();
    }

    // 선택한 영화의 정보 반환
    public String[] getMovieInfo(String movieName) {
        return movieMap.get(movieName);
    }
    public void removeItem(String movieName) {
        // HashMap에서 영화 정보 제거
        movieMap.remove(movieName);

        // 리스트뷰에서 아이템 제거
        adapter.remove(movieName);
        adapter.notifyDataSetChanged();
    }

}
