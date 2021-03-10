# SSM-CRUD(增删查改)

- ## 功能点

1. 分页

2. 数据校验

   > jQuery前端校验+JSR303后端校验

3. Ajax

4. Rest风格的URI；使用HTTP协议请求方式的 动词，来表示对资源的操作

   > GET(查询)、POST(新增)、PUT(修改)、DELETE(删除)

- ## 技术点

1. 基础框架-ssm
2. 数据库-MySQL
3. 前端框架-bootstrap快速搭建简洁美观的界面
4. 项目的依赖管理-Maven
5. 分页-pagehelper
6. 逆向工程-Mybatis Generator

- ## 基础环境搭建

1.创建Maven

2.引入项目依赖jar包

> - spring
>
> - springmvc
>
> - MyBatis
>
> - 数据库连接池、驱动包
>
> - 其它(jstl、servlet-api、junit)

3.引入BootStrap前端框架

4.编写ssm整合的关键配置文件

> - web.xml
> - spring
> - springmvc
> - mybatis，使用mybatis的逆向工程生成对应的bean和mapper

5.测试mapper

- ## 查询

1. 访问index.jsp页面
2. index.jsp页面发送查询员工列表请求
3. EmployeeController来接受请求，查出员工数据
4. 来到list.jsp页面进行展示

> - URI：/emps

- ## 查询-Ajax

1. index.jsp页面直接发送Ajax请求进行员工分页数据查询
2. 服务器查出的数据以json字符串的形式返回给浏览器
3. 浏览器收到json字符串。可以使用js对json进行解析，使用js通过dom增删改改变页面
4. 返回json。实现客户端的无关性。

- ## 新增

1. 在index.jsp页面点击”新增“

2. 弹出新增对话框

3. 去数据库查询部门列表，显示在对话框中

4. 用户输入数据 进行校验

   > - jQuery前端校验，Ajax用户名重复校验，重要数据（后端JSR303校验，唯一约束
   >
   >   

5. 完成保存

   

- URI:

> - /emp/{id} GET 查询员工
> - /emp  POST 保存员工
> - /emp/{id} PUT 修改员工
> - /emp/{id} DELETE 删除员工

- ## 修改-逻辑

1. 点击编辑
2. 弹出用户修改的模态框（显示用户信息）
3. 点击更新，完成用户修改



- ## 删除-逻辑

1. 单个删除

> URI:/emp/{id} DELETE





- ## 重难点

1. ### 事务管理

2. ### IOC容器

3. ### vue前端框架

4. ### jQuery，Ajax

5. ### mybatis操作数据库(mybatis generator-xxxMapper)

6. ### Maven依赖管理&构建（打war包）

7. ### 
