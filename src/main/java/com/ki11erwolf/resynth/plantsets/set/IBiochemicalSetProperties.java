/*
 * Copyright 2018-2019 Ki11er_wolf
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
package com.ki11erwolf.resynth.plantsets.set;

public interface IBiochemicalSetProperties extends PlantSetProperties {

    /**
     * @return the number of produce item the plant type
     * will drop when harvested. May be specified by config.
     */
    int numberOfProduceDrops();

    /**
     * @return the percentage (0.0 - 100.0) chance that seeds
     * will spawn when the one of the plant sets mobs
     * are killed.
     */
    float seedSpawnChanceFromMob();

    /**
     * @return the percentage (0.0 - 100.0) chance that seeds
     * will spawn when the plant sets produce is smashed.
     */
    float seedSpawnChanceFromBulb();
}
