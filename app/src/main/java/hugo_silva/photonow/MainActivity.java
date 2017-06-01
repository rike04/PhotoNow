package hugo_silva.photonow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Utilizador currentUser;

    public static NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        MainFragment mainFragment = new MainFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()

                .replace(R.id.main_container, mainFragment, mainFragment.getTag()).commit();

        getSupportActionBar().setTitle("Página Principal");

        navigationView.getMenu().getItem(0).setChecked(true);

        //Teste do Intent
        if(getIntent().hasExtra("current_user")) {
            currentUser = (Utilizador) getIntent().getSerializableExtra("current_user");
        }
    }

    public Utilizador getCurrentUser() {
        return currentUser;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mainpage) {
            MainFragment mainFragment = new MainFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.main_container, mainFragment, mainFragment.getTag()).commit();

        } else if (id == R.id.nav_albuns) {
            AlbunsFragment albunsFragment = new AlbunsFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.main_container, albunsFragment, albunsFragment.getTag()).commit();

        } else if (id == R.id.nav_galeria) {
            GaleriaFragment galeriaFragment = new GaleriaFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.main_container, galeriaFragment, galeriaFragment.getTag()).commit();

        } else if (id == R.id.nav_sair) {
            saveToDB();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        saveToDB();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveToDB();
    }

    private void saveToDB() {
        TinyDB database = new TinyDB(getBaseContext());
        ArrayList<Object> utilizadores = database.getListObject("array", Utilizador.class);
        for(Object o: utilizadores) {
            Utilizador user = (Utilizador) o;
            if(getCurrentUser().getId() == user.getId()) {
                utilizadores.remove(user);
                utilizadores.add(getCurrentUser());
                Log.d(getClass().getSimpleName(), "Guardado");
            }
        }
    }
}
