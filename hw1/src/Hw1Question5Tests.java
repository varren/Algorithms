
public class Hw1Question5Tests {
    public static void main(String[] args) {
        double x = 1000000000;
        double a = Math.sqrt(x);//Math.pow(2,Math.log(x));
        double b = Math.pow(10,x);//Math.pow(2,Math.pow(2,Math.log(x)));
        double c = Math.pow(x,1.5);//Math.pow(x,2.5);
        double d =Math.pow(2,Math.sqrt(Math.log(x))); //Math.pow(2,Math.sqrt(x));
        double o=5;
        double g =3 ;
        double e = Math.pow(x,o/g);//Math.log(x)*Math.sqrt(x);

        System.out.print("a= " +a+" b= "+ b+" c= "+c+" d= "+ d+ " e = " + e);
    }
}
