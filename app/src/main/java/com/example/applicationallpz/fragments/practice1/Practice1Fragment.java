package com.example.applicationallpz.fragments.practice1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.Gravity;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.applicationallpz.R;

public class Practice1Fragment extends Fragment {

    private ImageView imgMorning, imgDay, imgEvening, imgNight;
    private RelativeLayout layoutMorning;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "DAY_SCHEDULE_CHANNEL";

    public Practice1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.practice1fragment, container, false);

        initializeViews(view);
        setupClickListeners(view);
        createNotificationChannel();

        // Показываем утреннюю локацию по умолчанию
        showMorningLocation();

        return view;
    }

    private void initializeViews(View view) {
        imgMorning = view.findViewById(R.id.imageMorning);
        imgDay = view.findViewById(R.id.imageDay);
        imgEvening = view.findViewById(R.id.imageEvening);
        imgNight = view.findViewById(R.id.imageNight);
        layoutMorning = view.findViewById(R.id.layoutMorning);
    }

    private void setupClickListeners(View view) {
        Button btnMorning = view.findViewById(R.id.btnMorning);
        Button btnDay = view.findViewById(R.id.btnDay);
        Button btnEvening = view.findViewById(R.id.btnEvening);
        Button btnNight = view.findViewById(R.id.btnNight);

        btnMorning.setOnClickListener(v -> btnMorningClick());
        btnDay.setOnClickListener(v -> btnDayClick());
        btnEvening.setOnClickListener(v -> btnEveningClick());
        btnNight.setOnClickListener(v -> btnNightClick());

        // Кнопки для Toast сообщений
        view.findViewById(R.id.btnExercise1).setOnClickListener(v -> onExercise1Click());
        view.findViewById(R.id.btnExercise2).setOnClickListener(v -> onExercise2Click());
        view.findViewById(R.id.btnExercise3).setOnClickListener(v -> onExercise3Click());
        view.findViewById(R.id.btnExercise4).setOnClickListener(v -> onExercise4Click());
        view.findViewById(R.id.btnExercise5).setOnClickListener(v -> onExercise5Click());
    }

    private void createNotificationChannel() {
        notificationManager = (NotificationManager) requireContext().getSystemService(requireContext().NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Распорядок дня",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Уведомления о распорядке дня");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Методы для навигации по локациям
    private void btnMorningClick() {
        showMorningLocation();
        showMorningNotification();
    }

    private void btnDayClick() {
        showDayLocation();
        showDayNotification();
    }

    private void btnEveningClick() {
        showEveningLocation();
        showEveningNotification();
    }

    private void btnNightClick() {
        showNightLocation();
        showNightNotification();
    }

    private void showMorningLocation() {
        hideAllLocations();
        layoutMorning.setVisibility(View.VISIBLE);
        imgMorning.setVisibility(View.VISIBLE);
    }

    private void showDayLocation() {
        hideAllLocations();
        imgDay.setVisibility(View.VISIBLE);
    }

    private void showEveningLocation() {
        hideAllLocations();
        imgEvening.setVisibility(View.VISIBLE);
    }

    private void showNightLocation() {
        hideAllLocations();
        imgNight.setVisibility(View.VISIBLE);
    }

    private void hideAllLocations() {
        if (layoutMorning != null) layoutMorning.setVisibility(View.INVISIBLE);
        if (imgMorning != null) imgMorning.setVisibility(View.INVISIBLE);
        if (imgDay != null) imgDay.setVisibility(View.INVISIBLE);
        if (imgEvening != null) imgEvening.setVisibility(View.INVISIBLE);
        if (imgNight != null) imgNight.setVisibility(View.INVISIBLE);
    }

    // Методы для уведомлений
    private void showMorningNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentTitle("Утро")
                .setContentText("Время для утренней зарядки!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    private void showDayNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_edit)
                .setContentTitle("День")
                .setContentText("Пора заниматься учебой!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    private void showEveningNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
                .setContentTitle("Вечер")
                .setContentText("Время для тренировки!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    private void showNightNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Ночь")
                .setContentText("Пора спать! Хороших снов!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    // Методы для всплывающих сообщений (Toast)
    private void onExercise1Click() {
        showToast("Время для утренней зарядки!");
    }

    private void onExercise2Click() {
        showToast("Растяжка улучшает гибкость");
    }

    private void onExercise3Click() {
        showToast("Силовые упражнения");
    }

    private void onExercise4Click() {
        showToast("Душ после зарядки");
    }

    private void onExercise5Click() {
        showToast("Здоровый завтрак");
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}