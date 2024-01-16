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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;
    String posts = PropertyReader.getInstance().getProperty("resourcesPost");

    @BeforeClass
    public void getUser() {
        Map<String, Integer> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page", 25);
        response = given()
                .when()
                .get(posts)
                .then().statusCode(200);
    }
    //1. Verify the if the total record is 25
            @Test
            public void test01() {
                List<Map<String, ?>> list = response.extract().path("$");
                Assert.assertEquals(list.size(), 10);
        }
//2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
//cohaero libero.”
    @Test
    public void test02(){
        response.body("findAll{it.user_id=5914181}.get(0).title",equalTo("Ambitus thesaurus contabesco tero amplitudo confugo et tutamen vulgivagus."));

    }
    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test03(){
        response.body("user_id",hasItems(5914181));

    }
    //4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
    @Test
    public void test04(){
        response.body("user_id",hasItems("5914184","5914181","5914161"));
    }
    //5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
    //Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
    //vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
    //tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
    //acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
    @Test
public void test05(){
        response.body("findAll{it.user_id==5914184}.get(0).body",equalTo("Cinis non solum." +
                " Decretum auctus artificiose. Bos umerus totam. Sed cicuta debitis. Crur unde tum. Et tabella dignissimos." +
                " Cognomen vito bardus. Deduco ara una. Desparatus amet caste. Quis taedium sollers."));
    }




}
