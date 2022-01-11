package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {


    @Test
    public void simpleItem() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(19,foo.sellIn);
        assertEquals(9, foo.quality);
    }

    @Test
    public void goldenMaster() throws IOException {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 30;
        List actualLog = new ArrayList<String>();
        for (int i = 0; i <= days; i++) {
            actualLog.add("-------- day " + i + " --------");
            actualLog.add("name, sellIn, quality");
            for (Item item : items) {
                actualLog.add(item.toString());
            }
            app.updateQuality();
        }

        List expectedLog = new ArrayList<String>();
        String workingDir = System.getProperty("user.dir");
        String expectedPath = workingDir + "/../golden-master/expected-output.txt";
        File expectedFile = new File(expectedPath);
        FileReader reader = new FileReader(expectedFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if(! line.isEmpty()) {
                expectedLog.add(line);
            }
        }
        assertEquals(expectedLog, actualLog);
    }




    @Test
    public void testBackStagePassQuantityUpdatePolicy() throws Exception {

    }


    @Test
    public void testUpdateSellIn() throws Exception {

    }


    @Test
    public void testWhenSellInNegativeQuantityUpdatePolic() throws Exception {

    }


    @Test
    public void testDecreaseItemQuantity() throws Exception {
        GildedRose gildedRose = new GildedRose(new Item[]{});
        Item item =  new Item("+5 Dexterity Vest", 10, 20);
        gildedRose.decreaseItemQuantity(item,1);
        assertEquals(19,item.quality);
    }

    @Test
    public void testIncreaseItemQuantity() throws Exception {
        GildedRose gildedRose = new GildedRose(new Item[]{});
        Item item =  new Item("+5 Dexterity Vest", 10, 20);
        gildedRose.increaseItemQuantity(item,1);
        assertEquals(21,item.quality);
    }

    @Test
    public void testDecreaseSellIn() throws Exception {
        GildedRose gildedRose = new GildedRose(new Item[]{});
        Item item =  new Item("+5 Dexterity Vest", 10, 20);
        gildedRose.decreaseSellIn(item,1);
        assertEquals(9,item.sellIn);
    }


    @Test
    public void testIncreaseSellIn() throws Exception {
      GildedRose gildedRose = new GildedRose(new Item[]{});
      Item item =  new Item("+5 Dexterity Vest", 10, 20);
      gildedRose.increaseSellIn(item,1);
      assertEquals(11,item.sellIn);
    }


    @Test
    public void testIsSpecialItem() throws Exception {

 }


}
