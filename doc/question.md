## 4.16

### mysql索引

### 关于索引底层

### 主键索引，非主键索引区别

以mysql两种存储引擎解释(图中辅助键索引即非主键索引)：



 ![img](https://github.com/Bennett-Q/staticRepo/blob/master/images/mysql%E7%B4%A2%E5%BC%95%E5%AE%9E%E7%8E%B0.png?raw=true) 

  

#### InnoDB:

主键索引也叫聚集索引（或聚簇索引）

 非主键索引的叶子节点存放的是**主键的值**，而主键索引的叶子节点存放的是**整行数据**，其中非主键索引也被称为**二级索引**，而主键索引也被称为**聚簇索引**。



### 数据结构了解下

### 红黑树与B+树比较



###  B+Tree叶节点存放的是什么

key对应的一行数据 data

### springCloud组件说明

### springCloud 为什么配置文件修改能即时生效

### List,Map,Set区别

### hashMap默认容量，怎么扩容

### hashMap怎么插入值的

### hashMap底层，分版本

### hashmap与hashtable区别

### 讲一下concurrentHashMap是否安全，原理



### Synchronized和Reentrantlock 的区别

### Synchronized实现原理

### Reentrantlock 实现原理



### java内存区域，垃圾回收算法

### AQS原理

### 讲一下countDownLatch ,CyclicBarrier ，及应用场景 

### 讲一下线程池，核心参数，线程超负荷之后的应对

## 4.20

### 为什么要用依赖注入，好处是什么

### 2个线程之间如何保持通信

1. wait()  notify() notifyAll() 等待/唤醒线程也是一种通信
2. volatitle 共享变量

### 线程可见性

### volatitle关键字原理

volatitle修饰的变量在主内存（共享内存）中，当有线程修改时，则

### 2个线程如何保持顺序执行

1. BlockingQueue + countDownLanch

### 2个进程如何保持通信

常用的：

1. 消息队列
2. 共享内存
3. Socket

### dockerfile的配置

### springboot比spring好在哪

## 4.21

### spring的aop实现原理

### springboot怎么加载pom中的包

 每个要和sb整合的jar包，里面META-INF中都会有一个spring.factories 文件，里面定义了自动装备的类和一些sb的扩展监听器。 sb启动时会扫描所有jar包中的这个文件 

### 讲一讲jdk代理

### 常用的垃圾回收器

https://blog.csdn.net/high2011/article/details/80177473

### 讲一下自定义注解

### jdk1.8新特性

### 内存溢出排查，记录日志，传哪些参数

### 常用线程池，核心参数，会出现的问题

### 常见算法，哪种比较快

https://blog.csdn.net/u013270347/article/details/80604690

## 4.27 

### docker 常用命令



### dockerfile 配置的关键字

- **`FORM`** 指定基础镜像 ，并且必须是第一条指令

```dockerfile
格式：
　　FROM <image>
　　FROM <image>:<tag>
　　FROM <image>@<digest>
示例：
	FROM mysql:5.6
注：
	如果不以任何镜像为基础，那么写法为：FROM scratch
	tag或digest是可选的，如果不使用这两个值时，会使用latest版本的基础镜像
```

- **`MAINTAINER`** 指定维护者信息

```dockerfile
格式:
	MAINTAINER <name>
示例:
	MAINTAINER ZHANG 
	MAINTAINER zhang@163.com
	MAINTAINER 	Qi zhang <zhang@163.com>
```
- **`RUN`** 在镜像中要执行的命令

```dockerfile
RUN用于在镜像容器中执行命令，其有以下两种命令执行方式：
shell执行(command为shell命令)
格式：
    RUN <command>
exec执行(可将executable理解成为可执行文件，后面就是两个参数。)
格式：
    RUN ["executable", "param1", "param2"]
示例：
    RUN ["executable", "param1", "param2"]
    RUN apk update
    RUN ["/etc/execfile", "arg1", "arg1"]
注：　　
	RUN指令创建的中间镜像会被缓存，并会在下次构建中使用。如果不想使用这些缓存镜像，可以在构建时指定--no-cache参数，如：docker build --no-cache
```

- **`WORKDIR`**  指定当前工作目录，相当于cd

```dockerfile
格式：
    WORKDIR /path/to/workdir
示例：
    WORKDIR /a  (这时工作目录为/a)
    WORKDIR b  (这时工作目录为/a/b)
    WORKDIR c  (这时工作目录为/a/b/c)
注：
　　通过WORKDIR设置工作目录后，Dockerfile中其后的命令RUN、CMD、ENTRYPOINT、ADD、COPY等命令都会在该目录下执行。在使用docker run运行容器时，可以通过-w参数覆盖构建时所设置的工作目录。
```

- **`EXPOSE `** 指定容器要打开的端口

```dockerfile
格式：
    EXPOSE <port> [<port>...]
示例：
    EXPOSE 80 443
    EXPOSE 8080    
    EXPOSE 11211/tcp 11211/udp
注：　　
	EXPOSE并不会让容器的端口访问到主机。要使其可访问，需要在docker run运行容器时通过-p来发布这些端口，	或通过-P参数来发布EXPOSE导出的所有端口
```



- **`ENV `** 定义环境变量

```dockerfile
格式：
    ENV <key> <value>  #<key>之后的所有内容均会被视为其<value>的组成部分，因此，一次只能设置一个变量
    ENV <key>=<value> ...  #可以设置多个变量，每个变量为一个"<key>=<value>"的键值对，如果<key>中包含空格，可以使用\来进行转义，也可以通过""来进行标示；另外，反斜线也可以用于续行
示例：
    ENV myName John Doe
    ENV myDog Rex The Dog
    ENV JAVA_OPTS="-Xms512m -Xmx1024m"
    ENV myCat=fluffy
注:
	制定一个环境变量，会被后续RUN指令使用，并在容器运行时保持。
```

- **`ADD`** 将本地文件添加到容器中,tar类型文件会自动解压(网络压缩资源不会被解压),可以访问网络资源,例如wget

```dockerfile
格式：
	 ADD <src>... <dest>
 ADD ["<src>",... "<dest>"] 用于支持包含空格的路径
示例：
 ADD hom* /mydir/          # 添加所有以"hom"开头的文件
 ADD hom?.txt /mydir/      # ? 替代一个单字符,例如："home.txt"
 ADD test relativeDir/     # 添加 "test" 到 `WORKDIR`/relativeDir/
 ADD test /absoluteDir/    # 添加 "test" 到 /absoluteDir/
```



- **`COPY`** 复制本地主机的包,文件之类的到容器中,功能类似于`ADD`,没有ADD功能强大

```dockerfile
格式:
 COPY <src>... <dest>
 COPY ["<src>",... "<dest>"]
示例:
 COPY target/$APP_NAME-*.jar app.jar  
```

- **`ENTRYPOINT`** 配置容器，使其可执行化。配合CMD可省去"application"，只使用参数。

```dockerfile
格式：
    ENTRYPOINT ["executable", "param1", "param2"] (可执行文件, 优先)
    ENTRYPOINT command param1 param2 (shell内部命令)
示例：
    FROM ubuntu
    ENTRYPOINT ["top", "-b"]
    CMD ["-c"]
    ENTRYPOINT exec java -server $JAVA_OPTS -jar /app.jar $PARAMS
注：
	ENTRYPOINT与CMD非常类似，不同的是通过docker run执行的命令不会覆盖ENTRYPOINT，而docker run命令中指定的任何参数，都会被当做参数再次传递给ENTRYPOINT。Dockerfile中只允许有一个ENTRYPOINT命令，多指定时会覆盖前面的设置，而只执行最后的ENTRYPOINT指令。
```

 **举个**🌰

```dockerfile
# This my first nginx Dockerfile
# Version 1.0

# Base images 基础镜像
FROM centos

#MAINTAINER 维护者信息
MAINTAINER tianfeiyu 

#ENV 设置环境变量
ENV PATH /usr/local/nginx/sbin:$PATH

#ADD  文件放在当前目录下，拷过去会自动解压
ADD nginx-1.8.0.tar.gz /usr/local/  
ADD epel-release-latest-7.noarch.rpm /usr/local/  

#RUN 执行以下命令 
RUN rpm -ivh /usr/local/epel-release-latest-7.noarch.rpm
RUN yum install -y wget lftp gcc gcc-c++ make openssl-devel pcre-devel pcre && yum clean all
RUN useradd -s /sbin/nologin -M www

#WORKDIR 相当于cd
WORKDIR /usr/local/nginx-1.8.0 

RUN ./configure --prefix=/usr/local/nginx --user=www --group=www --with-http_ssl_module --with-pcre && make && make install

RUN echo "daemon off;" >> /etc/nginx.conf

#EXPOSE 映射端口
EXPOSE 80

#CMD 运行以下命令
CMD ["nginx"]
```

### jvm回收的算法

### sql优化

### 除了主键索引还有什么索引

### oracle和mysql的区别 (关键字,函数,语法)

### aciveMQ的几种消息方式

### springMVC前台访问后的运行流程

### springBoot配置文件有几种

### ajax跨域请求处理

#### 响应头添加Header允许访问

跨域资源共享（CORS）Cross-Origin Resource Sharing

这个跨域访问的解决方案的安全基础是基于"JavaScript无法控制该HTTP头"

它需要通过目标域返回的HTTP头来授权是否允许跨域访问。

```java
response.addHeader(‘Access-Control-Allow-Origin:*’); // 允许所有来源访问 
response.addHeader(‘Access-Control-Allow-Method:POST,GET’);//允许访问的方式
```



#### jsonp (只支持get请求不支持post请求)

用法：

1. dataType改为jsonp     
2. jsonp : "jsonpCallback"###发送到后端实际为http://a.a.com/a/FromServlet?userName=644064&jsonpCallback=jQueryxxx    
3.  后端获取get请求中的函数名
4. 构造回调结构

```javascript
//前端
$.ajax({
	type : "GET",
    async : false,
    url : "http://a.a.com/a/FromServlet?userName=644064",
    dataType : "jsonp",//数据类型为jsonp  
    jsonp : "getUserInfo",//服务端用于接收后端callback调用的前端function名的参数
    success : function(data) {
    	alert(data["userName"]);
    },
    error : function() {
    	alert('fail');
    }
});

//后端
String jsonpCallback = request.getParameter("getUserInfo");
//构造回调函数格式  jsonpCallback(result数据)
resp.getWriter().println(jsonpCallback+"("+result.toJSONString()+")");
```

#### httpClient内部转发

实现原理很简单，若想在B站点中通过Ajax访问A站点获取结果，固然有ajax跨域问题，但在B站点中访问B站点获取结果，不存在跨域问题，这种方式实际上是在B站点中ajax请求访问B站点的HttpClient，再通过HttpClient转发请求获取A站点的数据结果。但这种方式产生了两次请求，效率低，但内部请求，抓包工具无法分析，安全。

```javascript
//前端
$.ajax({
			type : "GET",
			async : false,
			url : "http://b.b.com:8080/B/FromAjaxservlet?userName=644064",
			dataType : "json",
			success : function(data) {
				alert(data["userName"]);
			},
			error : function() {
				alert('fail');
			}
		});
//后端

@WebServlet("/FromAjaxservlet")
public class FromAjaxservlet extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//创建默认连接
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建HttpGet对象,处理get请求,转发到A站点
			HttpGet httpGet = new HttpGet("http://a.a.com:8080/A/FromServlet?userName="+req.getParameter("userName")); 
                        //执行
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int code = response.getStatusLine().getStatusCode();
			//获取状态
			System.out.println("http请求结果为:"+code);
			if(code == 200){
                                //获取A站点返回的结果
				String result = EntityUtils.toString(response.getEntity());
				System.out.println(result);
                                //把结果返回给B站点
				resp.getWriter().print(result);
			}
			response.close();
			httpClient.close();
		} catch (Exception e) {
		}
	}
}
```

#### 使用nginx搭建企业级接口网关方式

我们访问以www.nginxtest.com开头且端口为80的网址，nginx将会进行拦截匹配，**若项目名为A，则分发到b.b.com:81。**实际上就是通过"同源"的域名，不同的项目名进行区分，通过nginx拦截匹配，转发到对应的网址。整个过程，两次请求，第一次请求nginx服务器，第二次nginx服务器通过拦截匹配分发到对应的网址。

```nginx
server {
        listen       80;
        server_name  www.nginxtest.com;
        location /A {
		    proxy_pass  http://b.b.com:81;
			index  index.html index.htm;
        }
		location /B {
		    proxy_pass  http://a.a.com:81;
			index  index.html index.htm;
        }
```

## 4.30

#### spring中的动态代理方式，AOP实现

1.  JDK自带的动态代理. 
2.  Spring框架自己提供的CGLIB的方式. 

#### jdk代理和cglib代理的区别

|   区别   |                         jdk动态代理                          |                        CGLIB动态代理                         |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| 使用场景 |     当被代理对象,实现了至少一个接口时会使用JDK动态代理.      |      当被代理对象没有实现任何接口时,spring会使用CGLIB.       |
| 使用方法 | 1.定义一个java.lang.reflect.InvocationHandler接口的实现类，重写invoke方法<br />2.将InvocationHandler对象作为参数传入java.lang.reflect.Proxy的newProxyInstance方法中<br />3.通过调用java.lang.reflect.Proxy的newProxyInstance方法获得动态代理对象<br />4.通过代理对象调用目标方法 | 1.定义一个org.springframework.cglib.proxy.MethodInterceptor接口的实现类，重写intercept方法<br/>2.获取org.springframework.cglib.proxy.Enhancer类的对象<br/>3.分别调用Enhancer对象的setSuperclass和setCallback方法，使用create方法获取代理对象<br/>4.通过代理对象调用目标方法 |
| 实现原理 |        通过java反射机制，只能对实现了接口的类生成代理        |      底层是依赖于ASM(开源的Java字节码编辑库,操作字节码)      |
|   优劣   |                                                              |                                                              |

#### SpringCloud中的异步调用