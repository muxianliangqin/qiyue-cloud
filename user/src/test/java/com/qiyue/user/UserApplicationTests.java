package com.qiyue.user;

import com.qiyue.base.util.BaseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
//    @Autowired
//    protected WebApplicationContext webApplicationContext;
//    @Autowired
//    private UserController userController;

//    private MockMvc mockMvc;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(webApplicationContext).build();
//    }

    @Test
    public void contextLoads() throws Exception {
//        String menuTree = userController.getMenuNode(1);
//        System.out.println(menuTree);
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.post("/getMenuNode")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .param("id", "1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
    }

    @Test
    public void test1() throws Exception {
//        System.out.println(BaseUtil.getRandomString(20,2));
        System.out.println(BaseUtil.encrypt("test123", "Tj1PC56Yl5mZEB6fak7S"));
    }
}
