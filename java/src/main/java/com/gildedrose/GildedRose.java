package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    /*
     /!\ Do not change code above this line /!\
     */

    public void updateQuality() {
        for (Item item: items) {
            if (!isSpecialItem(item.name) && item.quality > 0) {
                decreaseItemQuantity(item, 1);
            } else {
                if (item.quality < 50) {
                    increaseItemQuantity(item,1);
                    backStagePassQuantityUpdatePolicy(item);
                }
            }
            //update sellIn
            updateSellIn(item);
            whenSellInNegativeQuantityUpdatePolic(item);
        }
    }


    public void backStagePassQuantityUpdatePolicy(Item item){
        if (SpecialItem.BACK_STAGE_PASS.getName().equals(item.name)) {
            if (item.sellIn < 11 && item.quality < 50) {
                increaseItemQuantity(item,1);
            }

            if (item.sellIn < 6 && item.quality < 50) {
                increaseItemQuantity(item, 1);
            }
        }
    }

    public void updateSellIn(Item item){
        if (!SpecialItem.SULFURAS.getName().equals(item.name)) {
            decreaseSellIn(item,1);
        }
    }

    public void whenSellInNegativeQuantityUpdatePolic(Item item){
        if (item.sellIn < 0){
            if (SpecialItem.AGED_BRIE.getName().equals(item.name)){
                if (item.quality < 50){
                    increaseItemQuantity(item,1);
                }
            }else

            if (SpecialItem.BACK_STAGE_PASS.getName().equals(item.name)){
                item.quality = 0;
            }else

            if (item.quality > 0 && !SpecialItem.SULFURAS.getName().equals(item.name)){
                decreaseItemQuantity(item,1);
            }
        }
    }



    public Item decreaseItemQuantity(Item item,int num) throws IllegalArgumentException{
        if (item.quality - Math.abs(num) < 0){
            throw  new IllegalArgumentException("Item quantity can't be negative");
        }

        item.quality -= num;
        return item;
    }

    public Item increaseItemQuantity(Item item,int num){
        if (item.quality + Math.abs(num) < 0){
            throw  new IllegalArgumentException("Item quantity can't be negative");
        }

        item.quality += num;
        return item;
    }

    public Item decreaseSellIn(Item item, int num){
        item.sellIn -= 1;
        return item;
    }

    public Item increaseSellIn(Item item, int num){
        item.sellIn += 1;
        return item;
    }

    public Boolean isSpecialItem(String itemName){
        return  Arrays.stream(SpecialItem.values()).anyMatch(i -> i.getName().equals(itemName));
    }
}
