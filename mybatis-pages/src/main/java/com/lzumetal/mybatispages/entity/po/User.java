package com.lzumetal.mybatispages.entity.po;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>Description:</p>
 *
 * @Author liaosi
 * @Date 2018-03-10
 */
public class User {

    private Long id;
    private Long uid;
    private String username;
    private String realname;
    private String avatar;
    private String nickname;
    private Byte isAuthentication;
    private Byte isNew;
    private Byte passportType;
    private String passportId;
    private BigDecimal balance;
    private String password;
    private Byte status;
    private Byte pledge;
    private Integer pledgeFee;
    private Timestamp createTime;
    private Date recoverTime;
    private Integer manualRefundPledge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Byte getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(Byte isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public Byte getIsNew() {
        return isNew;
    }

    public void setIsNew(Byte isNew) {
        this.isNew = isNew;
    }

    public Byte getPassportType() {
        return passportType;
    }

    public void setPassportType(Byte passportType) {
        this.passportType = passportType;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPledge() {
        return pledge;
    }

    public void setPledge(Byte pledge) {
        this.pledge = pledge;
    }

    public Integer getPledgeFee() {
        return pledgeFee;
    }

    public void setPledgeFee(Integer pledgeFee) {
        this.pledgeFee = pledgeFee;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(Date recoverTime) {
        this.recoverTime = recoverTime;
    }

    public Integer getManualRefundPledge() {
        return manualRefundPledge;
    }

    public void setManualRefundPledge(Integer manualRefundPledge) {
        this.manualRefundPledge = manualRefundPledge;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", isAuthentication=" + isAuthentication +
                ", isNew=" + isNew +
                ", passportType=" + passportType +
                ", passportId='" + passportId + '\'' +
                ", balance=" + balance +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", pledge=" + pledge +
                ", pledgeFee=" + pledgeFee +
                ", createTime=" + createTime +
                ", recoverTime=" + recoverTime +
                ", manualRefundPledge=" + manualRefundPledge +
                '}';
    }
}
