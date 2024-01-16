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

public class UserExtractionTest extends TestBase {
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

    //1. Extract the All Ids
    @Test
    public void test01() {
        List<Integer> allIds = response.extract().path(("id"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of allIds : " + allIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the all Names
    @Test
    public void test02() {
        List<String> allNames = response.extract().path(("name"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of allNames are : " + allNames);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th object
    @Test
    public void test03() {
        String nameOf5thObject = response.extract().path(("[4].name"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Name of 5th object is : " + nameOf5thObject);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test04() {
        List<String> allInactiveStatus = response.extract().path(("findAll { it.status == 'inactive' }.name"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all inactive status are : " + allInactiveStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test05() {
        List<String> allActiveStatus = response.extract().path(("findAll { it.status == 'active' }.name"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Name of 5th object is : " + allActiveStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test06() {
        List<String> allGenderStatusFemale = response.extract().path(("findAll { it.status == 'active' }.name"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of Female object : " + allGenderStatusFemale);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test07() {
        List<String> emailsOfInactiveObjects = response.extract().path("findAll { it.status == 'inactive' }.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of Female object : " + emailsOfInactiveObjects);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test08() {
        List<String> idsOfMAleObject = response.extract().path("findAll { it.gender == 'male' }.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of Female object : " + idsOfMAleObject);
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the status
    @Test
    public void test09() {
        List<String> allStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of Female object : " + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get email of the object where name = " " AkshatEmbranthir".example
    @Test
    public void test10() {
        String email = response.extract().path("[5].email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of Female object : " + email);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get gender of id = 5914189
    @Test
    public void test11(){
        String email = response.extract().path(("findAll{it.id == '93944'}.gender"));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of Female object : " + email);
        System.out.println("------------------End of Test---------------------------");

    }



}