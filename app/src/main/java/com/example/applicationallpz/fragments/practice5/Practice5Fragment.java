package com.example.applicationallpz.fragments.practice5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.applicationallpz.R;
import com.example.applicationallpz.models.User;

import java.util.ArrayList;
import java.util.List;

public class Practice5Fragment extends Fragment {

    private EditText editTextName, editTextAge, editTextEmail;
    private Spinner spinnerDepartment;
    private Button btnAdd, btnRemove, btnClearAll;
    private ListView listViewUsers;

    private List<User> userList;
    private List<User> selectedUsers;
    private ArrayAdapter<User> adapter;
    private ArrayAdapter<String> departmentAdapter;

    public Practice5Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.practice5fragment_user_list_manager, container, false);

        initViews(view);
        setupData();
        setupAdapters();
        setupListeners();

        return view;
    }

    private void initViews(View view) {
        editTextName = view.findViewById(R.id.editTextName);
        editTextAge = view.findViewById(R.id.editTextAge);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        spinnerDepartment = view.findViewById(R.id.spinnerDepartment);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnRemove = view.findViewById(R.id.btnRemove);
        btnClearAll = view.findViewById(R.id.btnClearAll);

        listViewUsers = view.findViewById(R.id.listViewUsers);
    }

    private void setupData() {
        userList = new ArrayList<>();
        selectedUsers = new ArrayList<>();

        // Добавляем начальные данные
        userList.add(new User("Иван Петров", 25, "ivan@mail.ru", "Разработка"));
        userList.add(new User("Мария Сидорова", 28, "maria@mail.ru", "Дизайн"));
        userList.add(new User("Алексей Козлов", 32, "alex@mail.ru", "Маркетинг"));
        userList.add(new User("Елена Новикова", 24, "elena@mail.ru", "Тестирование"));
    }

    private void setupAdapters() {
        // Адаптер для списка пользователей
        adapter = new ArrayAdapter<User>(requireContext(),
                android.R.layout.simple_list_item_multiple_choice,
                userList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                User user = getItem(position);
                if (user != null) {
                    // Показываем имя и отдел в списке
                    android.widget.TextView textView = (android.widget.TextView) view;
                    textView.setText(user.getName() + " - " + user.getDepartment());
                }
                return view;
            }
        };

        listViewUsers.setAdapter(adapter);
        listViewUsers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Адаптер для выбора отдела
        String[] departments = {"Разработка", "Дизайн", "Маркетинг", "Тестирование", "Поддержка", "Аналитика"};
        departmentAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, departments);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(departmentAdapter);
    }

    private void setupListeners() {
        // Добавление пользователя
        btnAdd.setOnClickListener(v -> addUser());

        // Удаление выбранных пользователей
        btnRemove.setOnClickListener(v -> removeSelectedUsers());

        // Очистка всего списка
        btnClearAll.setOnClickListener(v -> clearAllUsers());

        // Обработка выбора в списке
        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = adapter.getItem(position);
                if (listViewUsers.isItemChecked(position)) {
                    selectedUsers.add(user);
                    view.setBackgroundColor(0xFFE3F2FD); // Голубой фон для выбранных
                } else {
                    selectedUsers.remove(user);
                    view.setBackgroundColor(0xFFFFFFFF); // Белый фон
                }
            }
        });

        // Долгое нажатие для просмотра деталей
        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = adapter.getItem(position);
                showUserDetails(user);
                return true;
            }
        });
    }

    private void addUser() {
        String name = editTextName.getText().toString().trim();
        String ageStr = editTextAge.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String department = spinnerDepartment.getSelectedItem().toString();

        // Валидация
        if (name.isEmpty()) {
            showError("Введите имя пользователя");
            editTextName.requestFocus();
            return;
        }

        if (ageStr.isEmpty()) {
            showError("Введите возраст");
            editTextAge.requestFocus();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 1 || age > 150) {
                showError("Введите корректный возраст (1-150)");
                editTextAge.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            showError("Возраст должен быть числом");
            editTextAge.requestFocus();
            return;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Введите корректный email");
            editTextEmail.requestFocus();
            return;
        }

        // Создаем нового пользователя
        User newUser = new User(name, age, email, department);
        adapter.add(newUser);
        adapter.notifyDataSetChanged();

        // Очищаем поля
        clearInputFields();

        // Показываем сообщение
        Toast.makeText(getContext(), "Пользователь добавлен: " + name, Toast.LENGTH_SHORT).show();
    }

    private void removeSelectedUsers() {
        if (selectedUsers.isEmpty()) {
            showError("Выберите пользователей для удаления");
            return;
        }

        for (User user : selectedUsers) {
            adapter.remove(user);
        }

        int removedCount = selectedUsers.size();
        selectedUsers.clear();
        listViewUsers.clearChoices();
        adapter.notifyDataSetChanged();

        Toast.makeText(getContext(), "Удалено пользователей: " + removedCount, Toast.LENGTH_SHORT).show();
    }

    private void clearAllUsers() {
        if (userList.isEmpty()) {
            showError("Список пользователей пуст");
            return;
        }

        userList.clear();
        selectedUsers.clear();
        adapter.notifyDataSetChanged();
        listViewUsers.clearChoices();

        Toast.makeText(getContext(), "Все пользователи удалены", Toast.LENGTH_SHORT).show();
    }

    private void showUserDetails(User user) {
        String details = "Имя: " + user.getName() +
                "\nВозраст: " + user.getAge() +
                "\nEmail: " + user.getEmail() +
                "\nОтдел: " + user.getDepartment();

        Toast.makeText(getContext(), details, Toast.LENGTH_LONG).show();
    }

    private void clearInputFields() {
        editTextName.setText("");
        editTextAge.setText("");
        editTextEmail.setText("");
        spinnerDepartment.setSelection(0);
        editTextName.requestFocus();
    }

    private void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
