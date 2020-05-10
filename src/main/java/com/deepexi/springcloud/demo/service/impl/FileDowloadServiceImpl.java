package com.deepexi.springcloud.demo.service.impl;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import com.deepexi.springcloud.demo.service.FileDowloadService;
import com.keep.utils.HttpDownload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2020-05-10 17:31
 */
@Service
public class FileDowloadServiceImpl implements FileDowloadService {

    private static final String FILE_PATH_PRE = "/Users/zhangnan/tool/";

    private static final String URL_PRE = "http://anym.zhongyulian.com/clubimg/csv/anym_memberlist-";

    private static final String URL_SUF = ".csv";

    @Override
    public Pair<String, String> dowloadNowFile() {
        LocalDate now = LocalDate.now();
        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String url = URL_PRE + nowStr + URL_SUF;
        String filePath = FILE_PATH_PRE + nowStr + URL_SUF;
        String oldFilePath = FILE_PATH_PRE + nowStr + "-old" + URL_SUF;
        File oldFile = new File(oldFilePath);
        if (oldFile.exists()){
            oldFile.delete();
        }
        File file = new File(filePath);
        if (file.exists()){
            file.renameTo(oldFile);
        }
       HttpDownload.download(url, filePath);

        return new Pair<>(filePath, oldFilePath);
    }


    public static void main(String[] args) {
        FileDowloadServiceImpl fileDowloadService = new FileDowloadServiceImpl();
        Pair<String, String> url = fileDowloadService.dowloadNowFile();
        fileDowloadService.readCsv(url.getKey());
    }

    @Override
    public Map<String, String> readCsv(String url){

        File file = new File(url);

        CsvReadConfig csvReadConfig = CsvReadConfig.defaultConfig();
        csvReadConfig.setContainsHeader(true);
        CsvReader csvReader = new CsvReader(file,Charset.forName("GBK") , csvReadConfig);
        CsvData data = csvReader.read();
        Map<String, String> name2Score = new HashMap<>(64);
        Iterator<CsvRow> iterator = data.iterator();
        while (iterator.hasNext()){
            CsvRow csvRow = iterator.next();
            String name = csvRow.getByName("账户名");
            String score = csvRow.getByName("俱乐部竞技分");
            if (StringUtils.isNotEmpty(score) && StringUtils.isNotEmpty(name)
                    && !"0".equalsIgnoreCase(score)){
                name2Score.put(name, score);
            }

        }

        return name2Score;
    }

}
