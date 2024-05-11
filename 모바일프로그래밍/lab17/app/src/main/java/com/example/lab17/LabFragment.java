package com.example.lab17;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class LabFragment extends Fragment {

    public EditText releaseYearEditText, movieNameEditText, directorEditText, ratingEditText, countryEditText;
    Button saveButton, updateButton, deleteButton;

    public LabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        releaseYearEditText = rootView.findViewById(R.id.releaseYearEditText);
        movieNameEditText = rootView.findViewById(R.id.movieNameEditText);
        directorEditText = rootView.findViewById(R.id.directorEditText);
        ratingEditText = rootView.findViewById(R.id.ratingEditText);
        countryEditText = rootView.findViewById(R.id.countryEditText);

        saveButton = rootView.findViewById(R.id.saveButton);
        updateButton = rootView.findViewById(R.id.updateButton);
        deleteButton = rootView.findViewById(R.id.cancelButton);

        // 리스트뷰의 아이템을 클릭했을 때 해당 영화 정보를 EditText에 표시
        ((MainActivity) getActivity()).listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movieName = ((TextView) view).getText().toString();
                String[] movieInfo = ((MainActivity) getActivity()).getMovieInfo(movieName);

                // 영화 정보가 있을 경우 EditText에 표시
                if (movieInfo != null) {
                    movieNameEditText.setText(movieInfo[0]);
                    releaseYearEditText.setText(movieInfo[1]);
                    directorEditText.setText(movieInfo[2]);
                    ratingEditText.setText(movieInfo[3]);
                    countryEditText.setText(movieInfo[4]);
                } else {
                    Toast.makeText(getContext(), "해당 영화 정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieName = movieNameEditText.getText().toString();
                String releaseYear = releaseYearEditText.getText().toString();
                String director = directorEditText.getText().toString();
                String rating = ratingEditText.getText().toString();
                String country = countryEditText.getText().toString();

                // 영화 정보를 토스트 메시지로 표시
                String message = "영화 이름: " + movieName + "\n" +
                        "제작 연도: " + releaseYear + "\n" +
                        "감독: " + director + "\n" +
                        "평점: " + rating + "\n" +
                        "국가: " + country;
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                String [] str = {movieName, releaseYear, director, rating, country};
                // MainActivity의 리스트뷰에 영화 제목 추가
                ((MainActivity) getActivity()).addItemToList(str);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String movieName = movieNameEditText.getText().toString();
                String releaseYear = releaseYearEditText.getText().toString();
                String director = directorEditText.getText().toString();
                String rating = ratingEditText.getText().toString();
                String country = countryEditText.getText().toString();

                // 영화 이름으로 저장된 정보 가져오기
                String[] movieInfo = ((MainActivity) getActivity()).getMovieInfo(movieName);

                // 가져온 정보가 있을 때만 업데이트
                if (movieInfo != null) {
                    movieInfo[1] = releaseYear;
                    movieInfo[2] = director;
                    movieInfo[3] = rating;
                    movieInfo[4] = country;

                    // 업데이트된 정보를 토스트 메시지로 표시
                    String updateMessage = "영화 이름: " + movieName + "\n" +
                            "제작 연도: " + releaseYear + "\n" +
                            "감독: " + director + "\n" +
                            "평점: " + rating + "\n" +
                            "국가: " + country;
                    Toast.makeText(getContext(), "영화 정보가 업데이트 되었습니다:\n" + updateMessage, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "해당 영화가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String movieName = movieNameEditText.getText().toString();
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.removeItem(movieName);
                }
            }
        });

        return rootView;
    }
    public void displayMovieInfo(String[] movieInfo) {
        movieNameEditText.setText(movieInfo[0]);
        releaseYearEditText.setText(movieInfo[1]);
        directorEditText.setText(movieInfo[2]);
        ratingEditText.setText(movieInfo[3]);
        countryEditText.setText(movieInfo[4]);
    }
}
