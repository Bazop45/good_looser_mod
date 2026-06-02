package org.github.kasuroskie.entity;

import org.github.kasuroskie.ModConstants;
import org.github.kasuroskie.combat.NailCombatHelper;
import org.github.kasuroskie.registry.ModEntities;
import org.github.kasuroskie.util.ModLogger;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
import org.slf4j.Logger;

public class NailProjectile extends Projectile implements GeoEntity {
    private static final Logger LOGGER = ModLogger.getLogger(NailProjectile.class);
    private static final EntityDataAccessor<Boolean> EMPOWERED =
            SynchedEntityData.defineId(NailProjectile.class, EntityDataSerializers.BOOLEAN);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public NailProjectile(EntityType<? extends NailProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        LOGGER.debug("Created nail projectile (type: {}, empowered: false)", type.getDescription().getString());
    }

    public NailProjectile(Level level, LivingEntity owner, boolean empowered) {
        this(ModEntities.NAIL.get(), level);
        this.setOwner(owner);
        this.setEmpowered(empowered);
        this.setNoGravity(true);
        LOGGER.debug("Created nail projectile (owner: {}, empowered: {})", 
                owner != null ? owner.getName().getString() : "null", empowered);
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
        Vec3 movement = this.getDeltaMovement();
        super.tick();
        if (movement.lengthSqr() > 0) {
            this.setDeltaMovement(movement);
        }
        this.setPos(this.position().add(movement));
        HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hit.getType() != HitResult.Type.MISS) {
            this.onHit(hit);
        }
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
            NailCombatHelper.onNailHit(living, this.level().getGameTime());
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<NailProjectile> state) {
        state.getController().setAnimation(RawAnimation.begin().thenLoop("fly"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
