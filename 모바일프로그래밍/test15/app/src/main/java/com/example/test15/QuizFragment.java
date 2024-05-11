package com.example.test15;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {

    private RadioButton radioJava; // 정답: Java

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        radioJava = view.findViewById(R.id.radioJava);

        Button btnCheckResult = view.findViewById(R.id.btnCheckResult);
        btnCheckResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        return view;
    }

    private void checkAnswer() {
        RadioGroup radioGroupOptions = requireView().findViewById(R.id.radioGroupOptions);
        int selectedId = radioGroupOptions.getCheckedRadioButtonId();
        RadioButton radioButton = requireView().findViewById(selectedId);

        if (radioButton == null) {
            Toast.makeText(requireContext(), "답을 선택하세요", Toast.LENGTH_SHORT).show();
        } else {
            if (radioButton.getId() == radioJava.getId()) {
                Toast.makeText(requireContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                getActivity().finish(); // 정답일 때 현재 액티비티 종료
            } else {
                Toast.makeText(requireContext(), "틀렸습니다!", Toast.LENGTH_SHORT).show();
                // 오답일 때 QuizActivity에게 오답을 전달하여 시작 버튼의 텍스트를 변경
                getActivity().finish(); // 정답일 때 현재 액티비티 종료
            }
        }
    }
}