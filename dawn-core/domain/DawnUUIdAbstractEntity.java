package com.dawn.core.domain;

import org.dayatang.domain.Entity;
import org.dayatang.domain.NamedParameters;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Dawn on 2020/4/2.
 */
@MappedSuperclass
public class DawnUUIdAbstatEntity extends DawnBaseEnity {
    private static final long serialVersionUID = 7767249106436964988L;

    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString().replace("-","");

    @Version
    @Column(name = "version")
    private Integer version;


    public DawnUUIdAbstatEntity() {
    }


    public void save(){
        getEntityRepository().save(this);
    }

    public void remove(){
        getEntityRepository().remove(this);
    }

    public static <T extends Entity> T get(Class<T> clazz, Serializable id){
        return getEntityRepository().get(clazz,id);
    }

    public static <T extends Entity> T getUnmodified(Class<T> clazz,T entity){
        return getEntityRepository().getUnmodified(clazz,entity);
    }

    public static <T extends Entity> T load(Class<T> clazz,Serializable id){
        return getEntityRepository().load(clazz,id);
    }


    public static <T extends Entity> List<T> findAll(Class<T> tClass){
        return getEntityRepository().createCriteriaQuery(tClass).list();
    }

    public static <T extends Entity> List<T> findByProperty(Class<T> tClass,String propName,Object value){
        return getEntityRepository().findByProperty(tClass,propName,value);
    }

    public static <T extends  Entity> List<T> findByProperties(Class<T> tClass, Map<String,Object> propValues){
        return getEntityRepository().findByProperties(tClass, NamedParameters.create(propValues));
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String[] businessKeys() {
        return new String[0];
    }
}
