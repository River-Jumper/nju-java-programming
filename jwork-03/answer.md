### 1、代码工作原理：
首先，我们将图片中的字节码提取出来，这里使用了SteganographyClassLoader类，它继承了URLClassLoader类，重写了findClass方法，使其能够从图片中提取出字节码。

然后，我们将提取出来的字节码转换成类，这里使用了loadClass方法，它继承了ClassLoader类，重写了findClass方法，使其能够将字节码转换成类。

最后，我们将类转换成对象，这里使用了newInstance方法，它继承了Class类，重写了newInstance方法，使其能够将类转换成对象。

### 2、图片URL：

插入排序：https://s1.imagehub.cc/images/2023/10/05/mysort.InsertSorter.png

快速排序：https://s1.imagehub.cc/images/2023/10/05/mysort.QuickSorter.png

### 3、动画链接：

两种排序合成了一个动画：https://asciinema.org/a/612129

### 4、使用同学的图片：

使用了李嘉辛同学的图片，得到了正确的结果。
