package com.example.fastcredits;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fastcredits.utils.PreferenceStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class RouterDrawerActivity  extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_router);

        String email = PreferenceStore.getPersistUser(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.router_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_router);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_router_layout);
        // Get context for drawer ui
        NavigationView navigationView = findViewById(R.id.nav_router_view);
        View headerView = navigationView.getHeaderView(0);

        userEmail = headerView.findViewById(R.id.drw_user_email);

        if (email != null) {
            userEmail.setText(email);
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_router, R.id.nav_router_clients, R.id.nav_router_reports)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_router_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Toolbar tb = findViewById(R.id.router_toolbar);
        getMenuInflater().inflate(R.menu.router_drawer, menu);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // CLEAR STORE
                        Boolean persistCredentials = PreferenceStore.getPersistCredentials(getApplicationContext());
                        PreferenceStore.setPersistSession(false, getApplicationContext());
                        PreferenceStore.clearMongoId(getApplicationContext());

                        if (!persistCredentials) {
                            PreferenceStore.clearSession(getApplicationContext());
                            PreferenceStore.setPersistCredentials(false, getApplicationContext());
                        }

                        startActivity(new Intent(getApplication(), MainActivity.class));

                        return onOptionsItemSelected(item);
                    }
                });

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_router_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
