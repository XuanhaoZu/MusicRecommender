package au.edu.unsw.infs3634.musicrecommender;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> implements Filterable {
    private List<Music> mMusic;
    private List<Music> mMusicFiltered;
    private ClickListener mListener;
    private MyDatabaseHelper dbHelper;
    public Context mContext;

    // A constructor method for the adapter class
    MusicAdapter(List<Music> music, ClickListener listener) {
        this.mMusic = music;
        this.mMusicFiltered = music;
        this.mListener = listener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mMusicFiltered = mMusic;
                } else {
                    ArrayList<Music> filteredList = new ArrayList<>();
                    for (Music music : mMusic) {
                        if (music.getName().toLowerCase().contains(charString.toLowerCase()) ||
                                music.getSinger().toLowerCase().contains(charString.toLowerCase()) ||
                                music.getGenre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(music);
                        }
                    }
                    mMusicFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mMusicFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mMusicFiltered = (ArrayList<Music>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    // ClickListener interface
    public interface ClickListener {
        void onMusicClick(View view, int id);
    }

    // Create a view and return it
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_row, parent, false);
        mContext = parent.getContext();
        return new MyViewHolder(view, mListener);
    }

    // Associate the data with the view holder for a given position in the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Retrieve the country by it's position in filtered list
        final Music music = mMusicFiltered.get(position);
        int id = position;
        holder.name.setText(music.getName());
        holder.singer.setText(music.getSinger());
        holder.genre.setText(music.getGenre());
        holder.ratingBar.setRating((float) music.getRating());
        holder.likes.setChecked(music.isLikes());
        holder.itemView.setTag(id);

        //Checkbox click event for Like attribute
        holder.likes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Music music = mMusic.get(id);
                String m = String.valueOf(music.getId());
                dbHelper = new MyDatabaseHelper(mContext, "Music", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (holder.likes.isChecked()) {
                    ContentValues values = new ContentValues();
                    values.put("likes", 1);
                    db.update("Music", values, "id = ?", new String[]{m});//update data into database
                } else {
                    ContentValues values = new ContentValues();
                    values.put("likes", 0);
                    db.update("Music", values, "id = ?", new String[]{m});//update data into database
                }
            }
        });
    }


    // Return the number of data items available to display
    @Override
    public int getItemCount() {
        return mMusicFiltered.size();
    }

    // Extend the signature of CountryViewHolder to implement a click listener
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, singer, genre;
        public ImageView imageId;
        private ClickListener listener;
        private CheckBox likes;
        public RatingBar ratingBar;

        // A constructor method for CountryViewHolder class
        public MyViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(MyViewHolder.this);
            name = itemView.findViewById(R.id.tvName);
            singer = itemView.findViewById(R.id.tvSinger);
            genre = itemView.findViewById(R.id.tvGenre);
            ratingBar = itemView.findViewById(R.id.rbRating);
            likes = itemView.findViewById(R.id.cbLike);
            singer.setSelected(true);
        }

        @Override
        public void onClick(View v) {
            listener.onMusicClick(v, (Integer) v.getTag());

        }
    }

    //Sort method (Name & Rating)
    public void sort(final int sortMethod) {
        if (mMusicFiltered.size() > 0) {
            Collections.sort(mMusicFiltered, new Comparator<Music>() {
                @Override
                public int compare(Music o1, Music o2) {
                    if (sortMethod == 1) {
                        return o1.getName().compareTo(o2.getName());
                    } else if (sortMethod == 2){
                        return -String.valueOf(o1.getRating()).compareTo(String.valueOf(o2.getRating()));}
                    else if(sortMethod == 3){
                        return o1.getSinger().compareTo(o2.getSinger());
                    }else
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        notifyDataSetChanged();
    }
}
