package survivalblock.shield_surf.common.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import survivalblock.shield_surf.common.component.ShieldStackComponent;
import survivalblock.shield_surf.common.init.ShieldSurfDamageTypes;
import survivalblock.shield_surf.common.init.ShieldSurfEntityComponents;
import survivalblock.shield_surf.common.init.ShieldSurfEntityTypes;
import survivalblock.shield_surf.common.init.ShieldSurfSoundEvents;

import java.util.Objects;

public class ProjectedShieldEntity extends PersistentProjectileEntity {

    public ProjectedShieldEntity(EntityType<? extends ProjectedShieldEntity> entityType, World world) {
        super(entityType, world);
        this.getShieldStackComponent().setShieldStack(Items.SHIELD.getDefaultStack());
    }

    public ProjectedShieldEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ShieldSurfEntityTypes.PROJECTED_SHIELD, owner.getX(), (owner.getY() * 4 + owner.getEyeY()) / 5, owner.getZ(), world);
        this.getShieldStackComponent().setShieldStack(stack.copyWithCount(stack.getCount()));
        this.setNoGravity(true);
        this.setOwner(owner);
    }

    private ShieldStackComponent getShieldStackComponent(){
        return ShieldSurfEntityComponents.SHIELD_STACK.get(this);
    }

    @Override
    public ItemStack asItemStack() {
        return this.getShieldStackComponent().getShieldStack().copy();
    }

    @Override
    public void tick() {
        boolean ownerDoesNotExist = this.getOwner() == null || this.getOwner().isRemoved(); // same as !this.getOwner().isAlive()
        if (this.age > 120 || ownerDoesNotExist) {
            this.kill();
        }
        super.tick();
        if (this.isInsideWall()){
            this.kill();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        Entity entity2 = this.getOwner();
        if (Objects.equals(entity, entity2)) {
            return;
        }
        DamageSource damageSource = new DamageSource(ShieldSurfDamageTypes.get(ShieldSurfDamageTypes.SHIELD_IMPACT, this.getWorld()), this, entity2);
        if (entity.damage(damageSource, (float) this.getDamage())) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (entity instanceof LivingEntity livingEntity2) {
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity2, livingEntity2);
                }
                this.onHit(livingEntity2);
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }

    @Override
    protected SoundEvent getHitSound() {
        return ShieldSurfSoundEvents.ENTITY_PROJECTED_SHIELD_HIT;
    }
}
