package com.example.applicationallpz;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.applicationallpz.fragments.FavoritesFragment;
import com.example.applicationallpz.fragments.HomeFragment;
import com.example.applicationallpz.fragments.Practice10Fragment;
import com.example.applicationallpz.fragments.practice1.Practice1Fragment;
import com.example.applicationallpz.fragments.practice2.Practice2Fragment;
import com.example.applicationallpz.fragments.practice3.Practice3Fragment;
import com.example.applicationallpz.fragments.practice4.Practice4Fragment;
import com.example.applicationallpz.fragments.practice5.Practice5Fragment;
import com.example.applicationallpz.fragments.Practice6Fragment;
import com.example.applicationallpz.fragments.Practice7Fragment;
import com.example.applicationallpz.fragments.Practice8Fragment;
import com.example.applicationallpz.fragments.Practice9Fragment;
import com.example.applicationallpz.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupNavigationDrawer();
        setupBottomNavigation();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        fragmentContainer = findViewById(R.id.fragment_container);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        // Устанавливаем иконку меню
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });
    }

    private void setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_practice1) {
                showFragment(new Practice1Fragment(), "Практика  - Распорядок дня");
            } else if (id == R.id.nav_practice2) {
                showFragment(new Practice2Fragment(), "Практика - Калькулятор");
            } else if (id == R.id.nav_practice3) {
                showFragment(new Practice3Fragment(), "Практика 3");
            } else if (id == R.id.nav_practice4) {
                showFragment(new Practice4Fragment(), "Практика 4");
            } else if (id == R.id.nav_practice5) {
                showFragment(new Practice5Fragment(), "Практика 5");
            } else if (id == R.id.nav_practice6) {
                showFragment(new Practice6Fragment(), "Практика 6");
            } else if (id == R.id.nav_practice7) {
                showFragment(new Practice7Fragment(), "Практика 7");
            } else if (id == R.id.nav_practice8) {
                showFragment(new Practice8Fragment(), "Практика 8");
            } else if (id == R.id.nav_practice9) {
                showFragment(new Practice9Fragment(), "Практика 9");
            } else if (id == R.id.nav_practice10) {
                showFragment(new Practice10Fragment(), "Практика 10");
            }

            drawerLayout.closeDrawer(navigationView);
            return true;
        });
    }

     public void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                showFragment(new HomeFragment(), "Главная");
            } else if (id == R.id.nav_favorites) {
                showFragment(new FavoritesFragment(), "Избранное");
            } else if (id == R.id.nav_profile) {
                showFragment(new ProfileFragment(), "Профиль");
            }
            return true;
        });
    }

    private void showFragment(Fragment fragment, String title) {
        toolbar.setTitle(title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
    public void openMenu(View view) {
        drawerLayout.openDrawer(navigationView);
    }
}