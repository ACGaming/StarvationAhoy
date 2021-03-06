package dmfmm.StarvationAhoy.FoodEdit.Packet;


import dmfmm.StarvationAhoy.api.FoodEdit.KnownFoods;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFoodUpdate implements IMessage{
    private ItemStack tochange;
    private int hunger;
    private float saturation;


    public PacketFoodUpdate(){
        //NULL
    }

    public PacketFoodUpdate(ItemStack stack, int h, float s){
        this.tochange = stack;
        this.hunger = h;
        this.saturation = s;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tagCompound =ByteBufUtils.readTag(buf);
        hunger = tagCompound.getInteger("int");
        saturation = tagCompound.getFloat("float");
        tochange = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setFloat("float", saturation);
        tagCompound.setInteger("int", hunger);
        ByteBufUtils.writeTag(buf, tagCompound);
        ByteBufUtils.writeItemStack(buf, tochange);
    }

    public static class Handler implements IMessageHandler<PacketFoodUpdate, IMessage> {

        @Override
        public IMessage onMessage(PacketFoodUpdate message, MessageContext ctx) {
            KnownFoods.changeFood(message.tochange, message.hunger, message.saturation);
            return null;
        }
    }
}
