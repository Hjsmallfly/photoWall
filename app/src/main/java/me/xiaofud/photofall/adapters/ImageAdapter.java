package me.xiaofud.photofall.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int position) {
        String imageURL = imageURLs.get(position);
        final View view = viewHolder.itemView;
        final TextView textView = (TextView) view.findViewById(R.id.info);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        Picasso.with(context)
                .load(imageURL)
                .placeholder(placeholder)
                .into(imageView);

        // 因为view会重用, 所以需要每次先还原为默认情况
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.photo_interaction_layout);
        layout.setVisibility(View.GONE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int visibility = layout.getVisibility();
                if (visibility == View.VISIBLE) {
                    layout.animate().alpha(0.0f).setDuration(240).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            layout.setVisibility(View.GONE);
                        }
                    });
                }
                else{
                    layout.setVisibility(View.INVISIBLE);
                    layout.animate()
                            .alpha(1.0f)
//                            .translationY(layout.getHeight())
                            .setDuration(140)
                            .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            layout.setVisibility(View.VISIBLE);
                        }
                    });


                }

            }
        });
        String title = "图片" + (position + 1);
        textView.setText(title);

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