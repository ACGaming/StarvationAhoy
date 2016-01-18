package dmfmm.StarvationAhoy.Core.Init;


import dmfmm.StarvationAhoy.Core.lib.WashLib;
import dmfmm.StarvationAhoy.CropWash.ModuleCropWash;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CropwashTextureRegistry {


    public static void initTextures(){
        registerBlock(WashLib.washBarrelName, 0);
        doDirtyItem();
    }



    private static void doDirtyItem() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(ModuleCropWash.cropItemLoader.getItem("dirty_item"),0,  ModuleCropWash.dirty_item_model);
    }

    private static void registerBlock(String blockName, int meta){
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        Item itemB = GameRegistry.findItem("starvationahoy", blockName);
        renderItem.getItemModelMesher().register(itemB, meta, new ModelResourceLocation("starvationahoy:"+ blockName, "inventory"));
    }

    private static void register(Item item, int meta){
        ItemModelMesher modelRegistry = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        //EFLog.fatal((ResourceLocation) Item.itemRegistry.getNameForObject(item));
        modelRegistry.register(item, 0, new ModelResourceLocation((ResourceLocation) Item.itemRegistry.getNameForObject(item), "inventory"));
    }
}