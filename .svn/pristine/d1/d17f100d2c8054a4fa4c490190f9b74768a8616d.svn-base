package org.mangosoft.leagoovn.smarthub.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.service.MainService;
import org.mangosoft.leagoovn.smarthub.ui.fragment.ListAppFragment;
import org.mangosoft.leagoovn.smarthub.ui.fragment.ProductFragment;
import org.mangosoft.leagoovn.smarthub.ui.fragment.PromotionFragment;
import org.mangosoft.leagoovn.smarthub.ui.fragment.StoreFragment;
import org.mangosoft.leagoovn.smarthub.ui.fragment.SupportFragment;
import org.mangosoft.leagoovn.smarthub.ui.fragment.WarrantyInfoFragment;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.OnFragmentInteractionListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    private ActionBarDrawerToggle toggle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        this.toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_container, WarrantyInfoFragment.newInstance());
        tx.commit();
        setTitle(R.string.side_menu_item_warranty);
        // Start main Service
        Intent startServiceIntent = new Intent(this, MainService.class);
        this.startService(startServiceIntent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment selectedFragment = null;
        if (id == R.id.nav_warranty) {
            selectedFragment = WarrantyInfoFragment.newInstance();
            selectedFragment.setArguments(getIntent().getExtras());
        } else if (id == R.id.nav_store) {
            selectedFragment = StoreFragment.newInstance();
            selectedFragment.setArguments(getIntent().getExtras());
        } else if (id == R.id.nav_product) {
            selectedFragment = ProductFragment.newInstance();
            selectedFragment.setArguments(getIntent().getExtras());
        } else if (id == R.id.nav_promotion) {
            selectedFragment = PromotionFragment.newInstance();
            selectedFragment.setArguments(getIntent().getExtras());
        } else if (id == R.id.nav_list_app) {
            selectedFragment = ListAppFragment.newInstance();
            selectedFragment.setArguments(getIntent().getExtras());
        } else if (id == R.id.nav_support) {
            selectedFragment = SupportFragment.newInstance();
            selectedFragment.setArguments(getIntent().getExtras());
        }
        fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
        fragmentTransaction.commit();
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawer.removeDrawerListener(this.toggle);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
