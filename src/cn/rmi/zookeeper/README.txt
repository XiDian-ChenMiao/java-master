1.首先使用ZooKeeper的客户端工具创建一个持久性ZNode，名为“/registry”，该节点不存放任何数据，使用如下命令：
    create /registry null

2.根据以下步骤验证 RMI 服务的高可用性：
（1）运行两个 Server 程序，一定要确保 port 是不同的。
（2）运行一个 Client 程序。
（3）停止其中一个 Server 程序，并观察 Client 控制台的变化（停止一个 Server 不会导致 Client 端调用失败）。
（4）重新启动刚才关闭的 Server 程序，继续观察 Client 控制台变化（新启动的 Server 会加入候选）。
（5）先后停止所有的 Server 程序，还是观察 Client 控制台变化（Client 会重试连接，多次连接失败后，自动关闭）。