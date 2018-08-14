package com.grandream.dagt.bean.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chenjing on 2018/4/12.
 */

public class HomeCoinTrendBean {

    /**
     * data : {"btcusdt":{"24_hours_trade_info":16424,"rise_fall_info":0.9,"current_price_info":8339.99,"24_hours_k_line_info":[{"data":"2018-04-15 10:00:00","price":8104.32},{"data":"2018-04-15 11:00:00","price":8087.13},{"data":"2018-04-15 12:00:00","price":8077.52},{"data":"2018-04-15 13:00:00","price":8104.74},{"data":"2018-04-15 14:00:00","price":8118.46},{"data":"2018-04-15 15:00:00","price":8103.39},{"data":"2018-04-15 16:00:00","price":8067.18},{"data":"2018-04-15 17:00:00","price":8121.58},{"data":"2018-04-15 18:00:00","price":8106.25},{"data":"2018-04-15 19:00:00","price":8104.86},{"data":"2018-04-15 20:00:00","price":8328},{"data":"2018-04-15 21:00:00","price":8329},{"data":"2018-04-15 22:00:00","price":8345},{"data":"2018-04-15 23:00:00","price":8324},{"data":"2018-04-16 00:00:00","price":8268},{"data":"2018-04-16 01:00:00","price":8263.57},{"data":"2018-04-16 02:00:00","price":8288.18},{"data":"2018-04-16 03:00:00","price":8316.75},{"data":"2018-04-16 04:00:00","price":8299.43},{"data":"2018-04-16 05:00:00","price":8286.7},{"data":"2018-04-16 06:00:00","price":8297.15},{"data":"2018-04-16 07:00:00","price":8340},{"data":"2018-04-16 08:00:00","price":8344},{"data":"2018-04-16 09:00:00","price":8378.25}]},"ethusdt":{"24_hours_trade_info":151750,"rise_fall_info":1.8,"current_price_info":531.38,"24_hours_k_line_info":[{"data":"2018-04-15 10:00:00","price":518.05},{"data":"2018-04-15 11:00:00","price":511.5},{"data":"2018-04-15 12:00:00","price":511.58},{"data":"2018-04-15 13:00:00","price":512.58},{"data":"2018-04-15 14:00:00","price":515.75},{"data":"2018-04-15 15:00:00","price":512.71},{"data":"2018-04-15 16:00:00","price":509.26},{"data":"2018-04-15 17:00:00","price":511.98},{"data":"2018-04-15 18:00:00","price":512.25},{"data":"2018-04-15 19:00:00","price":511.68},{"data":"2018-04-15 20:00:00","price":521.65},{"data":"2018-04-15 21:00:00","price":523.8},{"data":"2018-04-15 22:00:00","price":526.93},{"data":"2018-04-15 23:00:00","price":527.06},{"data":"2018-04-16 00:00:00","price":521.75},{"data":"2018-04-16 01:00:00","price":522.94},{"data":"2018-04-16 02:00:00","price":526.28},{"data":"2018-04-16 03:00:00","price":526.65},{"data":"2018-04-16 04:00:00","price":524.46},{"data":"2018-04-16 05:00:00","price":524.98},{"data":"2018-04-16 06:00:00","price":524.67},{"data":"2018-04-16 07:00:00","price":529.83},{"data":"2018-04-16 08:00:00","price":534.39},{"data":"2018-04-16 09:00:00","price":534.35}]},"dagtusdt":{"24_hours_trade_info":0,"rise_fall_info":0,"current_price_info":0,"24_hours_k_line_info":[{"data":"2018-04-15 10:00:00","price":518.05},{"data":"2018-04-15 11:00:00","price":511.5},{"data":"2018-04-15 12:00:00","price":511.58},{"data":"2018-04-15 13:00:00","price":512.58},{"data":"2018-04-15 14:00:00","price":515.75},{"data":"2018-04-15 15:00:00","price":512.71},{"data":"2018-04-15 16:00:00","price":509.26},{"data":"2018-04-15 17:00:00","price":511.98},{"data":"2018-04-15 18:00:00","price":512.25},{"data":"2018-04-15 19:00:00","price":511.68},{"data":"2018-04-15 20:00:00","price":521.65},{"data":"2018-04-15 21:00:00","price":523.8},{"data":"2018-04-15 22:00:00","price":526.93},{"data":"2018-04-15 23:00:00","price":527.06},{"data":"2018-04-16 00:00:00","price":521.75},{"data":"2018-04-16 01:00:00","price":522.94},{"data":"2018-04-16 02:00:00","price":526.28},{"data":"2018-04-16 03:00:00","price":526.65},{"data":"2018-04-16 04:00:00","price":524.46},{"data":"2018-04-16 05:00:00","price":524.98},{"data":"2018-04-16 06:00:00","price":524.67},{"data":"2018-04-16 07:00:00","price":529.83},{"data":"2018-04-16 08:00:00","price":534.39},{"data":"2018-04-16 09:00:00","price":534.35}]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * btcusdt : {"24_hours_trade_info":16424,"rise_fall_info":0.9,"current_price_info":8339.99,"24_hours_k_line_info":[{"data":"2018-04-15 10:00:00","price":8104.32},{"data":"2018-04-15 11:00:00","price":8087.13},{"data":"2018-04-15 12:00:00","price":8077.52},{"data":"2018-04-15 13:00:00","price":8104.74},{"data":"2018-04-15 14:00:00","price":8118.46},{"data":"2018-04-15 15:00:00","price":8103.39},{"data":"2018-04-15 16:00:00","price":8067.18},{"data":"2018-04-15 17:00:00","price":8121.58},{"data":"2018-04-15 18:00:00","price":8106.25},{"data":"2018-04-15 19:00:00","price":8104.86},{"data":"2018-04-15 20:00:00","price":8328},{"data":"2018-04-15 21:00:00","price":8329},{"data":"2018-04-15 22:00:00","price":8345},{"data":"2018-04-15 23:00:00","price":8324},{"data":"2018-04-16 00:00:00","price":8268},{"data":"2018-04-16 01:00:00","price":8263.57},{"data":"2018-04-16 02:00:00","price":8288.18},{"data":"2018-04-16 03:00:00","price":8316.75},{"data":"2018-04-16 04:00:00","price":8299.43},{"data":"2018-04-16 05:00:00","price":8286.7},{"data":"2018-04-16 06:00:00","price":8297.15},{"data":"2018-04-16 07:00:00","price":8340},{"data":"2018-04-16 08:00:00","price":8344},{"data":"2018-04-16 09:00:00","price":8378.25}]}
         * ethusdt : {"24_hours_trade_info":151750,"rise_fall_info":1.8,"current_price_info":531.38,"24_hours_k_line_info":[{"data":"2018-04-15 10:00:00","price":518.05},{"data":"2018-04-15 11:00:00","price":511.5},{"data":"2018-04-15 12:00:00","price":511.58},{"data":"2018-04-15 13:00:00","price":512.58},{"data":"2018-04-15 14:00:00","price":515.75},{"data":"2018-04-15 15:00:00","price":512.71},{"data":"2018-04-15 16:00:00","price":509.26},{"data":"2018-04-15 17:00:00","price":511.98},{"data":"2018-04-15 18:00:00","price":512.25},{"data":"2018-04-15 19:00:00","price":511.68},{"data":"2018-04-15 20:00:00","price":521.65},{"data":"2018-04-15 21:00:00","price":523.8},{"data":"2018-04-15 22:00:00","price":526.93},{"data":"2018-04-15 23:00:00","price":527.06},{"data":"2018-04-16 00:00:00","price":521.75},{"data":"2018-04-16 01:00:00","price":522.94},{"data":"2018-04-16 02:00:00","price":526.28},{"data":"2018-04-16 03:00:00","price":526.65},{"data":"2018-04-16 04:00:00","price":524.46},{"data":"2018-04-16 05:00:00","price":524.98},{"data":"2018-04-16 06:00:00","price":524.67},{"data":"2018-04-16 07:00:00","price":529.83},{"data":"2018-04-16 08:00:00","price":534.39},{"data":"2018-04-16 09:00:00","price":534.35}]}
         * dagtusdt : {"24_hours_trade_info":0,"rise_fall_info":0,"current_price_info":0,"24_hours_k_line_info":[{"data":"2018-04-15 10:00:00","price":518.05},{"data":"2018-04-15 11:00:00","price":511.5},{"data":"2018-04-15 12:00:00","price":511.58},{"data":"2018-04-15 13:00:00","price":512.58},{"data":"2018-04-15 14:00:00","price":515.75},{"data":"2018-04-15 15:00:00","price":512.71},{"data":"2018-04-15 16:00:00","price":509.26},{"data":"2018-04-15 17:00:00","price":511.98},{"data":"2018-04-15 18:00:00","price":512.25},{"data":"2018-04-15 19:00:00","price":511.68},{"data":"2018-04-15 20:00:00","price":521.65},{"data":"2018-04-15 21:00:00","price":523.8},{"data":"2018-04-15 22:00:00","price":526.93},{"data":"2018-04-15 23:00:00","price":527.06},{"data":"2018-04-16 00:00:00","price":521.75},{"data":"2018-04-16 01:00:00","price":522.94},{"data":"2018-04-16 02:00:00","price":526.28},{"data":"2018-04-16 03:00:00","price":526.65},{"data":"2018-04-16 04:00:00","price":524.46},{"data":"2018-04-16 05:00:00","price":524.98},{"data":"2018-04-16 06:00:00","price":524.67},{"data":"2018-04-16 07:00:00","price":529.83},{"data":"2018-04-16 08:00:00","price":534.39},{"data":"2018-04-16 09:00:00","price":534.35}]}
         */

