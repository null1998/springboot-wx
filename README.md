# springboot-wx
微信公众号开发框架

## 接入服务

SignUtil中实现了接入认证地业务逻辑，用户只要在对应的请求地址上调用checkSignature方法就可以进行接入认证，使用示例：

```
if (SignUtil.checkSignature(signature, timestamp, nonce, token)) {
    PrintWriter out = response.getWriter();
    out.print(echostr);
    out.close();
}
```

## Token生命周期管理

access_token是公众号的全局唯一接口调用凭据，access_token的有效期目前为2个小时，需定时刷新。因为每天获取token的次数有限，并且为了避免频繁请求token导致许多不必要的开销，必须要对token的生命周期进行管理。用户只要在wx.properties配置文件里设置下面的三个字段。就可以利用配置类WxConfig来进行获取token，刷新token等操作。

```
wx.appId=
wx.appSecret=
wx.token=
```

## 自定义菜单

微信开发框架的大部分实用的方法在IService的实现类WxService中。比如自定义菜单的相关方法

- 创建菜单接口

```
boolean menuCreate(List<JsonButtonEntity> buttons)
```

微信服务器创建菜单时要求在post请求中插入json数据，用来设置按钮数据。这里把按钮抽象为实体类。用户只要把各个按钮的属性设置好，传入接口，就可以创建想要的菜单。

- 查询菜单接口，返回json格式字符串

```
String menuQuery();
```

- 删除菜单接口

```
boolean menuDelete();
```

## 消息管理

- 开启消息接收服务，在springboot的Controller下，设置一个接收方法就可以。

```
@RequestMapping(value="/weixin",method=RequestMethod.POST)
@ResponseBody
public String handlerMessage(@RequestBody XmlMessageRequest message){
    return iService.receiveMessage(message);
}
```

- 针对不同消息的具体业务逻辑实现

示例：

```
@MsgHandler
public class MessageHandler {
    /**
     * 执行文本消息的方法
     *
     * @param message
     * @return
     */
    @Text(contains = "你好")
    @ResponseXml
    public Object textMessage(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("该消息关键字为你好");
        return response;
    }

}
```

利用@MsgHandler注解，将一个类标注为消息处理类，可以在该类下编写微信消息管理业务。

不同方法使用不同业务注解，比如：

- @Text(contains = "")文本消息，contains可以自定义关键字，方便处理不同文本请求。多个方法同时符合要求的情况下，最大匹配长度的优先。如果不希望使用关键字，可以不设置contains字段。

示例1：

```
@Text(contains = "你好")
@ResponseXml
public Object textMessage(XmlMessageRequest message) {
    XmlTextResponse response = new XmlTextResponse(message);
    response.setMsgType(WxConstants.XML_MSG_TEXT);
    response.setContent("该消息关键字为你好");
    return response;
}
```

示例2：

```
@Text(contains = "你")
@ResponseXml
public Object textMessage2(XmlMessageRequest message) {
    XmlTextResponse response = new XmlTextResponse(message);
    response.setMsgType(WxConstants.XML_MSG_TEXT);
    response.setContent("该消息关键字为你");
    return response;
}
```

![image-20200812023352469](C:\Users\test\AppData\Roaming\Typora\typora-user-images\image-20200812023352469.png)

- Click(eventKey = "")点击事件，eventKey可以输入菜单的key，可以方便地对不同点击事件进行处理

示例1：

```
@Click(eventKey = "KEY_2_1")
@ResponseXml
public Object clickEvent2(XmlMessageRequest message) {
    XmlTextResponse response = new XmlTextResponse(message);
    response.setMsgType(WxConstants.XML_MSG_TEXT);
    response.setContent("来自按钮KEY_2_1的点击事件");
    return response;
}
```

![demo](C:\Users\test\Desktop\demo.png)

- @Image图片消息

还有其它类似的注解。框架会自动识别微信服务器发来的不同类型的消息，然后匹配合适的方法进行处理。和微信服务器的交互，比如接收消息，回复消息，消息超时排重等，都由框架来处理。用户只需要集中注意力于实际业务处理。还有一个注解@ResponseXml，可以将XmlResponse对象转化为XML格式的字符串，这样可以方便地回复微信服务器。如果不添加该注解，则返回的是对象的toString()方法返回值。

