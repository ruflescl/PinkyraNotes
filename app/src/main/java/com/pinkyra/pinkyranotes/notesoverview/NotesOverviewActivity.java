package com.pinkyra.pinkyranotes.notesoverview;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pinkyra.pinkyranotes.R;

public class NotesOverviewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_overview);

        initDrawerLayout(initToolbar());
        initNavigationView();

        if (savedInstanceState == null) {
            initFragment(NotesOverviewFragment.newInstance());
        }
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.abno_toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initDrawerLayout(Toolbar toolbar) {
        drawerLayout = (DrawerLayout) findViewById(R.id.acno_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.string_syst_nav_bar_open, R.string.string_syst_nav_bar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.acno_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragment(Fragment notesOverviewFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.acno_fram_content_frame, notesOverviewFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.acno_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.acno_nav_manage) {
            // TODO: Criar e abrir tela de configurações
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
