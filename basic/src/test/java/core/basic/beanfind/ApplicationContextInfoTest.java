package core.basic.beanfind;

import core.basic.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

    //public 지워도 가능
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String []beanDefinitionNames=ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean=ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName+" object = "+bean);

        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String []beanDefinitionNames= ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition=ac.getBeanDefinition(beanDefinitionName);

            //ROLE_APPLICATION: 직접등록한 애플리케이션 빈
            //ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                //스프링내부등록이 아니라 애플리케이션개발을 위해서 등록하는 role
                Object bean=ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName+" object = "+bean);

            }
        }
    }

}
