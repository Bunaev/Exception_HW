package Task_005;
import java.util.HashMap;
import java.util.Scanner;

public class AllProductsInShop {
    HashMap<String, ProductList> allProducts;

    public AllProductsInShop() {
        this.allProducts = new HashMap<>();
    }
    // Метод существует исключительно для создания первичной базы.
    // Для добавления нового товара - addNewProduct(String categoryName, Product product)
    public void addProduct(String categoryName, Product product) {
        if (!allProducts.containsKey(categoryName.toUpperCase())) {
            allProducts.put(categoryName.toUpperCase(), new ProductList());
        }
        allProducts.get(categoryName.toUpperCase()).addProduct(product);
    }

    // Метод для вывода всех позиций на складе магазина.
    // Отрабатывается исключение OutOfStockAllItemsException - если товаров в магазине нет.
    public void allPositionsInShop() throws OutOfStockAllItemsException {
        double totalPrice = 0;
        int totalQuantity = 0;
        if (allProducts.isEmpty()) {
            throw new OutOfStockAllItemsException ("-  В настоящий момент товары отсутствую в магазине. Добавьте новые товары.");
        }
        for (String categoryName : allProducts.keySet()) {
            System.out.println("В категории " + categoryName + ": ");
            try {
                allProducts.get(categoryName).getAllPositions();
                for (Product product : allProducts.get(categoryName).productList) {
                    totalPrice = (product.getPrice() * product.getQuantity()) + totalPrice;
                    totalQuantity += product.getQuantity();
                }
            } catch (OutOfStockItemsException e) {
                System.out.println(e.getMessage());

            }
        }
        System.out.println("Всего товаров в магазине: " + totalQuantity + ", на общую сумму: " + totalPrice + ".\n");
    }

    // Метод для добавления нового товара в магазин.
    // Проверяет наличие ключа (категории товара), если таковой отсутствует - запрашивает у пользователя разрешения добавить новую категорию.
    // Отрабатывается исключение IllegalArgumentException - при некорректном ответе на запрос.
    public void addNewProduct(String categoryName, Product product) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        if (!allProducts.containsKey(categoryName.toUpperCase())) {
            System.out.print("Данной категории товаров не существует. Добавить новую категорию? Да/Нет: ");
            String answer = scanner.nextLine().toLowerCase();
            if (!answer.contains("д") && !answer.contains("н")){
                throw new IllegalArgumentException("Некорректный ввод данных.");
            }
            else if (answer.contains("д")) {
                allProducts.put(categoryName.toUpperCase(), new ProductList());
            }
            else if (answer.contains("н")) {
                System.out.println("\n*** Добавление нового товара отменено. ***\n");
                return;
            }
        }
        allProducts.get(categoryName.toUpperCase()).addProduct(product);
        System.out.println("\n*** Товар успешно добавлен. ***\n");
    }

// Метод для покупки товара. Проверяет наличие товара в магазине, если нет - выбрасывает исключение ProductNotFoundException.
    public void purchaseProduct(String productName, int quantity) throws ProductNotFoundException {
        for (String categoryName : allProducts.keySet()) {
            if (!searchProduct(productName)) {
                throw new ProductNotFoundException("\n-   Такого товара не существует.    -\n");
            }
            for (Product product : allProducts.get(categoryName).productList) {
                try {
                    if (product.getName().equals(productName)) {
                        allProducts.get(categoryName).purchaseProduct(productName, quantity);
                        System.out.println("\n*** Товар продан. ***\n");
                        break;
                    }
                } catch (OutOfQuantityItemsException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }

    // Приватный метод для поиска товара в магазине. Если находит - возвратит true, если нет - возвратит false.
    // Используется для метода purchaseProduct и проверки исключения ProductNotFoundException.
    // Хотел реализовать полноценный поисковик по товарам, но уже не хватает времени.
    private boolean searchProduct(String productName) {
        boolean position = false;
        for (String categoryName : allProducts.keySet()) {
            for (Product product : allProducts.get(categoryName).productList) {
                if (product.getName().equals(productName)) {
                    position = true;
                    break;
                }
            }
        }
        return position;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Список всех товаров в магазине:\n");
        for (String categoryName : allProducts.keySet()) {
            sb.append(categoryName).append(":\n");
            for (Product product : allProducts.get(categoryName).productList) {
                sb.append("- ").append(product);
            }
        }
        return sb.toString();
    }
}
