import java.util.Scanner;


public class JavaFactoryPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        FoodFactory foodFactory = new FoodFactory();
        Food food = foodFactory.getFood(input);
        System.out.println("The factory returned class " + food.getClass().getSimpleName());
        System.out.println(food.getType());
    }

    static class FoodFactory {
        public FoodFactory() {}

        public Food getFood(String input) {
            if ("pizza".equals(input)) {
                return new Pizza();
            } else if ("cake".equals(input)) {
                return new Cake();
            }
            return null;
        }
    }

    interface Food {
        String getType();
    }

    static class Pizza implements Food {

        @Override
        public String getType() {
            return "Someone ordered a Fast Food!";
        }
    }

    static class Cake implements Food {

        @Override
        public String getType() {
            return "Someone ordered a Dessert!";
        }
    }
}