        private BtcusdtBean btcusdt;
        private EthusdtBean ethusdt;
        private DagtusdtBean dagtusdt;

        public BtcusdtBean getBtcusdt() {
            return btcusdt;
        }

        public void setBtcusdt(BtcusdtBean btcusdt) {
            this.btcusdt = btcusdt;
        }

        public EthusdtBean getEthusdt() {
            return ethusdt;
        }

        public void setEthusdt(EthusdtBean ethusdt) {
            this.ethusdt = ethusdt;
        }

        public DagtusdtBean getDagtusdt() {
            return dagtusdt;
        }

        public void setDagtusdt(DagtusdtBean dagtusdt) {
            this.dagtusdt = dagtusdt;
        }

        public static class BtcusdtBean {
            /**
             * 24_hours_trade_info : 16424
             * rise_fall_info : 0.9
             * current_price_info : 8339.99
             * 24_hours_k_line_info : [{"data":"2018-04-15 10:00:00","price":8104.32},{"data":"2018-04-15 11:00:00","price":8087.13},{"data":"2018-04-15 12:00:00","price":8077.52},{"data":"2018-04-15 13:00:00","price":8104.74},{"data":"2018-04-15 14:00:00","price":8118.46},{"data":"2018-04-15 15:00:00","price":8103.39},{"data":"2018-04-15 16:00:00","price":8067.18},{"data":"2018-04-15 17:00:00","price":8121.58},{"data":"2018-04-15 18:00:00","price":8106.25},{"data":"2018-04-15 19:00:00","price":8104.86},{"data":"2018-04-15 20:00:00","price":8328},{"data":"2018-04-15 21:00:00","price":8329},{"data":"2018-04-15 22:00:00","price":8345},{"data":"2018-04-15 23:00:00","price":8324},{"data":"2018-04-16 00:00:00","price":8268},{"data":"2018-04-16 01:00:00","price":8263.57},{"data":"2018-04-16 02:00:00","price":8288.18},{"data":"2018-04-16 03:00:00","price":8316.75},{"data":"2018-04-16 04:00:00","price":8299.43},{"data":"2018-04-16 05:00:00","price":8286.7},{"data":"2018-04-16 06:00:00","price":8297.15},{"data":"2018-04-16 07:00:00","price":8340},{"data":"2018-04-16 08:00:00","price":8344},{"data":"2018-04-16 09:00:00","price":8378.25}]
             */

