/*
 * Copyright 2018 Ki11er_wolf
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
package com.ki11erwolf.resynth.plant;

import com.ki11erwolf.resynth.ResynthConfig;
import com.ki11erwolf.resynth.plant.block.BlockPlantMetallic;
import com.ki11erwolf.resynth.plant.block.BlockPlantOre;
import com.ki11erwolf.resynth.plant.item.ItemPlantSeed;
import com.ki11erwolf.resynth.util.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/**
 * The class used to create new metallic plants
 * with seeds item and produce (ore) block.
 */
@Mod.EventBusSubscriber
public abstract class PlantMetallic {

    /**
     * The minecraft ore block used to obtain seeds.
     */
    private final Block seedOre;

    /**
     * The block the plant should place..
     */
    private final BlockPlantOre ore;

    /**
     * The plant block.
     */
    private final BlockPlantMetallic plant;

    /**
     * The plants seeds item.
     */
    private final ItemPlantSeed seeds;

    /**
     * Constructs a new metallic plant
     * instance.
     *
     * @param name the name of the plant block.
     * @param produce the block the plant should place.
     */
    public PlantMetallic(String name, Block produce){
        this.seedOre = produce;
        this.ore = new BlockPlantOre(name);
        this.plant = new BlockPlantMetallic(ore, name){
            @Override
            protected Item getSeedItem(){
                return seeds;
            }

            @Override
            protected int getGrowthPeriod(){
                return getFloweringPeriod();
            }

            @Override
            protected boolean canBonemeal(){
                return canBoneMeal();
            }
        };
        this.seeds = new ItemPlantSeed(plant, name);
        register();
    }

    /**
     * Registers this plant (plant block, seeds and produce)
     * to the game.
     */
    private void register(){
        ResynthPlantRegistry.addOre(ore);
        ResynthPlantRegistry.addPlant(plant);
        ResynthPlantRegistry.addSeeds(seeds);
        ResynthPlantRegistry.addPlant(this);
    }

    /**
     * @return the block this plant places. Its
     * produce.
     */
    public BlockPlantOre getOre(){
        return this.ore;
    }

    /**
     * @return the seeds item used to
     * place this plant.
     */
    public ItemPlantSeed getSeeds(){
        return this.seeds;
    }

    /**
     * @return the plant block.
     */
    public BlockPlantMetallic getPlant(){
        return this.plant;
    }

    //Old idea for dropping seeds.
    //Abandoned because it's easy
    //to abuse.

    //Kept for use in other plant types
    //with different ore.

//    @SubscribeEvent
//    public static void onBlockBroken(BlockEvent.BreakEvent event){
//        if(!ResynthConfig.PLANTS_GENERAL.oreDropSeeds)
//            return;
//
//        if(event.getPlayer() == null)
//            return;
//
//        if(! (event.getPlayer().getHeldItemMainhand().getItem() instanceof ItemPickaxe))
//            return;
//
//        if(event.getPlayer().isCreative())
//            return;
//
//        //If has fortune enchantment.
//        for(NBTBase tag : event.getPlayer().getHeldItemMainhand().getEnchantmentTagList()){
//            if(((NBTTagCompound)tag).getShort("id") == 33)
//                return;
//        }
//
//        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
//        World world = event.getWorld();
//        double x = event.getPos().getX(), y = event.getPos().getY(), z = event.getPos().getZ();
//
//        for(PlantMetallic plant : ResynthPlantRegistry.getMetallicPlants()){
//            if(!plant.doesOreDropSeeds())
//                return;
//
//            if(block == plant.seedOre){
//                int random = MathUtil.getRandomIntegerInRange(0, plant.getOreSeedDropChance());
//
//                if(random == 1){
//                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(plant.seeds, 1)));
//                    event.setCanceled(true);
//                    world.setBlockToAir(event.getPos());
//                }
//            }
//        }
//    }

    /**
     * Called when an explosion occurs in the world.
     * <p>
     *     This handles the dropping of seeds
     *     from ore blocks.
     * </p>
     *
     * @param detonateEvent the event created by the explosion.
     */
    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate detonateEvent){
        List<BlockPos> affectedBlocks = detonateEvent.getAffectedBlocks();

        for(BlockPos blockPos : affectedBlocks){
            Block block = detonateEvent.getWorld().getBlockState(blockPos).getBlock();


            for(PlantMetallic plant : ResynthPlantRegistry.getMetallicPlants()){
                if(plant.doesOreDropSeeds() && block == plant.seedOre && ResynthConfig.PLANTS_GENERAL.oreDropSeeds){
                    //A Seed ore block has been blown up.

                    int random = MathUtil.getRandomIntegerInRange(0, plant.getOreSeedDropChance());

                    //Random chance.
                    if(random == 1){
                        detonateEvent.getWorld().setBlockToAir(blockPos);
                        detonateEvent.getWorld().spawnEntity(
                                new EntityItem(detonateEvent.getWorld(),
                                        blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                                        new ItemStack(plant.seeds, 1))
                        );
                    }
                }

                if(plant.doesOrganicOreDropSeeds() && block == plant.ore
                        && ResynthConfig.PLANTS_GENERAL.organicOreDropSeeds){
                    //An organic ore block has been blown up.

                    int random = MathUtil.getRandomIntegerInRange(0, plant.getOrganicOreSeedDropChance());

                    //Random chance.
                    if(random == 1){
                        detonateEvent.getWorld().setBlockToAir(blockPos);
                        detonateEvent.getWorld().spawnEntity(
                                new EntityItem(detonateEvent.getWorld(),
                                        blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                                        new ItemStack(plant.seeds, 1))
                        );
                    }
                }
            }
        }
    }

    /**
     * @return the item to give
     * when smelting this plants ore block.
     */
    public abstract ItemStack getResult();

    /**
     * @return how the long the plant takes
     * to grow in general. This chance of
     * growing is 1 in the number provided + 1.
     */
    protected abstract int getFloweringPeriod();

    /**
     * @return true if bonemeal can be used
     * on this plant.
     */
    protected abstract boolean canBoneMeal();

    /**
     * @return the integer chance of seeds
     * dropping from the minecraft ore block.
     */
    protected abstract int getOreSeedDropChance();

    /**
     * @return the integer chance of seeds
     * dropping from the plants ore block (produce).
     */
    protected abstract int getOrganicOreSeedDropChance();

    /**
     * @return true if seeds should drop from the
     * minecraft ore block.
     */
    protected abstract boolean doesOreDropSeeds();

    /**
     * @return true if seeds should drop from
     * the plants ore block (produce).
     */
    protected abstract boolean doesOrganicOreDropSeeds();
}
