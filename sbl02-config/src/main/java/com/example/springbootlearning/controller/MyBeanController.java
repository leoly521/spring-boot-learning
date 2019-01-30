package com.example.springbootlearning.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.context.properties.EnableConfigurationProperties;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import com.example.springbootlearning.bean.MyBean;
        import com.example.springbootlearning.bean.TestBean;

@RestController
@EnableConfigurationProperties({MyBean.class, TestBean.class})
public class MyBeanController {

    @Autowired
    MyBean myBean;

    @RequestMapping(value = "/myBean")
    public String myBean() {
        return myBean.getGreeting() + ", " + myBean.getName() + ", " + myBean.getUuid() + ", " + myBean.getMax();
    }

    @Autowired
    TestBean testBean;

    @RequestMapping(value = "/testBean")
    public String testBean() {
        return this.testBean.getName() + ": " + this.testBean.getAge();
    }
}
