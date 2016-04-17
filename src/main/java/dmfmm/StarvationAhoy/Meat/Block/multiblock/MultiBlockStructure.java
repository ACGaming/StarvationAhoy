package dmfmm.StarvationAhoy.Meat.Block.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public abstract class MultiBlockStructure {

    public MultiBlockStructure(){

    }

    public int x;
    public int y;
    public int z;

    public int bPos;

    public int orient;

    public NBTTagCompound sharedData = new NBTTagCompound();

        public void onUpdate(World w){
            if (bPos != 0) {
                return;

        }
    }

    public void checkAndDoUpdate(World w){if (checkForChanges(w)) updateStructure(w);}

    public boolean checkForChanges(World w){return false;}

    public void updateStructure(World world){
        if (bPos != 0){
            //SALog.error("Err.. Whoopsies! Im outa here! " + world.isRemote + bPos);
            return;
        }
        NBTTagCompound oldSha = sharedData;
        if (true) {onUpdate(world);}
        //if (sharedData != oldSha){
            //SALog.error("im making lots o lag!");
            syncData(this, this.bPos, new BlockPos(this.x, this.y, this.z), world);


    }

    public abstract int[] getPosForBlock(int bPos, int sBPos, int x, int y, int z, World world);

    public abstract int bPosMax();

    public void syncData(MultiBlockStructure structToSync, int bPos, BlockPos pos, World world){

        for (int blockPos = 0; blockPos < bPosMax(); blockPos++) {
            int[] bPosFor = getPosForBlock(blockPos, bPos, pos.getX(), pos.getY(), pos.getZ(), world);

            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(new BlockPos(bPosFor[0], bPosFor[1], bPosFor[2]));
            if (te == null){return;}
            MultiBlockStructure struct = te.multiBlockStructure;
            struct.sharedData = structToSync.sharedData;
            te.onSync();
        }

    }
    public void destroy(World world){
        for (int blockPos = 0; blockPos < bPosMax(); blockPos++) {
            int[] bPosFor = getPosForBlock(blockPos, bPos, x, y, z, world);

            world.setBlockToAir(new BlockPos(bPosFor[0], bPosFor[1], bPosFor[2]));
        }
    }

}
