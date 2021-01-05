/*
 * Copyright 2018-2021 Ki11er_wolf
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
package com.ki11erwolf.resynth.plant.set;

/**
 * A basic {@link IPlantSetProduceProperties} implementation for simple
 * PlantSetProduceProperties objects that always provide the same, immutable,
 * values. This is beneficial for defining default or costant values, and in
 * debugging.
 */
public class PlantSetProduceProperties implements IPlantSetProduceProperties {

    /**
     * The amount of resources a that a single item of {@link
     * PlantSet#getProduceItem() produce} will make when smelted.
     */
    private final int resourceCount;

    /**
     * The amount of time, in ticks, that a single item of {@link
     * PlantSet#getProduceItem() produce} takes to smelt in a furnace.
     */
    private final int smeltingTime;

    /**
     * The amount of experience points that a single item of {@link
     * PlantSet#getProduceItem() produce} will give the player when smelted.
     */
    private final double experienceWorth;

    /**
     * Creates a new, immutable, PlantSetProduceProperties object that always
     * provides the same property values specified in this constructor. Any
     * invalid arguments are rounded to the nearest valid value.
     *
     * @param resourceCount The amount of resources a that a single item of
     *                     produce will make when smelted.
     * @param smeltingTime The amount of time, in ticks, that a single item of
     *                    produce takes to smelt in a furnace.
     * @param experienceWorth The amount of experience points that a single item
     *                        of produce will give the player when smelted.
     */
    public PlantSetProduceProperties(int resourceCount, int smeltingTime, double experienceWorth) {
        this.resourceCount = resourceCount < 0 ? 1 : Math.min(resourceCount, 64);
        this.smeltingTime = smeltingTime < 0 ? 200 : smeltingTime;
        this.experienceWorth = experienceWorth < 0 ? 1 : experienceWorth;
    }

    /**
     * {@inheritDoc}
     *
     * @return the {@code resourceCount} value specified in the objects
     * constructor. Value is always in range.
     */
    @Override
    public final int produceYield() {
        return resourceCount;
    }

    /**
     * {@inheritDoc}
     *
     * @return the {@code smeltingTime} value specified in the objects
     * constructor. Value is always in range.
     */
    @Override
    public final int timePerYield() {
        return smeltingTime;
    }

    /**
     * {@inheritDoc}
     *
     * @return the {@code experienceWorth} value specified in the objects
     * constructor. Value is always in range.
     */
    @Override
    public final double experiencePoints() {
        return experienceWorth;
    }
}
