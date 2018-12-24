import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TesterHomeTest {

    @Test
    public void topices(){
        useRelaxedHTTPSValidation();  //如过报错添加证书
        get("https://testerhome.com/api/v3/topics.json")
                .then()
                .body("topics[0].title",containsString("霍格沃兹测试学院圣诞节活动"));

    }

}
