package com.zte.gu.webtools.service.ddm.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionsUtil {

    private static final Logger logger = LoggerFactory.getLogger(ActionsUtil.class);

    /** 动态管理操作类型和BoardInfo属性对应关系 */
    private static final String[][] TYPE_FIELD = new String[][] { { "ddmQueryDisk", "enableDiskClean" }, // 查询磁盘空间
            { "ddmCleanDisk", "enableDiskClean" }, // 清理磁盘空间
            { "ddmOperationResetBoard", "enableReset" }, // 复位
            { "ddmOperationPowerOffResetBoard", "enablePowerReset" }, // 下电复位
            { "ddmOperationPowerOnBoard", "enablePowerOn" }, // 上电
            { "ddmOperationPowerOffBoard", "enablePowerOff" }, // 下电
            { "ddmOperationHardwareResetBoard", "enableHardwareReset" },// 硬复位
            { "ddmOperationBlockBoard", "enableBlock" },// 闭塞
            { "ddmOperationUnblockBoard", "enableBlock" }, // 解闭塞
            { "ddmOperationPaOn", "enablePA1" },// 启用功放1.0
            { "ddmOperationPaOnNEW", "enablePA2" },// 启用功放2.0
            { "ddmOperationPaOff", "enablePA1" },// 停用功放1.0
            { "ddmOperationPaOffNEW", "enablePA2" },// 停用功放2.0
            { "ddmQueryCarrierPower", "notSupport" },// 载波功率查询1.0
            { "ddmQueryCarrierPowerNEW", "enableCarrierPower" },// 载波功率查询2.0
            { "ddmQueryFile", "rruQueryFile" },// RRU文件查询
            { "ddmOperationFileDownload", "rruFileDownload" },// RRU文件下载
            { "ddmOperationFileUpload", "rruFileUpload" },// RRU文件上传
            { "ddmOperationFileDelete", "rruFileDelete" },// RRU文件删除
            { "ddmAASFileDownload", "rruAASFileDownload" },// 下载幅相参数文件
            { "ddmAASOpen", "rruAASOpen" },// 开启有源天线自愈
            { "ddmAASClose", "rruAASClose" },// 关闭有源天线自愈
            { "ddmQueryAASStatus", "rruQueryAASStatus" }, // 查询有源天线自愈状态
            { "openAisgPower", "aisgPowerOpen" }, // 打开AISG口供电
            { "closeAisgPower", "aisgPowerClose" }, // 关闭AISG口供电
            { "subscribeReversePowerTest", "subscribeReversePowerTest" },// 订阅反向功率检测
            { "unsubscribeReversePowerTest" ,"unsubscribeReversePowerTest"}, // 退订反向功率检测
            { "ddmOperationLogUpload" ,"rruLogUpload"} // RRU日志上传
                                               };

    /**
     * 生成Actions列表
     * 
     * @param boardInfos
     * @return
     * @throws Exception
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Actions createActions(List<BoardInfo> boardInfos) throws Exception {
        Actions actions = new Actions();

        for (String[] typeField : TYPE_FIELD) {
            Action action = new Action();
            action.setType(typeField[0]);
            actions.getActions().add(action);
        }

        for (BoardInfo boardInfo : boardInfos) {
            for (int i = 0; i < TYPE_FIELD.length; i++) {
                String value = BeanUtils.getProperty(boardInfo, TYPE_FIELD[i][1]);
                logger.info(TYPE_FIELD[i][0] + " : " + boardInfo.getBoardName() + " : " + TYPE_FIELD[i][1] + "=" + value);
                if (value != null && !value.trim().isEmpty() && value.trim().contains("1")) {
                    Board board = new Board();
                    board.setType(boardInfo.getBoardName());
                    actions.getActions().get(i).getBoards().add(board);
                }
            }
        }

        return actions;
    }

    /**
     * 输出XML文件
     * 
     * @param actions
     * @param fileName
     * @param path
     * @throws java.lang.Exception
     */
    public static void outputXml(Actions actions, String fileName, String path) throws Exception {
        FileOutputStream outputStream = null;
        try {
            File file = new File(path + File.separator + fileName);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            JAXBContext context = JAXBContext.newInstance(Actions.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            outputStream = new FileOutputStream(file);
            marshaller.marshal(actions, outputStream);
        } finally {
            if (outputStream != null) {
                IOUtils.closeQuietly(outputStream);
            }
        }
    }

}
