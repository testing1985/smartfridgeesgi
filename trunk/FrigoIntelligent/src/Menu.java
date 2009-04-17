
	import java.util.Collection;
	import java.util.Iterator;

	public class Menu {
		String m_sName;

		/**
 *
 */
		private Collection<Recipe> recipe = null;

		/**
		 * Getter of the property <tt>recipe</tt>
		 * 
		 * @return Returns the recipe.
		 * 
		 */

		public Collection<Recipe> getRecipe() {
			return recipe;
		}

		/**
		 * Returns an iterator over the elements in this collection.
		 * 
		 * @return an <tt>Iterator</tt> over the elements in this collection
		 * @see java.util.Collection#iterator()
		 * 
		 */
		public Iterator<Recipe> recipeIterator() {
			return recipe.iterator();
		}

		/**
		 * Returns <tt>true</tt> if this collection contains no elements.
		 * 
		 * @return <tt>true</tt> if this collection contains no elements
		 * @see java.util.Collection#isEmpty()
		 * 
		 */
		public boolean isRecipeEmpty() {
			return recipe.isEmpty();
		}

		/**
		 * Returns <tt>true</tt> if this collection contains the specified
		 * element.
		 * 
		 * @param element
		 *            whose presence in this collection is to be tested.
		 * @see java.util.Collection#contains(Object)
		 * 
		 */
		public boolean containsRecipe(Recipe recipe) {
			return this.recipe.contains(recipe);
		}

		/**
		 * Returns <tt>true</tt> if this collection contains all of the elements
		 * in the specified collection.
		 * 
		 * @param elements
		 *            collection to be checked for containment in this
		 *            collection.
		 * @see java.util.Collection#containsAll(Collection)
		 * 
		 */
		public boolean containsAllRecipe(Collection<Recipe> recipe) {
			return this.recipe.containsAll(recipe);
		}

		/**
		 * Returns the number of elements in this collection.
		 * 
		 * @return the number of elements in this collection
		 * @see java.util.Collection#size()
		 * 
		 */
		public int recipeSize() {
			return recipe.size();
		}

		/**
		 * Returns all elements of this collection in an array.
		 * 
		 * @return an array containing all of the elements in this collection
		 * @see java.util.Collection#toArray()
		 * 
		 */
		public Recipe[] recipeToArray() {
			return recipe.toArray(new Recipe[recipe.size()]);
		}

		/**
		 * Setter of the property <tt>recipe</tt>
		 * 
		 * @param recipe
		 *            the recipe to set.
		 * 
		 */
		public void setRecipe(Collection<Recipe> recipe) {
			this.recipe = recipe;
		}

		/**
		 * Ensures that this collection contains the specified element (optional
		 * operation).
		 * 
		 * @param element
		 *            whose presence in this collection is to be ensured.
		 * @see java.util.Collection#add(Object)
		 * 
		 */
		public boolean addRecipe(Recipe recipe) {
			return this.recipe.add(recipe);
		}

		/**
		 * Removes a single instance of the specified element from this
		 * collection, if it is present (optional operation).
		 * 
		 * @param element
		 *            to be removed from this collection, if present.
		 * @see java.util.Collection#add(Object)
		 * 
		 */
		public boolean removeRecipe(Recipe recipe) {
			return this.recipe.remove(recipe);
		}

		/**
		 * Removes all of the elements from this collection (optional
		 * operation).
		 * 
		 * @see java.util.Collection#clear()
		 * 
		 */
		public void clearRecipe() {
			this.recipe.clear();
		}

	}
