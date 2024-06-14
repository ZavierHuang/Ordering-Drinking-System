package dao.product;

import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;
import bean.menuItem.MenuItem;
import bean.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{
    private DBConnection dbConnection = new DBConnectionImpl();
    private static final String GET_PRODUCTS_BY_TYPE = "SELECT distinct ProductName ,type, state FROM product WHERE type = ?";
    private static final String GET_ADD_INFO = "SELECT * FROM product WHERE ProductID = ?";
    private static final String GET_PRODUCTID = "SELECT ProductID FROM product WHERE ProductName = ? AND ice = ? AND size = ? AND sugar = ?";
    private static final String GET_TYPE = "SELECT DISTINCT type FROM product";
    private static final String GET_ALL_ITEM = "SELECT DISTINCT product.ProductName, product.Price, product.Size, product.Type, product.State, product.Stack FROM product;";
    private static final String UPDATE_ITEM = "UPDATE  product\n" +
            "SET ProductName = ?, type = ? , State = ? , Stack = ? , Size = ? , Price = ?\n" +
            "WHERE ProductName = ? AND type = ? AND State = ? AND Stack = ? AND Size = ? AND Price = ?";
    private static final String GET_MAX_PRODUCT_ID = "SELECT MAX(productId) AS MaxProductId FROM product;";
    private static final String Add_NEW_PRODUCT = "INSERT INTO product (ProductID, ProductName, Type, State, Sugar, Ice, Size, Price, Stack)\n" +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SEARCH_ITEM = "Select DISTINCT ProductName, Price, Size, Type, State, Stack from product where 1=1";
    private static final String SEARCH_ITEM_BY_SIZE = "Select DISTINCT ProductName, Price, Size, Type, State, Stack from product where 1=1";
    private static final String DELETE_ITEM_BY_NAME = "DELETE FROM product WHERE ProductName=?";

    private static final String GET_PRODUCT_AND_QUALITY = "SELECT product.ProductID, product.ProductName, addcart.Quantity, product.Stack\n" +
            "FROM addCart\n" +
            "JOIN product ON addcart.ProductId = product.productId\n" +
            "WHERE addcart.orderId = ?;";

    private static final String UPDATE_STACK_BY_PRODUCT_NAME = "UPDATE product SET stack = ?, state = CASE WHEN ? = 0 THEN 0 ELSE state END WHERE productName = ?;";

    Product product = null;
    @Override
    public List<Product> getProductsByType(String productType) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_TYPE)) {

            preparedStatement.setString(1, productType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("ProductName"));
                    product.setState(resultSet.getInt("State"));
                    product.setType(resultSet.getString("type"));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> getMilk() {
        return getProductsByType("Milk");
    }

    @Override
    public List<Product> getBoutique() {
        return getProductsByType("Boutique hand coffee");
    }
    @Override
    public List<Product> getLatte() {
        return getProductsByType("Latte");
    }
    @Override
    public List<Product> getTea() {
        return getProductsByType("Tea");
    }
    @Override
    public List<Product> getChocolate() {
        return getProductsByType("Chocolate");
    }
    @Override
    public List<Product> getCoffee() {
        return getProductsByType("Coffee");
    }
    @Override
    public Product getAddInfo(int ProductID) {
        Connection connection = dbConnection.getConnection();
        List<Product> MilkList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ADD_INFO))
        {
            preparedStatement.setInt(1, ProductID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if(resultSet.next()){
                    product = new Product();
                    product.setSize(resultSet.getString("size"));
                    product.setPrice(resultSet.getInt("price"));
                    product.setName(resultSet.getString("ProductName"));
                    product.setIce(resultSet.getString("ice"));
                    product.setSugar(resultSet.getString("sugar"));
                }
                else{
                    return product;
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Integer getProductID(Product product){
        Connection connection = dbConnection.getConnection();
        Integer productID = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTID))
        {
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getIce());
            preparedStatement.setString(3,product.getSize());
            preparedStatement.setString(4,product.getSugar());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productID = resultSet.getInt("ProductID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productID;
    }

    @Override
    public List<Product> getType(){
        List<Product> typeList = new ArrayList<>();
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TYPE))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setType(resultSet.getString("type"));

                    typeList.add(product);
                }
            }
            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return typeList;
    }
    @Override
    public List<Product> getAllProducts() {
        List<Product> allItems = new ArrayList<>();
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ITEM))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("ProductName"));
                    product.setPrice(resultSet.getInt("price"));
                    product.setSize(resultSet.getString("size"));
                    product.setType(resultSet.getString("type"));
                    product.setState(resultSet.getInt("State"));
                    product.setStack(resultSet.getInt("Stack"));
                    allItems.add(product);
                }
            }
            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return allItems;
    }

    @Override
    public void updateMenuItem(MenuItem oldMenuItem, MenuItem newMenuItem) {
        updateMenuItem_With_Size(oldMenuItem, newMenuItem, 'M');
        updateMenuItem_With_Size(oldMenuItem, newMenuItem, 'L');
    }

    private void updateMenuItem_With_Size(MenuItem oldMenuItem, MenuItem newMenuItem, char Size) {
        String oldItemName = oldMenuItem.getName();
        String oldItemType = oldMenuItem.getType();
        int oldPrice_L = oldMenuItem.getPrice_L();
        int oldPrice_M = oldMenuItem.getPrice_M();
        int oldState = oldMenuItem.getState().equals("Open")?1:0;
        int oldStack = oldMenuItem.getStack();

        String newItemName = newMenuItem.getName();
        String newItemType = newMenuItem.getType();
        int newPrice_L = newMenuItem.getPrice_L();
        int newPrice_M = newMenuItem.getPrice_M();
        int newState = newMenuItem.getState().equals("Open")?1:0;
        int newStack = newMenuItem.getStack();

        if(newStack == 0)
            newState = 0;

        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM)){
                preparedStatement.setString(1,newItemName);
                preparedStatement.setString(2,newItemType);
                preparedStatement.setInt(3,newState);
                preparedStatement.setInt(4,newStack);

                if(Size=='L') {
                    preparedStatement.setString(5, "L");
                    preparedStatement.setInt(6, newPrice_L);
                }
                else {
                    preparedStatement.setString(5, "M");
                    preparedStatement.setInt(6, newPrice_M);
                }

                preparedStatement.setString(7,oldItemName);
                preparedStatement.setString(8,oldItemType);
                preparedStatement.setInt(9,oldState);
                preparedStatement.setInt(10,oldStack);

                if(Size == 'L') {
                    preparedStatement.setString(11, "L");
                    preparedStatement.setInt(12,oldPrice_L);
                }
                else {
                    preparedStatement.setString(11, "M");
                    preparedStatement.setInt(12,oldPrice_M);
                }
//                System.out.println("preparedStatements:"+preparedStatement);
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewMenuItem(MenuItem newMenuItem) {
        int newProductId = getMaxProductId() + 1;

        ArrayList<String> iceList = new ArrayList<>();
        iceList.add("熱");
        iceList.add("正常");
        iceList.add("少冰");
        iceList.add("去冰");

        ArrayList<String> sugarList = new ArrayList<>();
        sugarList.add("正常");
        sugarList.add("無糖");
        sugarList.add("半糖");

        newProductId = addNewMenuItem_With_Size(newMenuItem, newProductId, iceList, sugarList, 'M');
        addNewMenuItem_With_Size(newMenuItem, newProductId, iceList, sugarList, 'L');
    }

    private int addNewMenuItem_With_Size(MenuItem newMenuItem, int newProductId, ArrayList<String> iceList, ArrayList<String> sugarList, char Size)
    {


        String newItemName = newMenuItem.getName();
        String newItemType = newMenuItem.getType();
        int newPrice_L = newMenuItem.getPrice_L();
        int newPrice_M = newMenuItem.getPrice_M();
        int newState = newMenuItem.getState().equals("Open")?1:0;
        int newStack = newMenuItem.getStack();

        if(newStack == 0)
            newState = 0;

        for(int i=0;i<iceList.size();i++)
        {
            for(int j=0;j< sugarList.size();j++)
            {
                try(Connection connection = dbConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(Add_NEW_PRODUCT)){
                    preparedStatement.setInt(1,newProductId);
                    preparedStatement.setString(2,newItemName);
                    preparedStatement.setString(3,newItemType);
                    preparedStatement.setInt(4,newState);
                    preparedStatement.setString(5,iceList.get(i));
                    preparedStatement.setString(6,sugarList.get(j));

                    if(Size=='L') {
                        preparedStatement.setString(7, "L");
                        preparedStatement.setInt(8, newPrice_L);
                    }
                    else {
                        preparedStatement.setString(7, "M");
                        preparedStatement.setInt(8, newPrice_M);
                    }

                    preparedStatement.setInt(9, newStack);
//                        System.out.println("PREPARE:"+preparedStatement);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                newProductId++;
            }
        }
        return newProductId;
    }

    public int getMaxProductId(){
        int maxProductId = 0;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_PRODUCT_ID)){
             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()) {
                 maxProductId = resultSet.getInt("MaxProductId");
             }
             resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("Error:"+e);
        }

        return maxProductId;
    }

    @Override
    public List<MenuItem> searchMenuItem(MenuItem searchMenuItem, String symbol) {
        String searchSql = addConstraint(searchMenuItem, symbol);

//        System.out.println("Search SQL:"+searchSql);

        List<MenuItem> matchItems = new ArrayList<>();
        List<MenuItem> price_M_MatchItems = new ArrayList<>();
        List<MenuItem> price_L_MatchItems = new ArrayList<>();
        sqlSearchMenuItem(searchSql, price_L_MatchItems, price_M_MatchItems);

        if(price_M_MatchItems.isEmpty())
            price_M_MatchItems = completeMenuItem(price_L_MatchItems,"M");      // price_L_MatchItems(price_M is null), get price_M_MatchItems(price_L is null)
        else if(price_L_MatchItems.isEmpty())
            price_L_MatchItems = completeMenuItem(price_M_MatchItems,"L");      // price_M_MatchItems(price_L is null), get price_L_MatchItems(price_M is null)

        matchItems = combineMatchItems(price_M_MatchItems, price_L_MatchItems);      // combine price_L_MatchItems and price_M_MatchItems = Both price_L and price_M are not null
        return matchItems;
    }

    private void sqlSearchMenuItem(String sql, List<MenuItem> menuItemList1, List<MenuItem> menuItemList2)
    {

//        System.out.println("SQL:"+sql);
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setName(resultSet.getString("ProductName"));
                    menuItem.setType(resultSet.getString("Type"));
                    menuItem.setState(resultSet.getInt("State")==1?"Open":"Close");
                    menuItem.setStack(resultSet.getInt("Stack"));

                    if(resultSet.getString("Size").equals("L")){
                        menuItem.setPrice_L(resultSet.getInt("Price"));
                        menuItemList1.add(menuItem);
                    }
                    else {
                        menuItem.setPrice_M(resultSet.getInt("Price"));
                        menuItemList2.add(menuItem);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<MenuItem> completeMenuItem(List<MenuItem> matchItemWithFirstSize, String size) {
        List<MenuItem> matchItemWithSecondSize = new ArrayList<>();

        StringBuilder sqlBuilder = new StringBuilder(SEARCH_ITEM_BY_SIZE);
        if(size.equals("L"))
            sqlBuilder.append(" AND Size='L'");
        else if(size.equals("M"))
            sqlBuilder.append(" AND Size='M'");

        for(int i=0;i<matchItemWithFirstSize.size();i++)
        {
            String productName = matchItemWithFirstSize.get(i).getName();
            String sql = sqlBuilder + " AND ProductName='" + productName + "'";
            sqlSearchMenuItem(sql, matchItemWithSecondSize, matchItemWithSecondSize);
        }

        return matchItemWithSecondSize;
    }

    private List<MenuItem> combineMatchItems(List<MenuItem> price_M_MatchItems, List<MenuItem> price_L_MatchItems) {

        List<MenuItem> matchItems = new ArrayList<>();

        for(int i=0;i<price_M_MatchItems.size();i++)
        {
            MenuItem matchItem = new MenuItem();
            String productName = price_M_MatchItems.get(i).getName();
            String type = price_M_MatchItems.get(i).getType();
            String state = price_M_MatchItems.get(i).getState();
            int stack = price_M_MatchItems.get(i).getStack();
            int price_M = price_M_MatchItems.get(i).getPrice_M();
            int price_L = findMatchItemWithPrice_L(price_L_MatchItems, productName, type, state);
            if(price_L == -1)
                continue;

            matchItem.setName(productName);
            matchItem.setType(type);
            matchItem.setState(state);
            matchItem.setPrice_M(price_M);
            matchItem.setPrice_L(price_L);
            matchItem.setStack(stack);
            matchItems.add(matchItem);
        }
        return matchItems;
    }

    private int findMatchItemWithPrice_L(List<MenuItem> price_L_MatchItems, String price_M_productName, String price_M_type, String price_M_state) {
        for(int i=0;i<price_L_MatchItems.size();i++)
        {
            String price_L_productName = price_L_MatchItems.get(i).getName();
            String price_L_type = price_L_MatchItems.get(i).getType();
            String price_L_state = price_L_MatchItems.get(i).getState();
            if(price_L_productName.equals(price_M_productName) && price_L_type.equals(price_M_type) && price_L_state.equals(price_M_state))
                return price_L_MatchItems.get(i).getPrice_L();
        }
        return -1;
    }

    public String addConstraint(MenuItem searchMenuItem, String symbol) {

        StringBuilder sqlBuilder = new StringBuilder(SEARCH_ITEM);

        if(searchMenuItem.getName()!=null)
            sqlBuilder.append(" AND ProductName='").append(searchMenuItem.getName()).append("'");
        else
            sqlBuilder.append(" AND ProductName=ProductName");

        if(searchMenuItem.getType()!=null)
            sqlBuilder.append(" AND type='").append(searchMenuItem.getType()).append("'");
        else
            sqlBuilder.append(" AND type=type");

        if(searchMenuItem.getState()!=null) {
            int state = searchMenuItem.getState().equals("Open")?1:0;
            sqlBuilder.append(" AND state=").append(state);
        }
        else
            sqlBuilder.append(" AND state=state");


        if(searchMenuItem.getPrice_M()!=null && searchMenuItem.getPrice_L()!=null)
            sqlBuilder.append(" AND ((Size='M' AND Price=")
                    .append(searchMenuItem.getPrice_M())
                    .append(") or (Size='L' AND Price=")
                    .append(searchMenuItem.getPrice_L())
                    .append("))");
        else if(searchMenuItem.getPrice_M()!=null)
            sqlBuilder.append(" AND Size='M' AND Price=").append(searchMenuItem.getPrice_M());
        else if(searchMenuItem.getPrice_L()!=null)
            sqlBuilder.append(" AND Size='L' AND Price=").append(searchMenuItem.getPrice_L());

        if(searchMenuItem.getStack()!=null) {
            int stack = searchMenuItem.getStack();
            if("<=".equals(symbol))
                sqlBuilder.append(" AND stack <= ").append(stack);
            else if(">=".equals(symbol))
                sqlBuilder.append(" AND stack >= ").append(stack);
        }
        else
            sqlBuilder.append(" AND stack=stack");

        return sqlBuilder.toString();
    }

    @Override
    public void deleteMenuItemByName(String menuItemName) {

        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_BY_NAME)) {

            preparedStatement.setString(1,menuItemName);
            preparedStatement.executeUpdate();
            //System.out.println("prepareStatement:"+ preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStack(Integer orderID) {
        String currentProductName = null;
        int currentStack;
        int newStack = 0;
        try (Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_AND_QUALITY)) {

            preparedStatement.setString(1, String.valueOf(orderID));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    if(resultSet.getString("ProductName").equals(currentProductName))
                        currentStack = newStack;
                    else {
                        currentProductName = resultSet.getString("ProductName");
                        currentStack = resultSet.getInt("Stack");
                    }
                    int quantity = resultSet.getInt("Quantity");


                    newStack = currentStack - quantity;

                    System.out.println("Current ProductName:"+currentProductName);
                    System.out.println("Current Stack:"+currentStack);
                    System.out.println("Quantity:"+quantity);

                    try (PreparedStatement updateStmt = connection.prepareStatement(UPDATE_STACK_BY_PRODUCT_NAME)) {
                        updateStmt.setInt(1, newStack);
                        updateStmt.setInt(2, newStack);
                        updateStmt.setString(3, currentProductName);
                        System.out.println("updateStmt:"+updateStmt);
                        updateStmt.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
