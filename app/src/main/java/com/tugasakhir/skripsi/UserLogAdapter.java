package com.tugasakhir.skripsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class UserLogAdapter extends RecyclerView.Adapter<UserLogAdapter.modelViewHolder> {

    List<PojoLogUser> myList;
    Context context;

    public UserLogAdapter(List<PojoLogUser> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public modelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.loguser_item, viewGroup, false);
        modelViewHolder obj = new modelViewHolder(view);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull modelViewHolder modelViewHolder, int i) {
        modelViewHolder.date.setText(myList.get(i).getWaktuLog());
        modelViewHolder.user.setText(myList.get(i).getUserLog());
        modelViewHolder.information.setText(myList.get(i).getInfoLog());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class modelViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView user;
        TextView information;
        public modelViewHolder(View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.txtWaktu);
            user=itemView.findViewById(R.id.txtUser);
            information=itemView.findViewById(R.id.txtinformation);
        }
    }
}