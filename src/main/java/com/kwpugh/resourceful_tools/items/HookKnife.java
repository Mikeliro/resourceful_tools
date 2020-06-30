package com.kwpugh.resourceful_tools.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HookKnife extends SwordItem
{
	public HookKnife(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		 World world = context.getWorld();
		 PlayerEntity player = context.getPlayer();
		 BlockPos pos = context.getPos();
		 BlockState state = world.getBlockState(pos);
		 Block block = state.getBlock();
		 ItemStack stack = context.getItem();
	      
	     if(block == Blocks.WHITE_WOOL ||
	    		 block == Blocks.BLACK_WOOL ||
	    		 block == Blocks.BLUE_WOOL ||
	    		 block == Blocks.BROWN_WOOL ||
	    		 block == Blocks.CYAN_WOOL ||
	    		 block == Blocks.GRAY_WOOL ||
	    		 block == Blocks.GREEN_WOOL ||
	    		 block == Blocks.LIGHT_BLUE_WOOL ||
	    		 block == Blocks.LIGHT_GRAY_WOOL ||
	    		 block == Blocks.LIME_WOOL ||
	    		 block == Blocks.MAGENTA_WOOL ||
	    		 block == Blocks.ORANGE_WOOL ||
	    		 block == Blocks.PINK_WOOL ||
	    		 block == Blocks.PURPLE_WOOL ||
	    		 block == Blocks.RED_WOOL ||
	    		 block == Blocks.YELLOW_WOOL
	    		 )
	     {
	    	 world.destroyBlock(pos, false);
	    	 world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.STRING, 4))); 
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });
	     }
	     
	     
		 return ActionResultType.PASS;
	}
	
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity)
	{
		if(entity instanceof SheepEntity)
		{
			World world = player.world;
			Vector3d pos = entity.getPositionVec();
			
			 world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.STRING, 1))); 
	    	 stack.damageItem(1, player, (p_220038_0_) -> {
		         p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		         });	
		}
    	 
		return true;
		
	}
	
	@Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stackIn)
    {
    	ItemStack stack = stackIn.copy();
    	stack.setDamage(getDamage(stack) + 1);

        return stack;
    }
	    
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslationTextComponent("item.resourceful_tools.hook_knife.line1").func_240699_a_(TextFormatting.GREEN)));
		tooltip.add((new TranslationTextComponent("item.resourceful_tools.hook_knife.line2").func_240699_a_(TextFormatting.GREEN)));
	}
}