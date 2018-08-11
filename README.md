# StickinessIndicatorView

---

![preview][1]

### 炫酷的指示器


 布局
 ```java
 <com.jack.cpt.stickinessindicatorview.StickinessIndicatorView
         android:id="@+id/indicator_view"
         android:layout_width="match_parent"
         android:layout_height="100dp" />
```

一行代码 即可配合ViewPager使用 记得setViewPager要放在ViewPager的setAdapter之后
```java
mIndicatorView.setViewPager(mViewPager);
 ```


  [1]: https://raw.githubusercontent.com/SAKURA5460/StickinessIndicatorView/master/preview.gif
