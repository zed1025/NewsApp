package inc.thebest.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chootdev.recycleclick.RecycleClick;

import java.util.List;

/**
 * Created by amitkumar on 24/01/18 at 20:12.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    private List<News> newsList;
    private CardView cardView;
    private Context context;

    public NewsAdapter(List<News> dataNewsList, Context context) {
        this.newsList = dataNewsList;
        this.context = context;
    }

    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.MyViewHolder holder, int position) {
        final News news = newsList.get(position);
        holder.newsTitle.setText(news.getNews_title());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Code for Implicit Intent
                 */
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getWebURl()));
                //context.startActivity(intent);

                Intent intent = new Intent(context, WebBrowser.class);
                intent.putExtra("toLoadUrl", news.getWebURl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView newsTitle;
        public CardView cardView;

        public MyViewHolder(View view){
            super(view);
            newsTitle = view.findViewById(R.id.news_title);
            cardView = view.findViewById(R.id.news_card);
        }
    }
}
