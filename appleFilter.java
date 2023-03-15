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


        System.out.println(filterApplesByColor(inventory, Color.GREEN).toString());
    }


    // 첫번째 시도
    // 초록색 사과 필터링
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

    // 두번째 시도 (색을 파라미터화)
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple : inventory) {
            System.out.println("!!");
            if (apple.getColor().equals(color)) {
                System.out.println("apple = " + apple);
                result.add(apple);
            }
        }
        return result;
    }

    // 무게로 필터링 하는 경우
    // 색 필터링 코드와 대부분 중복! bad
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple : inventory) {
            System.out.println("!!");
            if (apple.getWeight() > weight) {
                System.out.println("apple = " + apple);
                result.add(apple);
            }
        }
        return result;
    }

    // 세번째 시도
    // 색과 무게 필터 합치고 어떤 기준으로 필터링 할 지 flag 주는 방식? -> bad!
}

class Apple {
    public Color color;
    public int weight;

    public Color getColor() {
        return color;
    }
    public int getWeight() {
        return weight;
    }
}

enum Color {
    RED, GREEN
}