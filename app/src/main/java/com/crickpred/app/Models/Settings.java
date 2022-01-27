package com.crickpred.app.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Settings {


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("wallet")
        @Expose
        private String wallet;
        @SerializedName("total_paid")
        @Expose
        private String totalPaid;
        @SerializedName("total_redeemed")
        @Expose
        private String totalRedeemed;
        @SerializedName("user_referal_code")
        @Expose
        private String userReferalCode;
        @SerializedName("reffered_by")
        @Expose
        private String refferedBy;
        @SerializedName("reffered_paid")
        @Expose
        private String refferedPaid;
        @SerializedName("total_referals")
        @Expose
        private String totalReferals;
        @SerializedName("allow")
        @Expose
        private String allow;
        @SerializedName("device_id")
        @Expose
        private String deviceId;
        @SerializedName("profile_pic")
        @Expose
        private String profilePic;
        @SerializedName("active_date")
        @Expose
        private String activeDate;
        @SerializedName("onesignal_playerid")
        @Expose
        private String onesignalPlayerid;
        @SerializedName("onesignal_pushtoken")
        @Expose
        private String onesignalPushtoken;
        @SerializedName("joining_time")
        @Expose
        private String joiningTime;
        @SerializedName("app_name")
        @Expose
        private String appName;
        @SerializedName("app_logo")
        @Expose
        private String appLogo;
        @SerializedName("app_description")
        @Expose
        private String appDescription;
        @SerializedName("app_version")
        @Expose
        private String appVersion;
        @SerializedName("app_author")
        @Expose
        private String appAuthor;
        @SerializedName("app_contact")
        @Expose
        private String appContact;
        @SerializedName("app_email")
        @Expose
        private String appEmail;
        @SerializedName("app_website")
        @Expose
        private String appWebsite;
        @SerializedName("app_developed_by")
        @Expose
        private String appDevelopedBy;
        @SerializedName("redeem_currency")
        @Expose
        private String redeemCurrency;
        @SerializedName("home_bannerimg1")
        @Expose

        private String homeBannerimg1Enabled;
        @SerializedName("homeBannerimg1Enabled")
        @Expose

        private String homeBannerimg1;
        @SerializedName("home_bannerimg2")
        @Expose
        private String homeBannerimg2Enabled;
        @SerializedName("homeBannerimg2Enabled")
        @Expose
        private String homeBannerimg2;
        @SerializedName("home_bannerimg3")
        @Expose
        private String homeBannerimg3;
        @SerializedName("onesignalapp_id")
        @Expose
        private String onesignalappId;
        @SerializedName("onesignalapp_key")
        @Expose
        private String onesignalappKey;
        @SerializedName("refer_txt")
        @Expose
        private String referTxt;
        @SerializedName("admin_id")
        @Expose
        private String adminId;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("joining_bonus")
        @Expose
        private String joiningBonus;
        @SerializedName("per_refer")
        @Expose
        private String perRefer;
        @SerializedName("minDepositcoin")
        @Expose
        private String minDepositcoin;
        @SerializedName("hourly_quiz_coin")
        @Expose
        private String hourlyQuizCoin;
        @SerializedName("maths_quiz_coin")
        @Expose
        private String mathsQuizCoin;
        @SerializedName("maxm_maths_questn")
        @Expose
        private String maxmMathsQuestn;
        @SerializedName("hourly_spin_limit")
        @Expose
        private String hourlySpinLimit;
        @SerializedName("hourly_mathsquiz_limit")
        @Expose
        private String hourlyMathsquizLimit;
        @SerializedName("mathsQuiz_unlockMin")
        @Expose
        private String mathsQuizUnlockMin;
        @SerializedName("per_news_coin")
        @Expose
        private String perNewsCoin;
        @SerializedName("minimum_widthrawal")
        @Expose
        private String minimumWidthrawal;
        @SerializedName("min_redeem_amount")
        @Expose
        private String minRedeemAmount;
        @SerializedName("telegramlink")
        @Expose
        private String telegramlink;
        @SerializedName("youtube_link")
        @Expose
        private String youtubeLink;
        @SerializedName("facebook_page")
        @Expose
        private String facebookPage;
        @SerializedName("new_version")
        @Expose
        private String newVersion;
        @SerializedName("update_link")
        @Expose
        private String updateLink;
        @SerializedName("admin_msg")
        @Expose
        private String adminMsg;
        @SerializedName("join_group")
        @Expose
        private String joinGroup;
        @SerializedName("app_promo1")
        @Expose
        private String appPromo1;
        @SerializedName("app_promo2")
        @Expose
        private String appPromo2;
        @SerializedName("date")
        @Expose
        private String date;

    @SerializedName("kid")
    @Expose
    private String kid;
    @SerializedName("kmobile")
    @Expose
    private String kmobile;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("bankDetailsStatus")
    @Expose
    private String bankDetailsStatus;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("bankAccNo")
    @Expose
    private String bankAccNo;
    @SerializedName("bankIfsc")
    @Expose
    private String bankIfsc;
    @SerializedName("BankAccHolderName")
    @Expose
    private String bankAccHolderName;
    @SerializedName("paytmName")
    @Expose
    private String paytmName;
    @SerializedName("paytmNo")
    @Expose
    private String paytmNo;
    @SerializedName("gPayName")
    @Expose
    private String gPayName;
    @SerializedName("gPayNo")
    @Expose
    private String gPayNo;
    @SerializedName("phonePayName")
    @Expose
    private String phonePayName;
    @SerializedName("phonePayNo")
    @Expose
    private String phonePayNo;
    @SerializedName("patymUpiName")
    @Expose
    private String patymUpiName;
    @SerializedName("patymUpiNo")
    @Expose
    private String patymUpiNo;
    @SerializedName("upiName")
    @Expose
    private String upiName;
    @SerializedName("upiNo")
    @Expose
    private String upiNo;
    @SerializedName("kcreationDate")
    @Expose
    private String kcreationDate;
    @SerializedName("kupdatedDate")
    @Expose
    private String kupdatedDate;

        public Settings(String appName, String appLogo, String appDescription, String appVersion, String appAuthor, String appContact,
                        String appEmail,
                        String appWebsite, String appDevelopedBy, String redeemCurrency,String homeBannerimg1Enabled, String homeBannerimg1,
                        String homeBannerimg2Enabled,String homeBannerimg2, String homeBannerimg3, String onesignalappId,
                        String onesignalappKey, String referTxt, String image, String joiningBonus, String perRefer, String minDepositcoin,
                        String hourlyQuizCoin, String mathsQuizCoin, String maxmMathsQuestn, String hourlySpinLimit,
                        String hourlyMathsquizLimit, String mathsQuizUnlockMin, String perNewsCoin, String minimumWidthrawal,
                        String minRedeemAmount, String telegramlink, String youtubeLink, String facebookPage, String newVersion,
                        String updateLink, String adminMsg, String joinGroup, String appPromo1, String appPromo2, String date,
                        String kid, String kmobile, String userName, String bankDetailsStatus, String bankName, String bankAccNo,
                        String bankIfsc, String bankAccHolderName, String paytmName, String paytmNo, String gPayName, String gPayNo,
                        String phonePayName, String phonePayNo, String patymUpiName, String patymUpiNo, String upiName, String upiNo,
                        String kcreationDate, String kupdatedDate) {
            super();
            this.appName = appName;
            this.appLogo = appLogo;
            this.appDescription = appDescription;
            this.appVersion = appVersion;
            this.appAuthor = appAuthor;
            this.appContact = appContact;
            this.appEmail = appEmail;
            this.appWebsite = appWebsite;
            this.appDevelopedBy = appDevelopedBy;
            this.redeemCurrency = redeemCurrency;
            this.homeBannerimg1Enabled=homeBannerimg1Enabled;
            this.homeBannerimg1 = homeBannerimg1;
            this.homeBannerimg2Enabled=homeBannerimg2Enabled;
            this.homeBannerimg2 = homeBannerimg2;
            this.homeBannerimg3 = homeBannerimg3;
            this.onesignalappId = onesignalappId;
            this.onesignalappKey = onesignalappKey;
            this.referTxt = referTxt;
            this.image = image;
            this.joiningBonus = joiningBonus;
            this.perRefer = perRefer;
            this.minDepositcoin = minDepositcoin;
            this.hourlyQuizCoin = hourlyQuizCoin;
            this.mathsQuizCoin = mathsQuizCoin;
            this.maxmMathsQuestn = maxmMathsQuestn;
            this.hourlySpinLimit = hourlySpinLimit;
            this.hourlyMathsquizLimit = hourlyMathsquizLimit;
            this.mathsQuizUnlockMin = mathsQuizUnlockMin;
            this.perNewsCoin = perNewsCoin;
            this.minimumWidthrawal = minimumWidthrawal;
            this.minRedeemAmount = minRedeemAmount;
            this.telegramlink = telegramlink;
            this.youtubeLink = youtubeLink;
            this.facebookPage = facebookPage;
            this.newVersion = newVersion;
            this.updateLink = updateLink;
            this.adminMsg = adminMsg;
            this.joinGroup = joinGroup;
            this.appPromo1 = appPromo1;
            this.appPromo2 = appPromo2;
            this.date = date;
            this.kid = kid;
            this.kmobile = kmobile;
            this.userName = userName;
            this.bankDetailsStatus = bankDetailsStatus;
            this.bankName = bankName;
            this.bankAccNo = bankAccNo;
            this.bankIfsc = bankIfsc;
            this.bankAccHolderName = bankAccHolderName;
            this.paytmName = paytmName;
            this.paytmNo = paytmNo;
            this.gPayName = gPayName;
            this.gPayNo = gPayNo;
            this.phonePayName = phonePayName;
            this.phonePayNo = phonePayNo;
            this.patymUpiName = patymUpiName;
            this.patymUpiNo = patymUpiNo;
            this.upiName = upiName;
            this.upiNo = upiNo;
            this.kcreationDate = kcreationDate;
            this.kupdatedDate = kupdatedDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWallet() {
            return wallet;
        }

        public void setWallet(String wallet) {
            this.wallet = wallet;
        }

        public String getTotalPaid() {
            return totalPaid;
        }

        public void setTotalPaid(String totalPaid) {
            this.totalPaid = totalPaid;
        }

        public String getTotalRedeemed() {
            return totalRedeemed;
        }

        public void setTotalRedeemed(String totalRedeemed) {
            this.totalRedeemed = totalRedeemed;
        }

        public String getUserReferalCode() {
            return userReferalCode;
        }

        public void setUserReferalCode(String userReferalCode) {
            this.userReferalCode = userReferalCode;
        }

        public String getRefferedBy() {
            return refferedBy;
        }

        public void setRefferedBy(String refferedBy) {
            this.refferedBy = refferedBy;
        }

        public String getRefferedPaid() {
            return refferedPaid;
        }

        public void setRefferedPaid(String refferedPaid) {
            this.refferedPaid = refferedPaid;
        }

        public String getTotalReferals() {
            return totalReferals;
        }

        public void setTotalReferals(String totalReferals) {
            this.totalReferals = totalReferals;
        }

        public String getAllow() {
            return allow;
        }

        public void setAllow(String allow) {
            this.allow = allow;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getActiveDate() {
            return activeDate;
        }

        public void setActiveDate(String activeDate) {
            this.activeDate = activeDate;
        }

        public String getOnesignalPlayerid() {
            return onesignalPlayerid;
        }

        public void setOnesignalPlayerid(String onesignalPlayerid) {
            this.onesignalPlayerid = onesignalPlayerid;
        }

        public String getOnesignalPushtoken() {
            return onesignalPushtoken;
        }

        public void setOnesignalPushtoken(String onesignalPushtoken) {
            this.onesignalPushtoken = onesignalPushtoken;
        }

        public String getJoiningTime() {
            return joiningTime;
        }

        public void setJoiningTime(String joiningTime) {
            this.joiningTime = joiningTime;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppLogo() {
            return appLogo;
        }

        public void setAppLogo(String appLogo) {
            this.appLogo = appLogo;
        }

        public String getAppDescription() {
            return appDescription;
        }

        public void setAppDescription(String appDescription) {
            this.appDescription = appDescription;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getAppAuthor() {
            return appAuthor;
        }

        public void setAppAuthor(String appAuthor) {
            this.appAuthor = appAuthor;
        }

        public String getAppContact() {
            return appContact;
        }

        public void setAppContact(String appContact) {
            this.appContact = appContact;
        }

        public String getAppEmail() {
            return appEmail;
        }

        public void setAppEmail(String appEmail) {
            this.appEmail = appEmail;
        }

        public String getAppWebsite() {
            return appWebsite;
        }

        public void setAppWebsite(String appWebsite) {
            this.appWebsite = appWebsite;
        }

        public String getAppDevelopedBy() {
            return appDevelopedBy;
        }

        public void setAppDevelopedBy(String appDevelopedBy) {
            this.appDevelopedBy = appDevelopedBy;
        }

        public String getRedeemCurrency() {
            return redeemCurrency;
        }

        public void setRedeemCurrency(String redeemCurrency) {
            this.redeemCurrency = redeemCurrency;
        }

        public String getHomeBannerimg1() {
            return homeBannerimg1;
        }

        public void setHomeBannerimg1(String homeBannerimg1) {
            this.homeBannerimg1 = homeBannerimg1;
        }

        public String getHomeBannerimg2() {
            return homeBannerimg2;
        }

        public void setHomeBannerimg2(String homeBannerimg2) {
            this.homeBannerimg2 = homeBannerimg2;
        }

        public String getHomeBannerimg3() {
            return homeBannerimg3;
        }

        public void setHomeBannerimg3(String homeBannerimg3) {
            this.homeBannerimg3 = homeBannerimg3;
        }

        public String getOnesignalappId() {
            return onesignalappId;
        }

        public void setOnesignalappId(String onesignalappId) {
            this.onesignalappId = onesignalappId;
        }

        public String getOnesignalappKey() {
            return onesignalappKey;
        }

        public void setOnesignalappKey(String onesignalappKey) {
            this.onesignalappKey = onesignalappKey;
        }

        public String getReferTxt() {
            return referTxt;
        }

        public void setReferTxt(String referTxt) {
            this.referTxt = referTxt;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getJoiningBonus() {
            return joiningBonus;
        }

        public void setJoiningBonus(String joiningBonus) {
            this.joiningBonus = joiningBonus;
        }

        public String getPerRefer() {
            return perRefer;
        }

        public void setPerRefer(String perRefer) {
            this.perRefer = perRefer;
        }

    public String getMinDepositcoin() {
        return minDepositcoin;
    }

    public void setMinDepositcoin(String minDepositcoin) {
        this.minDepositcoin = minDepositcoin;
    }

    public String getHourlyQuizCoin() {
            return hourlyQuizCoin;
        }

        public void setHourlyQuizCoin(String hourlyQuizCoin) {
            this.hourlyQuizCoin = hourlyQuizCoin;
        }

        public String getMathsQuizCoin() {
            return mathsQuizCoin;
        }

        public void setMathsQuizCoin(String mathsQuizCoin) {
            this.mathsQuizCoin = mathsQuizCoin;
        }

        public String getMaxmMathsQuestn() {
            return maxmMathsQuestn;
        }

        public void setMaxmMathsQuestn(String maxmMathsQuestn) {
            this.maxmMathsQuestn = maxmMathsQuestn;
        }

        public String getHourlySpinLimit() {
            return hourlySpinLimit;
        }

        public void setHourlySpinLimit(String hourlySpinLimit) {
            this.hourlySpinLimit = hourlySpinLimit;
        }

        public String getHourlyMathsquizLimit() {
            return hourlyMathsquizLimit;
        }

        public void setHourlyMathsquizLimit(String hourlyMathsquizLimit) {
            this.hourlyMathsquizLimit = hourlyMathsquizLimit;
        }

        public String getMathsQuizUnlockMin() {
            return mathsQuizUnlockMin;
        }

        public void setMathsQuizUnlockMin(String mathsQuizUnlockMin) {
            this.mathsQuizUnlockMin = mathsQuizUnlockMin;
        }

        public String getPerNewsCoin() {
            return perNewsCoin;
        }

        public void setPerNewsCoin(String perNewsCoin) {
            this.perNewsCoin = perNewsCoin;
        }

        public String getMinimumWidthrawal() {
            return minimumWidthrawal;
        }

        public void setMinimumWidthrawal(String minimumWidthrawal) {
            this.minimumWidthrawal = minimumWidthrawal;
        }

        public String getMinRedeemAmount() {
            return minRedeemAmount;
        }

        public void setMinRedeemAmount(String minRedeemAmount) {
            this.minRedeemAmount = minRedeemAmount;
        }

        public String getTelegramlink() {
            return telegramlink;
        }

        public void setTelegramlink(String telegramlink) {
            this.telegramlink = telegramlink;
        }

        public String getYoutubeLink() {
            return youtubeLink;
        }

        public void setYoutubeLink(String youtubeLink) {
            this.youtubeLink = youtubeLink;
        }

        public String getFacebookPage() {
            return facebookPage;
        }

        public void setFacebookPage(String facebookPage) {
            this.facebookPage = facebookPage;
        }

        public String getNewVersion() {
            return newVersion;
        }

        public void setNewVersion(String newVersion) {
            this.newVersion = newVersion;
        }

        public String getUpdateLink() {
            return updateLink;
        }

        public void setUpdateLink(String updateLink) {
            this.updateLink = updateLink;
        }

        public String getAdminMsg() {
            return adminMsg;
        }

        public void setAdminMsg(String adminMsg) {
            this.adminMsg = adminMsg;
        }

        public String getJoinGroup() {
            return joinGroup;
        }

        public void setJoinGroup(String joinGroup) {
            this.joinGroup = joinGroup;
        }

        public String getAppPromo1() {
            return appPromo1;
        }

        public void setAppPromo1(String appPromo1) {
            this.appPromo1 = appPromo1;
        }

        public String getAppPromo2() {
            return appPromo2;
        }

        public void setAppPromo2(String appPromo2) {
            this.appPromo2 = appPromo2;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

    public String getHomeBannerimg1Enabled() {
        return homeBannerimg1Enabled;
    }

    public void setHomeBannerimg1Enabled(String homeBannerimg1Enabled) {
        this.homeBannerimg1Enabled = homeBannerimg1Enabled;
    }

    public String getHomeBannerimg2Enabled() {
        return homeBannerimg2Enabled;
    }

    public void setHomeBannerimg2Enabled(String homeBannerimg2Enabled) {
        this.homeBannerimg2Enabled = homeBannerimg2Enabled;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getKmobile() {
        return kmobile;
    }

    public void setKmobile(String kmobile) {
        this.kmobile = kmobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankDetailsStatus() {
        return bankDetailsStatus;
    }

    public void setBankDetailsStatus(String bankDetailsStatus) {
        this.bankDetailsStatus = bankDetailsStatus;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getBankIfsc() {
        return bankIfsc;
    }

    public void setBankIfsc(String bankIfsc) {
        this.bankIfsc = bankIfsc;
    }

    public String getBankAccHolderName() {
        return bankAccHolderName;
    }

    public void setBankAccHolderName(String bankAccHolderName) {
        this.bankAccHolderName = bankAccHolderName;
    }

    public String getPaytmName() {
        return paytmName;
    }

    public void setPaytmName(String paytmName) {
        this.paytmName = paytmName;
    }

    public String getPaytmNo() {
        return paytmNo;
    }

    public void setPaytmNo(String paytmNo) {
        this.paytmNo = paytmNo;
    }

    public String getgPayName() {
        return gPayName;
    }

    public void setgPayName(String gPayName) {
        this.gPayName = gPayName;
    }

    public String getgPayNo() {
        return gPayNo;
    }

    public void setgPayNo(String gPayNo) {
        this.gPayNo = gPayNo;
    }

    public String getPhonePayName() {
        return phonePayName;
    }

    public void setPhonePayName(String phonePayName) {
        this.phonePayName = phonePayName;
    }

    public String getPhonePayNo() {
        return phonePayNo;
    }

    public void setPhonePayNo(String phonePayNo) {
        this.phonePayNo = phonePayNo;
    }

    public String getPatymUpiName() {
        return patymUpiName;
    }

    public void setPatymUpiName(String patymUpiName) {
        this.patymUpiName = patymUpiName;
    }

    public String getPatymUpiNo() {
        return patymUpiNo;
    }

    public void setPatymUpiNo(String patymUpiNo) {
        this.patymUpiNo = patymUpiNo;
    }

    public String getUpiName() {
        return upiName;
    }

    public void setUpiName(String upiName) {
        this.upiName = upiName;
    }

    public String getUpiNo() {
        return upiNo;
    }

    public void setUpiNo(String upiNo) {
        this.upiNo = upiNo;
    }

    public String getKcreationDate() {
        return kcreationDate;
    }

    public void setKcreationDate(String kcreationDate) {
        this.kcreationDate = kcreationDate;
    }

    public String getKupdatedDate() {
        return kupdatedDate;
    }

    public void setKupdatedDate(String kupdatedDate) {
        this.kupdatedDate = kupdatedDate;
    }
}

