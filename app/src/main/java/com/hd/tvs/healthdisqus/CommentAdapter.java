package com.hd.tvs.healthdisqus;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import commentPOJO.TopicDetail;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    List<TopicDetail> list = new ArrayList<>();
    Context context;

    public CommentAdapter(Context context , List<TopicDetail> list)
    {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<TopicDetail> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.comment_model , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TopicDetail item = list.get(position);


        ImageLoader loader = ImageLoader.getInstance();

        loader.displayImage(item.getUserDetail().get(0).getUserProfile() , holder.image);

        holder.name.setText(item.getUserDetail().get(0).getUsername());
        holder.date.setText(item.getPostTime());
        holder.subject.setText(item.getSubject());

        Translate translate = new Translate();

        try {
            holder.desc.setText(translate.execute(item.getDescription() , Language.ENGLISH , Language.FRENCH));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name , date , subject , desc;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.image);
            name = (TextView)itemView.findViewById(R.id.name);
            date = (TextView)itemView.findViewById(R.id.date);
            subject = (TextView)itemView.findViewById(R.id.subject);
            desc = (TextView)itemView.findViewById(R.id.desc);

        }
    }

}
