### <center>License授权

#### 一、生成授权证书
1. 使用命令 `java -jar license-creator-1.0.0-RELEASE.jar` 运行License生成服务
2. 在浏览器输入 [http://IP:1000/license](http://ip:1000/license) 进入License生成页面
3. 依次输入相应参数后，点击按钮生成License证书

#### 二、项目引入License
1. 在Maven `settings.xml` 配置文件中设置以下配置
		<server>
		  <id>developer</id>
		  <username>developer</username>
		  <password>123456</password>
		</server>
2. 在项目的 `pom.xml` 依赖文件设置私有Repository
		<repository>
            <id>developer</id>
            <name>Private Repository</name>
            <url>http://www.whli745.com:8081/repository/maven-public/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
        </repository>
3. 在项目的 `pom.xml` 引入 `License-verify` 包
		<dependency>
            <groupId>com.whli</groupId>
            <artifactId>license-verify</artifactId>
            <version>1.0.0-RELEASE</version>
        </dependency>
4. 在 Spring 或 Spring Boot 项目，增加包扫描 `com.whli`
5. 将 `publicCerts.keystore` 拷贝至项目 `resources` 目录下
6. 拷贝以下代码至 `application.yml` 或 `applicatin.properis` 配置内，并修改 `subject` 值为生成License证书时填写的公司值
		license: #授权
		  subject: ''
		  publicAlias: publicCert
		  publicKeysStorePath: /publicCerts.keystore
		  storePass: e05fc2a074c362789d2f392a8fa34ce62572853a
7. 打包项目

#### 三、部署项目
1. 将第一阶段生成的证书文件放入客户机当前用户目录
2. 运行项目打包的执行jar包，直至看见 `++++++++ 证书安装成功 ++++++++` 时，说明项目已正式授权