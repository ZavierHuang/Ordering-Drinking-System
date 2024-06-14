package bean.cart;

public class Cart {
    private Integer CartID;
    private Integer CustomerID;
    private Integer ProductID;
    private Integer Quantity;

    public Cart() {
    }

    public Integer getBuildID() { return CartID; }

    public void setCartID(Integer cartID) { CartID = cartID; }

    public Integer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Integer customerID) {
        CustomerID = customerID;
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public Integer getQuantity() { return Quantity; }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
