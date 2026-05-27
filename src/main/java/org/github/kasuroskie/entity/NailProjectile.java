package org.github.kasuroskie.entity;

import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.registry.ModEffects;
import org.github.kasuroskie.registry.ModEntities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class NailProjectile extends Projectile {
    private static final EntityDataAccessor<Boolean> EMPOWERED =
            SynchedEntityData.defineId(NailProjectile.class, EntityDataSerializers.BOOLEAN);

    public NailProjectile(EntityType<? extends NailProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public NailProjectile(Level level, LivingEntity owner, boolean empowered) {
        this(ModEntities.NAIL.get(), level);
        this.setOwner(owner);
        this.setEmpowered(empowered);
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(EMPOWERED, false);
    }

    public boolean isEmpowered() {
        return this.entityData.get(EMPOWERED);
    }

    public void setEmpowered(boolean empowered) {
        this.entityData.set(EMPOWERED, empowered);
    }

    public void shoot(Vec3 direction, float speed) {
        Vec3 velocity = direction.normalize().scale(speed);
        this.setDeltaMovement(velocity);
        this.hasImpulse = true;
        double horizontal = velocity.horizontalDistance();
        this.setYRot((float) (Math.atan2(velocity.x, velocity.z) * (180.0F / Math.PI)));
        this.setXRot((float) (Math.atan2(velocity.y, horizontal) * (180.0F / Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 movement = this.getDeltaMovement();
        HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hit.getType() != HitResult.Type.MISS) {
            this.onHit(hit);
        }
        this.setPos(this.position().add(movement));
        ProjectileUtil.rotateTowardsMovement(this, 1.0F);
        if (!this.level().isClientSide && this.tickCount > 200) {
            this.discard();
        }
    }

    @Override
    protected boolean canHitEntity(net.minecraft.world.entity.Entity entity) {
        if (entity == this.getOwner()) {
            return false;
        }
        return super.canHitEntity(entity);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level().isClientSide) {
            return;
        }
        if (result.getEntity() instanceof LivingEntity living) {
            if (this.isEmpowered()) {
                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, ModConstants.EFFECT_DURATION_TICKS, 2));
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, ModConstants.EFFECT_DURATION_TICKS, 0));
            } else {
                living.addEffect(new MobEffectInstance(ModEffects.PINNED, ModConstants.EFFECT_DURATION_TICKS));
            }
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
    }
}
