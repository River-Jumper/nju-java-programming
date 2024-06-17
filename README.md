# task1

代码结构与gourds分支代码基本相同，在原来代码的基础上，增加了一个新的类：Monster， Monster继承自Creature，拥有其的所有属性， 
同时也实现了接口Comparable， 重写了compareTo方法，使得Monster可以彼此之间进行排序，之后修改了WorldScreen， 
将其中原本的葫芦娃的一维数组改为妖精的二维数组，同时也修改了其中对应的的一些方法，使得妖精也可以按照原来的逻辑运行， 
传递给BubbleSorter时将二维数组改为一维进行传递，从而在只更改WorldScreen的情况下实现了妖精的排序。

为了方便录屏，修改了Main类，使其能够自动运行而不需等待键盘输入。

运行结果：https://www.bilibili.com/video/BV1UB4y1d77z/
