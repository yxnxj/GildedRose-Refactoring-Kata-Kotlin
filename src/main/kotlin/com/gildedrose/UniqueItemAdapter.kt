package com.gildedrose

class UniqueItemAdapter {
    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"

        val handler = mapOf<String, (Item) -> Unit>(
            AGED_BRIE to this::updateAgedBrieQuality,
            BACKSTAGE_PASSES to this::updateBackstagePassesQuality,
            SULFURAS to this::updateSulfurasQuality
        )


        fun updateAgedBrieQuality(agedBrie: Item): Unit {
            agedBrie.sellIn -= 1


            if (agedBrie.quality >= 50) {
                return
            }

            if (agedBrie.sellIn < 0) agedBrie.quality += 2
            else agedBrie.quality += 1

            return
        }

        fun updateBackstagePassesQuality(backstagePass: Item): Unit {
            backstagePass.sellIn -= 1

            if (backstagePass.sellIn <= 0) {
                backstagePass.quality = 0
                return
            }

            if (backstagePass.quality >= 50) {
                return
            }

            if (backstagePass.sellIn <= 5) {
                backstagePass.quality += 3
            } else if (backstagePass.sellIn <= 10) {
                backstagePass.quality += 2
            } else {
                backstagePass.quality += 1
            }

            if (backstagePass.quality > 50) {
                backstagePass.quality = 50
            }

            return
        }

        fun updateSulfurasQuality(sulfuras: Item): Unit {
            return
        }
    }
}