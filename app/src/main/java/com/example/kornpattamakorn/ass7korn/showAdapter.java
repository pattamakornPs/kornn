package com.example.kornpattamakorn.ass7korn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class showAdapter extends RecyclerView.Adapter<showAdapter.MyViewHolder> {
    View itemview;

    public showAdapter(Context context, List<showRecycle> listshow) {
        this.context = context;
        Listshow = listshow;
    }

    Context context;
    List<showRecycle> Listshow;
    private TextView txtid;
    private TextView txtname;
    private TextView txttel;
    private TextView txtemail;
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtid;
        private TextView txtname;
        private TextView txttel;
        private TextView txtemail;
        public MyViewHolder(@NonNull View view) {
            super(view);
            txtid = view.findViewById(R.id.std_id);
            txtname = view.findViewById(R.id.std_name);
            txttel = view.findViewById(R.id.std_tel);
            txtemail = view.findViewById(R.id.std_mail);
        }
    }

    @NonNull
    @Override
    public showAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        itemview=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemshow,parent,false);
        context = parent.getContext();
        return new showAdapter.MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        showRecycle sh = Listshow.get(position);
        holder.txtid.setText(sh.getStdid());
        holder.txtname.setText(sh.getName());
        holder.txttel.setText(sh.getTel());
        holder.txtemail.setText(sh.getEmail());
    }


    @Override
    public int getItemCount() {
        return Listshow.size();
    }
}
