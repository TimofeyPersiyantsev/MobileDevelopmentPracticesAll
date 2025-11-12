package com.example.applicationallpz.fragments.practice3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.applicationallpz.R;
import com.example.applicationallpz.models.UserParcelable;
import com.example.applicationallpz.models.UserSerializable;

public class Practice3Fragment extends Fragment {
    private EditText editTextName, editTextCompany, editTextAge, editTextPhone;
    private Button btnPutExtra, btnSerializable, btnParcelable;

    public Practice3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.practices3fragment_user_input, container, false);

        initViews(view);
        setClickListeners();

        return view;
    }

    private void initViews(View view) {
        editTextName = view.findViewById(R.id.editTextName);
        editTextCompany = view.findViewById(R.id.editTextCompany);
        editTextAge = view.findViewById(R.id.editTextAge);
        editTextPhone = view.findViewById(R.id.editTextPhone);

        btnPutExtra = view.findViewById(R.id.btnPutExtra);
        btnSerializable = view.findViewById(R.id.btnSerializable);
        btnParcelable = view.findViewById(R.id.btnParcelable);
    }

    private void setClickListeners() {
        btnPutExtra.setOnClickListener(v -> sendDataWithPutExtra());
        btnSerializable.setOnClickListener(v -> sendDataWithSerializable());
        btnParcelable.setOnClickListener(v -> sendDataWithParcelable());
    }

    private boolean validateInput() {
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Имя обязательно для заполнения");
            editTextName.requestFocus();
            return false;
        }

        if (age.isEmpty()) {
            editTextAge.setError("Возраст обязателен для заполнения");
            editTextAge.requestFocus();
            return false;
        }

        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue <= 0 || ageValue > 150) {
                editTextAge.setError("Введите корректный возраст");
                editTextAge.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            editTextAge.setError("Возраст должен быть числом");
            editTextAge.requestFocus();
            return false;
        }

        return true;
    }

    private void sendDataWithPutExtra() {
        if (!validateInput()) return;

        String name = editTextName.getText().toString().trim();
        String company = editTextCompany.getText().toString().trim();
        int age = Integer.parseInt(editTextAge.getText().toString().trim());
        String phone = editTextPhone.getText().toString().trim();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("company", company);
        bundle.putInt("age", age);
        bundle.putString("phone", phone);
        bundle.putString("method", "PutExtra");

        openResultFragment(bundle);
    }

    private void sendDataWithSerializable() {
        if (!validateInput()) return;

        String name = editTextName.getText().toString().trim();
        String company = editTextCompany.getText().toString().trim();
        int age = Integer.parseInt(editTextAge.getText().toString().trim());
        String phone = editTextPhone.getText().toString().trim();

        UserSerializable user = new UserSerializable(name, company, age, phone);

        Bundle bundle = new Bundle();
        bundle.putSerializable("user_serializable", user);
        bundle.putString("method", "Serializable");

        openResultFragment(bundle);
    }

    private void sendDataWithParcelable() {
        if (!validateInput()) return;

        String name = editTextName.getText().toString().trim();
        String company = editTextCompany.getText().toString().trim();
        int age = Integer.parseInt(editTextAge.getText().toString().trim());
        String phone = editTextPhone.getText().toString().trim();

        UserParcelable user = new UserParcelable(name, company, age, phone);

        Bundle bundle = new Bundle();
        bundle.putParcelable("user_parcelable", user);
        bundle.putString("method", "Parcelable");

        openResultFragment(bundle);
    }

    private void openResultFragment(Bundle bundle) {
        UserResultFragment resultFragment = new UserResultFragment();
        resultFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null)
                .commit();

        Toast.makeText(getContext(), "Данные отправлены методом: " + bundle.getString("method"),
                Toast.LENGTH_SHORT).show();
    }
}