            @SerializedName("24_hours_trade_info")
            private int _$24_hours_trade_info;
            private String rise_fall_info;
            private String current_price_info;
            @SerializedName("24_hours_k_line_info")
            private List<_$24HoursKLineInfoBean> _$24_hours_k_line_info;

            public int get_$24_hours_trade_info() {
                return _$24_hours_trade_info;
            }

            public void set_$24_hours_trade_info(int _$24_hours_trade_info) {
                this._$24_hours_trade_info = _$24_hours_trade_info;
            }

            public String getRise_fall_info() {
                return rise_fall_info;
            }

            public void setRise_fall_info(String rise_fall_info) {
                this.rise_fall_info = rise_fall_info;
            }

            public String getCurrent_price_info() {
                return current_price_info;
            }

            public void setCurrent_price_info(String current_price_info) {
                this.current_price_info = current_price_info;
            }

            public List<_$24HoursKLineInfoBean> get_$24_hours_k_line_info() {
                return _$24_hours_k_line_info;
            }

            public void set_$24_hours_k_line_info(List<_$24HoursKLineInfoBean> _$24_hours_k_line_info) {
                this._$24_hours_k_line_info = _$24_hours_k_line_info;
            }

            public static class _$24HoursKLineInfoBean {
                /**
                 * data : 2018-04-15 10:00:00
                 * price : 8104.32
                 */

