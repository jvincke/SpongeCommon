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
package org.spongepowered.common.data.processor.value.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import org.spongepowered.api.data.DataTransactionBuilder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.CookedFish;
import org.spongepowered.api.data.type.CookedFishes;
import org.spongepowered.api.data.value.ValueContainer;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.common.SpongeImpl;
import org.spongepowered.common.data.processor.common.AbstractSpongeValueProcessor;
import org.spongepowered.common.data.type.SpongeCookedFish;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeValue;
import org.spongepowered.common.data.value.mutable.SpongeValue;

import java.util.Optional;

public class CookedFishValueProcessor extends AbstractSpongeValueProcessor<ItemStack, CookedFish, Value<CookedFish>> {

    public CookedFishValueProcessor() {
        super(ItemStack.class, Keys.COOKED_FISH);
    }

    @Override
    public boolean supports(ItemStack stack) {
        return stack.getItem().equals(Items.cooked_fish);
    }

    @Override
    protected Value<CookedFish> constructValue(CookedFish defaultValue) {
        return new SpongeValue<>(Keys.COOKED_FISH, CookedFishes.COD, defaultValue);
    }

    @Override
    protected ImmutableValue<CookedFish> constructImmutableValue(CookedFish defaultValue) {
        return new ImmutableSpongeValue<>(Keys.COOKED_FISH, CookedFishes.COD, defaultValue);
    }

    @Override
    public Optional<CookedFish> getVal(ItemStack stack) {
        final ItemFishFood.FishType fishType = ItemFishFood.FishType.byMetadata(stack.getMetadata());
        return SpongeImpl.getRegistry().getType(CookedFish.class, fishType.name());
    }

    @Override
    public boolean set(ItemStack stack, CookedFish value) {
        stack.setItemDamage(((SpongeCookedFish) value).fish.getMetadata());
        return true;
    }

    @Override
    public DataTransactionResult removeFrom(ValueContainer<?> container) {
        return DataTransactionBuilder.failNoData();
    }
}
