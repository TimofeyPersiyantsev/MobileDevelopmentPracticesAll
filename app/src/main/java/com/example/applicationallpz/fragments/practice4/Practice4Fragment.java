package com.example.applicationallpz.fragments.practice4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.applicationallpz.R;
import com.example.applicationallpz.adapters.AppListAdapter;
import com.example.applicationallpz.models.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class Practice4Fragment extends Fragment {

    private ListView listViewApps;
    private AppListAdapter adapter;
    private List<AppInfo> appList;

    public Practice4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.practice4fragment_app_list, container, false);

        initViews(view);
        setupAppList();
        setupListView();

        return view;
    }

    private void initViews(View view) {
        listViewApps = view.findViewById(R.id.listViewApps);
    }

    private void setupAppList() {
        appList = new ArrayList<>();

        // Получаем данные из ресурсов
        String[] appNames = getResources().getStringArray(R.array.app_names);
        String[] appDescriptions = getResources().getStringArray(R.array.app_descriptions);

        // Массив иконок (замените на реальные ресурсы)
        int[] appIcons = {
                android.R.drawable.ic_media_play,
                android.R.drawable.ic_partial_secure,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_media_next,
                android.R.drawable.ic_lock_lock,
                android.R.drawable.ic_menu_view,
                android.R.drawable.ic_btn_speak_now,
                android.R.drawable.ic_media_ff,
                android.R.drawable.ic_media_pause,
                android.R.drawable.ic_media_rew
        };

        // Если иконок нет, используем стандартные
        int defaultIcon = android.R.drawable.ic_dialog_info;

        // Создаем список приложений
        for (int i = 0; i < appNames.length; i++) {
            int icon = (i < appIcons.length) ? appIcons[i] : defaultIcon;
            String description = (i < appDescriptions.length) ? appDescriptions[i] : "Описание отсутствует";

            appList.add(new AppInfo(appNames[i], description, icon));
        }
    }

    private void setupListView() {
        adapter = new AppListAdapter(requireContext(), appList);
        listViewApps.setAdapter(adapter);

        // Обработка клика по элементу
        listViewApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo selectedApp = appList.get(position);
                showAppDetails(selectedApp);
            }
        });

        // Обработка долгого нажатия
        listViewApps.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo selectedApp = appList.get(position);
                showAppOptions(selectedApp);
                return true;
            }
        });
    }

    private void showAppDetails(AppInfo app) {
        Toast.makeText(getContext(),
                "Выбрано: " + app.getName() + "\n" + app.getDescription(),
                Toast.LENGTH_LONG).show();
    }

    private void showAppOptions(AppInfo app) {
        Toast.makeText(getContext(),
                "Долгое нажатие: " + app.getName(),
                Toast.LENGTH_SHORT).show();
    }
}