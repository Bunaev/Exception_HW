package Task_005;
import java.util.Scanner;

public class OnlineShop {
    public static void main(String[] args) {
        AllProductsInShop allProductsInShop = new AllProductsInShop();
        // Для удобства - подготовил небольшую базу товаров.
        // Было бы правильнее настроить работу через json и считывать/записывать оттуда, но я и так слишком увлёкся.
        allProductsInShop.addProduct("Одежда", new Product("Брюки", 5600, 10));
        allProductsInShop.addProduct("Одежда", new Product("Рубашка", 3000, 10));
        allProductsInShop.addProduct("Товары для дома", new Product("Подушка", 2000, 20));
        allProductsInShop.addProduct("Товары для дома", new Product("Полотенце", 900, 50));
        allProductsInShop.addProduct("Электроника", new Product("Телевизор Sony", 120000, 5));
        allProductsInShop.addProduct("Электроника", new Product("Ноутбук Asus ZenBook DUO", 210000, 2));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = menu();
            switch (choice) {
                case 1 -> {
                    if (allProductsInShop.allProducts.isEmpty()) {
                        System.out.println("\n*** Список товаров пуст. ***\n");
                    }else {
                        System.out.println(allProductsInShop);
                    }
                    continue;
                }
                case 2 -> {
                    try {
                        allProductsInShop.allPositionsInShop();
                    }catch (OutOfStockAllItemsException e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                case 3 -> {
                    System.out.print("Введите категорию товара: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("Введите наименование товара: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите цену товара: ");
                    double price;
                    int quantity;
                    try {
                        price = Double.parseDouble(scanner.nextLine());
                        System.out.print("Введите количество товара: ");
                        quantity = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException e){
                        System.out.println("\n-    Ошибка ввода. Попробуйте еще раз.   -\n");
                        continue;
                    }
                    try {
                        allProductsInShop.addNewProduct(categoryName, new Product(name, price, quantity));
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                case 4 -> {
                    System.out.print("Введите наименование товара: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите количество приобретаемого товара: ");
                    int quantity;
                    try {
                        quantity = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException e){
                        System.out.println("\n-    Ошибка ввода. Попробуйте еще раз.   -\n");
                        continue;
                    }
                    try {
                        allProductsInShop.purchaseProduct(name, quantity);
                    } catch (ProductNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                case 5 -> {
                    System.out.println("До свидания! Приходите к нам еще!");
                }
            }
            scanner.close();
            break;
        }
    }

// Метод для вывода меню в консоль.
    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        String menu = """
                1. Показать все товары.
                2. Показать все позиции на складе.
                3. Добавить новый товар.
                4. Продажа.
                5. Выход.
                Выберете пункт меню:\s""";
        int choice = 0;
        while (choice!= 1 && choice!= 2 && choice!= 3 && choice!= 4 && choice!= 5) {
            System.out.print(menu);
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        }
        return choice;
    }
}
