package com.app.oooelePartner.Bean;

public class BeanGetWalletData {
    /**
     * id : 2
     * amount : 200
     * member_id : 26
     * created : 2020-05-22 12:54:41
     * detail : Added By Admin For Received Payment
     */

    private String id;
    private String amount;
    private String member_id;
    private String created;
    private String detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}