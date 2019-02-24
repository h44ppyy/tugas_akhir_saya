package tabian.com.actionbar.data;

import java.util.List;

/**
 * 游戏内物品 实体类
 *
 * Created by Tnno Wu on 2018/03/16.
 */

public class GameItems {

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {

        private int status;
        private List<ItemsBean> items;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {

            private int id;
            private String name;
            private int cost;
            private int secret_shop;
            private int side_shop;
            private int recipe;
            private String localized_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

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
        }
    }
}
