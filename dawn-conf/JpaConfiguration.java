package com.dawn.conf;

import org.dayatang.domain.EntityRepository;
import org.dayatang.persistence.jpa.EntityRepositoryJpa;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.querychannel.impl.QueryChannelServiceImpl;
import org.hibernate.validator.HibernateValidator;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Properties;

/**
 * Created by Dawn on 2020/4/2.
 * 基于JPA初始化dddlib组件，dddlib支持JPA及Hibernate
 */
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.dawn.**.domain")
public class JpaConfiguration {

 /**事务切入点规则*/
 private static final String AOP_POINTCUT_EXPRESSION="(execution(* com.dawn..application.impl..*.*(..))) or (execution(* com.dawn..facade.impl..*.*(..))) or (execution(* org.dayatang.domain.EntityRepository.*(..)))";

 /**定义事务切面*/
 @Bean
 public Advisor advisor(PlatformTransactionManager transactionManager){
     AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
     pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
     DefaultPointcutAdvisor advisor= new DefaultPointcutAdvisor();

     advisor.setPointcut(pointcut);
     Properties properties = new Properties();
     properties.setProperty("get*","readOnly");
     properties.setProperty("find*","readOnly");
     properties.setProperty("*","PROPAGATION_REQUIRED");
     TransactionInterceptor txAdvice= new TransactionInterceptor(transactionManager,properties);
     advisor.setAdvice(txAdvice);
     return advisor;
 }

 /***
  *  初始化数据校验器
  * @param
  * @return
  * @author fanqianghua
  * @date 2020/4/2
  */
 @Bean
 public Validator validated(){
     ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
             .configure()
             //为true时代表快速失败模式，false则为全部校验后再结束
             .addProperty( "hibernate.validator.fail_fast", "false" )
             .buildValidatorFactory();
     Validator validator = validatorFactory.getValidator();
     return validator;
 }

 /**初始化dddlib仓库*/
@Bean("repository")
 public EntityRepository entityRepository(EntityManager entityManager){
     EntityRepositoryJpa entityRepositoryJpa = new EntityRepositoryJpa(entityManager);
     return entityRepositoryJpa;
 }

 @Bean("queryChannel")
 public QueryChannelServiceImpl queryChannelService(EntityRepository entityRepository){
     QueryChannelServiceImpl queryChannelService = new QueryChannelServiceImpl(entityRepository);
     return queryChannelService;
 }
}
