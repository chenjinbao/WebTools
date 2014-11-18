package com.zte.gu.webtools.excel.rfa;

public class RfaInfo {

    private String boardId;                                    // 单板编号
    private String boardName;                                  //单板名称
    private String radioMode;                                  //无线制式

    private String freqScan;                                   // 干扰分析
    private String vswr;                                       // 驻波比位置检测
    private String pimd;                                       // 无源互调
    private String allFreq;                                    // 接收频谱分析
    private String cellFreq;                                   // 载频频谱分析
    private String acsd;                                       // 天线线序检测
    private String txFreq;                                     // 发射频谱分析

    public String getBoardId() {
        return this.boardId;
    }

    public void setBoardId(String boardId) {
        int tempIndex = boardId.indexOf(".");
        if (tempIndex > 0) {
            boardId = boardId.substring(0, tempIndex);
        }
        this.boardId = boardId;
    }

    /**
     * @return the boardName
     */
    public String getBoardName() {
        return boardName;
    }

    /**
     * @param boardName the boardName to set
     */
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    /**
     * @return the radioMode
     */
    public String getRadioMode() {
        return radioMode;
    }

    /**
     * @param radioMode the radioMode to set
     */
    public void setRadioMode(String radioMode) {
        this.radioMode = radioMode;
    }

    /**
     * @return the freqScan
     */
    public String getFreqScan() {
        return freqScan;
    }

    /**
     * @param freqScan the freqScan to set
     */
    public void setFreqScan(String freqScan) {
        this.freqScan = freqScan;
    }

    /**
     * @return the vsvr
     */
    public String getVswr() {
        return vswr;
    }

    /**
     * @param vswr the vswr to set
     */
    public void setVswr(String vswr) {
        this.vswr = vswr;
    }

    /**
     * @return the pimd
     */
    public String getPimd() {
        return pimd;
    }

    /**
     * @param pimd the pimd to set
     */
    public void setPimd(String pimd) {
        this.pimd = pimd;
    }

    /**
     * @return the allFreq
     */
    public String getAllFreq() {
        return allFreq;
    }

    /**
     * @param allFreq the allFreq to set
     */
    public void setAllFreq(String allFreq) {
        this.allFreq = allFreq;
    }

    /**
     * @return the cellFreq
     */
    public String getCellFreq() {
        return cellFreq;
    }

    /**
     * @param cellFreq the cellFreq to set
     */
    public void setCellFreq(String cellFreq) {
        this.cellFreq = cellFreq;
    }

    /**
     * @return the acsd
     */
    public String getAcsd() {
        return acsd;
    }

    /**
     * @param acsd the acsd to set
     */
    public void setAcsd(String acsd) {
        this.acsd = acsd;
    }

    /**
     * @return the txFreq
     */
    public String getTxFreq() {
        return txFreq;
    }

    /**
     * @param txFreq the txFreq to set
     */
    public void setTxFreq(String txFreq) {
        this.txFreq = txFreq;
    }
}
