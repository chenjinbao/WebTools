package com.zte.gu.webtools.service.ddm.excel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BoardInfo {

    private String boardName;
    private String radioMode;
    private String enableDiskClean;
    private String enableReset;
    private String enablePowerReset;
    private String enablePowerOn;
    private String enablePowerOff;
    private String enableHardwareReset;
    private String enableBlock;
    private String enablePA1;
    private String enablePA2;
    private String enableCarrierPower;
    private String rruQueryFile;
    private String rruFileDownload;
    private String rruFileUpload;
    private String rruFileDelete;
    private String rruAASFileDownload;
    private String rruAASOpen;
    private String rruAASClose;
    private String rruQueryAASStatus;
    private String aisgPowerOpen;
    private String aisgPowerClose;
    private String subscribeReversePowerTest;
    private String unsubscribeReversePowerTest;
    private String rruLogUpload;

    private final String support    = "1";
    private final String notSupport = "0";

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getRadioMode() {
        return radioMode;
    }

    public void setRadioMode(String radioMode) {
        this.radioMode = radioMode;
    }

    public String getEnableDiskClean() {
        return enableDiskClean;
    }

    public void setEnableDiskClean(String enableDiskClean) {
        this.enableDiskClean = enableDiskClean;
    }

    public String getEnableReset() {
        return enableReset;
    }

    public void setEnableReset(String enableReset) {
        this.enableReset = enableReset;
    }

    public String getEnablePowerReset() {
        return enablePowerReset;
    }

    public void setEnablePowerReset(String enablePowerReset) {
        this.enablePowerReset = enablePowerReset;
    }

    public String getEnablePowerOn() {
        return enablePowerOn;
    }

    public void setEnablePowerOn(String enablePowerOn) {
        this.enablePowerOn = enablePowerOn;
    }
    
    public String getEnablePowerOff() {
        return enablePowerOff;
    }

    public void setEnablePowerOff(String enablePowerOff) {
        this.enablePowerOff = enablePowerOff;
    }

    public String getEnableHardwareReset() {
        return enableHardwareReset;
    }

    public void setEnableHardwareReset(String enableHardwareReset) {
        this.enableHardwareReset = enableHardwareReset;
    }

    public String getEnableBlock() {
        return enableBlock;
    }

    public void setEnableBlock(String enableBlock) {
        this.enableBlock = enableBlock;
    }

    public String getEnablePA1() {
        return enablePA1;
    }

    public void setEnablePA1(String enablePA1) {
        this.enablePA1 = enablePA1;
    }

    public String getEnablePA2() {
        return enablePA2;
    }

    public void setEnablePA2(String enablePA2) {
        this.enablePA2 = enablePA2;
    }

    public String getEnableCarrierPower() {
        return enableCarrierPower;
    }

    public void setEnableCarrierPower(String enableCarrierPower) {
        this.enableCarrierPower = enableCarrierPower;
    }

    public String getRruQueryFile() {
        return rruQueryFile;
    }

    public void setRruQueryFile(String rruQueryFile) {
        this.rruQueryFile = rruQueryFile;
    }

    public String getRruFileDownload() {
        return rruFileDownload;
    }

    public void setRruFileDownload(String rruFileDownload) {
        this.rruFileDownload = rruFileDownload;
    }

    public String getRruFileUpload() {
        return rruFileUpload;
    }

    public void setRruFileUpload(String rruFileUpload) {
        this.rruFileUpload = rruFileUpload;
    }

    public String getRruFileDelete() {
        return rruFileDelete;
    }

    public void setRruFileDelete(String rruFileDelete) {
        this.rruFileDelete = rruFileDelete;
    }

    public String getRruAASFileDownload() {
        return rruAASFileDownload;
    }

    public void setRruAASFileDownload(String rruAASFileDownload) {
        this.rruAASFileDownload = rruAASFileDownload;
    }

    public String getRruAASOpen() {
        return rruAASOpen;
    }

    public void setRruAASOpen(String rruAASOpen) {
        this.rruAASOpen = rruAASOpen;
    }

    public String getRruAASClose() {
        return rruAASClose;
    }

    public void setRruAASClose(String rruAASClose) {
        this.rruAASClose = rruAASClose;
    }

    public String getRruQueryAASStatus() {
        return rruQueryAASStatus;
    }

    public void setRruQueryAASStatus(String rruQueryAASStatus) {
        this.rruQueryAASStatus = rruQueryAASStatus;
    }

    public String getAisgPowerOpen() {
        return aisgPowerOpen;
    }

    public void setAisgPowerOpen(String aisgPowerOpen) {
        this.aisgPowerOpen = aisgPowerOpen;
    }

    public String getAisgPowerClose() {
        return aisgPowerClose;
    }

    public void setAisgPowerClose(String aisgPowerClose) {
        this.aisgPowerClose = aisgPowerClose;
    }

    public String getSubscribeReversePowerTest() {
        return subscribeReversePowerTest;
    }

    public void setSubscribeReversePowerTest(String subscribeReversePowerTest) {
        this.subscribeReversePowerTest = subscribeReversePowerTest;
    }

    public String getUnsubscribeReversePowerTest() {
        return unsubscribeReversePowerTest;
    }

    public void setUnsubscribeReversePowerTest(String unsubscribeReversePowerTest) {
        this.unsubscribeReversePowerTest = unsubscribeReversePowerTest;
    }

    public String getRruLogUpload() {
        return rruLogUpload;
    }

    public void setRruLogUpload(String rruLogUpload) {
        this.rruLogUpload = rruLogUpload;
    }

    public String getSupport() {
        return support;
    }

    public String getNotSupport() {
        return notSupport;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
