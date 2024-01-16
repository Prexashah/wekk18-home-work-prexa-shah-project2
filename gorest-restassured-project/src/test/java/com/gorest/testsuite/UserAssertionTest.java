package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import com.gorest.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;
    String users = PropertyReader.getInstance().getProperty("resourcesUser");

    @BeforeClass
    public void getUser() {
        Map<String, Integer> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page", 20);
        response = given()
                .when()
                .get(users)
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 10
    @Test
    public void test01() {
        List<Map<String, ?>> list = response.extract().path("$");
        Assert.assertEquals(list.size(), 10);
    }

    //   2. Verify  if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
    @Test
    public void test02() {
        response.body("findAll{ it.id == 5914074}.get(0).name", equalTo("Rev. Brahmaanand Khanna"));

    }

    //  3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
    @Test
    public void test03() {
        response.body("name", hasItem("Somu Bhat"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test04() {
        response.body("name", hasItems("Somu Bhat", "Bhishma Dubashi", "Goswami Prajapat"));
    }

    //5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
    @Test
    public void test05() {
        response.body("findAll{it.id==5914071}.get(0).email", equalTo("dubashi_bhishma@kemmer-dickens.test"));
    }
//6. Verify the status is “Active” of user name is “Amaresh Rana”
    @Test
    public void test06(){
        response.body("findAll{it.name=='Abhaya Mahajan I'}.get(0).status",equalTo("active"));
    }
//7. Verify the Gender = female of user name is “Dhanalakshmi Pothuvaal”
    @Test
    public void test07(){
        response.body("findAll{it.name=='Rev. Brahmaanand Khanna'}.get(0).gender",equalTo("female"));
    }

}