package me.xiaofud.photofall.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.xiaofud.photofall.R;

/**
 * Created by smallfly on 16-8-7.
 * 用于控制图片的显示
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.viewHolder> {

    private List<String> imageURLs = new ArrayList<>();
    private Context context;
    private int placeholder;

    public ImageAdapter(List<String> urls, Context context, int placeholder) {
        this.imageURLs = urls;
        this.context = context;
        this.placeholder = placeholder;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_card_layout,
                viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private void setAllVisibility(View[] views, int visibility){
        for (View view: views)
            view.setVisibility(visibility);
    }

    private void setAllAnimation(View[] views, float alpha, long duration_ms, int v1, final int v2){
        for(final View view : views){
            view.setVisibility(v1);
            view.animate().alpha(alpha).setDuration(duration_ms).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(v2);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int position) {
        String imageURL = imageURLs.get(position);
        final View view = viewHolder.itemView;
        final TextView textView = (TextView) view.findViewById(R.id.imageTitle);
        String title = "图片" + (position + 1);
        textView.setText(title);

        // 设置图片
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.with(context)
                .load(imageURL)
                .placeholder(placeholder)
                .into(imageView);

//         因为view会重用, 所以需要每次先还原为默认情况
        final View[] views = new View[]
                {
                    view.findViewById(R.id.horDivLine),
                    view.findViewById(R.id.likeLayout),
                    view.findViewById(R.id.commentLayout),
                    view.findViewById(R.id.verDivLine)
                };
        setAllVisibility(views, View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = views[0].getVisibility();
                if (visibility == View.VISIBLE)
                    setAllAnimation(views, 0.0f, 240, View.VISIBLE, View.GONE);
                else
                    setAllAnimation(views, 1.0f, 140, View.INVISIBLE, View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageURLs.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(View itemView) {
            super(itemView);
        }
    }
}