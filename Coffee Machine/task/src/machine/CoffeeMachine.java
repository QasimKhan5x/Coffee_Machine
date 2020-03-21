/*
  A simulation of a real life coffee machine that
  you can interact with using the command line.

 * @author  Muhammad Qasim Khan
 * @version 1
 * @date   21-03-20
 */
package machine;

import java.util.Scanner;

public class CoffeeMachine {
    /**
     * The attributes involve the current state of the coffee machine,
     * the amount of resources present in it, and a scanner object to
     * take user input.
     */
    private State state;
    private int water, milk, beans, cups, money;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The constructor initializes a coffee machine with its initial resources
     */
    public CoffeeMachine() {
        state = State.ChoosingAction;
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
    }

    /**
     * The program creates a coffee machine and simulates it
     * until the user types exit.
     * @param args command line arguments
     */
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

    /**
     * Starts the coffee machine after taking input of a command
     * from the user
     * @param command command of the user from main(); can take 5 values
     */
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

    /**
     * Checks the current state of the coffee machine
     * and then performs the required method
     */
    public void checkState() {
        switch (state) {
            case ChoosingCoffeeVariant: this.buyCoffee(this.chooseCoffee()); break;
            case FILLING: this.fill(); break;
            case TAKING: this.takeMoney(); break;
            case PRINTING: this.printResources(); break;
        }
    }

    /**
     * Asks the user to choose an action
     * @return the initial choice of the user once
     * the coffee machine starts
     */
    public String chooseAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        return scanner.nextLine();
    }

    /**
     * Prints whether the coffee machine has enough resources to
     * make a particular coffee
     * @param water amount of water required to make some coffee
     * @param milk amount of milk required to make some coffee
     * @param beans number of coffee beans required to make some coffee
     * @param money amount of money to deposit in the coffee machine
     */
    public void checkResources(int water, int milk, int beans, int money) {
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
        }
    }

    /**
     * Chooses a particular coffee after the user enters buy
     * Helps the buyCoffee() method decide which coffee to buy
     * @return int representing the user's choice of coffee
     */
    public int chooseCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                " back - to main menu:");
        String choice = scanner.nextLine();
        if (choice.equals("back")) {
            this.setState(State.ChoosingAction);
            return 0;
        }
        return Integer.parseInt(choice);
    }

    /**
     * Called when user enters "buy"
     * Buys the user's desired coffee
     * @param choice user's choice of coffee returned by chooseCoffee()
     */
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

    /**
     * Called when user enters "remaining"
     * prints the resources available in the coffee machine
     */
    public void printResources() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water\n" + milk + " of milk\n" + beans + " of coffee beans\n" +
                cups + " of disposable cups\n$" + money + " of money");
    }

    /**
     * Called when user enter "take".
     * Sets amount of money in the coffee machine to 0
     */
    public void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    /**
     * Called when user enters "fill".
     * Fills the coffee machine with resources after
     * taking input from the user
     */
    public void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        int addWater = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many ml of milk do you want to add:");
        int addMilk = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addBeans = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many disposable cups do you want to add:");
        int addCups = Integer.parseInt(scanner.nextLine());
        water += addWater;
        milk += addMilk;
        beans += addBeans;
        cups += addCups;
    }

}

enum State {
    ChoosingAction, ChoosingCoffeeVariant, FILLING, TAKING, PRINTING, EXITING
}