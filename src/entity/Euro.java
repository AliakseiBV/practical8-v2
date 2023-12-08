package entity;

import static entity.Constant.*;

public class Euro implements Comparable<Euro>{

    private int price;

    public Euro(int price){
        this.price = price;
    }

    public Euro(){
        this(0);
    }

    public Euro(Euro euro){
        this(euro.price);
    }

    public Euro(int euro, int cents){
        this(euro * DIVISOR + cents);
    }

    public Euro(String strCents){
       this(Integer.parseInt(strCents));
    }
    public Euro add(Euro euro){
        return new Euro(price + euro.price);
    }

    public Euro sub(Euro euro){
        return new Euro(price - euro.price);
    }

    public Euro mul(int value){
        return new Euro(price * value);
    }

    private int getCents(){
        return Math.abs(price) % DIVISOR;
    }

    private int getEuros(){
        return price / DIVISOR;
    }

    @Override
    public int compareTo(Euro euro){
        return price - euro.price;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + price;
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
        if(getClass() != object.getClass()){
            return false;
        }
        Euro another = (Euro) object;
        if (price != another.price){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return String.format(NUMBER_FORMAT, getEuros(), getCents());
    }
}
