<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">yhchat-sdk-core</h1>
<h4 align="center">云湖Java SDK，引入此 Core 可以快速构建您的云湖机器人</h4>
<p align="center">
<a href='https://gitee.com/daenmax/yhchat-sdk-core/stargazers'>
<img src='https://gitee.com/daenmax/yhchat-sdk-core/badge/star.svg?theme=dark' alt='star'>
</a>
<a href='https://github.com/daenmax/yhchat-sdk-core'>
<img src='https://img.shields.io/github/stars/daenmax/yhchat-sdk-core.svg?style=social&label=Stars' alt='star'>
</a>
<a href="https://github.com/daenmax/yhchat-sdk-core">
<img src="https://img.shields.io/badge/version-v1.0.3-brightgreen.svg">
</a>
</p>

## 🐻‍❄️ 介绍

云湖SDK Core服务，引入此 Core 可以快速构建您的云湖机器人，能让您以非常便捷的方式和云湖服务进行交互。

Core版本支持情况

| Core版本      | SpringBoot | JDK |
|-------------|------------|-----|
| 1.1.0及以上    | 3.x        | 17  |
| 1.1.0以下（不含） | 2.x        | 8   |
## 🦊 链接

### 文档教程

[文档教程](https://gitee.com/daenmax/yhchat-sdk-core/wikis)

### 官方Demo

[yhchat-sdk-demo](https://gitee.com/daenmax/yhchat-sdk-demo)

### 云湖官网

[云湖社交](https://www.yhchat.com/)

## 🦄 实现

### 事件

- [x] 普通消息事件
- [x] 指令消息事件
- [x] 关注机器人事件
- [x] 取消关注机器人事件
- [x] 消息中按钮点击事件
- [x] 加入群事件
- [x] 退出群事件
- [x] 机器人设置事件

### 接口

- [x] 发送消息
- [x] 批量发送消息
- [x] 编辑消息
- [x] 撤回消息
- [x] 设置看板
- [x] 取消设置看板

## 🕊️ 快速开始

### 新建项目

### 引入POM依赖

```xml
<!-- 云湖yhchat-sdk-core -->
<dependency>
    <groupId>cn.daenx</groupId>
    <artifactId>yhchat-sdk-core</artifactId>
    <version>最新版本</version>
</dependency>
```

### 在启动类上添加包扫描

```java
//"cn.daenx.yhchatDemo.plugin" 是当前demo的插件所在包路径
@ComponentScan({"cn.daenx.yhchatsdk", "cn.daenx.yhchatDemo.plugin"})
```

### 在配置文件中添加配置

```yml
yhchat:
  # 是否打印原始消息日志
  printLog: true
  # 机器人发送消息Token，在控制台中可以看到
  token: "5e4exxxxxxxxxxxxxxxe3dff6760"
```

### 开发插件

根据你的业务需要，实现相对应的事件接口即可，同一事件可多次实现

## ⚡ 反馈与交流

在使用过程中有任何问题和想法，请给我提 [Issue](https://gitee.com/daenmax/yhchat-sdk-core/issues)

你也可以在Issue查看别人提的问题和给出解决方案。

或者加入我们的交流群：

访问加入云湖群聊[【云湖Java SDk交流群】](https://yhfx.jwznb.com/share?key=Y9EOkHcu5KYP&ts=1686711472)


<table>
  <tbody>
    <tr>
      <td align="center" valign="middle">
        <img src="https://img.cdn.apipost.cn/client/user/0/avatar/748dd95d0520f728a75156a010ed837864892dfded51b.png" alt="群号: 373752019" style="width:100px;margin: 10px;">
        <p>群ID: 471883451</p>
      </td>
    </tr>
  </tbody>
</table>

## 🐽 后记

