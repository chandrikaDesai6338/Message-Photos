package com.work.messagesphotos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.work.messagesphotos.R;
import com.work.messagesphotos.models.Datum;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Constant feilds
    private static final int LOADING = 1;
    private static final int ITEM = 2;

    // flag for footer ProgressBar (i.e. last item of list)
    private boolean isLoadingAdded = false;

    private List<Datum> datumList;
    private Context context;


    public MessageAdapter(Context context, List<Datum> datumList) {
        this.context = context;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new DataViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Datum datum = datumList.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                DataViewHolder dataViewHolder = (DataViewHolder) holder;

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round);

                Glide.with(context)
                        .load(datum.getUserPhotoUrl()) // image url
                        .apply(options)
                        .into(dataViewHolder.imageView);

                dataViewHolder.tweet.setText(datum.getTweet());
                dataViewHolder.name.setText(datum.getName());
                break;
            case LOADING:
//                Do nothing
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == datumList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Datum datum) {
        datumList.add(datum);
        notifyItemInserted(datumList.size() - 1);
    }

    public void addAll(List<Datum> DList) {
        for (Datum d : DList) {
            add(d);
        }
    }

    public void remove(Datum data) {
        int position = datumList.indexOf(data);
        if (position > -1) {
            datumList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Datum());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = datumList.size() - 1;
        Datum item = getItem(position);

        if (item != null) {
            datumList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Datum getItem(int position) {
        return datumList.get(position);
    }

     /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView name, tweet;
        private ImageView imageView;

        public DataViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo);
            tweet = itemView.findViewById(R.id.tweet);
            name = itemView.findViewById(R.id.name);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
