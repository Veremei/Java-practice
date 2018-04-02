package l1;

public class MyEx extends Throwable {
    String str;

    public MyEx(String str){
        this.str = str;
    }

    public String getTxtEx(){
        String str1;
        str1 = "Некорректно введённое поле "  +str + "!";
        return str1;
    }

}
