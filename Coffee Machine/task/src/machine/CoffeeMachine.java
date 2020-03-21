package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private State state;
    private int water, milk, beans, cups, money;
    private static Scanner scanner = new Scanner(System.in);

    public CoffeeMachine() {
        state = State.ChoosingAction;
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        while (coffeeMachine.state != State.EXITING) {
            String command = coffeeMachine.chooseAction();
            coffeeMachine.start(command);
            if (!command.equals("exit")) System.out.println();
            coffeeMachine.checkState();
            if (!command.equals("exit")) System.out.println();
        }
    }

    public void start(String command) {
        switch (command) {
            case "buy": {
                this.setState(State.ChoosingCoffeeVariant);
                break;
            }
            case "fill": {
                this.setState(State.FILLING);
                break;
            }
            case "take": {
                this.setState(State.TAKING);
                break;
            }
            case "remaining": {
                this.setState(State.PRINTING);
                break;
            }
            case "exit": {
                setState(State.EXITING);
                break;
            }
            default:
                System.out.println("Invalid command");
                this.setState(State.ChoosingAction);
                break;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public void checkState() {
        switch (state) {
            case ChoosingCoffeeVariant: this.buyCoffee(this.chooseCoffee()); break;
            case FILLING: this.fill(); break;
            case TAKING: this.takeMoney(); break;
            case PRINTING: this.printResources(); break;
        }
    }

    public String chooseAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.nextLine();
        return action;
    }

    public boolean checkResources(int water, int milk, int beans, int money) {
        if (this.water < water) {
            System.out.println("Sorry, not enough water!");
        } else if (this.milk < milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (this.beans < beans) {
            System.out.println("Sorry, not enough beans!");
        } else if (this.cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= water;
            this.milk -= milk;
            this.beans -= beans;
            --this.cups;
            this.money += money;
            return true;
        }
        return false;
    }

    public int chooseCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                " back - to main menu:");
        String choice = scanner.nextLine();
        if (choice.equals("back")) {
            this.setState(State.ChoosingAction);
            return 0;
        }
        return Integer.valueOf(choice);
    }

    public void buyCoffee(int choice) {
        switch (choice) {
            case 0: break;
            case 1: {
                checkResources(250, 0, 16, 4);
                break;
            }
            case 2: {
                checkResources(350, 75, 20, 7);
                break;
            }
            case 3: {
                checkResources(200, 100, 12, 6);
                break;
            }
            default:
                System.out.println("Invalid choice");
        }
    }

    public void printResources() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water\n" + milk + " of milk\n" + beans + " of coffee beans\n" +
                cups + " of disposable cups\n$" + money + " of money");
    }

    public void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        int addWater = Integer.valueOf(scanner.nextLine());
        System.out.println("Write how many ml of milk do you want to add:");
        int addMilk = Integer.valueOf(scanner.nextLine());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addBeans = Integer.valueOf(scanner.nextLine());
        System.out.println("Write how many disposable cups do you want to add:");
        int addCups = Integer.valueOf(scanner.nextLine());
        water += addWater;
        milk += addMilk;
        beans += addBeans;
        cups += addCups;
    }


}

enum State {
    ChoosingAction, ChoosingCoffeeVariant, FILLING, TAKING, PRINTING, EXITING;
}