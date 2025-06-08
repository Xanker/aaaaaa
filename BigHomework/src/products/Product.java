package products;

public abstract class Product {
    private String name;
    private String type;
    private double Totalprice;
    private String description;
    private int ID;
    // 构造函数、getter和setter方法、toString()方法
    public int getID()
    {
        return ID;
    }
    public void setID(int ID)
    {
        this.ID = ID;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getType()
    {
        return type;
    }
    public void setType()
    {
        type = getClass().getSimpleName();
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public void setPrice(double price)
    {
        this.Totalprice = price;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getTotalPrice() {
        return Totalprice;
    }
}

