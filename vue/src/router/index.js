import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import RecipeDetailsView from '../views/RecipeDetailsView.vue';
import BreakfastView from '../views/BreakfastView.vue';
import CategoryView from '../views/CategoryView.vue';
import Instructions from '../components/Instructions.vue'
import GroceryListView from '../views/GroceryListView.vue';
import FavoriteMealsView from '../views/FavoriteMealsView.vue';
import FavoriteRecipesView from '../views/FavoriteRecipesView.vue';
import CustomRecipesView from '../views/CustomRecipesView.vue';


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/recipe/:id",
    name: "recipe",
    component: RecipeDetailsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/breakfast",
    name: "breakfast",
    component: BreakfastView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/category/:id",
    name: "category",
    component: CategoryView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/groceryList",
    name: "groceryList",
    component: GroceryListView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/favoriteMeals",
    name: "favoriteMeals",
    component: FavoriteMealsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/favoriteRecipes",
    name: "favoriteRecipes",
    component: FavoriteRecipesView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/customRecipes",
    name: "customRecipes",
    component: CustomRecipesView,
    meta: {
      requiresAuth: true
    }
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
