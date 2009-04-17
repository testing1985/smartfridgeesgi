	import java.util.Collection;
	import java.util.Iterator;

	public class Recipe {

		/**
 *
 */	
		// type of recipe ( dessert, Dish of resistance, ...)
		int m_iType;
		
		// Calories contains in the recipe
		int m_iCalorie;
				
		// number of persons
		int m_iPerson;
		
		private Collection<RecipeStage> recipeStage = null;

		/*
		 * (non-javadoc)
		 */
		private Menu menu = null;

		/**
		 * Getter of the property <tt>recipeStage</tt>
		 * 
		 * @return Returns the recipeStage.
		 * 
		 */

		public Collection<RecipeStage> getRecipeStage() {
			return recipeStage;
		}

		/**
		 * Returns an iterator over the elements in this collection.
		 * 
		 * @return an <tt>Iterator</tt> over the elements in this collection
		 * @see java.util.Collection#iterator()
		 * 
		 */
		public Iterator<RecipeStage> recipeStageIterator() {
			return recipeStage.iterator();
		}

		/**
		 * Returns <tt>true</tt> if this collection contains no elements.
		 * 
		 * @return <tt>true</tt> if this collection contains no elements
		 * @see java.util.Collection#isEmpty()
		 * 
		 */
		public boolean isRecipeStageEmpty() {
			return recipeStage.isEmpty();
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
		public boolean containsRecipeStage(RecipeStage recipeStage) {
			return this.recipeStage.contains(recipeStage);
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
		public boolean containsAllRecipeStage(
				Collection<RecipeStage> recipeStage) {
			return this.recipeStage.containsAll(recipeStage);
		}

		/**
		 * Returns the number of elements in this collection.
		 * 
		 * @return the number of elements in this collection
		 * @see java.util.Collection#size()
		 * 
		 */
		public int recipeStageSize() {
			return recipeStage.size();
		}

		/**
		 * Returns all elements of this collection in an array.
		 * 
		 * @return an array containing all of the elements in this collection
		 * @see java.util.Collection#toArray()
		 * 
		 */
		public RecipeStage[] recipeStageToArray() {
			return recipeStage.toArray(new RecipeStage[recipeStage.size()]);
		}

		/**
		 * Setter of the property <tt>recipeStage</tt>
		 * 
		 * @param recipeStage
		 *            the recipeStage to set.
		 * 
		 */
		public void setRecipeStage(Collection<RecipeStage> recipeStage) {
			this.recipeStage = recipeStage;
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
		public boolean addRecipeStage(RecipeStage recipeStage) {
			return this.recipeStage.add(recipeStage);
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
		public boolean removeRecipeStage(RecipeStage recipeStage) {
			return this.recipeStage.remove(recipeStage);
		}

		/**
		 * Removes all of the elements from this collection (optional
		 * operation).
		 * 
		 * @see java.util.Collection#clear()
		 * 
		 */
		public void clearRecipeStage() {
			this.recipeStage.clear();
		}

		/**
		 * Getter of the property <tt>menu</tt>
		 * 
		 * @return Returns the menu.
		 * 
		 */

		public Menu getMenu() {
			return menu;
		}

		/**
		 * Setter of the property <tt>menu</tt>
		 * 
		 * @param menu
		 *            The menu to set.
		 * 
		 */
		public void setMenu(Menu menu) {
			this.menu = menu;
		}

	}
