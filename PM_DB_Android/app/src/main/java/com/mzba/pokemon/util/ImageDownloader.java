package com.mzba.pokemon.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.mzba.pokemon.R;
import com.mzba.pokemon.view.BasicActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片加载处理
 * Created by 06peng on 2015/3/4 0004.
 */
public class ImageDownloader {

    private static final Object lock = new Object();

    private static ImageDownloader instance;

    private static Handler handler;

    private ImageDownloader(Handler handler) {
        this.handler = handler;
    }

    public static ImageDownloader getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new ImageDownloader(new Handler(Looper.getMainLooper()));
            }
        }
        return instance;
    }


    @SuppressLint("NewApi")
    public void displayImage(Context context, String url, ImageView imageView, int width, int height, int placeHolder) {
        if (context == null || !(context instanceof BasicActivity)) {
            return;
        }
        BasicActivity activity = (BasicActivity) context;
        if (activity.isFinishing()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (activity.isDestroyed()) {
                return;
            }
        }
        Glide.with(activity)
                .load(url)
                .crossFade()
                .override(width, height)
                .centerCrop()
                .placeholder(placeHolder)
                .dontAnimate()
                .into(imageView)
                .getSize(new SizeReadyCallback() {
                @Override
                public void onSizeReady(int width, int height) {

                }
            });
    }

    @SuppressLint("NewApi")
    public void displayImage(Fragment fragment, String url, ImageView imageView, int width, int height) {
        if (fragment.isDetached()) {
            return;
        }
        Glide.with(fragment)
                .load(url)
                .crossFade()
                .override(width, height)
                .centerCrop()
                .placeholder(R.color.cardview_light_background)
                .dontAnimate()
                .into(imageView);
    }

    @SuppressLint("NewApi")
    public void displayImage(Fragment fragment, String url, ImageView imageView, int width, int height, int thumbnail) {
        if (fragment.isDetached()) {
            return;
        }
        Glide.with(fragment)
                .load(url)
                .crossFade()
                .override(width, height)
                .centerCrop()
                .placeholder(thumbnail)
                .dontAnimate()
                .into(imageView);
    }

    public void displaySourceImage(Activity activity, String url, final View progressBar, ImageView imageView) {
        if (activity.isFinishing()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (activity.isDestroyed()) {
                return;
            }
        }
        progressBar.setVisibility(View.VISIBLE);

        DrawableTypeRequest<String> request = Glide.with(activity).load(url);
        request.override(SimpleTarget.SIZE_ORIGINAL, SimpleTarget.SIZE_ORIGINAL);
        request.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        request.centerCrop()
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);


    }


    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public void savePhoto(final BasicActivity activity, final String url) {
        if (activity.isFinishing()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (activity.isDestroyed()) {
                return;
            }
        }
        DrawableTypeRequest<String> request = Glide.with(activity).load(url);
        request.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        request.centerCrop()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                        return false;
                    }
                }).into(new SimpleTarget<GlideDrawable>(SimpleTarget.SIZE_ORIGINAL, SimpleTarget.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                if (resource != null) {
                    Bitmap bitmap = drawableToBitmap(resource);
                    String fileName = url.substring(url.lastIndexOf("/") + 1);
                    String newPath = Utils.getSDPath() + "/photo/";
                    File file = new File(newPath);
                    if (!file.isDirectory()) {
                        file.mkdirs();
                    }
                    file = new File(newPath, fileName);
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    FileOutputStream out;
                    try {
                        out = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.flush();
                        out.close();
                        Utils.forceRefreshSystemAlbum(activity, newPath);
                        Utils.scanPhoto(activity, newPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

}
