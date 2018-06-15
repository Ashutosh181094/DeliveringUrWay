package com.example.a1505197.deliveringurway;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by 1505197 on 9/13/2017.
 */

public class UniversalImageLoader {
    private static final int defaultImage= R.drawable.hut;
    private Context mContext;

    public UniversalImageLoader(Context mContext)  {
        this.mContext = mContext;
    }
    public ImageLoaderConfiguration getConfig()
    {
        DisplayImageOptions defaultOptions=new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .cacheOnDisk(true).cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        ImageLoaderConfiguration  config=new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024)
                .build();
        return config;


    }
    public static void setImage(String imageURL, ImageView imageView, final ProgressBar mProgressBar, String append)
    {
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(append + imageURL,imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageURL, View view) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageURL, View view, FailReason failReason) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageURL, View view, Bitmap loadedImage) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageURL, View view) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
//////////////