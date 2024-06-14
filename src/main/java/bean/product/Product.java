package bean.product;


public class Product {
    private Integer id;
    private String name;
    private String size;
    private Integer price;
    private String ice;
    private String sugar;
    private String type;
    private Integer state;
    private String Status;
    private Integer stack;

    public Integer getStack() {
        return stack;
    }

    public void setStack(Integer stack) {
        this.stack = stack;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setState(int state) {
        this.state = state;
    }

    private Integer quantity;

    public Product() {
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", ice='" + ice + '\'' +
                ", sugar='" + sugar + '\'' +
                ", type='" + type + '\'' +
                ", state=" + state +
                ", Status='" + Status + '\'' +
                ", stack=" + stack +
                ", quantity=" + quantity +
                '}';
    }
}
