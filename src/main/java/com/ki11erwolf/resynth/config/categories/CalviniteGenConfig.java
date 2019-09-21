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
package com.ki11erwolf.resynth.config.categories;

/**
 * Configuration settings for Calvinite Infused Netherrack world gen.
 */
public class CalviniteGenConfig extends GenConfig {

    /**
     * Creates a new config grouping with ore gen settings
     * for Calvinite Infused Netherrack.
     */
    public CalviniteGenConfig() {
        super(
                "calvinite",
                true, 4, 20, 0, 126, 0
        );
    }
}
