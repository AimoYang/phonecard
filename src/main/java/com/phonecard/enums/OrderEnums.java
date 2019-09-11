package com.phonecard.enums;

public interface OrderEnums {
    //（0创建订单未付款1已付款未发货2取消订单(未付款)3 已发货4已完成 5申请取消中 6退单（同意退款））
    public enum orderStatus{

        ORDER_CREATE_NOT_PAY(0,"创建订单未付款"),
        ORDER_PAID_NOT_DELIVERY(1,"已付款未发货"),
        ORDER_CANCEL_NOT_PAID(2,"取消订单(未付款)"),
        ORDER_DELIVERY(3,"已发货"),
        ORDER_COMPLETED(4,"已完成"),
        ORDER_CANCELING(5,"申请取消中"),
        ORDER_CANCELE_AGGREED(6,"退单（同意退款）");
        private int value;
        private String desc;
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private orderStatus(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

}
