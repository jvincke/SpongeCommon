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
package org.spongepowered.common.data.processor.data.item;

import com.google.common.collect.Iterables;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataTransactionBuilder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.item.ImmutableCoalData;
import org.spongepowered.api.data.manipulator.mutable.item.CoalData;
import org.spongepowered.api.data.type.CoalType;
import org.spongepowered.api.data.type.CoalTypes;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.common.SpongeImpl;
import org.spongepowered.common.data.manipulator.mutable.item.SpongeCoalData;
import org.spongepowered.common.data.processor.common.AbstractItemSingleDataProcessor;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeValue;
import org.spongepowered.common.item.SpongeCoalType;

import java.util.Optional;

public class CoalDataProcessor extends AbstractItemSingleDataProcessor<CoalType, Value<CoalType>, CoalData, ImmutableCoalData> {

    public CoalDataProcessor() {
        super(stack -> stack.getItem().equals(Items.coal), Keys.COAL_TYPE);
    }

    @Override
    protected CoalData createManipulator() {
        return new SpongeCoalData();
    }

    @Override
    protected boolean set(ItemStack itemStack, CoalType value) {
        itemStack.setItemDamage(((SpongeCoalType) value).type);
        return true;
    }

    @Override
    protected Optional<CoalType> getVal(ItemStack itemStack) {
        return Optional.of(Iterables.get(SpongeImpl.getRegistry().getAllOf(CoalType.class), itemStack.getMetadata()));
    }

    @Override
    protected ImmutableValue<CoalType> constructImmutableValue(CoalType value) {
        return ImmutableSpongeValue.cachedOf(Keys.COAL_TYPE, CoalTypes.COAL, value);
    }

    @Override
    public DataTransactionResult remove(DataHolder dataHolder) {
        return DataTransactionBuilder.failNoData();
    }
}