                private String data;
                private double price;

                public String getData() {
                    return data;
                }

                public void setData(String data) {
                    this.data = data;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }
            }
        }

        public static class EthusdtBean {
            /**
             * 24_hours_trade_info : 151750
             * rise_fall_info : 1.8
             * current_price_info : 531.38
             * 24_hours_k_line_info : [{"data":"2018-04-15 10:00:00","price":518.05},{"data":"2018-04-15 11:00:00","price":511.5},{"data":"2018-04-15 12:00:00","price":511.58},{"data":"2018-04-15 13:00:00","price":512.58},{"data":"2018-04-15 14:00:00","price":515.75},{"data":"2018-04-15 15:00:00","price":512.71},{"data":"2018-04-15 16:00:00","price":509.26},{"data":"2018-04-15 17:00:00","price":511.98},{"data":"2018-04-15 18:00:00","price":512.25},{"data":"2018-04-15 19:00:00","price":511.68},{"data":"2018-04-15 20:00:00","price":521.65},{"data":"2018-04-15 21:00:00","price":523.8},{"data":"2018-04-15 22:00:00","price":526.93},{"data":"2018-04-15 23:00:00","price":527.06},{"data":"2018-04-16 00:00:00","price":521.75},{"data":"2018-04-16 01:00:00","price":522.94},{"data":"2018-04-16 02:00:00","price":526.28},{"data":"2018-04-16 03:00:00","price":526.65},{"data":"2018-04-16 04:00:00","price":524.46},{"data":"2018-04-16 05:00:00","price":524.98},{"data":"2018-04-16 06:00:00","price":524.67},{"data":"2018-04-16 07:00:00","price":529.83},{"data":"2018-04-16 08:00:00","price":534.39},{"data":"2018-04-16 09:00:00","price":534.35}]
             */

            @SerializedName("24_hours_trade_info")
            private int _$24_hours_trade_info;
            private String rise_fall_info;
            private String current_price_info;
            @SerializedName("24_hours_k_line_info")
            private List<_$24HoursKLineInfoBeanX> _$24_hours_k_line_info;

            public int get_$24_hours_trade_info() {
                return _$24_hours_trade_info;
            }

            public void set_$24_hours_trade_info(int _$24_hours_trade_info) {
                this._$24_hours_trade_info = _$24_hours_trade_info;
            }

            public String getRise_fall_info() {
                return rise_fall_info;
            }

            public void setRise_fall_info(String rise_fall_info) {
                this.rise_fall_info = rise_fall_info;
            }

