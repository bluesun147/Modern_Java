import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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


        System.out.println(filterApplesByColor(inventory, Color.GREEN));
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

    // 네번째 시도 : 추상적 조건으로 필터링
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) { // predicate 객체로 사과 검사 조건 캡슐화
                result.add(apple);
            }
        }
        return result;
    }

    // 다섯번째 시도 : 익명 클래스
    // 익명 클래스는 클래스 선언과 인스턴스화 동시에 할 수 있다
    // 즉 즉석에서 필요한 구현 바로 만들어서 사용 가능!
    // 하지만 코드의 장황함 (verbosity)
    // 인터페이스 구현하는 여러 클래스 선언 과정 줄일 수 있지만 한눈에 이해 어렵다
    List<Apple> inventory = null;
    List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
        @Override
        public boolean test(Apple apple) {
            return Color.RED.equals(apple.getColor());
        }
    });

    // 여섯 번째 시도 : 람다 표현식 사용
    List<Apple> result =
            filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

    // 일곱 번째 시도 : 리스트 형식으로 추상화
    // 메소드 한정 제네릭! https://st-lab.tistory.com/153
    // public <T> T genericMethod(T o) { ..
    // 접근 제어자 <제네릭타입> [반환타입] [메소드명] ([제네릭타입] [파라미터]) { ..
    public static <T> List<T> filter(List<T> list, Predicate7<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    List<Apple> redApples7 =
            filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));


    private List<Integer> numbers;
    List<Integer> evenNumbers =
            filter(numbers, (Integer i) -> i % 2 == 0);
}

// 네번째 시도에서 사용
// 참/거짓 반환하는 함수 predicate 이라고 함.
// 선택 조건을 결정하는 인터페이스
// 사과 선택 전략을 캡슐화
interface ApplePredicate {
    boolean test(Apple apple);
}

// 여러 선택 조건의 predicate 정의 가능.
// 조건에 따라 predicate 적절하게 만들면 됨.

// 무거운 사과만 선택
class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.weight > 150;
    }
}

// 녹색 사과만 선택
class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}

// 일곱 번째 시도 : 리스트 형식으로 추상화
interface Predicate7<T> {
    boolean test(T t);
};




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