//Description: Develop a simplified e-commerce platform:
//Create an abstract class Product with fields like productId, name, and price, and an abstract method calculateDiscount().
//Extend it into concrete classes: Electronics, Clothing, and Groceries.
//Implement an interface Taxable with methods calculateTax() and getTaxDetails() for applicable product categories.
//Use encapsulation to protect product details, allowing updates only through setter methods.
//Showcase polymorphism by creating a method that calculates and prints the final price (price + tax - discount) for a list of Product.


abstract class Product {
    private String productId;
    private String name;
    private int price;

    Product(String productId, String name, int price){
        setName(name);
        setPrice(price);
        setProductId(productId);
    }

    public void productDetails(){
        System.out.println("Product Id: " + this.productId);
        System.out.println("Product name: " + this.name);
        System.out.println("Product price: " + this.price);
    }

    public String getProductId() {
        return this.productId;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract int calculateDiscount();
    abstract int getFinalPrice();
}

interface Taxable {
    void calculateTax();
    int getTaxDetails();
}

class Electronics extends Product implements Taxable {
    int tax;

    Electronics(String productId, String name, int price){
        super(productId, name, price);
    }

    @Override
    int calculateDiscount(){
        int price = getPrice();
        return price/10;
    }

    @Override
    int getFinalPrice(){
        return getPrice() - calculateDiscount() + this.tax;
    }

    @Override
    public void calculateTax(){
        int price = getPrice();
        this.tax = (price*15)/100;
    }

    @Override
    public int getTaxDetails(){
        return this.tax;
    }
}

class Clothing extends Product implements Taxable {
    int tax;

    Clothing(String productId, String name, int price){
        super(productId, name, price);
    }

    @Override
    int calculateDiscount(){
        int price = getPrice();
        return (price*12)/100;
    }

    @Override
    int getFinalPrice(){
        return getPrice() - calculateDiscount() + this.tax;
    }

    @Override
    public void calculateTax(){
        int price = getPrice();
        this.tax = (price*12)/100;
    }

    @Override
    public int getTaxDetails(){
        return this.tax;
    }
}

class Groceries extends Product implements Taxable {
    int tax;

    Groceries(String productId, String name, int price){
        super(productId, name, price);
    }

    @Override
    int getFinalPrice(){
        return getPrice() - calculateDiscount() + this.tax;
    }

    @Override
    int calculateDiscount(){
        int price = getPrice();
        return price/10;
    }

    @Override
    public void calculateTax(){
        int price = getPrice();
        this.tax = (price*13)/100;
    }

    @Override
    public int getTaxDetails(){
        return this.tax;
    }
}

public class ECommercePlatform {
    public static void main(String[] args) {
        Product electronics = new Electronics("1", "Electronics",5000);
        Product clothing = new Clothing("2", "Clothing", 1000);
        Product groceries = new Groceries("3", "Groceries", 700);

        Product[] products = {electronics,clothing,groceries};

        for(Product p : products){
            p.productDetails();
            p.getFinalPrice();
            ((Taxable) p).calculateTax();
            System.out.println("Tax to applied: " + ((Taxable) p).getTaxDetails());
            System.out.println("--------------");
        }
    }

//    Product Id: 1
//    Product name: Electronics
//    Product price: 5000
//    Tax to applied: 750
//            --------------
//    Product Id: 2
//    Product name: Clothing
//    Product price: 1000
//    Tax to applied: 120
//            --------------
//    Product Id: 3
//    Product name: Groceries
//    Product price: 700
//    Tax to applied: 91
//            --------------

}