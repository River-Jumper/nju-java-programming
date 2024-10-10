
#### 系统执行顺序
1. 接受客户端的输入，更新输入的键鼠信息
2. 射击处理：
   1. ShootSystem(射击并创造新的子弹实体)
3. 碰撞处理：
   1. CollisionDetectionSystem(碰撞检测系统，将碰撞事件加入消息队列)
   2. 并发执行
      - CollisionMoveSystem(碰撞移动)
      - CollisionDamageSystem(碰撞伤害)
      - BulletCollisionSystem(子弹碰撞消失处理)
   3. ClearCollisionEventsSystem(清除碰撞消息队列)
4. 移动处理(必须放在碰撞处理之后)：
   1. RandomMoveSystem(随机移动处理)
   2. TargetMoveSystem(目标方向移动处理)
   3. KeyboardMoveSystem(键盘输入移动处理)
   4. MovementSystem(具体移动)
   5. EdgeDetection(边缘检测)
   6. BulletEdgeDetectSystem(子弹碰撞边缘消失检测)
5. 生命处理(必须放在碰撞处理之后)
   1. DamageSystem(扣除生命值)
   2. HealthSystem(检测生命值是否为0或是否超过上限)
6. 消亡处理：
   DestructionSystem(将具有消亡组件的实体从world中remove)
7. 帧记录系统
   FrameRecordSystem(记录一帧信息，交给sever的输出端处理)

