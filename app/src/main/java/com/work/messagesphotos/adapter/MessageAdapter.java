package com.work.messagesphotos.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.work.messagesphotos.R;
import com.work.messagesphotos.models.Datum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                /*Glide.with(context)
                        .load(datum.getUserPhotoUrl()) // image url
                        .apply(options)
                        .into(dataViewHolder.imageView);*/

                String url = ExtractUrl(datum.getTweet());
                String tweetOnly = datum.getTweet().replace(url,"");
                dataViewHolder.tweet.setText(tweetOnly);

                SpannableString content = new SpannableString(url.trim());
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                dataViewHolder.url.setText(content);
                final String _url = url.trim();
                dataViewHolder.url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(_url));
                    context.startActivity(i);
                }
            });

                dataViewHolder.name.setText(datum.getName());
                dataViewHolder.screenname.setText(datum.getScreenName());
                dataViewHolder.social.setText("via " + datum.getSocialType());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                try {
                    Date date = dateFormat.parse(datum.getOriginalPostDateTime());
                    String dayOfTheWeek = (String) DateFormat.format("E", date);
                    String monthString  = (String) DateFormat.format("MMM",  date);
                    String time  = (String) DateFormat.format("HH:mm",  date);
                    String year = (String) DateFormat.format("yyyy", date); // 2013
                    dataViewHolder.datetime.setText(dayOfTheWeek +" "+ monthString +" "+ year +" "+ time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
            case LOADING:
//                Do nothing
                break;
        }
    }



    private String ExtractUrl(String tweet) {
        // Pattern for recognizing a URL, based off RFC 3986

        String containedUrls = "";
          final Pattern urlPattern = Pattern.compile(
                "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                        + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                        + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher urlMatcher = urlPattern.matcher(tweet);
        while (urlMatcher.find())
        {
            containedUrls = tweet.substring(urlMatcher.start(0),
                    urlMatcher.end(0));
        }

        return containedUrls;
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
        private TextView name, tweet, screenname, datetime, social, url;
        private ImageView imageView;

        public DataViewHolder(View itemView) {
            super(itemView);
          //  imageView = itemView.findViewById(R.id.photo);
            tweet = itemView.findViewById(R.id.tweet);
            screenname = itemView.findViewById(R.id.screenname);
            name = itemView.findViewById(R.id.name);
            datetime = itemView.findViewById(R.id.time);
            url = itemView.findViewById(R.id.url);
            social = itemView.findViewById(R.id.social);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
