package com.dawn.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.dayatang.domain.Entity;
import org.dayatang.domain.EntityRepository;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.BeanUtils;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Dawn on 2020/4/2.
 */
public abstract class DawnBaseEnity implements Entity {
    private static final long serialVersionUID = 7072577299321372830L;

    private static EntityRepository entityRepository;
    private static QueryChannelService queryChannelService;

    public DawnBaseEnity() {
    }

    @Override
    public Serializable getId() {
        return null;
    }

    @Override
    public boolean existed() {
        Object id = this.getId();
        return id==null?false:(id instanceof Number && ((Number)id).intValue()==0?false:(!(id instanceof String)||!((String)id).isEmpty()&&((String)id).length()!=0?getEntityRepository().exists(this.getClass(),this.getId()):false));
    }

    public static EntityRepository getEntityRepository(){
        if(entityRepository==null){
            entityRepository = InstanceFactory.getInstance(EntityRepository.class,"repository");
        }
        return entityRepository;
    }
    @Override
    public boolean notExisted() {
        return !this.existed();
    }

    public static void  setEntityRepository(EntityRepository repository){
        entityRepository=repository;
    }

    public static QueryChannelService getQueryChannelService(){
        if(queryChannelService==null){
            queryChannelService = InstanceFactory.getInstance(QueryChannelService.class,"queryChannelService");
        }
        return queryChannelService;
    }

    public static void setQueryChannelService(QueryChannelService queryChannelService){
        queryChannelService = queryChannelService;
    }

    public abstract String[] businessKeys();

    public int hashCode(){
        HashCodeBuilder builder = new HashCodeBuilder(13,37);
        Map<String,Object> propValues = (new BeanUtils(this)).getPropValues();
        if(this.businessKeys()==null){
            return builder.toHashCode();
        }else {
            String[] arr= this.businessKeys();
            int len = arr.length;

            for(int i = 0;i<len;i++){
                String businessKey=arr[i];
                builder=builder.append(propValues.get(businessKey));
            }
            return builder.toHashCode();
        }
    }

    public boolean equals(Object other){
        if(this==other){
            return true;
        }else if(other==null){
            return false;
        }else if(this.businessKeys()!=null && this.businessKeys().length!=0){
            if(!this.getClass().isAssignableFrom(other.getClass())){
                return false;
            }else {
                Map<String,Object> thisPropValue=(new BeanUtils(this)).getPropValuesExclude(new Class[]{Transient.class});
                Map<String,Object> otherPropValue = (new BeanUtils(this)).getPropValuesExclude(new Class[]{Transient.class});
                EqualsBuilder builder = new EqualsBuilder();
                String[] arr = this.businessKeys();
                int len = arr.length;

                for(int i=0;i<len;i++){
                    String businessKey=arr[i];
                    builder.append(thisPropValue.get(businessKey),otherPropValue.get(businessKey));
                }

                return builder.isEquals();
            }
        }else {
            return false;
        }
    }
}
