package com.gildedrose;

/**
 * @author kainingxin
 */

public enum SpecialItem {
    AGED_BRIE("Aged Brie"),BACK_STAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),SULFURAS("Sulfuras, Hand of Ragnaros");

    private String name;

    SpecialItem(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
