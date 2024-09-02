# Component 设计说明
## 组件应当只存储数据而不具备除了构造函数之外的其他方法
### 血量：HealthComponent
``int currentHealth; int maxHealth``
### 可攻击：AttackableComponent
``
### 可移动：MovableComponent

### 位置：PositionComponent
'int x; int y'
### 碰撞体积：CollisionComponent(简单设置成圆)