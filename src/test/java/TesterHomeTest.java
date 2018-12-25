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
                .statusCode(200);
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
}
