package net.darkhax.bladecraft.block;

import java.util.Random;

import net.darkhax.bladecraft.BladeCraft;
import net.darkhax.bladecraft.lib.Reference;
import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockColoranator extends BlockContainer {

	private Random rand = new Random();

	public BlockColoranator(int blockID) {

		super(blockID, Material.iron);
	}

	public int idDropped(int i, Random rand, int j) {

		return 0;
	}

	public void onBlockAdded(World world, int xCoord, int yCoord, int zCoord) {

		super.onBlockAdded(world, xCoord, yCoord, zCoord);
		this.setDefaultDirection(world, xCoord, yCoord, zCoord);
	}

	private void setDefaultDirection(World world, int xCoord, int yCoord,
			int zCoord) {

		if (!world.isRemote) {
			int block1ID = world.getBlockId(xCoord, yCoord, zCoord - 1);
			int block2ID = world.getBlockId(xCoord, yCoord, zCoord + 1);
			int block3ID = world.getBlockId(xCoord - 1, yCoord, zCoord);
			int block4ID = world.getBlockId(xCoord + 1, yCoord, zCoord);
			byte dir = 3;

			if (Block.opaqueCubeLookup[block1ID]
					&& !Block.opaqueCubeLookup[block2ID]) {
				dir = 3;
			}

			if (Block.opaqueCubeLookup[block2ID]
					&& !Block.opaqueCubeLookup[block1ID]) {
				dir = 2;
			}

			if (Block.opaqueCubeLookup[block3ID]
					&& !Block.opaqueCubeLookup[block4ID]) {
				dir = 5;
			}

			if (Block.opaqueCubeLookup[block4ID]
					&& !Block.opaqueCubeLookup[block3ID]) {
				dir = 4;
			}

			world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, dir, 2);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {

		return this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		this.blockIcon = iconRegister.registerIcon("furnace_side");
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer entityPlayer, int par6, float par7, float par8,
			float par9) {

		if (world.isRemote) {
			return true;
		}
		else {
			TileEntityColoranator tileentitycoloranator = (TileEntityColoranator) world.getBlockTileEntity(x, y, z);
			
			if (tileentitycoloranator != null)
			{
				entityPlayer.openGui(BladeCraft.instance, Reference.COLORANATOR_GUI_ID, world, x, y, z);
			}

			return true;
		}
	}

	public static void updateBlockState(World world, int x, int y, int z) {

		int l = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setBlockTileEntity(x, y, z, tileentity);
		}
	}

	public TileEntity createNewTileEntity(World world) {

		return new TileEntityColoranator();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entityLivingBase, ItemStack itemstack) {

		int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		if (itemstack.hasDisplayName()) {
			((TileEntityColoranator) world.getBlockTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}
	}

	public void breakBlock(World world, int x, int y, int z, int prevBlockID,
			int prevBlockMetadata) {

		TileEntityColoranator tileentitycoloranator = (TileEntityColoranator) world.getBlockTileEntity(x, y, z);

		if (tileentitycoloranator != null) {
			for (int j1 = 0; j1 < tileentitycoloranator.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentitycoloranator.getStackInSlot(j1);

				if (itemstack != null) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int k1 = this.rand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.rand.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.rand.nextGaussian()
								* f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.rand.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}

			world.func_96440_m(x, y, z, prevBlockID);
		}
		super.breakBlock(world, x, y, z, prevBlockID, prevBlockMetadata);
	}

	public boolean hasComparatorInputOverride() {

		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {

		return Container.calcRedstoneFromInventory((IInventory) world.getBlockTileEntity(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {

		return Blocks.coloranatorBlock.blockID;
	}
}
