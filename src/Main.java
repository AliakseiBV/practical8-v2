import entity.*;
import java.util.Map.Entry;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static entity.Constant.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        final String fileName = "C:\\Users\\a.busleyko\\IdeaProjects\\practical8-Strings\\src\\in.csv";
        try (Scanner scanner = new Scanner(new FileReader(fileName))){

            Map<Purchase, WeekDay> firstPurchaseMap = new HashMap<>();
            Map<Purchase, WeekDay> lastPurchaseMap = new HashMap<>();
            Map<WeekDay, List<Purchase>> dayPurchaseMap = new EnumMap<>(WeekDay.class);
            List<PricePurchase> priceDiscountPurchases = new ArrayList<>();

            while (scanner.hasNext()){
                String line = scanner.nextLine();
                Purchase purchase = PurchasesFactory.getPurchase(line);
                WeekDay weekDay = WeekDay.valueOf(scanner.nextLine());

                lastPurchaseMap.put(purchase, weekDay); //1

                if(!firstPurchaseMap.containsKey(purchase)){ //3
                    firstPurchaseMap.put(purchase, weekDay);
                }
                List<Purchase> purchaseList = dayPurchaseMap.get(weekDay); //12

                if (purchaseList == null){
                    purchaseList = new ArrayList<>();
                }
                purchaseList.add(purchase);
                dayPurchaseMap.put(weekDay,purchaseList);

                if (purchase instanceof PricePurchase){ //10
                    priceDiscountPurchases.add(new PricePurchase(line.split(SEPARATOR)));
                }
            }

            //2
            printMap(firstPurchaseMap, FIRST_MAP_BEFORE_CHANGES);
            //4
            printMap(lastPurchaseMap, LAST_MAP_BEFORE_CHANGES);
            //5
            final Purchase PURCHASE_BREAD_155 = new Purchase(BREAD, new Euro(155),0);
            findAndShow(firstPurchaseMap, PURCHASE_BREAD_155, THE_FIRST_WEEKDAYS + BREAD_WITH_PRICE_155);
            findAndShow(lastPurchaseMap, PURCHASE_BREAD_155, THE_LAST_WEEKDAYS + BREAD_WITH_PRICE_155);

            //6
            final Purchase PURCHASE_BREAD_170 = new Purchase(BREAD, new Euro(175),0);
            findAndShow(firstPurchaseMap, PURCHASE_BREAD_170, THE_FIRST_WEEKDAYS + BREAD_WITH_PRICE_170);

            //7
            removeEntries(firstPurchaseMap, new EntryChecker<Purchase, WeekDay>(){
                @Override
                public boolean check(Entry <Purchase, WeekDay> entry){
                    return (entry.getKey().getName().equals(MEAT));
                }
            });

            //8
            removeEntries(lastPurchaseMap, new EntryChecker<Purchase, WeekDay>() {
                @Override
                public boolean check(Entry<Purchase, WeekDay> entry) {
                    return (entry.getValue().equals(WeekDay.FRIDAY));
                }
            });

            //9
            printMap(firstPurchaseMap, FIRST_MAP_AFTER_CHANGES);
            printMap(lastPurchaseMap, LAST_MAP_AFTER_CHANGES);

            //11
            System.out.println(THE_TOTAL_COST_OF + PRICE_PURCHASES + EQUAL + getTotalCost(priceDiscountPurchases));

            //13
            printMap(dayPurchaseMap, DAYS_PURCHASE_MAP_BEFORE_CHANGES);

            //14
            for (WeekDay day : WeekDay.values()){
                System.out.println(THE_TOTAL_COST_OF + day + EQUAL + getTotalCost(dayPurchaseMap.get(day)));
            }

            //15
            findAndShow(dayPurchaseMap, WeekDay.MONDAY, SHOPPING_BY_DAY + WeekDay.MONDAY + SPACE);

            //16
            removeEntries(dayPurchaseMap, new EntryChecker<WeekDay, List<Purchase>>() {
                @Override
                public boolean check(Entry<WeekDay, List<Purchase>> entry) {
                    boolean isTrue = false;
                    for (Purchase purchase : entry.getValue()){
                        if (purchase.getName().equals(MILK)){
                            isTrue = isTrue;
                            break;
                        }
                    }
                    return isTrue;
                }
            });

            //17
            printMap(dayPurchaseMap, DAYS_PURCHASE_MAP_AFTER_CHANGES);

        }catch (FileNotFoundException e){
            System.err.println(FILE_NOT_FOUND);

        }
    }


    private static Euro getTotalCost(List<? extends Purchase> purchases){
        Euro cost = new Euro();
        if (purchases != null){
            for (Purchase purchase : purchases) {
                cost = cost.add(purchase.getCost());
            }
        }
        return cost;
    }


    private static <K,V> void findAndShow(Map<K,V> map, K searchKey, String header){
        V value = map.get(searchKey);
        if(value == null){
            System.out.println(ELEMENT_NOT_FOUND);
        } else {
            System.out.println(header + value);
        }

    }
    private static <K,V> void removeEntries(Map<K,V> map, EntryChecker<K, V> checker){
        for (Iterator<Map.Entry<K,V>> iterator = map.entrySet().iterator();iterator.hasNext();){
            if(checker.check(iterator.next())){
                iterator.remove();
            }
        }
    }

    private static <K, V> void printMap(Map<K,V> map, String message) {
        System.out.println(message);
        for (Entry<K,V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + SPACE + entry.getValue());
        }
        System.out.println(LINE_SEPARATOR);
    }
}