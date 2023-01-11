package au.edu.unsw.infs3634.musicrecommender;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //Sqlite statement- create music table
    public static final String CREATE_MUSIC = "Create table Music(" +
            "id integer primary key autoincrement," +
            "name text," +
            "singer text," +
            "rating real," +
            "likes integer," +
            "description text," +
            "imageId text," +
            "url text," +
            "genre text)";


    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_MUSIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //Read data from database
    public ArrayList<Music> getAllData() {
        ArrayList<Music> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Music", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            double rating = cursor.getDouble(3);
            boolean likes = ((cursor.getInt(4) == 1) ? true : false);
            String description = cursor.getString(5);
            int imageId = cursor.getInt(6);
            String url = cursor.getString(7);
            String genre = cursor.getString(8);
            Music music = new Music(id, name, singer, rating, likes, description, imageId, url, genre);
            arrayList.add(music);
        }

        return arrayList;
    }
}
