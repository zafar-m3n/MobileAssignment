package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment1.database.viewmodel.EMAViewModel;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private EMAViewModel emaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        emaViewModel = new ViewModelProvider(this).get(EMAViewModel.class);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.categories_container, new FragmentListCategory()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh) {
            reloadListFragment();
        }
        if (id == R.id.clear_text) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment instanceof DashboardActions) {
                ((DashboardActions) fragment).clearTextFields();
            }
        }
        if (id == R.id.delete_categories) {
            deleteAllCategories();
        }
        if (id == R.id.delete_event) {
            deleteAllEvents();
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Intent intent;
        if (itemId == R.id.nav_dashboard) {
            intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_all_categories) {
            intent = new Intent(this, ListCategoryActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_add_category) {
            intent = new Intent(this, NewEventCategoryActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_events) {
            intent = new Intent(this, ListEventActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_logout) {
            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void reloadListFragment() {
        FragmentListCategory fragment = new FragmentListCategory();
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.categories_container, fragment)
                .commit();
    }

    public interface DashboardActions {
        void clearTextFields();
    }

    private void deleteAllEvents() {
        emaViewModel.deleteAllEvents(); // Call the method to delete all events from ViewModel
        Toast.makeText(this, "All events deleted", Toast.LENGTH_SHORT).show();
    }


    private void deleteAllCategories() {
        emaViewModel.deleteAllCategories(); // Use ViewModel to delete all categories
        Toast.makeText(this, "All categories deleted", Toast.LENGTH_SHORT).show();
    }
}