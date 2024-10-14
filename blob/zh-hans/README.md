[<img alt="PowerNukkitX" width="838" src="https://raw.githubusercontent.com/PowerNukkitX/PowerNukkitX/master/blob/images/PNX_BANNER.png" />](https://www.powernukkitx.com)

<h2>🌐Need to switch languages?&ensp;/&ensp;多语言文档</h2>

[![Discord](https://img.shields.io/discord/944227466912870410?style=flat-square)](https://discord.gg/BcPhZCVJHJ)
[![English](https://img.shields.io/badge/English-100%25-green?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/blob/master/README.md)
[![简体中文](https://img.shields.io/badge/简体中文-100%25-green?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/blob/master/blob/zh-hans/README.md)
[![繁體中文](https://img.shields.io/badge/繁體中文-100%25-green?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/blob/master/blob/zh-hant/README.md)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/blob/master/LICENSE)
[![ChangeLog](https://img.shields.io/badge/ChangeLog-blue?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/blob/master/blob/zh-hans/CHANGELOG.md)
[![FAQ](https://img.shields.io/badge/FAQ-blue?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/wiki/FAQ)
[![PNX-DOC](https://img.shields.io/badge/PNX-DOC文档库-blue?style=flat-square)](https://doc.powernukkitx.cn)
[![Maven Central](https://img.shields.io/maven-central/v/cn.powernukkitx/powernukkitx.svg?label=Maven%20Central&style=flat-square)](https://search.maven.org/search?q=g:%22cn.powernukkitx%22%20AND%20a:%22powernukkitx%22)
[![](https://www.jitpack.io/v/PowerNukkitX/PowerNukkitX.svg)](https://www.jitpack.io/#PowerNukkitX/PowerNukkitX)

🤔什么是PowerNukkitX ？
---
[PowerNukkitX](https://github.com/PowerNukkitX/PowerNukkitX)是基于[PowerNukkit](https://github.com/PowerNukkit/PowerNukkit)的一个分支版本，由[PowerNukkitX](https://github.com/PowerNukkitX)小组负责开发维护，如遇任何问题可提交[Issue](https://github.com/PowerNukkitX/PowerNukkitX/issues)反馈。

加入我们:

* [Discord](https://discord.gg/BcPhZCVJHJ)
* [QQ](https://jq.qq.com/?_wv=1027&k=6rm3gbUI)

PowerNukkitX的优势：

1. 支持1.19.70协议。
2. 原生支持384限高（目前仅限主世界，其余则为256格限高）。
3. 原生支持地狱世界，无需另外安装插件补丁等。
4. 支持3D生物群系（完善中）。<!--自定义维度接口 TODO-->
5. 原生支持香草命令和命令方块等（完善中）。
6. 内置[Terra](https://github.com/PolyhedralDev/Terra)
   地形生成器（如有问题[点此查看](https://doc.powernukkitx.cn/zh-cn/faq/Terra%E9%97%AE%E9%A2%98.html)）。
7. 支持使用JavaSrcipt语言编写插件 （可在[此处](https://doc.powernukkitx.cn/zh-cn/plugin-dev/js/%E6%A6%82%E8%BF%B0.html)
   查找开发文档）。
8. 支持自定义方块 / 物品 / 实体（完善中，[文档](https://doc.powernukkitx.cn)待补充）。
9. 内置生物AI，无需安装MobPlugin（开发中，未完善）。
10. Todo...

---

## 🎮 如何使用

### 1.从[PNX-CLI](https://github.com/PowerNukkitX/PNX-CLI)启动:

1. 从[release](https://github.com/PowerNukkitX/PNX-CLI/releases)下载`PNX-CLI-Jar.zip`最新版本
2. 运行`java -jar PNX-CLI-0.0.1-alpha.jar`

您还可以在[此处](https://doc.powernukkitx.cn/zh-cn/%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8.html)查阅更多信息。

### 2.从命令启动:

PowerNukkitX要求的最低Java版本为17,请自行安装且配置环境变量.

1. 从[release](https://github.com/PowerNukkitX/PowerNukkitX/releases)下载libs.tar.gz和powernukkitx.jar
2. 将libs.tar.gz中的libs文件夹解压到和powernukkitx.jar同一路径下
3. 运行以下命令

Windows版本

```
java -Dfile.encoding=UTF-8 ^
-Djansi.passthrough=true ^
-Dterminal.ansi=true ^
-XX:+UnlockExperimentalVMOptions ^
-XX:+UseG1GC ^
-XX:+UseStringDeduplication ^
-XX:+EnableJVMCI ^
--module-path=.\libs\graal-sdk-22.2.0.jar;.\libs\truffle-api-22.2.0.jar; ^
--add-opens java.base/java.lang=ALL-UNNAMED ^
--add-opens java.base/java.io=ALL-UNNAMED ^
-cp .\powernukkitx.jar;.\libs\* ^
cn.nukkit.Nukkit
```

Linux版本

```
java -Dfile.encoding=UTF-8 \
-Djansi.passthrough=true \
-Dterminal.ansi=true \
-XX:+UnlockExperimentalVMOptions \
-XX:+UseG1GC \
-XX:+UseStringDeduplication \
-XX:+EnableJVMCI \
--module-path=./libs/truffle-api-22.2.0.jar:./libs/graal-sdk-22.2.0.jar: \
--add-opens java.base/java.lang=ALL-UNNAMED \
--add-opens java.base/java.io=ALL-UNNAMED \
-cp ./powernukkitx.jar:./libs/* \
cn.nukkit.Nukkit
```

### 3.从docker启动:

https://hub.docker.com/r/coolloong/powernukkitx

---

📌 声明
---

**PowerNukkitX**
是基于PowerNukkit和Nukkit的修改优化版本，修复了其BUG，添加了更多的功能支持等，如[LiteLoader](https://github.com/PowerNukkitX/LiteLoader-Libs)
（完善中）插件兼容等。

* 请注意**PowerNukkitX**
  非Cloudburst的开发人员维护，它依靠的是开源社区的开发者们用爱发电，如果您在使用PowerNukkitX时遇到了发现了任何问题，您首先应该在此存储库[创建一条issue](https://github.com/PowerNukkitX/PowerNukkitX/issues)
  （同时请注意阅读[贡献帮助指南](https://github.com/PowerNukkitX/PowerNukkitX/blob/master/CONTRIBUTING.md)中的规定）。

* 同时我们推荐您进行定期备份等操作，并使用为[PowerNukkitX](https://github.com/PowerNukkitX/PowerNukkitX)
  制作的插件，并使用[稳定版PNX](https://github.com/PowerNukkitX/PowerNukkitX/releases)进行部署。

* 大多数[Cloudburst Nukkit](https://github.com/cloudburstmc/nukkit)都可在该服务端上兼容使用，但在运行时可能会出一些错误等或不支持PowerNukkitX添加的新内容。

🧾 关于Nukkit核心

[Nukkit](https://github.com/Nukkit/Nukkit) 是一款为Minecraft: Pocket Edition而生的服务端，有着如下优势:

* 基于Java开发，速度更快，更稳定，高性能。
* 具有友好的架构，您可简单快速的上手为其开发插件等。
* Nukkit正在**不断优化改进中**，同时我们欢迎您为我们的开发做出贡献。

---

🛠 创建插件
---

* 添加PowerNukkitX至您的依赖项中（它由Maven Central托管，因此您无需指定自定义存储库）。

[点此查看完整的Gradle示例](https://github.com/PowerNukkitX/ExamplePlugin-Gradle)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile group: 'cn.powernukkitx', name: 'powernukkitx', version: '1.19.63-r1'
}
```

[点此查看完整的Maven示例](https://github.com/PowerNukkitX/ExamplePlugin-Maven)
```xml
<dependencies>
    <dependency>
        <groupId>cn.powernukkitx</groupId>
        <artifactId>powernukkitx</artifactId>
        <version>1.19.63-r1</version>
    </dependency>
</dependencies>
```
---

🛠 构建JAR核心文件
---
- `git clone https://github.com/PowerNukkitX/PowerNukkitX`
- `cd PowerNukkitX`
- `git submodule update --init`
- `./mvnw clean package`

* 编译后的JAR文件可在 `target/` 目录中找到。

* 编译完成的JAR文件运行参见[如何使用](#使用步骤:)。

🧐 贡献一份力量
---
您在提交任何问题或代码上传合并等请求时，请先阅读[贡献帮助指南](https://github.com/PowerNukkitX/PowerNukkitX/blob/master/CONTRIBUTING.md)后再进行提问或其它操作，信息不足或格式错误等我们将不会回复您并关闭您的请求。

---

🌐 友情链接
---

- __[🌐 PowerNukkitX官网](https://powernukkitx.cn/)__
- __[👩🏽‍💻 PowerNukkitX文档库](https://doc.powernukkitx.cn/)__
- __[💬 PowerNukkitX Discord](https://discord.gg/BcPhZCVJHJ)__
- __[💾 下载测试版PowerNukkitX](https://ci.lt-name.com/job/PowerNukkitX/)__
- __[🔌 Cloudburst Nukkit 插件中心](https://cloudburstmc.org/resources/categories/nukkit-plugins.1/)__
- __[🔌 PowerNukkit 插件中心](https://discuss.powernukkit.org/c/plugins/powernukkit-plugins/14/)__

🎨 数据统计
---

[![Issues](https://img.shields.io/github/issues/PowerNukkitX/PowerNukkitX?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/issues)
[![Issues-Closed](https://img.shields.io/github/issues-closed/PowerNukkitX/PowerNukkitX?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/issues?q=is%3Aissue+is%3Aclosed)
[![Pull-pr](https://img.shields.io/github/issues-pr/PowerNukkitX/PowerNukkitX?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/pulls)
[![Pull-pr-closed](https://img.shields.io/github/issues-pr-closed/PowerNukkitX/PowerNukkitX?style=flat-square)](https://github.com/PowerNukkitX/PowerNukkitX/pulls?q=is%3Apr+is%3Aclosed)

2019 - 2023 © [BlocklyNukkit](https://wiki.blocklynukkit.com) | [PowerNukkitX](https://www.powernukkitx.com)
