![](images/icon.png)

HenCoder 绘制 2 练习项目
===

### 这是什么？

这不是一个独立使用的项目，它是 [HenCoder Android 开发进阶：UI 1-2 Paint 详解](http://hencoder.com/ui-1-2) 的配套练习项目。

### 怎么用？

项目是一个可以直接运行的 Android App 项目，项目运行后，在手机上打开是这样的：

![](images/preview.png)

工程下有一个 `/practice` 目录：

![](images/project_practice.png)

你要做的是就是，在 `/practice` 下的每一个 `PracticeXxxView.java` 文件中写代码，绘制出和页面上半部分相同的效果。例如写 `PracticeLinearGradientView.java` 以绘制出用线性渐变来绘制颜色的圆形。就像这样：

![](images/preview_after.png)

> 当然，没必要做得和示例一毛一样。这是一个练习，而不是一个超级模仿秀，关键是把技能掌握。

如果做不出来，可以参考 `/sample` 目录下的代码：

![](images/project_sample.png)

练习做完，绘制第二期（Paint 详解）的内容也就掌握得差不多了。

### 关于有些方法无效

现在的 Android 默认是开启了硬件加速的，而 Canvas 和 Paint 有一些方法是不支持硬件加速的，你需要把它手动关闭才行。硬件加速的支持情况和手动关闭硬件加速的方法你可以看一下这个 Android 的[官方文档](https://developer.android.com/guide/topics/graphics/hardware-accel.html)。

以后我会专门用一节来讲硬件加速和图像的离屏缓冲（off-screen buffer），现在先不多说了。













设定着色器 shader - 渐变色
paint.setShader()

线性渐变
LinearGradient

镭射渐变
RadialGradient

扫描渐变
SweepGradient

图形着色
BitmapShader

>> Shader.TileMode
>>>> CLAMP:  在着色端点之外显示端点的颜色，看起来就像是端点处颜色延伸把着色部分夹住似的
>>>> MIRROR： 镜子模式，着色部分整体反转
>>>> REPEAT： 重复模式，着色部分整体重复

组合shader
ComposeShader
>> PorterDuff.Mode
>>>> SRC_IN：只显示交集部分 交集部分显示为src的图像
>>>> DST_IN： 只显示交集部分 交集部分显示为dst的图像
... 好多种模式


-----

颜色过滤器：
ColorFilter

LightingColorFilter
构造中有两个参数
1. mul
2. add

mul: 基础是 0xFFFFFF
如果这个参数设定为 0x00FFFF, 则在
paint.setColorFilter(lightingColorFilter) 
之后，paint中就没有红色部分了。

比如


// 使用 Paint.setColorFilter() 来设置 LightingColorFilter
ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
paint.setColorFilter(lightingColorFilter);

paint.setColor(Color.RED); // 因为设定的 color filter 中过滤掉了红色，所以最终显示的是黑色
canvas.drawCircle(100, 100, 50, paint);


Xfermode
paint.setXfermode()
设定图片组合方式类似于，ComposeShader的PorterDuff的不同mode

屏幕快照 2018-05-12 上午10.58.23.png 471 KB View full-size Download


线条形状 StrokeCap
paint.setStrokeCap
>> Paint.Cap.BUTT -- 默认，平头，无附加像素
>> Paint.Cap.ROUND -- 附加像素为圆头
>> Paint.Cap.SQUQRE -- 附加像素为平头

如图


006tNc79ly1fig74qv8rij30ct05rglp.jpg 8.66 KB View full-size Download



路径拐角形状
paint.setStrokeJoin
>> Paint.Join.MITTER
>> Paint.Join.BEVEL
>> Paint.Join.ROUND


006tNc79ly1fig75e27w6j30cp05ewem.jpg 11.1 KB View full-size Download



路径拐角形状miter限定
paint.setStrokeMiter(1)
从左到右依次是 1 - 2 - 5

屏幕快照 2018-05-12 上午11.01.48.png 16.8 KB View full-size Download



miter计算方式

006tNc79ly1fig7btolhij30e706dglp.jpg 9.49 KB View full-size Download



设定路径样式
paint.setPathEffect(pathEffect)

>> CornerPathEffect path的顶点是圆角
>> DiscretePathEffect 整个path是离散的一段一段的线段组成具有指定的随机偏移量
>> DashPathEffect  整个path是虚线组成
>> PathDashPathEffect 先用path画一个图像出来，然后整个path上的dash就用这个图像取代DashPathEffect中的线段
>> SumPathEffect path上显示两个path effect的组合
>> ComposePathEffect path上显示两个path effect组合之后的结果



屏幕快照 2018-05-12 上午11.08.45.png 88.5 KB View full-size Download


设置阴影
paint.setShadowLayer(.)


paint.setShadowLayer(10, 15, 5, Color.RED);
paint.setTextSize(120);
canvas.drawText("Hello HenCoder", 50, 200, paint);


屏幕快照 2018-05-12 上午11.19.30.png 44 KB View full-size Download


设置背景虚化
paint.setMaskFilter
>> BlurMaskFilter, BlurMaskFilter.Blur.NORMAL / INNER / OUTER / SOLID


-----


得到绘制路径
paint.getFillPath(srcPath, dstPath)

paint.getTextPath(..)
