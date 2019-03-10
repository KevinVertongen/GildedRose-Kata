package com.gildedrose;

import com.gildedrose.config.quality.QualityConfiguration;
import com.gildedrose.config.quality.QualityConfigurationFactory;
import com.gildedrose.function.quality.QualityOperator;
import com.gildedrose.function.quality.QualityOperatorFactory;
import com.gildedrose.model.Inventory;
import com.gildedrose.model.Item;


class GildedRose {

    QualityConfiguration qualityConfiguration;
    QualityOperatorFactory qualityOperatorFactory;

    Item[] items;

    public GildedRose(Item[] items) {
        this.qualityConfiguration = QualityConfigurationFactory.createItemQualityConfiguration();
        this.items = items;
    }

    public void updateQuality() {
        final int length = items.length;

        for (int i = 0; i < length; i++) {
            Item item = items[i];

            QualityOperator qualityOperator = QualityOperatorFactory.getQualityOperator(qualityConfiguration, item);
            items[i] = qualityOperator.apply(item);

            if (!Inventory.LEGENDARY_ITEMS.contains(item.name)) {
                item.sellIn = item.sellIn - 1;
            }
        }
    }

}
