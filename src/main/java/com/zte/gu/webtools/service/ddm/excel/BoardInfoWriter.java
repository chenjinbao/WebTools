package com.zte.gu.webtools.service.ddm.excel;

import com.google.common.io.Files;
import com.zte.gu.webtools.service.ddm.util.ExcelConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardInfoWriter {
    
    private static final Logger LOG = LoggerFactory.getLogger(BoardInfoWriter.class);
    
    private static final List<String> RADIOS = Arrays.asList("gsm", "umts");
    private static final String OUTPUT_FILE = "product-{0}-ddm-satisfy-boards-conf.xml";
    
    public static File write(InputStream boardInputStream, InputStream rruInputStream, ExcelConfig config) {
        try {
            Map<String, BoardInfo> boardMap = BoardInfoReader.readBoardInfo(boardInputStream, config);
            Map<String, BoardInfo> rrudMap = BoardInfoReader.readRruBoardInfo(rruInputStream, config);
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
            
            List<File> xmlFiles = new ArrayList<File>();
            for (String radio : RADIOS) {
                Actions actions = ActionsUtil.createActions(boardInfoMap.get(radio), config);
                File file = ActionsUtil.outputXml(actions, MessageFormat.format(OUTPUT_FILE, radio));
                xmlFiles.add(file);
            }
            return zipXml(xmlFiles);
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
    
    private static File zipXml(List<File> xmlFiles) {
        ZipOutputStream zipOutputStream = null;
        InputStream in = null;
        try {
            File tempFile = File.createTempFile("ddm", ".zip", Files.createTempDir());
            zipOutputStream = new ZipOutputStream(tempFile);
            for (File xmlFile : xmlFiles) {
                try {
                    in = new FileInputStream(xmlFile);
                    ZipEntry entry = new ZipEntry(xmlFile.getName());
                    zipOutputStream.putNextEntry(entry);
                    IOUtils.copy(in, zipOutputStream);
                } finally {
                    IOUtils.closeQuietly(in);
                }
            }
            return tempFile;
        } catch (Exception e) {
            LOG.warn("压缩XML文件出错，", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(zipOutputStream);
        }
    }
    
}
