package com.example.ecommerce

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
class WebMvcControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

}