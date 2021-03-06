/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.data.processor.data.tileentity;

import net.minecraft.tileentity.TileEntityHopper;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataTransactionBuilder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.tileentity.ImmutableCooldownData;
import org.spongepowered.api.data.manipulator.mutable.tileentity.CooldownData;
import org.spongepowered.api.data.value.immutable.ImmutableBoundedValue;
import org.spongepowered.api.data.value.mutable.MutableBoundedValue;
import org.spongepowered.common.data.manipulator.mutable.tileentity.SpongeCooldownData;
import org.spongepowered.common.data.processor.common.AbstractTileEntitySingleDataProcessor;
import org.spongepowered.common.data.value.SpongeValueFactory;

import java.util.Optional;

public class CooldownDataProcessor
        extends AbstractTileEntitySingleDataProcessor<TileEntityHopper, Integer, MutableBoundedValue<Integer>, CooldownData, ImmutableCooldownData> {

    public CooldownDataProcessor() {
        super(TileEntityHopper.class, Keys.COOLDOWN);
    }

    @Override
    public boolean set(TileEntityHopper entity, Integer value) {
        entity.transferCooldown = value;
        return true;
    }

    @Override
    public Optional<Integer> getVal(TileEntityHopper entity) {
        return Optional.of(entity.transferCooldown);
    }

    @Override
    public ImmutableBoundedValue<Integer> constructImmutableValue(Integer value) {
        return SpongeValueFactory.boundedBuilder(Keys.COOLDOWN)
                .minimum(0)
                .maximum(Integer.MAX_VALUE)
                .defaultValue(0)
                .actualValue(value)
                .build()
                .asImmutable();
    }

    @Override
    public CooldownData createManipulator() {
        return new SpongeCooldownData();
    }

    @Override
    public DataTransactionResult remove(DataHolder dataHolder) {
        return DataTransactionBuilder.failNoData();
    }
}
