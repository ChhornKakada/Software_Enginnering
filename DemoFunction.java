import javax.sql.rowset.spi.SyncFactory;

public class DemoFunction {

    // Sum from 1 to n
    static int Sum1toN(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++){
            sum += i;
        }
        return sum;
    }

    // Greeting message
    static void greeting(String name, String gender){
        if (gender == "MALE"){
            System.out.printf("\nHello, %s. You are so handsome today.\n", name);
        } else {
            System.out.printf("\nHello, %s. You are so beautiful today.\n", name);
        } 
    }


    public static void main(String[] args) {
        System.out.printf("Sum: %d\n", Sum1toN(10));
        String gender = "male";
        gender = gender.toUpperCase();
        greeting("kakada Chhorn", gender.toUpperCase());
        String name = "Chhorn-Kakada";
        name = name.toUpperCase();
        System.out.printf("%s\n", name);
        name = name.toLowerCase();
        System.out.printf("%s\n", name);
    }
}
