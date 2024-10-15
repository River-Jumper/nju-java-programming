[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/DR4iMN-I)
# J04 211220149 屈子康

## 任务二
请新建`maze`分支，并在该分支上实现迷宫生成算法，然后实现葫芦娃走迷宫（可以用算法指挥葫芦娃走出迷宫，或手工操作键盘指挥葫芦娃走迷宫）

#### 设计概述
将迷宫生成算法中生成的边界、屏障、通道转换为Tile上面的LimitBlock、Wall和空白，将
玩家放置在起点处的Tile上，通过相邻Tile之间的swap方法交换玩家和空白处来达到移动的效果；
设置键盘监听器，监听到WASD按键时分别调用玩家当前所处Tile和上下左右不同Tile之间的交换；

#### 继承关系
游戏逻辑世界中的继承关系如图：
![class.png](resources%2Fclass.png)
游戏屏幕中的继承关系如图：
![screen.png](resources%2Fscreen.png)

#### 迷宫生成算法
改编了github上面的某个迷宫生成算法，添加了边界方块以及起点和终点的获得方法

#### 键盘事件监听
复写了Screen类里面的respondToUserInput方法，以事件驱动的方式实现了对键盘监听事件的回应