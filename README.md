# up-lang

> 七牛云文件管理工具

#### 作用

1. 上传图片至已申请的千牛云空间 直接获取其外链
2. 七牛云空间文件管理（新增 删除）

#### 部署

1. 申请七牛空间 获取到（accessKey secretKey 七牛云秘钥对  bucket 存储空间）
2. 如图![image](http://lang.sliver.fun/2019-12-23_175159367.png)
3. 外链域名 （ 以上都是七牛云的配置 可参考https://developer.qiniu.com/af/kb/1479/how-to-access-or-locate-the-access-key-and-secret-key） 
4. secret是删除的秘钥 （因为没做权限 在删除上做了一下简单限制）
5. 放到tomcat里run


#### 使用到技术

1. jfinal 简单小巧的后端mvc框架
2. vue+iview 前端视图与逻辑
3. vue-clipboard 复制到剪切板插件
4. axios ajax请求
5. 七牛云sdk


#### 效果图

![image](http://lang.sliver.fun/2019-12-23_175950546.png)