            public String getCurrent_price_info() {
                return current_price_info;
            }

            public void setCurrent_price_info(String current_price_info) {
                this.current_price_info = current_price_info;
            }

            public List<_$24HoursKLineInfoBeanX> get_$24_hours_k_line_info() {
                return _$24_hours_k_line_info;
            }

            public void set_$24_hours_k_line_info(List<_$24HoursKLineInfoBeanX> _$24_hours_k_line_info) {
                this._$24_hours_k_line_info = _$24_hours_k_line_info;
            }

            public static class _$24HoursKLineInfoBeanX {
                /**
                 * data : 2018-04-15 10:00:00
                 * price : 518.05
                 */

                private String data;
                private double price;

                public String getData() {
                    return data;
                }

                public void setData(String data) {
                    this.data = data;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }
            }
        }

        public static class DagtusdtBean {
            /**
             * 24_hours_trade_info : 0
             * rise_fall_info : 0
             * current_price_info : 0
             * 24_hours_k_line_info : [{"data":"2018-04-15 10:00:00","price":518.05},{"data":"2018-04-15 11:00:00","price":511.5},{"data":"2018-04-15 12:00:00","price":511.58},{"data":"2018-04-15 13:00:00","price":512.58},{"data":"2018-04-15 14:00:00","price":515.75},{"data":"2018-04-15 15:00:00","price":512.71},{"data":"2018-04-15 16:00:00","price":509.26},{"data":"2018-04-15 17:00:00","price":511.98},{"data":"2018-04-15 18:00:00","price":512.25},{"data":"2018-04-15 19:00:00","price":511.68},{"data":"2018-04-15 20:00:00","price":521.65},{"data":"2018-04-15 21:00:00","price":523.8},{"data":"2018-04-15 22:00:00","price":526.93},{"data":"2018-04-15 23:00:00","price":527.06},{"data":"2018-04-16 00:00:00","price":521.75},{"data":"2018-04-16 01:00:00","price":522.94},{"data":"2018-04-16 02:00:00","price":526.28},{"data":"2018-04-16 03:00:00","price":526.65},{"data":"2018-04-16 04:00:00","price":524.46},{"data":"2018-04-16 05:00:00","price":524.98},{"data":"2018-04-16 06:00:00","price":524.67},{"data":"2018-04-16 07:00:00","price":529.83},{"data":"2018-04-16 08:00:00","price":534.39},{"data":"2018-04-16 09:00:00","price":534.35}]
             */

            @SerializedName("24_hours_trade_info")
            private int _$24_hours_trade_info;
            private String rise_fall_info;
            private String current_price_info;
            @SerializedName("24_hours_k_line_info")
            private List<_$24HoursKLineInfoBeanXX> _$24_hours_k_line_info;

            public int get_$24_hours_trade_info() {
                return _$24_hours_trade_info;
            }

            public void set_$24_hours_trade_info(int _$24_hours_trade_info) {
                this._$24_hours_trade_info = _$24_hours_trade_info;
            }

            public String getRise_fall_info() {
                return rise_fall_info;
            }

            public void setRise_fall_info(String rise_fall_info) {
                this.rise_fall_info = rise_fall_info;
            }

            public String getCurrent_price_info() {
                return current_price_info;
            }

            public void setCurrent_price_info(String current_price_info) {
                this.current_price_info = current_price_info;
            }

            public List<_$24HoursKLineInfoBeanXX> get_$24_hours_k_line_info() {
                return _$24_hours_k_line_info;
            }

            public void set_$24_hours_k_line_info(List<_$24HoursKLineInfoBeanXX> _$24_hours_k_line_info) {
                this._$24_hours_k_line_info = _$24_hours_k_line_info;
            }

            public static class _$24HoursKLineInfoBeanXX {
                /**
                 * data : 2018-04-15 10:00:00
                 * price : 518.05
                 */

                private String data;
                private double price;

                public String getData() {
                    return data;
                }

                public void setData(String data) {
                    this.data = data;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }
            }
        }
    }
}
