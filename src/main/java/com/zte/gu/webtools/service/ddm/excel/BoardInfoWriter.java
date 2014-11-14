package com.zte.gu.webtools.service.ddm.excel;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardInfoWriter {

    private static final Logger LOG = LoggerFactory.getLogger(BoardInfoWriter.class);

    private static final List<String> RADIOS = Arrays.asList("gsm", "umts");
    private static final String OUTPUT_FILE = "product-{0}-ddm-satisfy-boards-conf.xml";

    public static boolean write(File boardFile, File rruFile, String savePath) {
        try {
            Map<String, BoardInfo> boardMap = BoardInfoReader.readBoardInfo(boardFile);
            Map<String, BoardInfo> rrudMap = BoardInfoReader.readRruBoardInfo(rruFile);
            Map<String, List<BoardInfo>> boardInfoMap = new HashMap<String, List<BoardInfo>>();
            // List<BoardInfo> gsmBoardInfos = new ArrayList<BoardInfo>();
            // List<BoardInfo> umtsBoardInfos = new ArrayList<BoardInfo>();
            for (Map.Entry<String, BoardInfo> entry : boardMap.entrySet()) {
                String boardName = entry.getKey();
                BoardInfo boardInfo = entry.getValue();
                String radioMode = boardInfo.getRadioMode();
                if (rrudMap.containsKey(boardName)) {
                    LOG.info("Find RRU from RRU Define Document: " + boardName);
                    BoardInfo info = rrudMap.get(boardName);
                    info.setEnablePA1(boardInfo.getEnablePA1());
                    info.setEnableDiskClean(boardInfo.getEnableDiskClean());
                    // 特殊处理复位
                    if (info.getEnableReset().contains("-1")) {
                        info.setEnableReset(boardInfo.getEnableReset());
                        LOG.info("使用物理设备定义中的复位操作定义，" + boardInfo.getBoardName());
                    }
                    // 特殊处理下电复位
                    if (info.getEnablePowerReset().contains("-1")) {
                        info.setEnablePowerReset(boardInfo.getEnablePowerReset());
                        LOG.info("使用物理设备定义中的下电复位操作定义," + boardInfo.getBoardName());
                    }
                    // 特殊处理硬复位
                    if (info.getEnableHardwareReset().contains("-1")) {
                        info.setEnableHardwareReset(boardInfo.getEnableHardwareReset());
                        LOG.info("使用物理设备定义中的硬复位操作定义," + boardInfo.getBoardName());
                    }
                    // 特殊处理闭塞解闭塞
                    if (info.getEnableBlock().contains("-1")) {
                        info.setEnableBlock(boardInfo.getEnableBlock());
                        LOG.info("使用物理设备定义中的闭塞解闭塞操作定义," + boardInfo.getBoardName());
                    }
                    boardInfo = info;
                }
                for (String radio : RADIOS) {
                    if (radioMode.toLowerCase().contains(radio)) {
                        putToMap(radio, boardInfo, boardInfoMap);
                    }
                }
            }

            for (String radio : RADIOS) {
                Actions actions = ActionsUtil.createActions(boardInfoMap.get(radio));
                ActionsUtil.outputXml(actions, MessageFormat.format(OUTPUT_FILE, radio), savePath);
            }
            return true;
        } catch (Exception e) {
            LOG.warn("解析并生成XML文件出错，", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 放入Map中
     *
     * @param radio
     * @param boardInfo
     * @param boardInfoMap
     */
    private static void putToMap(String radio, BoardInfo boardInfo, Map<String, List<BoardInfo>> boardInfoMap) {
        List<BoardInfo> boardInfos = boardInfoMap.get(radio);
        if (boardInfos == null) {
            boardInfos = new ArrayList<BoardInfo>();
            boardInfoMap.put(radio, boardInfos);
        }
        boardInfos.add(boardInfo);
    }

}
