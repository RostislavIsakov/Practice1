package ru.rvi.gasstation.noteActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.rvi.gasstation.R;

/**
 * Класс, отвечающий за кастомный адаптер для отображения списка заметок
 *
 * @author Исаков.Р, 17ит17
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList note_id, note_drive, note_fueled, note_paid;

    CustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note_title, ArrayList note_author,
                  ArrayList note_pages) {
        this.activity = activity;
        this.context = context;
        this.note_id = note_id;
        this.note_drive = note_title;
        this.note_fueled = note_author;
        this.note_paid = note_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_drive_txt.setText(String.valueOf(note_drive.get(position)));
        holder.note_fueled_txt.setText(String.valueOf(note_fueled.get(position)));
        holder.note_paid_txt.setText(String.valueOf(note_paid.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(note_id.get(position)));
                intent.putExtra("title", String.valueOf(note_drive.get(position)));
                intent.putExtra("author", String.valueOf(note_fueled.get(position)));
                intent.putExtra("pages", String.valueOf(note_paid.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_id_txt, note_drive_txt, note_fueled_txt, note_paid_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_id_txt = itemView.findViewById(R.id.note_id_txt);
            note_drive_txt = itemView.findViewById(R.id.note_title_txt);
            note_fueled_txt = itemView.findViewById(R.id.note_author_txt);
            note_paid_txt = itemView.findViewById(R.id.note_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
