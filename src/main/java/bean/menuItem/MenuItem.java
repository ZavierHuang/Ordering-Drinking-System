package bean.menuItem;

public class MenuItem {
    private String name;
    private String size_M;
    private String size_L;
    private Integer price_M;
    private Integer price_L;
    private String type;
    private String state;

    private Integer stack;

    public Integer getStack() {
        return stack;
    }

    public void setStack(Integer stack) {
        this.stack = stack;
    }

    public MenuItem(){}

    public MenuItem(String name, Integer stack){
        this.name = name;
        this.stack = stack;
    }
    public MenuItem(String name, Integer price_M, Integer price_L, String type, String state, Integer stack) {
        this.name = name;
        this.price_M = price_M;
        this.price_L = price_L;
        this.type = type;
        this.state = state;
        this.stack = stack;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize_M() {
        return size_M;
    }

    public void setSize_M(String size_M) {
        this.size_M = size_M;
    }

    public String getSize_L() {
        return size_L;
    }

    public void setSize_L(String size_L) {
        this.size_L = size_L;
    }

    public Integer getPrice_M() {
        return price_M;
    }

    public void setPrice_M(Integer price_M) {
        this.price_M = price_M;
    }

    public Integer getPrice_L() {
        return price_L;
    }

    public void setPrice_L(Integer price_L) {
        this.price_L = price_L;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", size_M='" + size_M + '\'' +
                ", size_L='" + size_L + '\'' +
                ", price_M=" + price_M +
                ", price_L=" + price_L +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", stack=" + stack +
                '}';
    }
}
