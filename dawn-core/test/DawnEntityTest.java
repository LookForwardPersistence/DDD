package com.dawn.core.test;

import com.dawn.core.domain.Dawn;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

/**
 * Created by Dawn on 2020/4/2.
 */
public class DawnEntity extends BaseTest {

    @Test
    @Rollback(value = false)
    public void save(){
        Dawn dawn = new Dawn();
        dawn.setAddr("平南");
        dawn.setAge("30");
        dawn.setName("Dawn");
        System.err.println(dawn.getId());
        dawn.save();
    }
}
