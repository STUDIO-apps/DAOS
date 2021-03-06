package uk.co.appsbystudio.damealiceowens;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.appsbystudio.damealiceowens.Pages.newsContentViews.NewsList;
import uk.co.appsbystudio.damealiceowens.util.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

	public DatabaseHelper dbHelper;
	public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    dbHelper = new DatabaseHelper(this);
	    db = dbHelper.getWritableDatabase();

	    NewsList list = new NewsList();
	    list.setListenerContext(this);

        setContentView(R.layout.activity_main);
	    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, list).commit();

		if(getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.title_activity_main);
		}
    }
}
