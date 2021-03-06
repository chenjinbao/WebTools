package com.zte.gu.webtools.excel.ddm;

import com.google.common.io.Files;
import com.zte.gu.webtools.util.DdmExcelConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.Reflections;

public class ActionsUtil {

    private static final Logger logger = LoggerFactory.getLogger(ActionsUtil.class);

    /**
     * 生成Actions列表
     *
     * @param boardInfos
     * @param config
     * @return
     * @throws Exception
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Actions createActions(List<BoardInfo> boardInfos, DdmExcelConfig config) throws Exception {
        Map<String, String> typeFields = config.getActionFields();

        Actions actions = new Actions();

        for (Map.Entry<String, String> entry : typeFields.entrySet()) {
            Action action = new Action();
            action.setType(entry.getKey());
            actions.getActions().add(action);
            for (BoardInfo boardInfo : boardInfos) {
                String value = (String) Reflections.invokeGetter(boardInfo, entry.getValue());
                if (value != null && !value.trim().isEmpty() && value.trim().contains("1")) {
                    Board board = new Board();
                    board.setType(boardInfo.getBoardName());
                    action.getBoards().add(board);
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
     * @return 
     * @throws java.lang.Exception
     */
    public static File outputXml(Actions actions, String fileName) throws Exception {
        FileOutputStream outputStream = null;
        try {
            File tempDir = Files.createTempDir();
            File file = new File(tempDir.getPath() + File.separator + fileName);
            JAXBContext context = JAXBContext.newInstance(Actions.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            outputStream = new FileOutputStream(file);
            marshaller.marshal(actions, outputStream);
            return file;
        } finally {
            if (outputStream != null) {
                IOUtils.closeQuietly(outputStream);
            }
        }
    }

}
