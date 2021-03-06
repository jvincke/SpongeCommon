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
package org.spongepowered.common.data.value.immutable;

import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableWeightedCollectionValue;
import org.spongepowered.api.data.value.mutable.WeightedCollectionValue;
import org.spongepowered.api.util.weighted.WeightedCollection;
import org.spongepowered.api.util.weighted.WeightedObject;

public abstract class ImmutableSpongeWeightedCollectionValue<E extends WeightedObject<?>, I extends ImmutableWeightedCollectionValue<E, I, M>,
    M extends WeightedCollectionValue<E, M, I>> extends ImmutableSpongeCollectionValue<E, WeightedCollection<E>, I, M> implements
ImmutableWeightedCollectionValue<E, I, M> {

    public ImmutableSpongeWeightedCollectionValue(Key<? extends BaseValue<WeightedCollection<E>>> key) {
        super(key, new WeightedCollection<>());
    }

    public ImmutableSpongeWeightedCollectionValue(Key<? extends BaseValue<WeightedCollection<E>>> key, WeightedCollection<E> actualValue) {
        super(key, new WeightedCollection<>(), actualValue);
    }

    @Override
    public WeightedCollection<E> getAll() {
        final WeightedCollection<E> weightedCollection = new WeightedCollection<>();
        weightedCollection.addAll(this.actualValue);
        return weightedCollection;
    }
}
