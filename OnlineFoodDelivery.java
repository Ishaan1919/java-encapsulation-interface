// Abstract class representing a food item
abstract class FoodItem implements Discountable {
    private String itemName;
    private double price;
    private int quantity;

    FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public void getItemDetails() {
        System.out.println("Item Name: " + itemName);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    abstract double calculateTotalPrice(); // Must be implemented by subclasses
}

// Interface for applying discounts
interface Discountable {
    double applyDiscount();
    double getDiscountDetails();
}

// Subclass for Vegetarian Food
class VegItem extends FoodItem {
    private double discount;

    VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    double calculateTotalPrice() {
        return (getPrice() * getQuantity()) - applyDiscount();
    }

    @Override
    public double applyDiscount() {
        discount = (getPrice() * getQuantity()) * 0.1; // 10% discount on veg items
        return discount;
    }

    @Override
    public double getDiscountDetails() {
        return discount;
    }
}

// Subclass for Non-Vegetarian Food
class NonVegItem extends FoodItem {
    private double extraCharge = 20.0; // Extra charge for non-veg items
    private double discount;

    NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    double calculateTotalPrice() {
        return (getPrice() * getQuantity() + extraCharge) - applyDiscount();
    }

    @Override
    public double applyDiscount() {
        discount = (getPrice() * getQuantity()) * 0.05; // 5% discount on non-veg items
        return discount;
    }

    @Override
    public double getDiscountDetails() {
        return discount;
    }
}

// Main class to test the system
public class OnlineFoodDelivery {
    public static void main(String[] args) {
        FoodItem vegPizza = new VegItem("Veg Pizza", 12.5, 2);
        FoodItem chickenBurger = new NonVegItem("Chicken Burger", 8.0, 3);

        FoodItem[] order = {vegPizza, chickenBurger};

        for (FoodItem item : order) {
            item.getItemDetails();
            System.out.println("Discount Applied: $" + item.getDiscountDetails());
            System.out.println("Total Price after Discount: $" + item.calculateTotalPrice());
            System.out.println("----------------------------");
        }

//        Item Name: Veg Pizza
//        Price: $12.5
//        Quantity: 2
//        Discount Applied: $0.0
//        Total Price after Discount: $22.5
//                ----------------------------
//        Item Name: Chicken Burger
//        Price: $8.0
//        Quantity: 3
//        Discount Applied: $0.0
//        Total Price after Discount: $42.8
//                ----------------------------

    }
}
