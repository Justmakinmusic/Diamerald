package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Diameraldhoe extends ItemHoe {

	protected ToolMaterial theToolMaterial;

	public Diameraldhoe(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
		this.theToolMaterial = par2ToolMaterial;
		this.maxStackSize = 1;
		this.setMaxDamage(par2ToolMaterial.getMaxUses());
		this.setCreativeTab(Diamerald.tabDiamerald);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
				par1ItemStack)) {
			return false;
		} else {
			UseHoeEvent event = new UseHoeEvent(par2EntityPlayer,
					par1ItemStack, par3World, par4, par5, par6);
			if (MinecraftForge.EVENT_BUS.post(event)) {
				return false;
			}

			if (event.getResult() == Result.ALLOW) {
				par1ItemStack.damageItem(1, par2EntityPlayer);
				return true;
			}
			for (int i = -2; i < 3; i++) {
				for (int j = -2; j < 3; j++) {
					Block block = par3World.getBlock(par4 + j, par5, par6
							+ i);
//					Block block1 = par3World.func_147439_a(par4 + j, par5 + 1,
//							par6 + i);

					if (!((par7 == 0 || par3World.getBlock(par4 + j, par5 + 1, par6 + i).isAir(par3World, par4 + j, par5 + 1, par6
									+ i) && block != Blocks.grass) && block != Blocks.dirt)) {
						Block block2 = Blocks.farmland;
						par3World.playSoundEffect(
										(double) ((float) par4 + j + 0.5F),
										(double) ((float) par5 + 0.5F),
										(double) ((float) par6 + j + 0.5F),
										block2.stepSound.func_150496_b(),
										(block2.stepSound.getVolume() + 1.0F) / 2.0F,
										block2.stepSound.getPitch() * 0.8F);
						if (!par3World.isRemote) {
							par3World.setBlock(par4 + j, par5, par6 + i,
									Blocks.farmland);
						}
					}
				}
			}
			par1ItemStack.damageItem(1, par2EntityPlayer);
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public String getMaterialName() {
		return this.theToolMaterial.toString();
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diameraldhoe");
	}

}
