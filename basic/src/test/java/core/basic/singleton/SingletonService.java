package core.basic.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    //조회는 이함수로만 가능
    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static메서드를 통해서만 조회가능하도록 허용
    public static SingletonService getInstance(){//조회할때
        return instance;
    }

    //생성자를 private로 선언해서 외부에서 new키워드를 사용한 객체생성을 못하게 막음
    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
      /*public static void main(String[]args){
          SingletonService singletonService=new SingletonService();
      }*/

}
