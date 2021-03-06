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
package org.spongepowered.common.effect.particle;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.data.type.NotePitch;
import org.spongepowered.api.effect.particle.ColoredParticle;
import org.spongepowered.api.effect.particle.ItemParticle;
import org.spongepowered.api.effect.particle.NoteParticle;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ResizableParticle;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.awt.Color;

public class SpongeParticleEffectBuilder implements ParticleEffect.Builder {

    protected SpongeParticleType type;

    protected Vector3d motion = Vector3d.ZERO;
    protected Vector3d offset = Vector3d.ZERO;

    protected int count = 1;

    @Override
    public ParticleEffect.Builder type(ParticleType particleType) {
        this.type = (SpongeParticleType) particleType;
        return this;
    }

    @Override
    public SpongeParticleEffectBuilder motion(Vector3d motion) {
        checkNotNull(motion, "The motion vector cannot be null! Use Vector3d.ZERO instead!");
        this.motion = motion;
        return this;
    }

    @Override
    public SpongeParticleEffectBuilder offset(Vector3d offset) {
        checkNotNull(offset, "The offset vector cannot be null! Use Vector3d.ZERO instead!");
        this.offset = offset;
        return this;
    }

    @Override
    public SpongeParticleEffectBuilder count(int count) throws IllegalArgumentException {
        checkArgument(count > 0, "The count has to be greater then zero!");
        this.count = count;
        return this;
    }

    @Override
    public SpongeParticleEffect build() throws IllegalStateException {
        return new SpongeParticleEffect(this.type, this.motion, this.offset, this.count);
    }

    @Override
    public SpongeParticleEffectBuilder reset() {
        return this;
    }

    public static class BuilderColorable extends SpongeParticleEffectBuilder implements ColoredParticle.Builder {

        private Color color;

        @Override
        public BuilderColorable color(Color color) {
            checkNotNull(color, "The color cannot be null!");
            this.color = color;
            return this;
        }

        @Override
        public BuilderColorable type(ParticleType particleType) {
            checkArgument(particleType instanceof ParticleType.Colorable);
            return (BuilderColorable) super.type(particleType);
        }

        @Override
        public BuilderColorable motion(Vector3d motion) {
            return (BuilderColorable) super.motion(motion);
        }

        @Override
        public BuilderColorable offset(Vector3d motion) {
            return (BuilderColorable) super.offset(motion);
        }

        @Override
        public BuilderColorable count(int count) {
            return (BuilderColorable) super.count(count);
        }

        @Override
        public SpongeParticleEffect.Colored build() {
            return new SpongeParticleEffect.Colored(this.type, this.motion, this.offset, this.color, this.count);
        }

        @Override
        public BuilderColorable reset() {
            return (BuilderColorable) super.reset();
        }

    }

    public static class BuilderResizable extends SpongeParticleEffectBuilder implements ResizableParticle.Builder {

        private float size;


        @Override
        public BuilderResizable size(float size) {
            checkArgument(size >= 0f, "The size has to be greater or equal to zero!");
            this.size = size;
            return this;
        }

        @Override
        public BuilderResizable type(ParticleType particleType) {
            checkArgument(particleType instanceof ParticleType.Resizable);
            return (BuilderResizable) super.type(particleType);
        }

        @Override
        public BuilderResizable motion(Vector3d motion) {
            return (BuilderResizable) super.motion(motion);
        }

        @Override
        public BuilderResizable offset(Vector3d offset) {
            return (BuilderResizable) super.offset(offset);
        }

        @Override
        public BuilderResizable count(int count) {
            return (BuilderResizable) super.count(count);
        }

        @Override
        public SpongeParticleEffect.Resized build() {
            return new SpongeParticleEffect.Resized(this.type, this.motion, this.offset, this.size, this.count);
        }

        @Override
        public BuilderResizable reset() {
            return (BuilderResizable) super.reset();
        }

    }

    public static class BuilderNote extends SpongeParticleEffectBuilder implements NoteParticle.Builder {

        private NotePitch note;

        @Override
        public BuilderNote note(NotePitch note) {
            checkNotNull(note, "The note has to scale between 0 and 24!");
            this.note = note;
            return this;
        }

        @Override
        public BuilderNote type(ParticleType particleType) {
            checkArgument(particleType instanceof ParticleType.Note);
            return (BuilderNote) super.type(particleType);
        }

        @Override
        public BuilderNote motion(Vector3d motion) {
            return (BuilderNote) super.motion(motion);
        }

        @Override
        public BuilderNote offset(Vector3d offset) {
            return (BuilderNote) super.offset(offset);
        }

        @Override
        public BuilderNote count(int count) {
            return (BuilderNote) super.count(count);
        }

        @Override
        public SpongeParticleEffect.Note build() {
            return new SpongeParticleEffect.Note(this.type, this.motion, this.offset, this.note, this.count);
        }

        @Override
        public BuilderNote reset() {
            return (BuilderNote) super.reset();
        }

    }

    public static class BuilderMaterial extends SpongeParticleEffectBuilder implements ItemParticle.Builder {

        private ItemStackSnapshot item;

        @Override
        public BuilderMaterial item(ItemStackSnapshot item) {
            checkNotNull(item, "The item type cannot be null!");
            this.item = item;
            return this;
        }

        @Override
        public BuilderMaterial type(ParticleType particleType) {
            checkArgument(particleType instanceof ParticleType.Material);
            return (BuilderMaterial) super.type(particleType);
        }

        @Override
        public BuilderMaterial motion(Vector3d motion) {
            return (BuilderMaterial) super.motion(motion);
        }

        @Override
        public BuilderMaterial offset(Vector3d offset) {
            return (BuilderMaterial) super.offset(offset);
        }

        @Override
        public BuilderMaterial count(int count) {
            return (BuilderMaterial) super.count(count);
        }

        @Override
        public SpongeParticleEffect.Materialized build() {
            return new SpongeParticleEffect.Materialized(this.type, this.motion, this.offset, this.item, this.count);
        }

        @Override
        public BuilderMaterial reset() {
            return (BuilderMaterial) super.reset();
        }

    }

}
