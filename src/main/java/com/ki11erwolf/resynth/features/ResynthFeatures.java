/*
 * Copyright 2018-2020 Ki11er_wolf
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ki11erwolf.resynth.features;

import com.ki11erwolf.resynth.ResynthMod;
import com.ki11erwolf.resynth.block.ResynthBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResynthFeatures {

    private static final List<ResynthFeature<?>> FEATURE_LIST = new ArrayList<>();

    // **********
    //  Features
    // **********

    private static final ResynthFeature<ResynthOreFeature> MINERAL_STONE = new ResynthOreFeature(
            new ResourceLocation(ResynthMod.MODID,  "mineral_stone_ore"),
            new Biome.Category[] {
                    Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS, Biome.Category.JUNGLE, Biome.Category.MESA,
                    Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.ICY, Biome.Category.BEACH, Biome.Category.FOREST,
                    Biome.Category.OCEAN, Biome.Category.DESERT, Biome.Category.RIVER, Biome.Category.SWAMP, Biome.Category.MUSHROOM
            }, ResynthBlocks.BLOCK_MINERAL_STONE, MatchBlockListRuleTest.MATCH_OVERWORLD_ROCK,
            100, 100, 0, 255
    ).register();

    private static final ResynthFeature<ResynthOreFeature> CALVINITE = new ResynthOreFeature(
            new ResourceLocation(ResynthMod.MODID,  "calvinite_ore"),
            new Biome.Category[] {
                    Biome.Category.NETHER
            }, ResynthBlocks.BLOCK_CALVINITE_NETHERRACK, MatchBlockListRuleTest.MATCH_NETHERWORLD_ROCK,
            100, 100, 0, 255
    ).register();

    private static final ResynthFeature<ResynthOreFeature> SYLVANITE = new ResynthOreFeature(
            new ResourceLocation(ResynthMod.MODID,  "sylvanite_ore"),
            new Biome.Category[] {
                    Biome.Category.THEEND
            }, ResynthBlocks.BLOCK_SYLVANITE_END_STONE, MatchBlockListRuleTest.MATCH_ENDWORLD_ROCK,
            100, 100, 0, 255
    ).register();

    // **************
    //  Registration
    // **************

    private ResynthFeatures() { }

    static {
        MinecraftForge.EVENT_BUS.addListener(ResynthFeatures::onBiomeLoading);
    }

    public static void init() { }

    protected static <T extends ResynthFeature<?>> T addFeature(T feature) {
        FEATURE_LIST.add(Objects.requireNonNull(feature));
        return feature;
    }

    private static void onBiomeLoading(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        FEATURE_LIST.forEach((resynthFeature -> resynthFeature.configure(generation, event.getCategory())));
    }
}
