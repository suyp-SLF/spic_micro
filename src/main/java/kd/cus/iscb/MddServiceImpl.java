package kd.cus.iscb;

import com.alibaba.fastjson.JSON;
import kd.bos.logging.Log;
import kd.bos.logging.LogFactory;
import kd.cus.common.ThrowableUtils;
import kd.cus.iscb.service.MddService;
import kd.fi.bcm.business.olap.OlapSaveBuilder;
import kd.fi.bcm.common.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Yanqi
 */
public class MddServiceImpl implements MddService {
    @Override
    public Map<String, Object> saveDataFromJykj(String modelNum, Map<String, String> datas) {
        /*
        维度：
         "Process", // 过程
         "Currency", // 币别
         "AuditTrail", // 审计线索
         "ChangeType", // 变动类型
         "MultiGAAP", // 准则
         "Entity", // 组织
         "Account", // 科目
         "Year", // 年度
         "Period", // 期间
         "Scenario", // 情景
         "InternalCompany", // 往来组织
         "C1", // 业务版块
         "C2", // 计量单位
         "C3" // YSGD
         */
        Log logMonitor = LogFactory.getLog(MddServiceImpl.class);
        Map<String, Object> excuteInfo = new HashMap<>();
        try {
            logMonitor.info("数据开始导入数据库：" + modelNum + "，数据为：" + JSON.toJSONString(datas));
            OlapSaveBuilder save = new OlapSaveBuilder(modelNum);
            save.setCrossDimensions("Process", "Currency", "AuditTrail", "ChangeType", "MultiGAAP", "Entity", "Account", "Year", "Period", "Scenario", "InternalCompany", "C1", "C2", "C3", "C4");
            save.setMeasures("FMONEY");
            String process = datas.get("Process");
            String currency = datas.get("Currency");
            String auditTrail = datas.get("AuditTrail");
            String changeType = datas.get("ChangeType");
            String multiGAAP = datas.get("MultiGAAP");
            String entity = datas.get("Entity");
            String account = datas.get("Account");
            String year = datas.get("Year");
            String period = datas.get("Period");
            String scenario = datas.get("Scenario");
            String internalCompany = datas.get("InternalCompany");
            String c1 = datas.get("C1");
            String c2 = datas.get("C2");
            String c3 = datas.get("C3");
            String c4 = datas.get("C4");
            String money = datas.get("money");
            List<Pair<String[], Object>> saveValPairs = new ArrayList<>();
            saveValPairs.add(Pair.onePair(new String[]{process, currency, auditTrail, changeType, multiGAAP, entity, account, year, period, scenario, internalCompany, c1, c2, c3, c4}, money));
            save.setCellSet(saveValPairs);
            save.doSave();
            excuteInfo.put("success", true);
            excuteInfo.put("message", "success");
        } catch (Exception e) {
            excuteInfo.put("success", false);
            excuteInfo.put("message", ThrowableUtils.getStackTrace(e));
            logMonitor.error("数据开始导入数据库：" + modelNum + "，数据为：" + JSON.toJSONString(datas) + "，错误信息为：" + ThrowableUtils.getStackTrace(e));
        }
        return excuteInfo;
    }
}
