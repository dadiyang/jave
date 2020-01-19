/*
 * JAVE - A Java Audio/Video Encoder (based on FFMPEG)
 *
 * Copyright (C) 2008-2009 Carlo Pelliccia (www.sauronsoftware.it)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.sauronsoftware.jave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The default ffmpeg executable locator, which exports on disk the ffmpeg
 * executable bundled with the library distributions. It should work both for
 * windows and many linux distributions. If it doesn't, try compiling your own
 * ffmpeg executable and plug it in JAVE with a custom {@link FFMPEGLocator}.
 *
 * @author Carlo Pelliccia
 */
public class DefaultFFMPEGLocator extends FFMPEGLocator {
    private static final Logger log = LoggerFactory.getLogger(DefaultFFMPEGLocator.class);
    /**
     * Trace the version of the bundled ffmpeg executable. It's a counter: every
     * time the bundled ffmpeg change it is incremented by 1.
     */
    private static final int MYEX_EVERSION = 1;
    private static final String WINDOWS = "windows";
    private static final String MAC = "mac";

    /**
     * The ffmpeg executable file path.
     */
    private String path;

    /**
     * It builds the default FFMPEGLocator, exporting the ffmpeg executable on a
     * temp file.
     */
    public DefaultFFMPEGLocator() {
        // Windows?
        boolean isWindows;
        boolean isMac = false;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains(WINDOWS)) {
            isWindows = true;
        } else {
            isWindows = false;
            isMac = os.contains(MAC);
        }
        // Temp dir?
        File temp = null;
        String ffmpegHome = System.getProperty("ffmpeg.home");
        if (ffmpegHome != null && !"".equals(ffmpegHome)) {
            log.info("ffmpeg.home: " + ffmpegHome);
            temp = new File(ffmpegHome);
        }
        if (temp == null || !temp.exists()) {
            temp = new File(System.getProperty("java.io.tmpdir"), "jave-"
                    + MYEX_EVERSION);
            log.info("ffmpeg.home does not exists, use default bin path: " + temp.getAbsolutePath());
        }
        if (!temp.exists()) {
            temp.mkdirs();
            temp.deleteOnExit();
        }
        // ffmpeg executable export on disk.
        String suffix = isWindows ? ".exe" : isMac ? "-mac" : "";
        File exe = new File(temp, "ffmpeg" + suffix);
        if (!exe.exists() || exe.length() <= 0) {
            copyFile("bin/ffmpeg" + suffix, exe);
        }
        // pthreadGC2.dll
        if (isWindows) {
            File dll = new File(temp, "pthreadGC2.dll");
            if (!dll.exists()) {
                copyFile("bin/pthreadGC2.dll", dll);
            }
        }
        // Need a chmod?
        if (!isWindows) {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(new String[]{"/bin/chmod", "755",
                        exe.getAbsolutePath()});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Ok.
        this.path = exe.getAbsolutePath();
    }

    @Override
    public String getFFMPEGExecutablePath() {
        return path;
    }

    /**
     * Copies a file bundled in the package to the supplied destination.
     *
     * @param path The name of the bundled file.
     * @param dest The destination.
     * @throws RuntimeException If aun unexpected error occurs.
     */
    private void copyFile(String path, File dest) throws RuntimeException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream(path);
            output = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int l;
            while ((l = input.read(buffer)) != -1) {
                output.write(buffer, 0, l);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file "
                    + dest.getAbsolutePath());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (Throwable ignored) {

                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (Throwable ignored) {
                }
            }
        }
        if (!dest.exists()) {
            String errMsg = "copy ffmpeg executable file to " + dest.getAbsolutePath() + " fail";
            log.info(errMsg);
            throw new IllegalStateException(errMsg);
        }
    }

}
