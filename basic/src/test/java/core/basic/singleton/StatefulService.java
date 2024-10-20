package core.basic.singleton;

public class StatefulService {

    //싱글톤 방식의 변수 공유 되는 부분 해결하기 위해 주석 처리
   // private int price;

   /* public void order (String name,int price){
        System.out.println("name = " + name+"price: "+price);
        this.price=price;
    }*/

    public int order (String name,int price){
        System.out.println("name = " + name+"price: "+price);
        return price;
    }


   /* public int getPrice(){
        return price;
    }*/

}
