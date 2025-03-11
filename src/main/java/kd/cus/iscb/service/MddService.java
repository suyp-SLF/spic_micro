package kd.cus.iscb.service;

import java.util.Map;

/**
 * @author Wu Yanqi
 */
public interface MddService {
    /**
     * 将纬度值保存进多维数据库
     *
     * @param datas
     * @return
     */
    Map<String, Object> saveDataFromJykj(String modelNum, Map<String, String> datas);
}
