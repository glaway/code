package com.glaway.ids.functionManage.util;


import java.io.BufferedReader;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * @author HASEE
 */
public class FileReader {

    private File file;
    private Charset charset;

    /** 默认编码：UTF-8 */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private FileReader(File file, Charset charset) {
        this.file = file;
        this.charset = charset;
        checkFile();
    }

    public FileReader(String filePath, Charset charset) {
        this(new File(filePath), charset);
    }

    public FileReader(String filePath) {
        this(new File(filePath), DEFAULT_CHARSET);
    }


    /**
     * 从文件中读取每一行数据
     *
     * @param <T> 集合类型
     * @param collection 集合
     * @return 文件中的每行内容的集合
     * @throws RuntimeException IO异常
     */
    public <T extends Collection<String>> T readLines(T collection) throws RuntimeException {
        BufferedReader reader = null;
        try {
            reader = FileReader.getReader(file, charset);
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                collection.add(line);
            }
            return collection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FileReader.close(reader);
        }
    }


    /**
     * 获得一个文件读取器
     *
     * @param file 文件
     * @param charset 字符集
     * @return BufferedReader对象
     * @throws RuntimeException IO异常
     */
    public static BufferedReader getReader(File file, Charset charset) throws RuntimeException {
        return FileReader.getBufferedReader(getInputStream(file), charset);
    }


    /**
     * 获得一个Reader
     *
     * @param in 输入流
     * @param charset 字符集
     * @return BufferedReader对象
     */
    private static BufferedReader getBufferedReader(InputStream in, Charset charset) {
        if (null == in) {
            return null;
        }

        InputStreamReader reader = null;
        if (null == charset) {
            reader = new InputStreamReader(in);
        } else {
            reader = new InputStreamReader(in, charset);
        }

        return new BufferedReader(reader);
    }

    /**
     * 获得输入流
     *
     * @param file 文件
     * @return 输入流
     * @throws RuntimeException 文件未找到
     */
    private static BufferedInputStream getInputStream(File file) throws RuntimeException {
        return new BufferedInputStream(FileReader.toStream(file));
    }



    /**
     * 文件转为流
     *
     * @param file 文件
     * @return {@link FileInputStream}
     */
    private static FileInputStream toStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查文件
     *
     * @throws RuntimeException IO异常
     */
    private void checkFile() throws RuntimeException {
        if (!file.exists()) {
            throw new RuntimeException("File not exist: " + file);
        }
        if (!file.isFile()) {
            throw new RuntimeException("Not a file:" + file);
        }
    }

    /**
     * 关闭<br>
     * 关闭失败不会抛出异常
     *
     * @param closeable 被关闭的对象
     */
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }
}
