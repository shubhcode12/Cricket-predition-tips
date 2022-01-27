package com.crickpred.app.Util;

public class Events {
    // Event used to send message from fragment to activity.
    public static class FragmentActivityMessage {
        private String message;
        public FragmentActivityMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to fragment.
    public static class VideoAdsReload {
        private String message;
        public VideoAdsReload(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to activity.
    public static class RewardNotify {
        private String message;
        public RewardNotify(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

}
