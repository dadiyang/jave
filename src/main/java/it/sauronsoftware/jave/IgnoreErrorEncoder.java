package it.sauronsoftware.jave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;

/**
 * 忽略所有转换过程中的异常
 *
 * @author dadiyang
 * date 2018/12/18
 */
public class IgnoreErrorEncoder extends Encoder {
    private Logger log = LoggerFactory.getLogger(IgnoreErrorEncoder.class);

    @Override
    protected void processErrorOutput(EncodingAttributes attributes, BufferedReader errorReader, File source, EncoderProgressListener listener) {
        try {
            String line;
            while ((line = errorReader.readLine()) != null) {
                log.debug(line);
            }
        } catch (Exception exp) {
            log.error("file convert error message process failed. ", exp);
        }
    }
}