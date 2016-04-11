package io.github.phantamanta44.spaceres.worldgen;

import io.github.phantamanta44.spaceres.util.BlockWithMeta;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGenSimple implements IWorldGenerator { 
	
	private BlockWithMeta genBlock, replBlock;
	private int dim;
	private int minYLevel, maxYLevel;
	private int iterations, veinSize;
	
	public OreGenSimple(BlockWithMeta blockToGen, int dimensionToGenerate, BlockWithMeta blockToReplace, int minY, int maxY, int perChunk, int veinDensity) {
		genBlock = blockToGen;
		dim = dimensionToGenerate;
		replBlock = blockToReplace;
		minYLevel = minY;
		maxYLevel = maxY;
		iterations = perChunk;
		veinSize = veinDensity;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.getWorldInfo().getVanillaDimension() == dim) {
			for(int i = 0; i < iterations; i++) {
				int x = chunkX * 16 + random.nextInt(16);
				int y = random.nextInt(maxYLevel - minYLevel) + minYLevel;
				int z = chunkZ * 16 + random.nextInt(16);
				for (int j = 0; j < veinSize; j++) {
					if (world.getBlock(x, y, z).equals(replBlock.getBlock()) && world.getBlockMetadata(x, y, z) == replBlock.getMeta())
						world.setBlock(x, y, z, genBlock.getBlock(), genBlock.getMeta(), 0);
					int dir = random.nextInt(3);
					if (dir == 0)
						x += random.nextBoolean() ? 1 : -1;
					else if (dir == 1)
						y += random.nextBoolean() ? 1 : -1;
					else
						z += random.nextBoolean() ? 1 : -1;
				}
			}
		}
	}
}