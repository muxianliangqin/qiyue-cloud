package com.qiyue.user;

import com.qiyue.user.controller.UserController;
import com.qiyue.user.node.Node;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(webApplicationContext).build();
//    }

    @Test
    public void contextLoads() throws Exception {
        String menuTree = userController.getMenuNode(1);
        System.out.println(menuTree);
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.post("/getMenuNode")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .param("id", "1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
    }
}
