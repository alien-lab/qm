package com.alienlab.niit.qm.entity.dto;

/**
 * Created by Administrator on 2017/4/29.
 */
public class BaseErrorMsg {
    private int totalnum;
    private int successnum;
    private int errornum;
    private String brach_id;
    private String err_msg  ;

    /**
     * @return the totalnum
     */
    public int getTotalnum() {
        return totalnum;
    }

    /**
     * @param totalnum
     *            the totalnum to set
     */
    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }

    /**
     * @return the successnum
     */
    public int getSuccessnum() {
        return successnum;
    }

    /**
     * @param successnum
     *            the successnum to set
     */
    public void setSuccessnum(int successnum) {
        this.successnum = successnum;
    }

    /**
     * @return the errornum
     */
    public int getErrornum() {
        return errornum;
    }

    /**
     * @param errornum
     *            the errornum to set
     */
    public void setErrornum(int errornum) {
        this.errornum = errornum;
    }

    /**
     * @return the brach_id
     */
    public String getBrach_id() {
        return brach_id;
    }

    /**
     * @param brach_id
     *            the brach_id to set
     */
    public void setBrach_id(String brach_id) {
        this.brach_id = brach_id;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }
}
