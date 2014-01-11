package jmm.mods.Diamerald;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorDiamerald implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		case 7:
			generateTwilight(world, random, chunkX * 16, chunkZ * 16);
		}

	}

	private void generateSurface(World world, Random random, int BlockX,
			int BlockZ) {
		for (int i = 0; i < 2; i++) {
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(16);

			(new WorldGenMinable(Diamerald.oreDiamerald.blockID, 4)).generate(
					world, random, Xcoord, Ycoord, Zcoord);
		}

	}

	private void generateNether(World world, Random random, int i, int j) {

	}

	private void generateTwilight(World world, Random random, int BlockX,
			int BlockZ) {
		for (int i = 0; i < 3; i++) {
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(16);

			(new WorldGenMinable(Diamerald.oreDiamerald.blockID, 8)).generate(
					world, random, Xcoord, Ycoord, Zcoord);
		}

	}

}