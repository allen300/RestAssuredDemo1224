import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TesterHomeTest {

    @BeforeClass
    public static void BeforeAll(){
        useRelaxedHTTPSValidation();  //如过报错添加证书

    }

    @Test
    public void topices(){

        get("https://testerhome.com/api/v3/topics.json")
                .then()
                .body("topics[0].title",containsString("霍格沃兹测试学院圣诞节活动"));
    }

    @Test
    public  void getBaidu(){
        given()
                .log().all()
                .proxy(8889)
                .param("wd","mp3")
                .param("ie","utf-8")
                .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .header("Cookie","BIDUPSID=BA5CE8786B61A5BA8574CED93C32C9A9; PSTM=1437293141; BD_UPN=123253; BAIDUID=C8AA8334EA3DD3C0A2EC56E20045ABAD:FG=1; BDUSS=ktYd1dLQzRremxFQzRxcEZyVWM0T2RtUVdhVE4tQVdFUFJBREVEN3pYWDAxZ3RjQVFBQUFBJCQAAAAAAAAAAAEAAAA3XVkSYWxsZW4zMDA1OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPRJ5Fv0SeRbT; delPer=0; BD_CK_SAM=1; BD_HOME=1; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; rsv_jmp_slow=1545385836150; BDRCVFR[S4-dAuiWMmn]=I67x6TjHwwYf0; PSINO=1; H_PS_PSSID=1468_21122_28206_28131_27751_28139_27509; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; H_PS_645EC=d5f5XKKsXOzAG4y%2FXu7dSFgYr3ycAaToljIKtKXfIzYpjWaymP%2Bzn8z6KQElOirlTA8i; BDSVRTM=156; WWW_ST=1545641277538")
                .get("https://www.baidu.com/s")
                .then()
                .log().all()
                .statusCode(200)

        ;

    }

    @Test
    public  void postJenkins(){
        given()
                .proxy(8889)
                .formParam("j_username","111")
                .formParam("j_password","222")
                .formParam("from","/")
                .formParam("Submit","%E7%99%BB%E5%BD%95")
                .when().post("http://116.62.196.57:8080/j_acegi_security_check")
                .then()
                .statusCode(302);
    }

    @Test
    public  void getBaiduxpath(){
        given()
                .log().all()
                .proxy(8889)
                .param("wd","mp3")
                .param("ie","utf-8")
                .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .header("Cookie","BIDUPSID=BA5CE8786B61A5BA8574CED93C32C9A9; PSTM=1437293141; BD_UPN=123253; BAIDUID=C8AA8334EA3DD3C0A2EC56E20045ABAD:FG=1; BDUSS=ktYd1dLQzRremxFQzRxcEZyVWM0T2RtUVdhVE4tQVdFUFJBREVEN3pYWDAxZ3RjQVFBQUFBJCQAAAAAAAAAAAEAAAA3XVkSYWxsZW4zMDA1OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPRJ5Fv0SeRbT; delPer=0; BD_CK_SAM=1; BD_HOME=1; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; rsv_jmp_slow=1545385836150; BDRCVFR[S4-dAuiWMmn]=I67x6TjHwwYf0; PSINO=1; H_PS_PSSID=1468_21122_28206_28131_27751_28139_27509; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; H_PS_645EC=d5f5XKKsXOzAG4y%2FXu7dSFgYr3ycAaToljIKtKXfIzYpjWaymP%2Bzn8z6KQElOirlTA8i; BDSVRTM=156; WWW_ST=1545641277538")
                .get("https://www.baidu.com/s")
                .then()
                .log().all()
                .statusCode(200)
                .body("html.head.title" , equalTo("mp3_百度搜索"))
                .body("**.find{it.@class== 'nums_text'}", equalTo("百度为您找到相关结果约41,000,000个"))
//                .body(hasXPath("//*[@class='nums_text' and contains(text(),'百度为您找到相关结果约41,000,000个')]"))
//                .body(hasXPath("//*[@id='container']/div[2]/div/div[2]/span , '百度为您找到相关结果约41,000,000个'"))
        /**
         * 不标准的html  xpath 不好用，还是官方的方法*/
        ;
    }
    @Test
    public void JsonPathTest(){

        final String operand = "TesterHome 上海 2018 年第三期沙龙圆满结束&PPT 下载";
        String title = "您有一份来自社区的礼物请查收";
        given()
                .proxy(8889)
                .when()
                .get("https://testerhome.com/api/v3/topics.json")
                .then()
                .body("topics.title[0]",equalTo(title))
                .body("topics.size()",equalTo(23))
                .body("topics.find {it.title.contains('TesterHome')}.title",equalTo(operand))
                //标题发生变化会报错
                .body("topics.findAll {it.title.contains('TesterHome')}.title[0]",equalTo(operand))
                //返回的是数组两种方法都可以，


        ;

    }
}
