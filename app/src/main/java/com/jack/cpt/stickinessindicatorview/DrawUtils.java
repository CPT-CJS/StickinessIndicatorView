package com.jack.cpt.stickinessindicatorview;

import android.content.Context;

public class DrawUtils {
    public static float sDensity = 1.0f;

    public static void init(Context context) {
        sDensity = context.getResources().getDisplayMetrics().density;
    }

    /**
     * dip/dp转像素
     *
     * @param dipVlue dip或 dp大小
     * @return 像素值
     */
    public static int dip2px(float dipVlue) {
        return (int) (dipVlue * sDensity + 0.5f);
    }
}
