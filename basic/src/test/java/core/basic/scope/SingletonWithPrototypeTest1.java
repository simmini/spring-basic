package core.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2=ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class,ClientBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

        //assertThat(clientBean1).isEqualTo(clientBean2);
        System.out.println("clientBean1 = " + clientBean1);
        System.out.println("clientBean2 = " + clientBean2);
        System.out.println("count1 = " + count1);
        System.out.println("count2 = " + count2);
    }
    @Scope("singleton")//기본값이라 생략가능
    static class ClientBean{
        //private final PrototypeBean prototypeBean;//생성 시점에 주입,이미 주입이 되어있어서 계속 같은거 씀
        //주입된 애를 같이 씀
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        @Autowired
            private Provider<PrototypeBean> prototypeBeanProvider;

          /*  @Autowired
            private ClientBean(PrototypeBean prototypeBean) {
                this.prototypeBean = prototypeBean;
            }*/

            public int logic(){
                PrototypeBean prototypeBean=prototypeBeanProvider.get();
                prototypeBean.addCount();//생성시점에 주입된 그 값을 쓰고 있는거
                int count= prototypeBean.getCount();
                return count;
            }


    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;

        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init = " + this);
        }

        @PreDestroy
        public void destory(){
            System.out.println("prototype.destory");
        }

    }
}
