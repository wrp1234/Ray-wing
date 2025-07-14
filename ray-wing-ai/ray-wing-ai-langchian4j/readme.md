
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

## 模型参数
1. 日志配置
```properties
langchain4j.open-ai.chat-model.log-requests = true
langchain4j.open-ai.chat-model.log-responses = true
logging.level.dev.langchain4j = DEBUG
```

2. 监听
`dev.langchain4j.model.chat.listener.ChatModelListener`

## 多模态

1. qwen-vl-max 图文理解
2. wanx2.1-t2i-turbo 通义万相，文生图


## 流式返回
```xml
       <!--    streaming高阶api    -->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j-reactor</artifactId>
        </dependency>
```

```yaml
server:
  servlet:
    encoding:
      charset: utf-8
      enabled: true
      force: true
```

## 记忆上下文
> 推荐模型：qwen-long
> 
### 记忆淘汰策略
1. dev.langchain4j.memory.chat.MessageWindowChatMemory
2. dev.langchain4j.memory.chat.TokenWindowChatMemory, 通过dev.langchain4j.model.TokenCountEstimator计算token数

### 持久化
> 实现ChatMemoryStore

## 提示词工程
> 作用： 
> - 角色设定：塑造AI助手的专业身份
> - 打造专业的限定能力范围和作用边界的AI助手
>

### 消息类型
> https://docs.langchain4j.dev/tutorials/chat-and-language-models#types-of-chatmessage
> 

1. SystemMessage 系统消息，开发人员拟定ai的角色、回答什么内容、风格、返回结果类型等
2. UserMessage 用户消息，可以使用{{prompt}}，格式化用户消息
3. AiMessage Ai的响应消息
4. ToolExecutionResultMessage ToolExecutionResult的结果
5. CustomMessage 自定义消息，还不够完善 currently only Ollama

### 演化历程
1. 简单的字符串提问
2. 占位符格式化prompt
3. 多角色消息

### 方式
- 方式1：@SystemMessage + @UserMessage + @V
- 方式2：@SystemMessage + @StructuredPrompt + 业务类
- 方式3：PromptTemplate + Prompt


## function calling


