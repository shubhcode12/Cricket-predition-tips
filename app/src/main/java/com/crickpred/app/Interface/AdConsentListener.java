package com.crickpred.app.Interface;

import com.google.ads.consent.ConsentStatus;

public interface AdConsentListener {
    void onConsentUpdate(ConsentStatus consentStatus);
}
