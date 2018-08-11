package com.jack.cpt.stickinessindicatorview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by cpt on 2018/8/7.
 * 不知道叫啥效果的指示器
 */
public class StickinessIndicatorView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mSelectColor = 0xffff4d4d;//255,77,77
    private int mNormalColor = 0xffd8d8d8;//216,216,216
    private int mLineHeight = DrawUtils.dip2px(4);
    private int mLineSelectWidth = DrawUtils.dip2px(18);
    private int mLineNormalWidth = DrawUtils.dip2px(4);
    private int mIntervalWidth = mLineSelectWidth - mLineNormalWidth;//选中长度和未选中长度之差
    private int mRadius = DrawUtils.dip2px(4);
    private int mPadding = DrawUtils.dip2px(3);
    private int mCount = 0;
    private int mStartX = 0;//开始的X坐标
    private int mStartY = 0;//开始的Y坐标
    float mPositionOffset = 0f;//一开始是没移动的，所以是0
    float mAllOffset = 0f;
    private int mLeftSelect;//动画过程中的左边的index
    private int mRightSelect;//动画过程中的右边的index

    public StickinessIndicatorView(Context context) {
        this(context, null);
    }

    public StickinessIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickinessIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mStartX = (mWidth - (mLineSelectWidth + ((mCount - 1) * mLineNormalWidth) + ((mCount - 1) * mPadding))) / 2;
        mStartY = (mHeight - mLineHeight) / 2;
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager.getAdapter() == null) return;
        viewPager.removeOnPageChangeListener(mListener);
        viewPager.addOnPageChangeListener(mListener);
        mCount = viewPager.getAdapter().getCount();
        invalidate();
    }

    ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mAllOffset = positionOffset + position;
            mLeftSelect = (int) mAllOffset;
            mRightSelect = mLeftSelect + 1;
            mPositionOffset = positionOffset;
            invalidate();
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF;
        for (int i = 0; i < mCount; i++) {
            rectF = new RectF();
            if (i == 0) {
                rectF.left = mStartX;
            } else {
                if (i > mLeftSelect) {
                    rectF.left = mStartX + (i * mPadding) + (mLineSelectWidth - (i == mRightSelect ? (mPositionOffset * mIntervalWidth) : 0) + mLineNormalWidth * (i - 1));
                } else {
                    rectF.left = mStartX + (i * mPadding) + (mLineNormalWidth * i);
                }
            }

            if (i > mLeftSelect) {
                rectF.right = mStartX + (i * mPadding) + (mLineSelectWidth + mLineNormalWidth * i);
            } else if (i < mLeftSelect) {
                rectF.right = mStartX + (i * mPadding) + (mLineNormalWidth * (i + 1));
            } else {
                rectF.right = mStartX + (i * mPadding) + (i == mLeftSelect ? mLineSelectWidth - (mPositionOffset * mIntervalWidth) : mLineNormalWidth) + (i * mLineNormalWidth);
            }

            rectF.top = mStartY;
            rectF.bottom = mStartY + mLineHeight;

            //255,77,77 216,216,216
            //255-216 = 39
            //216-77  = 139

            if (i == mLeftSelect) {
                mPaint.setColor(Color.rgb((int) (255 - mPositionOffset * 39), (int) (77 + mPositionOffset * 139), (int) (77 + mPositionOffset * 139)));
            } else if (i == mRightSelect) {
                mPaint.setColor(Color.rgb((int) (216 + mPositionOffset * 39), (int) (216 - mPositionOffset * 139), (int) (216 - mPositionOffset * 139)));
            } else {
                mPaint.setColor(mNormalColor);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);
            } else {
                canvas.drawRect(rectF, mPaint);
            }
        }
    }
}
