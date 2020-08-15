# 微信公众号开发框架v1

[toc]

## 一、开始开发

### 接入微信公众平台开发

[微信公众平台接入要求详见](https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html)

使用示例：

```
@GetMapping("/weixin")
@ResponseBody
public String checkSinnature(
                   @RequestParam(value = "signature") String signature,
                   @RequestParam(value = "timestamp") String timestamp,
                   @RequestParam(value = "nonce") String nonce,
                   @RequestParam(value = "echostr") String echostr) {
    return iService.checkSignature(signature, timestamp, nonce, echostr);
}
```

注意：使用时路径要与接口配置信息中的URL一致，要使用GET方法

### 获取Access token

[access token相关要求详见](https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html)

access_token是公众号的全局唯一接口调用凭据，access_token的有效期为2个小时，需定时刷新。因为每天获取access_token的次数有限，并且频繁请求access_token会导致不必要的开销，所以微信开发框架对token进行了管理周期管理。

- takeAccessToken():String  获取token
- takeAccessToken(boolean):String 获取时可以选择是否获得最新的token
- expireAccessToken():void 使现有token失效
- isAccessTokenExpired():boolean 判断token是否失效

使用示例：

```
WxConfig wxConfig = WxConfig.getInstance();
String accessToken = wxConfig.getAccessToken();
```

### 获取微信服务器IP地址

如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表或者IP网段信息。

#### 获取微信callback IP地址

使用示例：

```
List<Object> cbip =  wxService.getCallBackIp();
```

#### 获取微信API接口 IP地址

使用示例：

```
List<Object> adip =  wxService.getApiDomainIp();
```

## 二、自定义菜单

### 创建菜单

[多种类型按钮详见](https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html)

使用示例：

```
//生成一个2*2的菜单
//一级菜单
JsonButtonEntity button1 = new JsonButtonEntity();
button1.setName("点击");
List<JsonButtonEntity> sub_button1 = new LinkedList<>();
//二级菜单
JsonButtonEntity button1_1 = new JsonButtonEntity();
button1_1.setName("点击1");
button1_1.setType("click");
button1_1.setKey("key1_1");
sub_button1.add(button1_1);
//二级菜单
JsonButtonEntity button1_2 = new JsonButtonEntity();
button1_2.setName("点击2");
button1_2.setType("click");
button1_2.setKey("key1_2");
sub_button1.add(button1_2);
button1.setSub_button(sub_button1);


//一级菜单
JsonButtonEntity button2 = new JsonButtonEntity();
button2.setName("链接");
List<JsonButtonEntity> sub_button2 = new LinkedList<>();
//二级菜单
JsonButtonEntity button2_1 = new JsonButtonEntity();
button2_1.setName("百度");
button2_1.setType("view");
button2_1.setUrl("http://www.baidu.com/");
sub_button2.add(button2_1);
//二级菜单
JsonButtonEntity button2_2 = new JsonButtonEntity();
button2_2.setName("哔哩哔哩");
button2_2.setType("view");
button2_2.setUrl("https://www.bilibili.com/");
sub_button2.add(button2_2);
button2.setSub_button(sub_button2);

//整合所有一级菜单
List<JsonButtonEntity> buttons = new ArrayList<>();
buttons.add(button1);
buttons.add(button2);
//调用创建菜单接口
wxService.menuCreate(buttons);
```

### 查询菜单

[返回菜单参数详见](https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Querying_Custom_Menus.html)

使用示例：

```
String menu = wxService.menuQuery()
```

返回的菜单信息是Json格式字符串

### 删除菜单

使用示例：

```
boolean isDelete = wxService.menuDelete()
```

## 三、消息管理

使用示例：

```
@RequestMapping(value="/weixin",method=RequestMethod.POST)
@ResponseBody
public String handlerMessage(@RequestBody XmlMessageRequest message){
    return iService.receiveMessage(message);
}
```

以上代码用于接收消息，但处理各种不同类型的消息需要利用不同的注解。

首先要在处理消息的类上加@MsgHandler，这样框架会扫描该类下的方法，并且在收到消息时，从中找出最匹配的一个方法，对消息进行处理。

#### 接收普通消息

##### 文本消息

使用示例：

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

@Text表示该方法用于处理文本消息，contains用于匹配关键字。若文本消息匹配多个@Text方法，则以最长匹配的方法为处理方法。如果忽略contains字段，则视为默认处理方法，所有文本消息都会由该方法处理。

@ResponseXml表示将返回值转化为XML格式的字符串，否则，返回toString()的值。

##### 图片消息

使用示例：

```
@Image
public Object imageMessage(XmlMessageRequest message) {
    return "success";
}
```

##### 语音消息

```
@Voice
```

##### 视频消息

```
@Video
```

##### 小视频消息

```
@ShortVideo
```

##### 地理位置消息

```
@Location
```

##### 链接消息

```
@Link
```

#### 接收事件推送

##### 关注事件

```
@Subscribe
```

##### 取消关注事件

```
@UnSubscribe
```

##### 扫描带参数二维码事件

```
@Scan
```

##### 上报地理位置事件

```
@EventLocation
```

##### 点击菜单拉取消息时的事件推送

使用示例：

```
@Click(eventKey = "key1_1")
public Object clickEvent(XmlMessageRequest message) {
    return "success";
}
```

eventKey字段用于匹配不同的click，与自定义菜单接口中KEY值对应

##### 点击菜单跳转链接时的事件推送

```
@View(eventKey = "http://www.baidu.com/")
@ResponseXml
public Object viewEvent(XmlMessageRequest message) {
    return "success";
}
```

eventKey字段用于匹配不同的view，与设置的跳转URL对应。