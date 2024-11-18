package com.rhseung.reach.data

import com.rhseung.reach.init.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models

class ModelProvider(output: FabricDataOutput): FabricModelProvider(output) {
    override fun generateBlockStateModels(blockModel: BlockStateModelGenerator) {
    }

    override fun generateItemModels(itemModel: ItemModelGenerator) {
        itemModel.register(ModItems.STEEL_SWORD, Models.HANDHELD);
        itemModel.register(ModItems.STEEL_PICKAXE, Models.HANDHELD);
        itemModel.register(ModItems.STEEL_AXE, Models.HANDHELD);
        itemModel.register(ModItems.STEEL_SHOVEL, Models.HANDHELD);
        itemModel.register(ModItems.STEEL_HOE, Models.HANDHELD);
    }
}