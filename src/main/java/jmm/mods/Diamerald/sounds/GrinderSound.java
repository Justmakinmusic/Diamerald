package jmm.mods.Diamerald.sounds;

import net.minecraft.client.audio.ISound;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GrinderSound implements ISound {

	private static ResourceLocation resourceLocation = new ResourceLocation("diamerald:Grindersnd");
	float xPos;
	float yPos;
	float zPos;
    
	public GrinderSound(float xpos, float ypos, float zpos) {
		System.out.println("Creating new GrinderSound @ [" + xpos + "," + ypos
				+ "," + zpos + "]");
		this.xPos = xpos;
		this.yPos = ypos;
		this.zPos = zpos;
	}

	public ResourceLocation getPositionedSoundLocation() {
		return resourceLocation;
	}

	public boolean canRepeat() {
		return true;
	}

	public int getRepeatDelay() {
		return 0;
	}

	public float getVolume() {
		return 1.0F;
	}

	public float getPitch() {
		return 0.5F;
	}

	public float getXPosF() {
		// TODO Auto-generated method stub
		return this.xPos;
	}

	public float getYPosF() {
		// TODO Auto-generated method stub
		return this.yPos;
	}

	public float getZPosF() {
		// TODO Auto-generated method stub
		return this.zPos;
	}

	public AttenuationType getAttenuationType() {
		return ISound.AttenuationType.LINEAR;
	}

	public void readFromNBT(NBTTagCompound soundNBT) {
		this.xPos = soundNBT.getFloat("xcoord");
		this.yPos = soundNBT.getFloat("ycoord");
		this.zPos = soundNBT.getFloat("zcoord");
	}

	public void writeToNBT(NBTTagCompound soundNBT) {
		soundNBT.setFloat("xcoord", (float) this.xPos);
		soundNBT.setFloat("ycoord", (float) this.yPos);
		soundNBT.setFloat("zcoord", (float) this.zPos);
	}

}
