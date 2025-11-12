package com.example.applicationallpz.fragments.practice3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.applicationallpz.R;
import com.example.applicationallpz.models.UserParcelable;
import com.example.applicationallpz.models.UserSerializable;

public class UserResultFragment extends Fragment {

    private TextView textViewResult, textViewMethod;
    private Button btnBack;

    public UserResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.practices3fragment_user_result, container, false);

        initViews(view);
        displayData();
        setClickListeners();

        return view;
    }

    private void initViews(View view) {
        textViewResult = view.findViewById(R.id.textViewResult);
        textViewMethod = view.findViewById(R.id.textViewMethod);
        btnBack = view.findViewById(R.id.btnBack);
    }

    private void displayData() {
        Bundle bundle = getArguments();
        if (bundle == null) return;

        String method = bundle.getString("method", "Неизвестно");
        textViewMethod.setText("Метод передачи: " + method);

        StringBuilder result = new StringBuilder();

        switch (method) {
            case "PutExtra":
                displayPutExtraData(bundle, result);
                break;
            case "Serializable":
                displaySerializableData(bundle, result);
                break;
            case "Parcelable":
                displayParcelableData(bundle, result);
                break;
        }

        textViewResult.setText(result.toString());
    }

    private void displayPutExtraData(Bundle bundle, StringBuilder result) {
        String name = bundle.getString("name", "Не указано");
        String company = bundle.getString("company", "Не указано");
        int age = bundle.getInt("age", 0);
        String phone = bundle.getString("phone", "Не указано");

        result.append("Имя: ").append(name)
                .append("\nКомпания: ").append(company)
                .append("\nВозраст: ").append(age)
                .append("\nТелефон: ").append(phone);
    }

    private void displaySerializableData(Bundle bundle, StringBuilder result) {
        UserSerializable user = (UserSerializable) bundle.getSerializable("user_serializable");
        if (user != null) {
            result.append("Имя: ").append(user.getName())
                    .append("\nКомпания: ").append(user.getCompany())
                    .append("\nВозраст: ").append(user.getAge())
                    .append("\nТелефон: ").append(user.getPhone());
        }
    }

    private void displayParcelableData(Bundle bundle, StringBuilder result) {
        UserParcelable user = bundle.getParcelable("user_parcelable");
        if (user != null) {
            result.append("Имя: ").append(user.getName())
                    .append("\nКомпания: ").append(user.getCompany())
                    .append("\nВозраст: ").append(user.getAge())
                    .append("\nТелефон: ").append(user.getPhone());
        }
    }

    private void setClickListeners() {
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }
}
