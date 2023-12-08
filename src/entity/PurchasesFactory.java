package entity;
import static entity.Constant.*;


public class PurchasesFactory {
        private  enum PurchaseKind{

            GENERAL_PURCHASE{
                public Purchase getPurchase(String[] values){
                    return new Purchase(values);
                }
            },
            PRICE_DISCOUNT_PURCHASE{
                public Purchase getPurchase(String[] values){
                    return new Purchase(values);
                }
            };
            protected abstract Purchase getPurchase(String[] values);
    }


    public static Purchase getPurchase(String args){
        String[] values = args.split(SEPARATOR);
        return PurchaseKind.values()[values.length - PURCHASE_FIELDS_NUMBER].getPurchase(values);
    }
}
