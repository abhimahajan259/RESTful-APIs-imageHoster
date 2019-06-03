package com.upgrad.technical.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Write the annotation to tell jUnit to run using Spring's testing support
@RunWith(SpringRunner.class)
//Write the annotation to specify that this test class runs Spring Boot based tests
@SpringBootTest
//Write the annotation to enable and configure auto-configuration of MockMvc
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void getImage() throws Exception {

        //perform() method calls the endpoint with the GET Request and passes the authorization header.
        //Passing the uriTemplate "/images/uuid_of_image_in_database" as a GET Request.
        //Also pass the access token "given to a user at the time of authentication" in the authorization header for authorization.
        //This test passes when you pass the correct image uuid and also the correct access token(with admin as role)
        //Change the image uuid and header in the GET Request according to your database
        mvc.perform(MockMvcRequestBuilders.get("/images/e8861dd3-1138-46c9-bed7-465fb6fa2415").header("authorization", "eyJraWQiOiIwOTUyZDE2Ny1jMjY0LTQwNDUtYjM5OS01NzU3NzY3YmM0NjkiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJjMTBmYmVjNy0xNjVkLTRlYmItYTE4NC1lMDBjY2FjNjFhZTciLCJpc3MiOiJodHRwczovL3Byb21hbi5pbyIsImV4cCI6MTUzNzEzNCwiaWF0IjoxNTM3MTA1fQ.Fp7WjBdwPugJkYnscWCabDNF1IkZ5stKXCRNSzS-uc-WAt18jm8Ik-lnumukAVeKb3y5U2fKarWY_RTk2KrGQA"))
                .andExpect(status().isOk());
    }


    @Test
    public void getImageWithWronguuid() throws Exception {
        //This test passes when you pass the wrong image uuid but the correct access token(with admin as role)
        mvc.perform(MockMvcRequestBuilders.get("/images/e8861dd3-1138-46c9-bed7-465fb6fa241").header("authorization", "eyJraWQiOiIwOTUyZDE2Ny1jMjY0LTQwNDUtYjM5OS01NzU3NzY3YmM0NjkiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJjMTBmYmVjNy0xNjVkLTRlYmItYTE4NC1lMDBjY2FjNjFhZTciLCJpc3MiOiJodHRwczovL3Byb21hbi5pbyIsImV4cCI6MTUzNzEzNCwiaWF0IjoxNTM3MTA1fQ.Fp7WjBdwPugJkYnscWCabDNF1IkZ5stKXCRNSzS-uc-WAt18jm8Ik-lnumukAVeKb3y5U2fKarWY_RTk2KrGQA"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getImageWithnonadminRole() throws Exception {
        //This test passes when you pass the correct image uuid but the access token should be of non-admin
        mvc.perform(MockMvcRequestBuilders.get("/images/e8861dd3-1138-46c9-bed7-465fb6fa2415").header("authorization", "eyJraWQiOiI3YWJiN2NjNi0zNTkwLTRmMTUtYTA3Ny0wODA3NmI2ZGNkNDEiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiIxMTMwYTE1NS1jNTgxLTQ5NzAtYTk4ZS1mOTliZWM5OGU2NTgiLCJpc3MiOiJodHRwczovL3Byb21hbi5pbyIsImV4cCI6MTUzNzEzNCwiaWF0IjoxNTM3MTA1fQ.EVtzNVaFAIzw1YByKcb69wPdNTvYojms_EDCc18ER0GNU2oamh-Lwg-WPnb55wIPV59191n5hnDIygMIihXbAQ"))
                .andExpect(status().isForbidden());
    }
}
