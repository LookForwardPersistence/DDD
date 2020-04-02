package com.dawn.core.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Dawn on 2020/4/2.
 */
@Entity
@Table(name = "dawn")
public class Dawn extends DawnGenerateAbstractEntity {
    private static final long serialVersionUID = 2052729255665759386L;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "addr")
    private String addr;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
