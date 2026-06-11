package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices){
            val item = items[i]
            val updater = UniqueItemAdapter.handler[item.name] ?: this::updateNormalItemQuality
            updater(item)
        }
    }

    fun updateNormalItemQuality(item: Item): Unit{
        item.sellIn -= 1
        if (item.quality <= 0) {
            return
        }
        var qualityDecrease = 1

        if (item.sellIn < 0) {
            qualityDecrease = 2
        }

        if (item.name.substringBefore(" ") == "Conjured"){
            qualityDecrease *= 2
        }

        item.quality -= qualityDecrease
    }

}

