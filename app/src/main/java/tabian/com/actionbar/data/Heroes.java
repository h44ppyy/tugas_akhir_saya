package tabian.com.actionbar.data;

import java.util.List;

/**
 * 英雄 实体类
 *
 * Created by Tnno Wu on 2018/03/16.
 */

public class Heroes {

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {

        private int status;
        private int count;
        private List<HeroesBean> heroes;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<HeroesBean> getHeroes() {
            return heroes;
        }

        public void setHeroes(List<HeroesBean> heroes) {
            this.heroes = heroes;
        }

        public static class HeroesBean {

            private String name;
            private int id;
            private String localized_name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
