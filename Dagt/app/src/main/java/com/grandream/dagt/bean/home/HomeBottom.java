package com.grandream.dagt.bean.home;

import retrofit2.http.POST;

/**
 * Created by chenjing on 2018/6/26.
 */

public class HomeBottom {
    private Databean data;

    public Databean getDatabean() {
        return data;
    }

    public void setDatabean(Databean databean) {
        this.data = databean;
    }

    public class Databean {
        private int warning;

        public int getWarning() {
            return warning;
        }

        public void setWarning(int warning) {
            this.warning = warning;
        }
    }


}
