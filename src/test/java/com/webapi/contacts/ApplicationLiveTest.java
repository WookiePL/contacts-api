package com.webapi.contacts;

import com.webapi.contacts.service.ContactServiceTest;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// To execute these tests ContactsApplication has to be running
@Disabled
public class ApplicationLiveTest {
    
    @Test
    public void givenLoggedUser_whenGetContactById_thenOK() {
        final Response response = givenAuth("user1", "pass1").get("http://localhost:8080/contacts/1");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("contactId"));
    }
    @Test
    public void givenBadPassword_whenGetContactById_thenUnauthorized() {
        final Response response = givenAuth("user1", "notGoodPassword").get("http://localhost:8080/contacts/1");
        assertEquals(401, response.getStatusCode());
    }

    @Test
    public void givenLoggedUserAndValidContact_whenGetAllContacts_thenOK() {
        final Response response = givenAuth("user1", "pass1").contentType(MediaType.APPLICATION_JSON_VALUE).body(ContactServiceTest.getValidContact()).post("http://localhost:8080/contacts");
        assertEquals(200, response.getStatusCode());
    }



    private RequestSpecification givenAuth(String username, String password) {
        return RestAssured.given().log().uri().auth().form(username, password, new FormAuthConfig("/login","username","password"));
    }
}