	import java.util.Collection;
	import java.util.Iterator;

	public class RecipeStage {
		int m_iDifficulty;
		int m_iDuration;
		String m_sDescription;
		/*
		 * (non-javadoc)
		 */
		private Recipe recipe = null;

		/**
		 * Getter of the property <tt>recipe</tt>
		 * 
		 * @return Returns the recipe.
		 * 
		 */

		public Recipe getRecipe() {
			return recipe;
		}

		/**
		 * Setter of the property <tt>recipe</tt>
		 * 
		 * @param recipe
		 *            The recipe to set.
		 * 
		 */
		public void setRecipe(Recipe recipe) {
			this.recipe = recipe;
		}

	}
