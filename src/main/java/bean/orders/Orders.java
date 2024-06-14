package bean.orders;

public class Orders {
    private Integer orderID;
    private  Integer CustomerID;
    private Integer productID;
    private String dates;
    private String statusString;
    private String customerName;
    private Integer amount;
    private Integer quantity;
    private Integer status;

    public Orders() {

    }

    public Orders(int orderID){
        this.orderID = orderID;
    }

    public  Orders(int orderID,int customerID,String dates,int amount,int status){
        this.orderID=orderID;
        this.CustomerID=customerID;
        this.dates=dates;
        this.amount=amount;
        this.status=status;
        setStatusString();
    }

    public Orders(int orderID,int status){
        this.orderID=orderID;
        this.status=status;
        setStatusString();
    }
    public void setStatusString(){
        if(this.status==0){
            statusString="訂單已送出";
        }else if(this.status==1){
            statusString="訂單製作中";
        }else if(this.status==2){
            statusString="訂單已完成";
        }else if(this.status==-1){
            statusString="訂單已取消";
        }
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getCustomerID() { return CustomerID; }

    public void setCustomerID(Integer customerID) { CustomerID = customerID; }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getDates() { return dates; }

    public void setDates(String dates) { this.dates = dates; }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public String getStatusString() { return statusString; }

    public void setCustomerName(String customerName) {this.customerName = customerName;}

    public String getCustomerName() {return customerName;}

    @Override
    public String toString(){
        String str = "Order [OrderID = " + this.getOrderID() + "\n"
                    +"CustomerID = " + this.getCustomerID() + "\n"
                    +"Date = " + this.getDates() + "\n"
                    +"Product = " +this.getProductID() + "\n"
                    +"Quantity = " + this.getQuantity() + "\n"
                    +"Amount = " + this.getAmount() + "\n"
                    +"Status = " + this.getStatus() + "]\n";
        return str;
    }
}
