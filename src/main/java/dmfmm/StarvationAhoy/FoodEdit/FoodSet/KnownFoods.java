package dmfmm.StarvationAhoy.FoodEdit.FoodSet;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class KnownFoods {
	
	
	
	
	public static ArrayList<ArrayList<Object>> knownFoods = new ArrayList<ArrayList<Object>>();
	
	
	public void insertFoodI(String foodname, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foodname);
		Food.add(HungerHunch);
		Food.add(saturation);
		if (KnownFoods.knownFoods.contains(Food)){
			knownFoods.remove(Food);
		}
		KnownFoods.knownFoods.add(Food);
	}
	
	public static void ok(ItemStack is){
		for (ArrayList<Object> Food : knownFoods){
			if (((ItemStack)Food.get(0)).getItem() == is.getItem()){
				knownFoods.remove(Food);
				break;
			}
		}
	}
	
	public void insertFoodI(ItemStack foods, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foods);
		Food.add(HungerHunch);
		Food.add(saturation);
		ok(foods);
		KnownFoods.knownFoods.add(Food);
	}
	
	public static void insertFood(String foodname, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foodname);
		Food.add(HungerHunch);
		Food.add(saturation);
		if (knownFoods.contains(Food)){
			knownFoods.remove(Food);
		}
		knownFoods.add(Food);
	}
	
	public static void insertFood(ItemStack foods, int HungerHunch, float saturation){
		ArrayList<Object> Food = new ArrayList<>();
		Food.add(foods);
		Food.add(HungerHunch);
		Food.add(saturation);
		if (knownFoods.contains(Food)){
			knownFoods.remove(Food);
		}
		ok(foods);
		knownFoods.add(Food);
	}

	public static void changeFood(ItemStack i, int hunger, float saturation){
		boolean found = false;
		for(ArrayList<Object> foods : KnownFoods.knownFoods) {
			ItemStack bob = (ItemStack) foods.get(0);

			if (bob == i){
				int aboutToEdit = KnownFoods.knownFoods.indexOf(foods);
				ArrayList<Object> food = new ArrayList<>();

				food.add(i);
				food.add(hunger);
				food.add(saturation);
				found = true;
				KnownFoods.knownFoods.set(aboutToEdit, food);
			}

		}
		if (found == false){
			KnownFoods.insertFood(i, hunger, saturation);
		}

	}
	
	public static int getFoodHunger(ItemStack stack){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(1);
			}
		}
		return -1;
	}
	public static int getFoodHunger(String name){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			ItemStack stack = OreDictionary.getOres(name).get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(1);
			}
		}
		return -1;
	}
	public static float getFoodSaturation(ItemStack stack){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return  (float) foods.get(2);
			}
		}
		return -1;
	}
	public static int getFoodSaturation(String name){
		for(ArrayList<Object> foods : knownFoods){
			ItemStack bob =  (ItemStack) foods.get(0);
			ItemStack stack = OreDictionary.getOres(name).get(0);
			
			if(bob.getItem() == stack.getItem() && bob.getItemDamage() == stack.getItemDamage()){
				return (int) foods.get(2);
			}
		}
		return -1;
	}
}
