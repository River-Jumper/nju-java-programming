# task2

使用迷宫生成算法生成一个迷宫，然后按照迷宫矩阵来放置墙和路，在WorldScreen中初始化时实现该过程，然后初始化一个葫芦娃，将其放在迷宫入口，
然后修改respondToUserInput来使得葫芦娃可以通过方向键在迷宫中移动，直到到达迷宫出口。

运行结果：https://www.bilibili.com/video/BV1TQ4y1s74L/

新增加了异常处理，新增了两个异常OutOfRangeException和UnreachableException，分别用于处理越界和无法到达的情况。