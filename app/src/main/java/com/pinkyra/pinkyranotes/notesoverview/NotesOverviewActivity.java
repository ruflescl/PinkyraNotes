package com.pinkyra.pinkyranotes.notesoverview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.view.View;

import com.pinkyra.pinkyranotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesOverviewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.acno_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.abno_toolbar) Toolbar toolbar;
    @BindView(R.id.acno_nav_view) NavigationView navigationView;
    @BindView(R.id.abno_fab_add_note) FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_overview);
        ButterKnife.bind(this);

        initDrawerLayout(initToolbar());
        initNavigationView();

        if (savedInstanceState == null) {
            initFragment(NotesOverviewFragment.newInstance());
        }
    }

    private Toolbar initToolbar() {
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initDrawerLayout(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.string_syst_nav_bar_open, R.string.string_syst_nav_bar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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

    @OnClick(R.id.abno_fab_add_note)
    public void onFabAddNote_Click(View view) {
        NotesOverviewFragment fragment = (NotesOverviewFragment)
                getSupportFragmentManager().findFragmentById(R.id.acno_fram_content_frame);

        fragment.onFabAddNote_Click();
    }
}
