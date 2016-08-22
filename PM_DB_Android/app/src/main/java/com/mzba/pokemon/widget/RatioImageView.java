/*
 * Copyright 2015 XiNGRZ <chenxingyu92@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mzba.pokemon.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.mzba.pokemon.R;


/**
 * 一个能保持比例的 ImageView
 *
 * @author XiNGRZ
 */
public class RatioImageView extends ImageView {

    private float originalWidth;
    private float originalHeight;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, float originalWidth, float originalHeight) {
        super(context);
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        if (a != null) {
            originalHeight = a.getFloat(R.styleable.RatioImageView_ratioHeigh, 0);
            originalWidth = a.getFloat(R.styleable.RatioImageView_ratioWidth, 0);
            a.recycle();
        }

    }

    /**
     * 设置宽高比例
     */
    public void setOriginalSize(float originalWidth, float originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = originalWidth / originalHeight;

            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            //根据宽和比例计算出对应的高
            if (width > 0) {
                height = (int) ((float) width / ratio);
            }

            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
