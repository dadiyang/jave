/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package it.sauronsoftware.jave;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/***
 *
 * @since:jave 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a> 
 * 2018/06/26 13:44
 */
public class AmrToMp3Encoder extends Encoder {

    private Logger logger=Logger.getLogger(AmrToMp3Encoder.class.getName());

    @Override
    protected void processErrorOutput(EncodingAttributes attributes, BufferedReader errorReader, File source, EncoderProgressListener listener) throws EncoderException, IOException {
        // 屏蔽默認的錯誤處理
        try {
            String line;
            while ((line = errorReader.readLine()) != null) {
                logger.info(line);
            }
        }
        catch (Exception exp) {
            logger.info("file convert error message process failed. "+exp);
        }
    }
}
