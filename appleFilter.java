import java.util.ArrayList;
import java.util.List;

public class appleFilter {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        Apple apple1 = new Apple();
        apple1.color = Color.GREEN;
        inventory.add(apple1);

        Apple apple2 = new Apple();
        apple2.color = Color.RED;
        inventory.add(apple2);

        Apple apple3 = new Apple();
        apple3.color = Color.GREEN;
        inventory.add(apple3);


        System.out.println(filterGreenApples(inventory).toString());
    }


    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple : inventory) {
            System.out.println("!!");
            if (Color.GREEN.equals(apple.getColor())) {
                System.out.println("apple = " + apple);
                result.add(apple);
            }
        }
        return result;
    }
}

class Apple {
    public Color color;

    public Color getColor() {
        return color;
    }
}

enum Color {
    RED, GREEN
}
