package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 */
public class Chef {

    public static void main(String[] args) {
    	Chef chef = new Chef();
    	
    }

    public Chef() {
    	Oven oven = new Oven(300);
    	SousChef sousChef = new SousChef(oven);
    	RecipeReadyCallback callback = new RecipeReadyCallback() {
    		@Override public void recipeReadyToCook(Recipe recipe) {
    			recipe.initializeFromOven(oven);
    		}
    	};
    	 
    	sousChef.prepare(new PotRoast(oven), callback);
    	sousChef.prepare(new Baguette(oven, 0.5), callback);
    	sousChef.prepare(new RoastedSweetPotato(oven), callback);
    	sousChef.prepare(new Baguette(oven, 0.5), callback);
    }

}
