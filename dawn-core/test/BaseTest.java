package com.dawn.core.test;

import com.dawn.core.ApplicationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Created by Dawn on 2020/4/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@Transactional
public abstract class BaseTest {

    @Before
    public void before(){

    }

    @After
    public void after(){

    }


}
