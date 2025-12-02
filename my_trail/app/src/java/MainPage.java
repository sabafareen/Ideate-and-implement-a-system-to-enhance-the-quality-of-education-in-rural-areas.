package com.example.mytrail1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;
import android.view.WindowManager;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        String emailid = getIntent().getStringExtra("emailid");
        String name = getIntent().getStringExtra("name");

        
        loadFragment(new HomeFragment(), null);

        
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                Bundle bundle = null;

                if (item.getItemId() == R.id.home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.category) {
                    selectedFragment = new CategoryFragment();
                } else if (item.getItemId() == R.id.resources) {
                    selectedFragment = new ResourcesFragment();
                } else if (item.getItemId() == R.id.profile) {
                    selectedFragment = new ProfileFragment();

                    bundle = new Bundle();
                    bundle.putString("emailid", emailid);
                    bundle.putString("name", name);
                }

                
                loadFragment(selectedFragment, bundle);
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment, Bundle bundle) {
        if (fragment != null) {
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            fragmentTransaction.commit();
        }
    }
}
