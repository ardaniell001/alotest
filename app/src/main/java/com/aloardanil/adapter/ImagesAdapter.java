package com.aloardanil.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.aloardanil.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private List<String> images = new ArrayList<>();

    public ImagesAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_images, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(images.get(position), position);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setItem(List<String> images) {
        this.images = images;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView1;

        private String image;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView1 = (ImageView) itemView.findViewById(R.id.image_item);
            itemView.setOnClickListener(this);
        }

        public void bind(final String image, int position) {
            this.image = image;
            this.position = position;

            Glide.with(itemView)
                    .load(image)
                    .centerCrop()
                    .placeholder(activity.getResources().getDrawable(R.drawable.default_image))
                    .into(imageView1);
        }

        public String getItem() {
            return this.image;
        }

        private int getItemPosition() {
            return this.position;
        }

        @Override
        public void onClick(View v) {
            if (imageClickListener == null) return;
            imageClickListener.onClick(v, getItem(), getItemPosition());
        }
    }

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setImageClickListener(OnImageClickListener listener) {
        imageClickListener = listener;
    }

    private OnImageClickListener imageClickListener;

    public interface OnImageClickListener {

        void onClick(View v, String image, int position);
    }
}
