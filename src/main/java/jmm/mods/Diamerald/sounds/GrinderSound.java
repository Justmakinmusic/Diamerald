package jmm.mods.Diamerald.sounds;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;

public class GrinderSound implements ISound {

	private static ResourceLocation resourceLocation = 
			new ResourceLocation("diamerald:Grindersnd");
	float xPos;
	float yPos;
	float zPos;
	
	
	public GrinderSound(float xpos, float ypos, float zpos) {
		System.out.println("Creating new GrinderSound @ ["+xpos+
				","+ypos+","+zpos+"]");
		this.xPos = xpos;
		this.yPos = ypos;
		this.zPos = zpos;
	}
	
	@Override
	public ResourceLocation getPositionedSoundLocation() {
		return resourceLocation;
	}

	@Override
	public boolean canRepeat() {
		return true;
	}

	@Override
	public int getRepeatDelay() {
		return 0;
	}

	@Override
	public float getVolume() {
		return 1.0F;
	}

	@Override
	public float getPitch() {
		return 0.5F;
	}

	@Override
	public float getXPosF() {
		// TODO Auto-generated method stub
		return this.xPos;
	}

	@Override
	public float getYPosF() {
		// TODO Auto-generated method stub
		return this.yPos;
	}

	@Override
	public float getZPosF() {
		// TODO Auto-generated method stub
		return this.zPos;
	}

	@Override
	public AttenuationType getAttenuationType() {
		return ISound.AttenuationType.LINEAR;
	}

}
