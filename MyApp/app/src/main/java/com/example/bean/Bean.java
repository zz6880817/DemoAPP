package com.example.bean;

import java.util.List;

/**
 * 主页实体bean
 * Created by Administrator on 2016/3/8.
 */
public class Bean {


    private GoodsItemsListGetResponseEntity goods_items_list_get_response;

    private int status_code;
    private String result;

    public void setGoods_items_list_get_response(GoodsItemsListGetResponseEntity goods_items_list_get_response) {
        this.goods_items_list_get_response = goods_items_list_get_response;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public GoodsItemsListGetResponseEntity getGoods_items_list_get_response() {
        return goods_items_list_get_response;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getResult() {
        return result;
    }

    public static class GoodsItemsListGetResponseEntity {
        private int total;

        private List<ItemsEntity> items;

        public void setTotal(int total) {
            this.total = total;
        }

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
        }

        public int getTotal() {
            return total;
        }

        public List<ItemsEntity> getItems() {
            return items;
        }

        public static class ItemsEntity {
            private int site_id;
            private int shop_market_id;
            private int shop_floor_id;
            private int shop_id;
            private String shop_name;
            private String shop_tb_nick;
            private String shop_qq;
            private String shop_market;
            private String shop_floor;
            private String shop_dangkou;
            private String shop_services;
            private Object shop_youhui;
            private int goods_id;
            private String title;
            private double price1;
            private double price2;
            private String gno;
            private int cate_id;
            private String tb_url;
            private long tb_num_iid;
            private int status;
            private String tb_img;
            private Object tb_imgs;
            private int quantity;
            private Object attributes;
            private Object sku_attributes;
            private Object skus;

            public void setSite_id(int site_id) {
                this.site_id = site_id;
            }

            public void setShop_market_id(int shop_market_id) {
                this.shop_market_id = shop_market_id;
            }

            public void setShop_floor_id(int shop_floor_id) {
                this.shop_floor_id = shop_floor_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public void setShop_tb_nick(String shop_tb_nick) {
                this.shop_tb_nick = shop_tb_nick;
            }

            public void setShop_qq(String shop_qq) {
                this.shop_qq = shop_qq;
            }

            public void setShop_market(String shop_market) {
                this.shop_market = shop_market;
            }

            public void setShop_floor(String shop_floor) {
                this.shop_floor = shop_floor;
            }

            public void setShop_dangkou(String shop_dangkou) {
                this.shop_dangkou = shop_dangkou;
            }

            public void setShop_services(String shop_services) {
                this.shop_services = shop_services;
            }

            public void setShop_youhui(Object shop_youhui) {
                this.shop_youhui = shop_youhui;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setPrice1(double price1) {
                this.price1 = price1;
            }

            public void setPrice2(double price2) {
                this.price2 = price2;
            }

            public void setGno(String gno) {
                this.gno = gno;
            }

            public void setCate_id(int cate_id) {
                this.cate_id = cate_id;
            }

            public void setTb_url(String tb_url) {
                this.tb_url = tb_url;
            }

            public void setTb_num_iid(long tb_num_iid) {
                this.tb_num_iid = tb_num_iid;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setTb_img(String tb_img) {
                this.tb_img = tb_img;
            }

            public void setTb_imgs(Object tb_imgs) {
                this.tb_imgs = tb_imgs;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public void setAttributes(Object attributes) {
                this.attributes = attributes;
            }

            public void setSku_attributes(Object sku_attributes) {
                this.sku_attributes = sku_attributes;
            }

            public void setSkus(Object skus) {
                this.skus = skus;
            }

            public int getSite_id() {
                return site_id;
            }

            public int getShop_market_id() {
                return shop_market_id;
            }

            public int getShop_floor_id() {
                return shop_floor_id;
            }

            public int getShop_id() {
                return shop_id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public String getShop_tb_nick() {
                return shop_tb_nick;
            }

            public String getShop_qq() {
                return shop_qq;
            }

            public String getShop_market() {
                return shop_market;
            }

            public String getShop_floor() {
                return shop_floor;
            }

            public String getShop_dangkou() {
                return shop_dangkou;
            }

            public String getShop_services() {
                return shop_services;
            }

            public Object getShop_youhui() {
                return shop_youhui;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public String getTitle() {
                return title;
            }

            public double getPrice1() {
                return price1;
            }

            public double getPrice2() {
                return price2;
            }

            public String getGno() {
                return gno;
            }

            public int getCate_id() {
                return cate_id;
            }

            public String getTb_url() {
                return tb_url;
            }

            public long getTb_num_iid() {
                return tb_num_iid;
            }

            public int getStatus() {
                return status;
            }

            public String getTb_img() {
                return tb_img;
            }

            public Object getTb_imgs() {
                return tb_imgs;
            }

            public int getQuantity() {
                return quantity;
            }

            public Object getAttributes() {
                return attributes;
            }

            public Object getSku_attributes() {
                return sku_attributes;
            }

            public Object getSkus() {
                return skus;
            }
        }
    }
}
