package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import com.gorest.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {
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

    @Test
    public void test01() {
        //1. Extract the title
        List<String> titles = response.extract().path(("title"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all the titles are : " + titles);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total number of record
    @Test
    public void test02() {
        List<Integer> total = response.extract().path(("total"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("total number of records are : " + total.size());
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the body of 15th record
    @Test
    public void test03() {
        int totalRecord =  response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of records are : " + totalRecord);
        System.out.println("------------------End of Test---------------------------");

    }
    //4. Extract the user_id of all the records
    @Test
    public void test04(){
        List<String> listOfIds = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of records are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the title of all the records
    @Test
    public void test05(){
        List<String> listOfIds = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total list of ids : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }
    //6. Extract the title of all records whose user_id = 5914200
    @Test
    public void test06(){
        List<String> listOfRecords = response.extract().path("findAll { it.user_id == 93943 }.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of records are : " + listOfRecords);
        System.out.println("------------------End of Test---------------------------");

    }
    //7.7. Extract the body of all records whose id = 93957
    @Test
    public void test07(){
        List<String> listOfIds = response.extract().path("data.findAll { it.id == 93957 }.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of records are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

}