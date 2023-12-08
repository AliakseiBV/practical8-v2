package entity;

import static entity.Constant.*;

public class Purchase {
    private String name;
    private Euro price;
    private int number;

    public Purchase(){
        this(null, new Euro(), 0);
    }

    public Purchase(String name, Euro price, int number){
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Purchase(String[] args){
        this(args[NAME_FIELD], new Euro(args[PRICE_FIELD]), Integer.parseInt((args[NUMBER_FIELD])));
    }

    public String getName() {
        return name;
    }

    public Euro getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Euro getCost(){
        return price.mul(number);
    }

    protected String fieldsToString(){
        return getClass().getSimpleName() + SEPARATOR + name + SEPARATOR + price + SEPARATOR + number;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name==null) ? 0 : name.hashCode());
        result = prime * result + ((name==null) ? 0 : price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) {
            return true;
        }
        if (object == null){
            return false;
        }
        if(!(object instanceof Purchase)){
            return false;
        }
        Purchase another = (Purchase) object;
        return name.equals(another.name) && price.equals(another.price);
    }

    public String toString(){
        return fieldsToString() + SEPARATOR + getCost();
    }
}
