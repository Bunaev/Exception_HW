package Task_005;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    List<Product> productList;

    public ProductList() {
        productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }
    // Метод для получения списка всех товаров на складе в определенной категории.
    // Отрабатывается исключение OutOfStockItemsException - если все товары в категории проданы.
    public void getAllPositions() throws OutOfStockItemsException {
        double totalPrice = 0;
        int totalQuantity = 0;
        if (productList.isEmpty()) {
            throw new OutOfStockItemsException("-   Товаров данной категории в настоящий момент нет на складе.\n");
        } else {
            for (Product product : productList) {
                totalPrice = (product.getPrice() * product.getQuantity()) + totalPrice;
                totalQuantity += product.getQuantity();
            }
            System.out.println("    Всего товаров на складе: " + totalQuantity + "\n" + "    На общую сумму: " + totalPrice + " руб.\n");
        }
    }

    public void purchaseProduct(String productName, int quantity) throws OutOfQuantityItemsException {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                product.setQuantity(product.getQuantity()-quantity);
                if (product.getQuantity() == 0){
                    productList.remove(product);
                    break;
                }
                else if (product.getQuantity() - quantity < 0){
                    throw new OutOfQuantityItemsException("\n-   Недостаточно товаров в наличии.  -\n");
                }
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : productList) {
            sb.append(product);
        }
        return sb.toString();
    }
}
