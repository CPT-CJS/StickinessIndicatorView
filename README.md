# StickinessIndicatorView

---

![preview][1]

炫酷的指示器
========
- 把项目的Indicator类复制到你项目去，然后就可以用啦。
- 为什么我不用gradle依赖的方式提供给大家？ 因为我觉得有了源码，你们想干嘛就干嘛不是更好吗。
- 虽然懒也是一部分原因。

使用
========

### 布局

>记得改这个前缀(com.xxx.xxx.xxx)，改成你自己目录下的路径

 ```java
 <com.xxx.xxx.xxx.StickinessIndicatorView
         android:id="@+id/indicator_view"
         android:layout_width="match_parent"
         android:layout_height="100dp" />
```

>一行代码 即可配合ViewPager使用 记得setViewPager要放在ViewPager的setAdapter之后
```java
mIndicatorView.setViewPager(mViewPager);
 ```


  [1]: https://raw.githubusercontent.com/SAKURA5460/StickinessIndicatorView/master/preview.gif
