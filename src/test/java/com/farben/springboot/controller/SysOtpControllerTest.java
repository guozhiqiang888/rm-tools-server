package com.farben.springboot.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SysOtpControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(
                wac).build();
    }

//    @Test
//    public void testSaveSysOtp() throws Exception {
//        String JSON = "{\"id\":\"1001\",\"userId\":\"43990001\",\"otp\":\"123456\",\"expires\":15}";
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sysOtp/save")
//                .accept(MediaType.APPLICATION_JSON).content(JSON)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
//        Assert.assertEquals("1",response.getContentAsString());
//    }

    @Test
    public void testGetSysOtp() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sysOtp/get").param("id","1001").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
    }

}
