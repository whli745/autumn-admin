# autumn-admin

#### 介绍
为初学者搭建的一套基于Spring Boot的WEB项目脚手架

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. License模块使用说明
	- 进入docs目录下，执行`java -jar license-creator-1.0.0-SNAPSHOT.jar`命令，启动完成后在浏览器输入 `http://ipAdress:1000/license/index.html`，填写相关参数并点击按钮生成授权文件
    -  在自己的WEB项目中引入docs目录下`license-verfiy-1.0.0-SNAPSHOT.jar`模块，并将docs目录下`publicCerts.keystore`拷贝至`resources`目录
   配置Spring拦截器，引用`License -> verify`方法，拦截请求验证授权  
	-  在`application.yml`或`application.properties`中配置如下参数
	```
	license:
		subject: 生成授权文件时填写的公司
		publicAlias: publicCert
		publicKeysStorePath: /publicCerts.keystore
		storePass: e05fc2a074c362789d2f392a8fa34ce62572853a
	```
	- 部署项目：将第一步生成的license.lic文件拷贝至系统用户目录下，启动项目
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)