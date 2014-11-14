package com.zte.gu.webtools.service.ddm.excel;

public class RfaInfo {

    private String boardId;                                    // 单板编号
    private String cpu;                                        // CPU&amp;RAM使用率
    private String gps = "12288/12290/12291/59904/59905/16384"; // 卫星实时查看
    private String rfScan;                                     // RRU扫频
    private String freqScan;                                   // 干扰分析
    private String antenna;                                    // 天线线序检测
    private String passive;                                    // 天馈系统无源互调检测

    public String getBoardId() {
        if (boardId == null || boardId.trim().length() > 0) {
            int value = (int) Double.parseDouble(boardId);
            return String.valueOf(value);
        }
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getRfScan() {
        return rfScan;
    }

    public void setRfScan(String rfScan) {
        this.rfScan = rfScan;
    }

    public String getFreqScan() {
        return freqScan;
    }

    public void setFreqScan(String freqScan) {
        this.freqScan = freqScan;
    }

    public String getAntenna() {
        return antenna;
    }

    public void setAntenna(String antenna) {
        this.antenna = antenna;
    }

    public String getPassive() {
        return passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }
}
