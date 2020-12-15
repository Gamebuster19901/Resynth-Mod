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
package com.ki11erwolf.resynth.plant.set;

/**
 * Defines the required configurable settings used by Biochemical plant sets.
 */
public interface IBiochemicalSetProperties extends IPlantSetProperties {

    /**
     * Returns the number of produce item the plant type
     * will drop when harvested.
     */
    int plantYield();

    /**
     * Returns the percentage (0.0 - 100.0) chance that seeds
     * will spawn when the one of the plant sets mobs
     * are killed.
     */
    float seedSpawnChanceFromMob();

    /**
     * Returns the percentage (0.0 - 100.0) chance that seeds
     * will spawn when the plant sets produce is smashed.
     */
    float seedSpawnChanceFromBulb();
}
