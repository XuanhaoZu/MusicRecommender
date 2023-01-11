package au.edu.unsw.infs3634.musicrecommender;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    int i = 0;
    private MyDatabaseHelper dbHelper;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbHelper = new MyDatabaseHelper(this, "Music", null, 1);

        //Get the musicId from MainActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        ArrayList<Music> mMusic = dbHelper.getAllData();//get music list from database
        //initialize UI elements
        TextView description = findViewById(R.id.tvDescription);
        TextView name = findViewById(R.id.tvDName);
        TextView singer = findViewById(R.id.tvDSinger);
        ImageView image = findViewById(R.id.ivImage);
        RatingBar ratingBar = findViewById(R.id.rbRatingBar);
        CheckBox likes = findViewById(R.id.cbDLike);
        ImageButton imageButton = findViewById(R.id.ibVideo);
        TextView video = findViewById(R.id.tvVideo);

        for (i = 0; i < mMusic.size(); i++) {
            if (message.equals(String.valueOf(mMusic.get(i).getId()))) {
                // Set title of the activity
                setTitle(mMusic.get(i).getName());
                //set values for UI elements
                name.setText(String.valueOf(mMusic.get(i).getName()));
                singer.setText(String.valueOf(mMusic.get(i).getSinger()));
                description.setText(String.valueOf(mMusic.get(i).getDescription()));
                image.setImageResource(mMusic.get(i).getImageId());
                ratingBar.setRating((float) mMusic.get(i).getRating());
                likes.setChecked(mMusic.get(i).isLikes());
                //get url values for video intent using
                url = String.valueOf(mMusic.get(i).getUrl());
            }
        }


        //Set click event for ratingbar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //update database
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("rating", rating);
                db.update("Music", values, "id = ?", new String[]{message});
            }
        });


        //set click event for checkBox(like)
        likes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (likes.isChecked()) {
                    ContentValues values = new ContentValues();
                    values.put("likes", 1);
                    //update database
                    db.update("Music", values, "id = ?", new String[]{message});
                } else {
                    ContentValues values = new ContentValues();
                    values.put("likes", 0);
                    //update database
                    db.update("Music", values, "id = ?", new String[]{message});
                }
            }
        });


        //set Video button & text
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}

