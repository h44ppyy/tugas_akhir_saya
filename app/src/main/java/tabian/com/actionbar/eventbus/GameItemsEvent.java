package tabian.com.actionbar.eventbus;

/**
 * Created by Tnno Wu on 2018/3/18.
 */

public class GameItemsEvent {

    private int cost;

    private int secret_shop;

    private int side_shop;

    private int recipe;

    private String localized_name;

    private String img_url;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSecret_shop() {
        return secret_shop;
    }

    public void setSecret_shop(int secret_shop) {
        this.secret_shop = secret_shop;
    }

    public int getSide_shop() {
        return side_shop;
    }

    public void setSide_shop(int side_shop) {
        this.side_shop = side_shop;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public String getLocalized_name() {
        return localized_name;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public GameItemsEvent(int cost, int secret_shop, int side_shop, int recipe, String localized_name, String img_url) {
        this.cost = cost;
        this.secret_shop = secret_shop;
        this.side_shop = side_shop;
        this.recipe = recipe;
        this.localized_name = localized_name;
        this.img_url = img_url;
    }
}
