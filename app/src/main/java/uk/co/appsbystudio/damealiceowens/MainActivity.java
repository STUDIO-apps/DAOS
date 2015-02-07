package uk.co.appsbystudio.damealiceowens;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import uk.co.appsbystudio.damealiceowens.Pages.Home;
import uk.co.appsbystudio.damealiceowens.Pages.Login;
import uk.co.appsbystudio.damealiceowens.Pages.News;
import uk.co.appsbystudio.damealiceowens.Pages.Schedule;

public class MainActivity extends ActionBarActivity  {

    private String[] items;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence title;
    private CharSequence drawerTitle;

	private Fragment fragment_login = new Login();
	private Fragment fragment_home = new Home();
	private Fragment fragment_news = new News();
	private Fragment fragment_schedule = new Schedule();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment_home).commit();

        title = drawerTitle = getTitle();
        items = getResources().getStringArray(R.array.main_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
	        @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

	        @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        listView = (ListView) findViewById(R.id.left_drawer);

        listView.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, items));
        listView.setSelection(0);

        listView.setOnItemClickListener(new DrawerItemCLickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	    return drawerToggle.onOptionsItemSelected(item) || item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

	@Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(listView)) {
            drawerLayout.closeDrawer(listView);
        } else {
            super.onBackPressed();
        }
    }

    public class DrawerItemCLickListener implements ListView.OnItemClickListener {
	    Map<Integer, Fragment> fragments = new HashMap<>();

	    public DrawerItemCLickListener() {
		    fragments.put(0, fragment_home);
		    fragments.put(1, fragment_news);
		    fragments.put(2, fragment_schedule);
	    }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragments.get(position)).addToBackStack(null).commit();

	        listView.setItemChecked(position, true);
	        setTitle(items[position]);
	        drawerLayout.closeDrawer(listView);
        }
    }
}