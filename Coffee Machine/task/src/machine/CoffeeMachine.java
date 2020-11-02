package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    int waterSupply;
    int milkSupply;
    int coffeeBeansSupply;
    int disposableCupsSupply;
    int cash;
    MachineState state;

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();

        machine.process("");
        while (true) {
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            }
            machine.process(input);
        }
    }

    CoffeeMachine() {
        waterSupply = 400;
        milkSupply = 540;
        coffeeBeansSupply = 120;
        disposableCupsSupply = 9;
        cash = 550;
        state = MachineState.CHOOSE_OPERATION;
    }

    void setState(MachineState state) {
        this.state = state;
        switch (state) {
            case CHOOSE_OPERATION:
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            case BUY_COFFEE:
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case FILL_WATER:
                System.out.println("\nWrite how many ml of water do you want to add:");
                break;
            case FILL_MILK:
                System.out.println("\nWrite how many ml of milk do you want to add:");
                break;
            case FILL_COFFEE_BEANS:
                System.out.println("\nWrite how many grams of coffee beans do you want to add:");
                break;
            case FILL_DISPOSABLE_CUPS:
                System.out.println("\nWrite how many disposable cups of coffee do you want to add:");
                break;
            default:
                break;
        }
    }

    void process(String input) {
        int amount = 0;
        try {
            amount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
        }
        switch (state) {
            case CHOOSE_OPERATION:
                switch (input) {
                    case "remaining":
                        printSupplies();
                        setState(MachineState.CHOOSE_OPERATION);
                        break;
                    case "buy":
                        setState(MachineState.BUY_COFFEE);
                        break;
                    case "fill":
                        setState(MachineState.FILL_WATER);
                        break;
                    case "take":
                        takeCash();
                        setState(MachineState.CHOOSE_OPERATION);
                        break;
                    default:
                        setState(MachineState.CHOOSE_OPERATION);
                        break;
                }
                break;
            case BUY_COFFEE:
                if ("back".equals(input)) {
                    setState(MachineState.CHOOSE_OPERATION);
                } else {
                    makeCoffee(input);
                }
                break;
            case FILL_WATER:
                waterSupply += amount;
                setState(MachineState.FILL_MILK);
                break;
            case FILL_MILK:
                milkSupply += amount;
                setState(MachineState.FILL_COFFEE_BEANS);
                break;
            case FILL_COFFEE_BEANS:
                coffeeBeansSupply += amount;
                setState(MachineState.FILL_DISPOSABLE_CUPS);
                break;
            case FILL_DISPOSABLE_CUPS:
                disposableCupsSupply += amount;
                setState(MachineState.CHOOSE_OPERATION);
                break;
            default:
                break;
        }
    }

    void takeCash() {
        System.out.printf("\nI gave you $%d\n", cash);
        cash = 0;
    }

    void makeCoffee(String menuItem) {
        boolean isLegal = "1".equals(menuItem) || "2".equals(menuItem) || "3".equals(menuItem);
        if (!isLegal) {
            setState(MachineState.BUY_COFFEE);
            return;
        }
        CoffeeType coffeeType = CoffeeType.values()[Integer.parseInt(menuItem) - 1];

        if (coffeeType.water > waterSupply) {
            System.out.println("Sorry, not enough water!");
        } else if (coffeeType.milk > milkSupply) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffeeType.coffeeBeans > coffeeBeansSupply) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (disposableCupsSupply == 0) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            waterSupply -= coffeeType.water;
            milkSupply -= coffeeType.milk;
            coffeeBeansSupply -= coffeeType.coffeeBeans;
            disposableCupsSupply--;
            cash += coffeeType.price;
        }
        setState(MachineState.CHOOSE_OPERATION);
    }

    void printSupplies() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d of water\n", waterSupply);
        System.out.printf("%d of milk\n", milkSupply);
        System.out.printf("%d of coffee beans\n", coffeeBeansSupply);
        System.out.printf("%d of disposable cups\n", disposableCupsSupply);
        System.out.printf("$%d of money\n", cash);
    }
}

enum CoffeeType {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    int water;
    int milk;
    int coffeeBeans;
    int price;

    CoffeeType(int water, int milk, int coffeeBeans, int price) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.price = price;
    }
}

enum MachineState {
    CHOOSE_OPERATION,
    BUY_COFFEE,
    FILL_WATER,
    FILL_MILK,
    FILL_COFFEE_BEANS,
    FILL_DISPOSABLE_CUPS,
}