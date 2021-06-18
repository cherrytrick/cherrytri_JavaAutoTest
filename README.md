# cherrytri接口自动化测试框架
**该项目提供了两种获取testcase的方法，通过excel表和数据库中获取，可以根据具体情况选择不同的方式**
  * 使用excel方式的运行ExcelTestCase，可以得到extent生成的测试报告。
  * 使用数据库方式，可以框架基础上根据需求自由搭配，修改dao层。

**项目架构**
   * 采用TestNg，springboot结合的方法，通过jpa读取数据库获取测试用例，并且在testcase中自动运行生成测试报告，也可以从excel表中读取
   测试用例，自动生成另一种样式的测试报告（为美观考虑）。
   * 考虑添加dubbo类型接口的测试（待完成）
   * **_Base_** 
       * TestBase  接口请求父类，springboot与testng结合的父类，用于启动
      
   * **_Client_**
       * Client 封装http的基本方法，POST,GET,PUT,DELETE封装为可以使用复用的请求方法
       * CookieManager  处理cookie的方法
       * RestTemplete 已另外一种方式封装了四种方法，可以根据需求选择使用。
   * **_Dao_**
       * DAO层采用jpa，对数据库进行操作，选择jpa的原因是单表操做，测试用例的表之间没有联动的关系，jpa
       更加方便简洁。
       
   * **_Enity_**
      * 不过多介绍了，实体类。
   
 
   *  **_Failed_**
       * FailedRetry    FailedRetryListener 失败重试的listener，失败用例可重复执行
   * **_Report_**
       * ExtentTestNGReporterListener   产出测试报告的listener
   * **_TestCase_**
       * ExcelTestCase
            * 1.连接系统接口,发送接收json数据
            * 2.准备数据 excel准备测试数据,接收数据写入excel中
            *  3.解析响应的json
       * DataBaseTestCase
            * 模拟get方法，从数据库获取测试用例返回测试结果
   * **_util_**
       * 读取json数据，读取excel数据等配置类
       
   * 感谢在tal实习期间，获得tal前辈 CuiZhongyuan 测试技术的分享，收益匪浅。
   * 感谢 https://github.com/LeoXuLiang ，使用了其接口数据并在其部分架构基础改进了架构。

