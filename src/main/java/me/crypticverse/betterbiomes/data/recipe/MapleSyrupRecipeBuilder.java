package me.crypticverse.betterbiomes.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.crypticverse.betterbiomes.BetterBiomes;
import me.crypticverse.betterbiomes.recipe.MapleSyrupRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class MapleSyrupRecipeBuilder implements CraftingRecipeJsonBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.create();


    public MapleSyrupRecipeBuilder(ItemConvertible ingredient, ItemConvertible result, int count) {
        this.ingredient = Ingredient.ofItems(ingredient);
        this.result = result.asItem();
        this.count = count;
    }
    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> conditions) {
        this.advancement.criterion(name, conditions);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return result;
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        exporter.accept(new JsonBuilder(recipeId, this.result, this.count, this.ingredient,
                this.advancement, new Identifier(recipeId.getNamespace(), "recipes/"
                + recipeId.getPath())));

    }
    public static class JsonBuilder implements RecipeJsonProvider {
        private final Identifier id;
        private final Item result;
        private final Ingredient ingredient;
        private final int count;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;

        public JsonBuilder(Identifier id, Item result, int count, Ingredient ingredient,
                           Advancement.Builder advancement, Identifier advancementId) {
            this.id = id;
            this.result = result;
            this.ingredient = ingredient;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;

        }

        @Override
        public void serialize(JsonObject json) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(ingredient.toJson(true));

            json.add("ingredients", jsonArray);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Registries.ITEM.getId(this.result).toString());
            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }
            json.add("output", jsonObject);
        }

        @Override
        public Identifier id() {
            return new Identifier(BetterBiomes.MOD_ID,
                    Registries.ITEM.getId(this.result).getPath() + "_from_maple_boiler");
        }

        @Override
        public RecipeSerializer<?> serializer() {
            return MapleSyrupRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public AdvancementEntry advancement() {
            return new AdvancementEntry(id(), advancement.build(id()).value());
        }
    }
}
