package entity;

import javax.swing.*;

import static entity.Constant.*;

public class PricePurchase extends Purchase {

    private final Euro discount;

    public PricePurchase(){
        discount = new Euro();
    }

    public PricePurchase(Euro discount){
        this.discount = discount;
    }

    public PricePurchase(String[] args){
        super(args[NAME_FIELD], new Euro(args[PRICE_FIELD]), Integer.parseInt(args[NUMBER_FIELD]));
        discount = new Euro(args[DISCOUNT_FIELD]);
    }

    public Euro getDiscount() {
        return discount;
    }

    public Euro getCost(){
        return super.getCost().sub(discount.mul(getNumber()));
    }

    protected String fieldsToString(){
        return super.fieldsToString() + SEPARATOR + discount;
    }
}
