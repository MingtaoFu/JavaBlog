/**
 * Created by mingtao on 15-11-7.
 */
/*
依赖拓展类库 {
    TomEE: {
        服务器
    },
    mysql-connector-java: {
        JDBC，连接 MySQL 数据库的驱动
    },
    jedis: {
        连接 redis 数据库的驱动
        redis: 非关系型数据库，用于存储登录状态，再说
    }

}

PD class 编写 {
    请严格按照MySQL字段来编写PD class，MySQL字段开头为大写，属性请用小写，若为多单词属性，采用驼峰式命名。
    例如Article:
        SQL字段为：Id, Title, Content, Time, ModifyTime
        则 Article 类属性理应为：id, title, content, time, modifyTime
        但以下三个字段不能由自己编写
        - Id 字段，为自动增加(auto_increment)的主键(primary key)，每次插入Id自动增一(但用户的Id是不一样的)
        - Time 字段，类型为时间戳，其值为当前时间戳(current_timestamp)，插入时由MySQL自动设置
        - ModifyTime (只有 Article 有)，基本同Time，但值为 on update timestamp，如果一条记录被修改，ModifyTime的值自动更新,
          但Time值不会
          只有从 MySQL 5.6 起才支持一张表2个 timestamp 类型字段，请注意你的数据库版本

            例如 Article 表中有如下记录
            +----+-------+---------+---------------------+---------------------+
            | Id | Title | Content | Time                | ModifyTime          |
            +----+-------+---------+---------------------+---------------------+
            |  1 | da    | xx      | 2015-11-06 18:33:02 | 2015-11-06 18:33:37 |
            +----+-------+---------+---------------------+---------------------+

            则插入语句应该为 insert into Article (Title, Content) values ('title', 'content')，插入后变为
            +----+-------+---------+---------------------+---------------------+
            | Id | Title | Content | Time                | ModifyTime          |
            +----+-------+---------+---------------------+---------------------+
            |  1 | da    | xx      | 2015-11-06 18:33:02 | 2015-11-06 18:33:37 |
            |  2 | title | content | 2015-11-07 09:09:17 | 2015-11-07 09:09:17 |
            +----+-------+---------+---------------------+---------------------+
            Id，Time，ModifyTime 字段自动生成

            若执行修改语句 update Article set Content = 'haha' where Id = 2
            则执行后，数据为
            +----+-------+---------+---------------------+---------------------+
            | Id | Title | Content | Time                | ModifyTime          |
            +----+-------+---------+---------------------+---------------------+
            |  1 | da    | xx      | 2015-11-06 18:33:02 | 2015-11-06 18:33:37 |
            |  2 | title | haha    | 2015-11-07 09:09:17 | 2015-11-07 09:13:23 |
            +----+-------+---------+---------------------+---------------------+
            除了 Content 字段变化之外，请注意 ModifyTime 的变化

}

DA class 编写 {
    要求参考PD class
}

Service class 编写 {
    Service class 是后台与前端的接口，Service class应该提供一些类似于
        {
            "status": 1,
            "data": {
                "name": "xxx",
                "pwd": "xxx"
            }
        }
    该格式（称为Json）的接口给前端，前端通过访问不同的 url （如 localhost:8080/api/account/xxx）而获取到

    在 Service 包中，分模块编写，例如 Account（帐号） 模块，设置一个总 url （"api/account"）
    另可在其中设置一系列小 url （如 login，则总 url 成了 "api/account/login"）

    编写格式：

        @Path("api/account")
        public class Account {
            @POST
            @Path("register")
            @Produces(MediaType.APPLICATION_JSON)
            public RT_Register register() {
                ...
            }

            @POST
            @Path("login")
            @Produces(MediaType.APPLICATION_JSON)
            public RT_Login login() {
                ...
            }
        }

    对于 "@POST" 的格式，若该 url 的功能仅仅是获取信息，则设为 "@GET"，其余一律写为 "@POST"，其他的 "@PUT"，"@DELETE"等暂不使用

    对于函数的返回值：
        要返回 Json 需要定义特定的类，见 Service 包中的 RT.java。

    对于前端的访问，除了通过 url 的不同来区分开来之外，还有另外的一些参数，如QueryParam，FormParam，CookieParam
    QueryParam(查询参数): 标记为 GET 请求的 url 使用该参数。例如前端访问 "api/article?id=1" 即可用
        @QueryParam("id") int id
        获取到 id 的值
    FormParam(表单参数): 标记为 POST 请求的url 使用该参数，获取方法同 QueryParam
    CookieParam: 通用，一般用于携带个人验证信息。在本项目中，登录后，后台 Account 模块会生成 token （令牌）给前端，
        作为身份证，每一个需要身份验证的 url （例如评论、回复、留言等等），都需要验证 id 与 token 是否匹配才能继续执行，
        可以从 CookieParam 中分别获得 id 与 token 的值。
        User 类提供了一个静态方法 validate 来验证 id 与 token 值的有效性。获得 id 与 token 后执行User.validate(id, token)，
        若验证通过，直接返回 User 对象，否则返回 null 。

}

*/
