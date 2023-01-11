package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String EXTRA_MESSAGE;
    private MusicAdapter mAdapter;
    private RecyclerView mRecyclerView;
    String message;
    private MyDatabaseHelper dbHelper;
    private boolean firstVisit = true;
    private ArrayList<Music> mMusic;


    //Refresh list when back from DetailActivity because data has updated
    @Override
    public void onRestart() {
        super.onRestart();
        if (!firstVisit) {
            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(layoutManager);
            dbHelper = new MyDatabaseHelper(this, "Music", null, 1);
            mMusic = dbHelper.getAllData();
            MusicAdapter.ClickListener listener = new MusicAdapter.ClickListener() {
                @Override
                public void onMusicClick(View view, int id) {
                    final Music music = mMusic.get(id);
                    //Display details in detailed screen for the selected RecyclerView item
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    message = String.valueOf(music.getId());
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }
            };
            mAdapter = new MusicAdapter(mMusic, listener);
            mRecyclerView.setAdapter(mAdapter);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check whether the table is existed: if existed - do nothing, if not, create table
        if (!sqlTableIsExist("Music")) {
            //Create table
            dbHelper = new MyDatabaseHelper(this, "Music", null, 1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //Insert data into database
            ContentValues values = new ContentValues();
            ArrayList<Music> musicList = Music.getMusic();
            for (int i = 0; i < musicList.size(); i++) {
                values.put("name", musicList.get(i).getName());
                values.put("singer", musicList.get(i).getSinger());
                values.put("rating", musicList.get(i).getRating());
                values.put("likes", (musicList.get(i).isLikes()) ? 1 : 0);
                values.put("description", musicList.get(i).getDescription());
                values.put("imageId", musicList.get(i).getImageId());
                values.put("url", musicList.get(i).getUrl());
                values.put("genre", musicList.get(i).getGenre());
                db.insert("Music", null, values);
                values.clear();
            }
        }

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        // Set the layout manager (Linear)
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        //Get music from database
        dbHelper = new MyDatabaseHelper(this, "Music", null, 1);
        mMusic = dbHelper.getAllData();
        // Implement the ClickListener for the adapter
        MusicAdapter.ClickListener listener = new MusicAdapter.ClickListener() {
            @Override
            public void onMusicClick(View view, int id) {
                final Music music = mMusic.get(id);
                //Display details in detailed screen for the selected RecyclerView item
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                message = String.valueOf(music.getId());
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        };
        // Create an adapter and supply the music data to be displayed
        mAdapter = new MusicAdapter(mMusic, listener);
        // Connect the adapter with the RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        //Set firstVisit false for next time backing and refreshing
        firstVisit = false;
    }


    // Instantiate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    // React to user interaction with the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortName:
                mAdapter.sort(1);
                return true;
            case R.id.sortRating:
                mAdapter.sort(2);
                return true;
            case R.id.sortSinger:
                mAdapter.sort(3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Check whether the table has already created
    private boolean sqlTableIsExist(String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        SQLiteDatabase db = null;
        Cursor cursor = null;
        //check by using table name
        try {
            db = openOrCreateDatabase("Music", Context.MODE_PRIVATE, null);
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
