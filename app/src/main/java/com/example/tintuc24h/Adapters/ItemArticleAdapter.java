package com.example.tintuc24h.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tintuc24h.R;
import com.example.tintuc24h.itemArticleModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemArticleAdapter extends RecyclerView.Adapter<ItemArticleAdapter.MyViewHoler> {

    Context mContext;
    List<itemArticleModel> mArticleModelList;
    private static ClickListener clickListener;

    public ItemArticleAdapter(Context mContext, List<itemArticleModel> mArticleModelList) {
        this.mContext = mContext;
        this.mArticleModelList = mArticleModelList;
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_line, parent, false);
        final MyViewHoler viewHoler = new MyViewHoler(view);

//        viewHoler.articleLine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "click item" + viewHoler.getAdapterPosition()+1, Toast.LENGTH_SHORT).show();
//            }
//        });

        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        Picasso.with(mContext).load(mArticleModelList.get(position).image).into(holder.imageArticle);
        holder.textViewTitle.setText(mArticleModelList.get(position).getTitle());
        holder.textViewNameArticle.setText(mArticleModelList.get(position).getNameArticle());
        holder.textViewTime.setText(mArticleModelList.get(position).getTime());
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#2f2f2f"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#393939"));
        }
    }

    @Override
    public int getItemCount() {
        return mArticleModelList.size();
    }

    public static class MyViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageArticle;
        TextView textViewTitle;
        TextView textViewNameArticle;
        TextView textViewTime;
        LinearLayout articleLine;


        public MyViewHoler(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            articleLine = itemView.findViewById(R.id.articleLine);

            imageArticle = itemView.findViewById(R.id.imageArticle);
            imageArticle.setImageResource(R.drawable.article);

            textViewTitle = itemView.findViewById(R.id.titleArticle);
            textViewNameArticle = itemView.findViewById(R.id.nameArticle);
            textViewTime = itemView.findViewById(R.id.timeArticle);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ItemArticleAdapter.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(int position, View v);
    }
}
