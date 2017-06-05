package hugo_silva.photonow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Utilizador currentUser;
    private String filename = "dados.srl";

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

        getSupportActionBar().setTitle("PhotoNow");

        navigationView.getMenu().getItem(0).setChecked(true);

        //Teste do Intent
        if(getIntent().hasExtra("current_user")) {
            currentUser = (Utilizador) getIntent().getSerializableExtra("current_user");
        }

        if(checkFile()) {
            read();
            Log.d(getClass().getSimpleName(), "Ficheiro foi lido com sucesso.");
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
            FragmentAlbuns fragmentAlbuns = new FragmentAlbuns();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.main_container, fragmentAlbuns, fragmentAlbuns.getTag()).commit();

        } else if (id == R.id.nav_galeria) {
            FragmentGaleria fragmentGaleria = new FragmentGaleria();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.main_container, fragmentGaleria, fragmentGaleria.getTag()).commit();

        } else if (id == R.id.nav_sair) {
            write();
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

    public void read(){
        ObjectInputStream input;

        try {
            input = new ObjectInputStream(new FileInputStream(new File(
                    new File(getFilesDir(),"")+File.separator+ filename)));
             currentUser = (Utilizador) input.readObject();
            Log.v("serialization","Person a="+ currentUser.getUsername());
            input.close();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(){
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+ filename));
            out.writeObject(getCurrentUser());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean checkFile() {
        String path = getFilesDir().getAbsolutePath() + "/" + filename;
        File file = new File(path);
        return file.exists();
    }

    @Override
    public void onPause() {
        super.onPause();
        write();
    }


}
