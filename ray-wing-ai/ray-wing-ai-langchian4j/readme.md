
## langchain4j
> https://docs.langchain4j.dev/get-started
> 

## 常用模型
1. OpenAI
2. 阿里百炼
3. DeepSeek
4. 智谱清言
5. 硅基流动
6. OLLama
7. 其他支持OpenAI协议的大模型

## 入门
> 阿里百炼
1. 获取apikey
2. 获取模型名
3. 获取baseUrl调用地址

### 依赖包
1. 原生 低阶、高阶api
```xml

<!--    基础API    -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai</artifactId>
    <!--            <version>${langchain4j.version}</version>-->
</dependency>

<!--    高级API    -->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j</artifactId>
    <!--            <version>${langchain4j.version}</version>-->
</dependency>
```

2. springboot整合 高阶、低阶
> 整合生成的ChatModel: 名称：openAiChatModel, 类型class dev.langchain4j.model.openai.OpenAiChatModel

```xml
<!--低阶-->
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>
</dependency>

<!--高阶-->
<dependency>
<groupId>dev.langchain4j</groupId>
<artifactId>langchain4j-spring-boot-starter</artifactId>
</dependency>
```

### LLM的两种api 
1. ChatModel 推荐
2. LanguageModel 不再支持

### AI Services and Chains.
1. Chains 过时
2. AI Services 推荐