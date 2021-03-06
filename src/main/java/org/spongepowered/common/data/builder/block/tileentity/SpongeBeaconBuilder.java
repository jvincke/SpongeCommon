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
package org.spongepowered.common.data.builder.block.tileentity;

import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.api.Game;
import org.spongepowered.api.block.tileentity.carrier.Beacon;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.tileentity.BeaconData;
import org.spongepowered.api.potion.PotionEffectType;
import org.spongepowered.api.service.persistence.InvalidDataException;
import org.spongepowered.common.data.util.DataQueries;

import java.util.Optional;

public class SpongeBeaconBuilder extends SpongeLockableBuilder<Beacon> {

    public SpongeBeaconBuilder(Game game) {
        super(game);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Beacon> build(DataView container) throws InvalidDataException {
        Optional<Beacon> beaconOptional = super.build(container);
        if (!beaconOptional.isPresent()) {
            throw new InvalidDataException("The container had insufficient data to create a Banner tile entity!");
        }
        if (!container.contains(DataQueries.PRIMARY) || !container.contains(DataQueries.SECONDARY)) {
            throw new InvalidDataException("The provided container does not contain the data to make a Banner!");
        }
        final BeaconData beaconData = null;
        beaconData.set(Keys.BEACON_PRIMARY_EFFECT, Optional.of((PotionEffectType) Potion.potionTypes[container.getInt(DataQueries.PRIMARY).get()]));
        beaconData.set(Keys.BEACON_SECONDARY_EFFECT, Optional.of((PotionEffectType) Potion.potionTypes[container.getInt(DataQueries.SECONDARY).get()]));

        final Beacon beacon = beaconOptional.get();
        beacon.offer(beaconData);
        ((TileEntityBeacon) beacon).validate();
        return Optional.of(beacon);
    }

}
