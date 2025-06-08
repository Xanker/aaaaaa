import products.Fruit;
import products.Herb;
import products.Product;
import products.Wood;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory <T extends Product>{
    private ArrayList<T> products = new ArrayList<>();//商品列表
    private final HashMap<T, Integer> productsCount = new HashMap<>();//库存数量

    //add（进货）
    public void Add(T product)
    {
        products.add(product);
        productsCount.put(product, productsCount.getOrDefault(product, 0) + 1);
    }

    //sell(销售)
    public void sell(T product)
    {

        if(productsCount.get(product) != 0)
        {
            products.remove(product);
            productsCount.remove(product);
        }

        else
            System.out.println("产品数量为0");
    }
    //Check（库存查询）
    public int getStock(){
        return productsCount.size();
    }
    public int getWoodStock( ){
        int num = 0;
        for(T product : products)
        {
            if(product.getType().equals("Wood"))
                num++;
        }
        return num;
    }
    public int getHerbStock( ){
        int num = 0;
        for(T product : products)
        {
            if(product.getType().equals("Herb"))
                num++;
        }
        return num;
    }
    public int getFruitStock( ){
        int num = 0;
        for(T product : products)
        {
            if(product.getType().equals("Fruit"))
                num++;
        }
        return num;
    }

    //Update(库存更新)
    public void UpdateCount(T product, int count,int i){
        switch(i){
            case 0:
                break;
            case 1:
                break;
        }
    }
    public void increaseCount(T product, int count){
        int tem = productsCount.getOrDefault(product,0);
        productsCount.put(product,tem+count);
    }
    public void decreaseCount(T product, int count){
        int tem = productsCount.getOrDefault(product,0);
        if(tem >= count)
            productsCount.getOrDefault(product,0);
        else
            System.out.println("库存不足");

    }
    public T getWOod(int Id){
        for(T product: products) {
            String type = product.getType();
            if(type.equals("Wood") && product.getID() == Id)
            {
                return product;
            }

        }
        return null;
    }
    public Wood getWood(int ID) {
        for(T product : products) {
            if(product.getID() == ID && product.getType().equals("Wood")) {
                return (Wood) product;
            }

        }
        return null;
    }
    public Fruit getFruit(int ID) {
        for(T product : products ) {
            if(product.getID() == ID && product.getType().equals("Fruit")) {
                return (Fruit) product;
            }
        }
        return null;
    }
    public Herb getHerb(int ID) {
        for(T product : products) {
            if(product.getID() ==  ID && product.getType().equals("Herb")) {
                return (Herb) product;
            }
        }
        return null;
    }
    public ArrayList<T> getAllProducts() {
        return new ArrayList<>(products);
    }
    public void setProducts(ArrayList<T> products) {
        this.products = products;
    }

}
