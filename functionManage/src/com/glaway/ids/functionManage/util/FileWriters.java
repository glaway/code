package com.glaway.ids.functionManage.util;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * @author HASEE
 */
public class FileWriters implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 默认编码：UTF-8
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    private File file;


    public FileWriters() {
    }

    public FileWriters(File file) {
        this.file = file;
        checkFile();
    }


    private void checkFile() throws RuntimeException {
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!file.isFile()) {
            throw new RuntimeException("Not a file:" + file);
        }
    }


    /**
     * 将列表写入文件
     *
     * @param <T>      集合元素类型
     * @param list     列表
     * @param isAppend 是否追加
     * @return 目标文件
     * @throws RuntimeException IO异常
     */
    public <T> File writeLines(Collection<T> list, boolean isAppend) throws RuntimeException {
        return writeLines(list, null, isAppend);
    }

    public <T> File writeLines(Collection<T> list, LineSeparator lineSeparator, boolean isAppend) throws RuntimeException {
        PrintWriter writer = getPrintWriter(isAppend);
        for (T t : list) {
            if (null != t) {
                writer.print(t.toString());
                printNewLine(writer, lineSeparator);
                writer.flush();
            }
        }
        return this.file;
    }

    private void printNewLine(PrintWriter writer, LineSeparator lineSeparator) {
        if (null == lineSeparator) {
            //默认换行符
            writer.println();
        } else {
            //自定义换行符
            writer.print("\n");
        }
    }

    private PrintWriter getPrintWriter(boolean isAppend) throws RuntimeException {
        return new PrintWriter(getWriter(isAppend));
    }

    private BufferedWriter getWriter(boolean isAppend) throws RuntimeException {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(touch(file), isAppend), DEFAULT_CHARSET));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static File touch(File file) throws RuntimeException {
        if (null == file) {
            return null;
        }
        if (!file.exists()) {
            mkParentDirs(file);
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    private static File mkParentDirs(File file) {
        final File parentFile = file.getParentFile();
        if (null != parentFile && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return parentFile;
    }

}
