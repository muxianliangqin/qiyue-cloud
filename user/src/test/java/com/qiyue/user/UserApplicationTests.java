package com.qiyue.user;

import com.qiyue.base.utils.encrypt.CipherUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
//        String menuTree = userController.getMenuNodeByUserId(1);
//        System.bo.println(menuTree);
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.post("/getMenuNodeByUserId")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .model("id", "1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
    }

    @Test
    public void test1() throws Exception {
//        System.bo.println(BaseUtil.getRandomString(20,2));
        System.out.println(CipherUtil.SHA.encrypt("test123", "Tj1PC56Yl5mZEB6fak7S"));
    }
}
