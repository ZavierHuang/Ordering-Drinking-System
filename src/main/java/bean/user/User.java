package bean.user;

public class User {
    private Integer id;
    private String account;
    private String password;
    private int level = 0;
    private String phone;

    public User(){

    }

    public User(String account, String password, int level, String phone) {

        this.account = account;
        this.password = password;
        this.level = level;
        this.phone = phone;
    }

    public User(int id,String account, String password, int level, String phone) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.level = level;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", level='" + level + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}


