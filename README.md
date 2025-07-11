# Ray-wing
Springboot3项目模板


## 授权
1. 用户-权限-资源模型
2. 用户-角色-资源模型
3. 用户-角色-权限-资源模型

### 基于request的授权

### 基于方法的授权
> @EnableMethodSecurity
> 
- @PreAuthorize("hasRole('ADMIN')")
- @PreAuthorize("hasRole('ADMIN') and authenticate.name == 'wrp'") 复杂表达式
- @PreAuthorize("hasAuthority('USER_REGISTER')") 

## OAuth2
> RFC-6749
流程
1. 客户应用从授权服务器获取access_token
2. 授权服务器询问用户是否同意，是，将access_token给客户应用
3. 客户应用拿着access_token，访问资源服务器，获取用户信息

### 四种授权模式
1. 授权码 （最常用，最安全）
   1. 在授权服务器上注册客户应用（client_id,client_secret）
   2. 访问授权服务器时带上client_id，返回一个授权码，跳转到后端接口
   3. 客户应用后端接口，拿着授权码和client_secret再去请求授权服务器，返回一个access_token，并跳转到真实的客户应用页面
2. 隐藏式（最简单，安全性不高）
   1. 只有前端的客户端程序
3. 密码式（同一个单位的应用）
4. 凭证式

Spring Security能够实现
1. 客户应用
2. 资源服务器
Spring Authorization Server实现
1. 授权服务器
```xml
<!--    客户应用    -->
        <dependencies>
           <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-oauth2-client</artifactId>
           </dependency>

           <!--    资源服务器    -->
           <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
           </dependency>

           <!--    授权服务器    -->
           <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-oauth2-authorization-server</artifactId>
           </dependency>
        </dependencies>
```